package zcla71.baudoze.view.model;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import lombok.Data;
import zcla71.baudoze.service.Service;
import zcla71.baudoze.service.model.Colecao;
import zcla71.baudoze.service.model.Livro;

@Data
public class Colecoes {
    private String nome;
    private Integer qtdLivros;
    private Integer paginas;

    public Colecoes(Colecao colecao) throws StreamReadException, DatabindException, IOException {
        Service service = Service.getInstance();
        Collection<String> idsLivros = colecao.getIdsLivros();

        this.nome = colecao.getNome();
        this.qtdLivros = idsLivros.size();
        this.paginas = 0;
        for (String idLivro : idsLivros) {
            Livro livro = service.buscaLivroPorId(idLivro);
            if (livro.getPaginas() != null) {
                this.paginas += livro.getPaginas();
            }
        }
    }
}
