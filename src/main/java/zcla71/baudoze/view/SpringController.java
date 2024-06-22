package zcla71.baudoze.view;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import zcla71.baudoze.controller.BauDoZe;

@Controller
public class SpringController {
    @GetMapping("/")
    public String index(Model model) throws StreamReadException, DatabindException, IOException {
        return stats(model);
    }

    @GetMapping("/atividades")
    public String atividades(Model model) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        model.addAttribute("atividades", bauDoZe.getAtividades());
        return "atividades";
    }

    @GetMapping("/colecoes")
    public String colecoes(Model model) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        model.addAttribute("colecoes", bauDoZe.getColecoes());
        return "colecoes";
    }

    @GetMapping("/editoras")
    public String editoras(Model model) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        model.addAttribute("editoras", bauDoZe.getEditoras());
        return "editoras";
    }

    @GetMapping("/etiquetas")
    public String etiquetas(Model model) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        model.addAttribute("etiquetas", bauDoZe.getEtiquetas());
        return "etiquetas";
    }

    @GetMapping("/livros")
    public String livros(Model model) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        model.addAttribute("livros", bauDoZe.getLivros());
        return "livros";
    }

    @GetMapping("/obras")
    public String obras(Model model) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        model.addAttribute("obras", bauDoZe.getObras());
        return "obras";
    }

    @GetMapping("/pessoas")
    public String pessoas(Model model) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        model.addAttribute("pessoas", bauDoZe.getPessoas());
        return "pessoas";
    }

    @GetMapping("/stats")
    public String stats(Model model) throws StreamReadException, DatabindException, IOException {
        BauDoZe bauDoZe = BauDoZe.getInstance();
        model.addAttribute("stats", bauDoZe.getStats());
        return "stats";
    }
}
