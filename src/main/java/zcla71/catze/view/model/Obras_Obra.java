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
    private Integer qtdOutrosAutores;
    private Integer qtdLivros;

    public Obras_Obra(Obra obra) throws StreamReadException, DatabindException, IOException {
        this.titulo = obra.getTitulo();

        this.autorPrincipal = null;
        this.qtdOutrosAutores = 0;
        Service service = Service.getInstance();
        for (String idPessoa : obra.getIdsAutores()) {
            Pessoa autor = service.buscaPessoaPorId(idPessoa);
            if (this.autorPrincipal == null) {
                this.autorPrincipal = autor.getNome();
            } else {
                this.qtdOutrosAutores++;
            }
        }

        this.qtdLivros = service.listaLivrosDaObra(obra.getId()).size();
    }
}
