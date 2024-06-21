package zcla71.catze.controller;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import zcla71.catze.service.Service;
import zcla71.catze.service.model.Editora;
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;
import zcla71.catze.view.model.Stats;
import zcla71.catze.view.model.Editoras;
import zcla71.catze.view.model.Livros;
import zcla71.catze.view.model.Obras;
import zcla71.catze.view.model.Pessoas;

public class CatZe {
    private static CatZe instance;

    public static CatZe getInstance() {
        if (instance == null) {
            instance = new CatZe();
        }
        return instance;
    }

    public Collection<Editoras> getEditoras() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Editora> editoras = service.listaEditoras();
        List<Editoras> result = new ArrayList<>();
        for (Editora editora : editoras) {
            result.add(new Editoras(editora));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Editoras>() {
            @Override
            public int compare(Editoras o1, Editoras o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getNome(), o2.getNome());
            }
        });

        return result;
    }

    public Collection<Livros> getLivros() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Livro> livros = service.listaLivros();
        List<Livros> result = new ArrayList<>();
        for (Livro livro : livros) {
            result.add(new Livros(livro));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Livros>() {
            @Override
            public int compare(Livros o1, Livros o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getTitulo(), o2.getTitulo());
            }
        });

        return result;
    }

    public Collection<Obras> getObras() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Obra> obras = service.listaObras();
        List<Obras> result = new ArrayList<>();
        for (Obra obra : obras) {
            result.add(new Obras(obra));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Obras>() {
            @Override
            public int compare(Obras o1, Obras o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getTitulo(), o2.getTitulo());
            }
        });

        return result;
    }

    public Collection<Pessoas> getPessoas() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Pessoa> pessoas = service.listaPessoas();
        List<Pessoas> result = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            result.add(new Pessoas(pessoa));
        }

        // Atualmente desnecessário, pois o DataTable já ordena
        Collections.sort(result, new Comparator<Pessoas>() {
            @Override
            public int compare(Pessoas p1, Pessoas p2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(p1.getNome(), p2.getNome());
            }
        });

        return result;
    }

    public Stats getStats() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Stats result = new Stats(service.listaObras().size(), service.listaLivros().size(), service.listaPessoas().size(), service.listaEditoras().size());
        return result;
    }
}
