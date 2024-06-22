package zcla71.baudoze.view.model;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.Data;
import zcla71.baudoze.service.Service;
import zcla71.baudoze.service.model.Atividade;

@Data
public class Atividades {
    private LocalDate data;
    private String tipo;
    private String livro;

    public Atividades(Atividade atividade) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();

        this.data = atividade.getData();
        this.tipo = atividade.getTipo().getTexto();
        this.livro = service.buscaLivroPorId(atividade.getIdLivro()).getTitulo();
    }
}
