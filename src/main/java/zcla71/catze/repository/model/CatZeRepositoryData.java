package zcla71.catze.repository.model;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;

@Data
public class CatZeRepositoryData {
    private Collection<Livro> livros;
    private Collection<Obra> obras;
    private Collection<Pessoa> pessoas;

    public CatZeRepositoryData() {
        this.livros = new ArrayList<>();
        this.obras = new ArrayList<>();
        this.pessoas = new ArrayList<>();
    }

    public Pessoa getPessoaById(String id) {
        return this.pessoas.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Obra getObraById(String id) {
        return this.obras.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
