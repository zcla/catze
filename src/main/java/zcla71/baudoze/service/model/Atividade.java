package zcla71.baudoze.service.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atividade {
    public enum Tipo {
        CADASTRO,
        INICIO_LEITURA,
        TERMINO_LEITURA,
        ABANDONO_LEITURA
    }
    private Tipo tipo;
    private LocalDate data;
    private String idLivro;
}
