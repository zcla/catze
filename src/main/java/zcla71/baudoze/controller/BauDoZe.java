package zcla71.baudoze.controller;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import zcla71.baudoze.repository.model.RepositoryException;
import zcla71.baudoze.service.Service;
import zcla71.baudoze.service.model.Atividade;
import zcla71.baudoze.service.model.Colecao;
import zcla71.baudoze.service.model.Editora;
import zcla71.baudoze.service.model.Etiqueta;
import zcla71.baudoze.service.model.Livro;
import zcla71.baudoze.service.model.Obra;
import zcla71.baudoze.service.model.Pessoa;
import zcla71.baudoze.view.livro.LivroPagina;
import zcla71.baudoze.view.livros.LivrosPagina;
import zcla71.baudoze.view.livros.LivrosPaginaLivro;
import zcla71.baudoze.view.model.Atividades;
import zcla71.baudoze.view.model.Colecoes;
import zcla71.baudoze.view.model.Editoras;
import zcla71.baudoze.view.model.Etiquetas;
import zcla71.baudoze.view.model.Stats;
import zcla71.baudoze.view.obras.ObrasPagina;
import zcla71.baudoze.view.obras.ObrasPaginaObra;
import zcla71.baudoze.view.pessoas.PessoasPagina;
import zcla71.baudoze.view.pessoas.PessoasPaginaPessoa;

public class BauDoZe {
    private static BauDoZe instance;

    public static BauDoZe getInstance() {
        if (instance == null) {
            instance = new BauDoZe();
        }
        return instance;
    }

    // atividades

    public Collection<Atividades> getAtividades() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Atividade> atividades = service.listaAtividades();
        List<Atividades> result = new ArrayList<>();
        for (Atividade atividade : atividades) {
            result.add(new Atividades(atividade));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Atividades>() {
            @Override
            public int compare(Atividades a1, Atividades a2) {
                return a2.getData().compareTo(a1.getData());
            }
        });

        return result;
    }

    // colecoes

    public Collection<Colecoes> getColecoes() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Colecao> colecoes = service.listaColecoes();
        List<Colecoes> result = new ArrayList<>();
        for (Colecao colecao : colecoes) {
            result.add(new Colecoes(colecao));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Colecoes>() {
            @Override
            public int compare(Colecoes c1, Colecoes c2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(c1.getNome(), c2.getNome());
            }
        });

        return result;
    }

    // editoras

    public Collection<Editoras> getEditoras() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Editora> editoras = service.listaEditoras();
        List<Editoras> result = new ArrayList<>();
        for (Editora editora : editoras) {
            result.add(new Editoras(editora));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Editoras>() {
            @Override
            public int compare(Editoras o1, Editoras o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getNome(), o2.getNome());
            }
        });

        return result;
    }

    // etiquetas

    public Collection<Etiquetas> getEtiquetas() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Etiqueta> etiquetas = service.listaEtiquetas();
        List<Etiquetas> result = new ArrayList<>();
        for (Etiqueta etiqueta : etiquetas) {
            result.add(new Etiquetas(etiqueta));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Etiquetas>() {
            @Override
            public int compare(Etiquetas o1, Etiquetas o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getNome(), o2.getNome());
            }
        });

        return result;
    }

    // livros

    public LivroPagina getLivro(String id) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Livro livro = service.buscaLivroPorId(id);
        LivroPagina result = new LivroPagina(livro);
        return result;
    }

    public void setLivro(LivroPagina livroPagina) throws StreamReadException, DatabindException, IOException, RepositoryException {
        Service service = Service.getInstance();
        Livro livro = livroPagina.toLivro();
        if (livro.getId() == null) {
            service.incluiLivro(livro);
        } else {
            service.alteraLivro(livro);
        }
    }

    public LivrosPagina getLivros() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Livro> livros = service.listaLivros();
        LivrosPagina result = new LivrosPagina();
        for (Livro livro : livros) {
            LivrosPaginaLivro lpLivro = new LivrosPaginaLivro();

            lpLivro.setId(livro.getId());
            lpLivro.setTitulo(livro.getTitulo());
            lpLivro.setQtdObras(livro.getIdsObras().size());

            lpLivro.setAutorPrincipal(null);
            lpLivro.setQtdOutrosAutores(0);
            for (String idObra : livro.getIdsObras()) {
                Obra obra = service.buscaObraPorId(idObra);
                for (String idPessoa : obra.getIdsAutores()) {
                    Pessoa autor = service.buscaPessoaPorId(idPessoa);
                    if (lpLivro.getAutorPrincipal() == null) {
                        lpLivro.setAutorPrincipal(autor.getNome());
                    } else {
                        lpLivro.setQtdOutrosAutores(lpLivro.getQtdOutrosAutores() + 1);
                    }
                }
            }

            lpLivro.setIsbn(livro.getIsbn13() != null ? livro.getIsbn13() : livro.getIsbn10());

            lpLivro.setEditoraPrincipal(null);
            lpLivro.setQtdOutrasEditoras(0);
            for (String idEditora : livro.getIdsEditoras()) {
                Editora editora = service.buscaEditoraPorId(idEditora);
                if (lpLivro.getEditoraPrincipal() == null) {
                    lpLivro.setEditoraPrincipal(editora.getNome());
                } else {
                    lpLivro.setQtdOutrasEditoras(lpLivro.getQtdOutrasEditoras() + 1);
                }
            }

            lpLivro.setAno(livro.getAno());

            Colecao colecao = service.listaColecoes().stream().filter(c -> c.getIdsLivros().contains(livro.getId())).findFirst().orElse(null);
            if (colecao != null) {
                lpLivro.setColecao(colecao.getNome());
            }

            Collection<Etiqueta> etiquetas = service.listaEtiquetas().stream().filter(e -> livro.getIdsEtiquetas().contains(e.getId())).toList();
            lpLivro.setEtiquetas("");
            for (Etiqueta etiqueta : etiquetas) {
                lpLivro.setEtiquetas(etiqueta.getNome() + ", ");
            }
            if (lpLivro.getEtiquetas().length() > 0) {
                lpLivro.setEtiquetas(lpLivro.getEtiquetas().substring(0, lpLivro.getEtiquetas().length() - 2));
            }

            lpLivro.setPaginas(livro.getPaginas());
            lpLivro.setEdicao(livro.getEdicao());
            lpLivro.setStatus(service.buscaUltimaAtividadeDoLivro(livro.getId()).getTipo().getStatus());

            result.add(lpLivro);
        }

        // // Atualmente desnecessário, pois o DataTable já ordena
        // Collections.sort(result, new Comparator<LivrosPaginaLivro>() {
        //     @Override
        //     public int compare(LivrosPaginaLivro o1, LivrosPaginaLivro o2) {
        //         Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
        //         return ptBrCollator.compare(o1.getTitulo(), o2.getTitulo());
        //     }
        // });

        return result;
    }

    // obras

    public ObrasPagina getObras() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();

        Collection<Obra> obras = service.listaObras();
        ObrasPagina result = new ObrasPagina();
        for (Obra obra : obras) {
            ObrasPaginaObra opObra = new ObrasPaginaObra();

            opObra.setId(obra.getId());
            opObra.setTitulo(obra.getTitulo());

            opObra.setAutorPrincipal(null);
            opObra.setQtdOutrosAutores(0);
            for (String idPessoa : obra.getIdsAutores()) {
                Pessoa autor = service.buscaPessoaPorId(idPessoa);
                if (opObra.getAutorPrincipal() == null) {
                    opObra.setAutorPrincipal(autor.getNome());
                } else {
                    opObra.setQtdOutrosAutores(opObra.getQtdOutrosAutores() + 1);
                }
            }
    
            opObra.setQtdLivros(service.listaLivrosDaObra(obra.getId()).size());
    
            result.add(opObra);
        }

        // // Atualmente desnecessário, pois o DataTable já ordena
        // Collections.sort(result, new Comparator<Obras>() {
        //     @Override
        //     public int compare(Obras o1, Obras o2) {
        //         Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
        //         return ptBrCollator.compare(o1.getTitulo(), o2.getTitulo());
        //     }
        // });

        return result;
    }

    // pessoas

    public PessoasPagina getPessoas() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Pessoa> pessoas = service.listaPessoas();
        PessoasPagina result = new PessoasPagina();
        for (Pessoa pessoa : pessoas) {
            PessoasPaginaPessoa ppPessoa = new PessoasPaginaPessoa();

            ppPessoa.setNome(pessoa.getNome());

            Collection<Obra> obrasAutor = service.listaObras().stream().filter(o -> o.getIdsAutores().contains(pessoa.getId())).toList();
            ppPessoa.setQtdObras(obrasAutor.size());

            Collection<Livro> livros = service.listaLivros();
            ppPessoa.setQtdLivros(0);
            for (Livro livro : livros) {
                ppPessoa.setQtdLivros(ppPessoa.getQtdLivros() + obrasAutor.stream().filter(o -> livro.getIdsObras().contains(o.getId()) && o.getIdsAutores().contains(pessoa.getId())).toArray().length);
            }

            result.add(ppPessoa);
        }

        // // Atualmente desnecessário, pois o DataTable já ordena
        // Collections.sort(result, new Comparator<PessoasPaginaPessoa>() {
        //     @Override
        //     public int compare(PessoasPaginaPessoa p1, PessoasPaginaPessoa p2) {
        //         Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
        //         return ptBrCollator.compare(p1.getNome(), p2.getNome());
        //     }
        // });

        return result;
    }

    // stats

    public Stats getStats() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Stats result = new Stats(service.listaObras().size(),
                service.listaLivros().size(),
                service.listaPessoas().size(),
                service.listaEditoras().size(),
                service.listaColecoes().size(),
                service.listaEtiquetas().size(),
                service.listaAtividades().size());
        return result;
    }
}
