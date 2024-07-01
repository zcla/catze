package zcla71.baudoze.view.livro;

import java.util.Collection;

import lombok.Data;

@Data
public class LivroForm {
    private String id;
    private String titulo;
    private Collection<String> obras;
}
