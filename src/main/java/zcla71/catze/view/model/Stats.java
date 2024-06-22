package zcla71.catze.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stats {
    private Integer obras;
    private Integer livros;
    private Integer pessoas;
    private Integer editoras;
    private Integer colecoes;
    private Integer etiquetas;
}
