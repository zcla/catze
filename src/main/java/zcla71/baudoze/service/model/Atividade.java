package zcla71.baudoze.service.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atividade {
    public enum Tipo {
        CADASTRO("Cadastro"),
        INICIO_LEITURA("Início de leitura"),
        TERMINO_LEITURA("Término de leitura");

        @Getter
        private String texto;

        private Tipo(String texto) {
            this.texto = texto;
        }
    }

    private Tipo tipo;
    private LocalDate data;
    private String idLivro;
}
