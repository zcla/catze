package zcla71.catze.view;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import zcla71.catze.controller.CatZe;

@Controller
public class SpringController {
    @GetMapping("/")
    public String index(Model model) throws StreamReadException, DatabindException, IOException {
        CatZe catZe = CatZe.getInstance();
        model.addAttribute("stats", catZe.getStats());
        return "index";
    }

    @GetMapping("/editoras")
    public String editoras(Model model) throws StreamReadException, DatabindException, IOException {
        CatZe catZe = CatZe.getInstance();
        model.addAttribute("editoras", catZe.getEditoras());
        return "editoras";
    }

    @GetMapping("/livros")
    public String livros(Model model) throws StreamReadException, DatabindException, IOException {
        CatZe catZe = CatZe.getInstance();
        model.addAttribute("livros", catZe.getLivros());
        return "livros";
    }

    @GetMapping("/obras")
    public String obras(Model model) throws StreamReadException, DatabindException, IOException {
        CatZe catZe = CatZe.getInstance();
        model.addAttribute("obras", catZe.getObras());
        return "obras";
    }

    @GetMapping("/pessoas")
    public String pessoas(Model model) throws StreamReadException, DatabindException, IOException {
        CatZe catZe = CatZe.getInstance();
        model.addAttribute("pessoas", catZe.getPessoas());
        return "pessoas";
    }
}
