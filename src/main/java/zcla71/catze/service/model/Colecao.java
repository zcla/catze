package zcla71.catze.service.model;

import java.util.Collection;

import lombok.Data;

@Data
public class Colecao {
    private String id;
    private String nome;
    private Collection<String> idsLivros;
}
