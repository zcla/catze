package zcla71.catze.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import zcla71.catze.service.model.Obra;

@Data
@AllArgsConstructor
public class Obras_Obra {
    private String titulo;

    public Obras_Obra(Obra obra) {
        this.titulo = obra.getTitulo();
    }
}
