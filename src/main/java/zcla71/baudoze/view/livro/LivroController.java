package zcla71.baudoze.view.livro;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import zcla71.baudoze.controller.BauDoZe;
import zcla71.baudoze.repository.model.RepositoryException;
import zcla71.baudoze.view.Pagina.Estado;

@Controller
public class LivroController {
    private String livroSetModel(Model model, BauDoZe bauDoZe, LivroPagina livro) throws StreamReadException, DatabindException, IOException {
        model.addAttribute("livro", livro);
        model.addAttribute("obras", bauDoZe.getObras()); // TODO Criar classe equivalente em view, conforme abaixo
        // new LivroPaginaObra(obra)
        // Obra obra = service.buscaObraPorId(idObra);
        // String autores = "";
        // for (String idAutor : obra.getIdsAutores()) {
        //     autores += service.buscaPessoaPorId(idAutor).getNome() + ", ";
        // }
        // if (autores.length() > 0) {
        //     autores = " (" + autores.substring(0, autores.length() - 2) + ")";
        // }
        return "livro";
    }

    @GetMapping("/livro/{id}")
    public String livroGet(Model model, @PathVariable String id) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        LivroPagina livro = BauDoZe.getInstance().getLivro(id);
        return livroSetModel(model, bauDoZe, livro);
    }

    @PutMapping("/livro/{id}")
    public String livroPut(Model model, @PathVariable String id, @ModelAttribute LivroForm form) throws StreamReadException, DatabindException, IOException, RepositoryException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        LivroPagina livro = bauDoZe.getLivro(id);
        livro.setTitulo(form.getTitulo());
        if (livro.dadosValidos()) {
            bauDoZe.setLivro(livro);
            livro.setEstadoPagina(null);
        } else {
            livro.setEstadoPagina(Estado.UPDATE);
        }
        return livroSetModel(model, bauDoZe, livro);
    }
}
