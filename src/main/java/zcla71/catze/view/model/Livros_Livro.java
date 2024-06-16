package zcla71.catze.view.model;

import lombok.Data;
import zcla71.catze.service.model.Livro;

@Data
public class Livros_Livro {
    private String titulo;

    public Livros_Livro(Livro livro) {
        this.titulo = livro.getTitulo();
    }
}
