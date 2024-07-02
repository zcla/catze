package zcla71.baudoze.view.livros;

import lombok.Data;

@Data
public class LivrosPaginaLivro {
    private String id;
    private String titulo;
    private Integer qtdObras;
    private String autorPrincipal;
    private Integer qtdOutrosAutores;
    private String isbn;
    private String editoraPrincipal;
    private Integer qtdOutrasEditoras;
    private Integer ano;
    private String colecao;
    private String etiquetas;
    private Integer paginas;
    private String edicao;
    private String status;
}
