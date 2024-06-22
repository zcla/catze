package zcla71.baudoze.repository.model;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;
import zcla71.baudoze.service.model.Colecao;
import zcla71.baudoze.service.model.Editora;
import zcla71.baudoze.service.model.Etiqueta;
import zcla71.baudoze.service.model.Livro;
import zcla71.baudoze.service.model.Obra;
import zcla71.baudoze.service.model.Pessoa;

@Data
public class BauDoZeRepositoryData {
    private Collection<Livro> livros;
    private Collection<Obra> obras;
    private Collection<Pessoa> pessoas;
    private Collection<Editora> editoras;
    private Collection<Colecao> colecoes;
    private Collection<Etiqueta> etiquetas;

    public BauDoZeRepositoryData() {
        this.livros = new ArrayList<>();
        this.obras = new ArrayList<>();
        this.pessoas = new ArrayList<>();
        this.editoras = new ArrayList<>();
        this.colecoes = new ArrayList<>();
        this.etiquetas = new ArrayList<>();
    }

    public Colecao buscaColecaoPorNome(String nome) {
        return this.colecoes.stream().filter(p -> p.getNome().equals(nome)).findFirst().orElse(null);
    }

    public Editora buscaEditoraPorId(String id) {
        return this.editoras.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Editora buscaEditoraPorNome(String nome) {
        return this.editoras.stream().filter(p -> p.getNome().equals(nome)).findFirst().orElse(null);
    }

    public Etiqueta buscaEtiquetaPorNome(String nome) {
        return this.etiquetas.stream().filter(p -> p.getNome().equals(nome)).findFirst().orElse(null);
    }

    public Pessoa buscaPessoaPorId(String id) {
        return this.pessoas.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Obra buscaObraPorId(String id) {
        return this.obras.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
