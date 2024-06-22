package zcla71.baudoze.view.model;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.Data;
import zcla71.baudoze.service.Service;
import zcla71.baudoze.service.model.Editora;

@Data
public class Editoras {
    private String nome;
    private Integer qtdLivros;

    public Editoras(Editora editora) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();

        this.nome = editora.getNome();

        this.qtdLivros = service.listaLivros().stream().filter(l -> l.getIdsEditoras().contains(editora.getId())).toArray().length;
    }
}
