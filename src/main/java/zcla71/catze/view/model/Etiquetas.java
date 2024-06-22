package zcla71.catze.view.model;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.Data;
import zcla71.catze.service.Service;
import zcla71.catze.service.model.Etiqueta;

@Data
public class Etiquetas {
    private String nome;
    private Integer qtdLivros;

    public Etiquetas(Etiqueta etiqueta) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();

        this.nome = etiqueta.getNome();

        this.qtdLivros = service.listaLivros().stream().filter(l -> l.getIdsEtiquetas().contains(etiqueta.getId())).toArray().length;
    }
}
