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
import zcla71.catze.service.model.Livro;
import zcla71.catze.service.model.Obra;
import zcla71.catze.view.model.Index_Stats;
import zcla71.catze.view.model.Livros_Livro;
import zcla71.catze.view.model.Obras_Obra;

public class CatZe {
    private static CatZe instance;

    public static CatZe getInstance() {
        if (instance == null) {
            instance = new CatZe();
        }
        return instance;
    }

    public Index_Stats getIndex() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Index_Stats result = new Index_Stats(service.getObras().size(), service.getLivros().size());
        return result;
    }

    public Collection<Obras_Obra> getObras() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Obra> obras = service.getObras();
        List<Obras_Obra> result = new ArrayList<>();
        for (Obra obra : obras) {
            result.add(new Obras_Obra(obra));
        }

        Collections.sort(result, new Comparator<Obras_Obra>() {
            @Override
            public int compare(Obras_Obra o1, Obras_Obra o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getTitulo(), o2.getTitulo());
            }
        });

        return result;
    }

    public Collection<Livros_Livro> getLivros() throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<Livro> livros = service.getLivros();
        List<Livros_Livro> result = new ArrayList<>();
        for (Livro livro : livros) {
            result.add(new Livros_Livro(livro));
        }

        Collections.sort(result, new Comparator<Livros_Livro>() {
            @Override
            public int compare(Livros_Livro o1, Livros_Livro o2) {
                Collator ptBrCollator = Collator.getInstance(Locale.forLanguageTag("pt-BR"));
                return ptBrCollator.compare(o1.getTitulo(), o2.getTitulo());
            }
        });

        return result;
    }
}
