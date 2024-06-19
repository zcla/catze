package zcla71.catze.service;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import zcla71.catze.repository.Repository;
import zcla71.catze.repository.model.CatZeRepositoryData;
import zcla71.catze.service.model.Editora;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;

public class Service {
    private static final String JSON_FILE_LOCATION = "data/catze.json"; // TODO Jogar pro application.properties
    private static Service instance;

    public static Service getInstance() throws StreamReadException, DatabindException, IOException {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    private Repository<CatZeRepositoryData> repository;

    private Service() throws StreamReadException, DatabindException, IOException {
        this.repository = new Repository<CatZeRepositoryData>(CatZeRepositoryData.class, JSON_FILE_LOCATION, true);
    }

    // Editoras

    public Collection<Editora> listaEditoras() throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().getEditoras();
    }

    public Editora buscaEditoraPorNome(String nome) {
        return this.repository.getData().buscaEditoraPorNome(nome);
    }

    // Livros

    public Collection<Livro> listaLivros() throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().getLivros();
    }

    public Collection<Livro> listaLivrosDaObra(String idObra) throws StreamReadException, DatabindException, IOException {
        return this.listaLivros().stream().filter(l -> l.getIdsObras().contains(idObra)).toList();
    }

    // Obras

    public Collection<Obra> listaObras() throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().getObras();
    }

    public Obra buscaObraPorId(String id) {
        return this.repository.getData().buscaObraPorId(id);
    }

    // Pessoas

    public Collection<Pessoa> listaPessoas() throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().getPessoas();
    }

    public Pessoa buscaPessoaPorId(String id) throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().buscaPessoaPorId(id);
    }
}
