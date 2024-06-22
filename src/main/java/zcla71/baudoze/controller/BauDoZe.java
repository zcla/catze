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

import zcla71.baudoze.service.Service;
import zcla71.baudoze.service.model.Atividade;
import zcla71.baudoze.service.model.Colecao;
import zcla71.baudoze.service.model.Editora;
import zcla71.baudoze.service.model.Etiqueta;
import zcla71.baudoze.service.model.Livro;
import zcla71.baudoze.service.model.Obra;
import zcla71.baudoze.service.model.Pessoa;
import zcla71.baudoze.view.model.Stats;
import zcla71.baudoze.view.model.Atividades;
import zcla71.baudoze.view.model.Colecoes;
import zcla71.baudoze.view.model.Editoras;
import zcla71.baudoze.view.model.Etiquetas;
import zcla71.baudoze.view.model.Livros;
import zcla71.baudoze.view.model.Obras;
import zcla71.baudoze.view.model.Pessoas;

public class BauDoZe {
    private static BauDoZe instance;

    public static BauDoZe getInstance() {
        if (instance == null) {
            instance = new BauDoZe();
        }
        return instance;
    }

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


    public Collection<Livros> getLivros() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Livro> livros = service.listaLivros();
        List<Livros> result = new ArrayList<>();
        for (Livro livro : livros) {
            result.add(new Livros(livro));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Livros>() {
            @Override
            public int compare(Livros o1, Livros o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getTitulo(), o2.getTitulo());
            }
        });

        return result;
    }

    public Collection<Obras> getObras() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Obra> obras = service.listaObras();
        List<Obras> result = new ArrayList<>();
        for (Obra obra : obras) {
            result.add(new Obras(obra));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Obras>() {
            @Override
            public int compare(Obras o1, Obras o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getTitulo(), o2.getTitulo());
            }
        });

        return result;
    }

    public Collection<Pessoas> getPessoas() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Pessoa> pessoas = service.listaPessoas();
        List<Pessoas> result = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            result.add(new Pessoas(pessoa));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Pessoas>() {
            @Override
            public int compare(Pessoas p1, Pessoas p2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(p1.getNome(), p2.getNome());
            }
        });

        return result;
    }

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
