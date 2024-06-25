package zcla71.baudoze.view.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.Data;
import zcla71.baudoze.service.Service;
import zcla71.baudoze.service.model.Livro;
import zcla71.baudoze.service.model.Obra;

@Data
public class ViewLivro {
    private String id;
    private String titulo;
    private Collection<String> obras;
    private String isbn13;
    private String isbn10;
    private Collection<String> editoras;
    private Integer ano;
    private Collection<String> etiquetas;
    private Integer paginas;
    private String edicao;

    public ViewLivro(Livro livro) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        this.id = livro.getId();
        this.titulo = livro.getTitulo();

        this.obras = new ArrayList<>();
        for (String idObra : livro.getIdsObras()) {
            Obra obra = service.buscaObraPorId(idObra);
            String autores = "";
            for (String idAutor : obra.getIdsAutores()) {
                autores += service.buscaPessoaPorId(idAutor).getNome() + ", ";
            }
            if (autores.length() > 0) {
                autores = " (" + autores.substring(0, autores.length() - 2) + ")";
            }
            this.obras.add(obra.getTitulo() + autores);
        }

        this.isbn13 = livro.getIsbn13();
        this.isbn10 = livro.getIsbn10();

        this.editoras = new ArrayList<>();
        for (String idEditora : livro.getIdsEditoras()) {
            this.editoras.add(service.buscaEditoraPorId(idEditora).getNome());
        }

        this.ano = livro.getAno();

        this.etiquetas = new ArrayList<>();
        for (String idEtiqueta : livro.getIdsEtiquetas()) {
            this.etiquetas.add(service.buscaEtiquetaPorId(idEtiqueta).getNome());
        }

        this.paginas = livro.getPaginas();
        this.edicao = livro.getEdicao();
    }
}
