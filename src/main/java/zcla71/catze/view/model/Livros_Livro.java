package zcla71.catze.view.model;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.Data;
import zcla71.catze.service.Service;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;

@Data
public class Livros_Livro {
    private String titulo;
    private String autorPrincipal;
    private String qtdOutrosAutores;
    private String outrosAutores;
    private String isbn;

    public Livros_Livro(Livro livro) throws StreamReadException, DatabindException, IOException {
        this.titulo = livro.getTitulo();
        this.qtdOutrosAutores = null;
        this.outrosAutores = null;
        this.isbn = (livro.getIsbn13() != null ? livro.getIsbn13() : livro.getIsbn10());

        Integer qtdOutrosAutores = 0;
        for (String idObra : livro.getIdsObras()) {
            Obra obra = Service.getInstance().getObraById(idObra);
            for (String idPessoa : obra.getIdsAutores()) {
                Pessoa autor = Service.getInstance().getPessoaById(idPessoa);
                if (autorPrincipal == null) {
                    this.autorPrincipal = autor.getNome();
                } else {
                    qtdOutrosAutores++;
                    this.outrosAutores += autor.getNome() + "\n";
                }
            }
        }
        if (qtdOutrosAutores > 0) {
            this.qtdOutrosAutores = "+" + qtdOutrosAutores;
        }
    }
}
