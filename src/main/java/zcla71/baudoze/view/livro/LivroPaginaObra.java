package zcla71.baudoze.view.livro;

import lombok.Data;
import zcla71.baudoze.view.model.Obras;

@Data
public class LivroPaginaObra {
    private String id;
    private String nome;

    public LivroPaginaObra(Obras obra) {
        this.id = obra.getId();
        this.nome = obra.getTitulo();
        if (obra.getAutorPrincipal() != null) {
            this.nome += " (" + obra.getAutorPrincipal() + ")";
        }
    }
}
