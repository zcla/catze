package zcla71.catze.view.model;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.AllArgsConstructor;
import lombok.Data;
import zcla71.catze.service.Service;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;

@Data
@AllArgsConstructor
public class Pessoas {
    private String nome;
    private Integer qtdObras;
    private Integer qtdLivros;

    public Pessoas(Pessoa pessoa) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();

        this.nome = pessoa.getNome();

        Collection<Obra> obrasAutor = service.listaObras().stream().filter(o -> o.getIdsAutores().contains(pessoa.getId())).toList();
        this.qtdObras = obrasAutor.size();

        Collection<Livro> livros = service.listaLivros();
        this.qtdLivros = 0;
        for (Livro livro : livros) {
            this.qtdLivros += obrasAutor.stream().filter(o -> o.getIdsAutores().contains(pessoa.getId()) && livro.getIdsObras().contains(o.getId())).toArray().length;
        }
    }
}
