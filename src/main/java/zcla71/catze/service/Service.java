package zcla71.catze.service;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import zcla71.catze.repository.Repository;
import zcla71.catze.repository.model.CatZeRepositoryData;
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


    public Collection<Livro> getLivros() throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().getLivros();
    }

    public Collection<Obra> getObras() throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().getObras();
    }

    public Obra getObraById(String id) {
        return this.repository.getData().getObraById(id);
    }

    public Collection<Pessoa> getPessoas() throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().getPessoas();
    }

    public Pessoa getPessoaById(String id) throws StreamReadException, DatabindException, IOException {
        return this.repository.getData().getPessoaById(id);
    }
}
