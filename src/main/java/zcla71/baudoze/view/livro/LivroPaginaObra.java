package zcla71.baudoze.view.livro;

import lombok.Data;
import zcla71.baudoze.view.obras.ObrasPaginaObra;

@Data
public class LivroPaginaObra {
    private String id;
    private String nome;

    public LivroPaginaObra(ObrasPaginaObra obra) {
        this.id = obra.getId();
        this.nome = obra.getTitulo();
        if (obra.getAutorPrincipal() != null) {
            this.nome += " (" + obra.getAutorPrincipal() + ")";
        }
    }
}
