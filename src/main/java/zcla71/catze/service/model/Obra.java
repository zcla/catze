package zcla71.catze.service.model;

import java.util.Collection;

import lombok.Data;

@Data
public class Obra {
    private String id;
    private String titulo;
    private Collection<String> idsAutores;
}
