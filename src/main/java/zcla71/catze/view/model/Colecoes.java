package zcla71.catze.view.model;

import lombok.Data;
import zcla71.catze.service.model.Colecao;

@Data
public class Colecoes {
    private String nome;
    private Integer qtdLivros;

    public Colecoes(Colecao colecao) {
        this.nome = colecao.getNome();
        this.qtdLivros = colecao.getIdsLivros().size();
    }
}
