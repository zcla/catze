package zcla71.catze.view.model;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.Data;
import zcla71.catze.service.Service;
import zcla71.catze.service.model.Editora;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;

@Data
public class Livros {
    private String titulo;
    private Integer qtdObras;
    private String autorPrincipal;
    private Integer qtdOutrosAutores;
    private String isbn;
    private String editoraPrincipal;
    private Integer qtdOutrasEditoras;
    private Integer ano;

    public Livros(Livro livro) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();

        this.titulo = livro.getTitulo();
        this.qtdObras = livro.getIdsObras().size();

        this.autorPrincipal = null;
        this.qtdOutrosAutores = 0;
        for (String idObra : livro.getIdsObras()) {
            Obra obra = service.buscaObraPorId(idObra);
            for (String idPessoa : obra.getIdsAutores()) {
                Pessoa autor = service.buscaPessoaPorId(idPessoa);
                if (autorPrincipal == null) {
                    this.autorPrincipal = autor.getNome();
                } else {
                    this.qtdOutrosAutores++;
                }
            }
        }

        this.isbn = (livro.getIsbn13() != null ? livro.getIsbn13() : livro.getIsbn10());

        this.qtdOutrasEditoras = 0;
        for (String idEditora : livro.getIdsEditoras()) {
            Editora editora = service.buscaEditoraPorId(idEditora);
            if (editoraPrincipal == null) {
                this.editoraPrincipal = editora.getNome();
            } else {
                this.qtdOutrasEditoras++;
            }
        }

        this.ano = livro.getAno();
    }
}
