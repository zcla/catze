package zcla71.baudoze.view.model;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.AllArgsConstructor;
import lombok.Data;
import zcla71.baudoze.service.Service;
import zcla71.baudoze.service.model.Obra;
import zcla71.baudoze.service.model.Pessoa;

@Data
@AllArgsConstructor
public class Obras {
    private String id;
    private String titulo;
    private String autorPrincipal;
    private Integer qtdOutrosAutores;
    private Integer qtdLivros;

    public Obras(Obra obra) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();

        this.id = obra.getId();
        this.titulo = obra.getTitulo();

        this.autorPrincipal = null;
        this.qtdOutrosAutores = 0;
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
