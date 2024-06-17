package zcla71.catze.view.model;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.AllArgsConstructor;
import lombok.Data;
import zcla71.catze.service.Service;
import zcla71.catze.service.model.Obra;
import zcla71.catze.service.model.Pessoa;

@Data
@AllArgsConstructor
public class Obras_Obra {
    private String titulo;
    private String autorPrincipal;
    private String qtdOutrosAutores;
    private String outrosAutores;

    public Obras_Obra(Obra obra) throws StreamReadException, DatabindException, IOException {
        this.titulo = obra.getTitulo();
        this.qtdOutrosAutores = null;
        this.outrosAutores = "";

        Integer qtdOutrosAutores = 0;
        for (String idPessoa : obra.getIdsAutores()) {
            Pessoa autor = Service.getInstance().getPessoaById(idPessoa);
            if (autorPrincipal == null) {
                this.autorPrincipal = autor.getNome();
            } else {
                qtdOutrosAutores++;
                this.outrosAutores += autor.getNome() + "\n";
            }
        }
        if (qtdOutrosAutores > 0) {
            this.qtdOutrosAutores = "+" + qtdOutrosAutores;
        }
    }
}
