package zcla71.catze.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;

public class Repository {
    private static final String JSON_FILE_LOCATION = "data/catze.json";

    private static Repository instance;

    public static Repository getInstance() throws StreamReadException, DatabindException, IOException {
        if (instance == null) {
            instance = new Repository();
            instance.readData();
        }
        return instance;
    }

    private Boolean inTransaction;

    @Getter @Setter
    private Collection<Livro> livros;
    @Getter @Setter
    private Collection<Obra> obras;
    @Getter @Setter
    private Collection<Pessoa> pessoas;

    private Repository() {
        this.inTransaction = false;

        clear();
    }

    public void clear() {
        this.livros = new ArrayList<>();
        this.obras = new ArrayList<>();
        this.pessoas = new ArrayList<>();
    }

    public void beginTransaction() {
        if (this.inTransaction) {
            throw new RuntimeException("Já há transação iniciada");
        }
        this.inTransaction = true;
    }

    public void commitTransaction() throws StreamWriteException, DatabindException, IOException {
        if (!this.inTransaction) {
            throw new RuntimeException("Não há transação iniciada");
        }
        saveData();
        this.inTransaction = false;
    }

    public void rollbackTransaction() throws StreamReadException, DatabindException, IOException {
        if (!this.inTransaction) {
            throw new RuntimeException("Não há transação iniciada");
        }
        readData();
        this.inTransaction = false;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    private File getFile() {
        File file = new File(JSON_FILE_LOCATION);
        return file;
    }

    private void readData() throws StreamReadException, DatabindException, IOException {
        ObjectMapper objectMapper = getObjectMapper();
        File file = getFile();
        if (file.exists()) {
            instance = objectMapper.readValue(file, Repository.class);
        }
    }

    private void saveData() throws StreamWriteException, DatabindException, IOException {
        ObjectMapper objectMapper = getObjectMapper();
        File file = getFile();
        objectMapper.writeValue(file, this);
    }
}
