package zcla71.baudoze.view.obras;

import lombok.Data;

@Data
public class ObrasPaginaObra {
    private String id;
    private String titulo;
    private String autorPrincipal;
    private Integer qtdOutrosAutores;
    private Integer qtdLivros;
}
