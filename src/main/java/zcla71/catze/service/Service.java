package zcla71.catze.service;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import zcla71.catze.repository.Repository;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;

public class Service {
    private static Service instance;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public Collection<Livro> getLivros() throws StreamReadException, DatabindException, IOException {
        return Repository.getInstance().getLivros();
    }

    public Collection<Obra> getObras() throws StreamReadException, DatabindException, IOException {
        return Repository.getInstance().getObras();
    }

    public Collection<Pessoa> getPessoas() throws StreamReadException, DatabindException, IOException {
        return Repository.getInstance().getPessoas();
    }
}
