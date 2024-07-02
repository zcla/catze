package zcla71.baudoze.view;

import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public abstract class Pagina {
    public enum Estado {
        CREATE,
        READ,
        UPDATE,
        DELETE
    }
    @Data
    @AllArgsConstructor
    public class MensagemDeErro {
        String campo;
        String mensagem;
    }

    private Estado estadoPagina = Estado.READ;
    private Collection<MensagemDeErro> mensagensDeErro;

    public Pagina() {
        this.mensagensDeErro = new ArrayList<>();
    }

    public void adicionaMensagemDeErro(String campo, String mensagem) {
        this.mensagensDeErro.add(new MensagemDeErro(campo, mensagem));
    }

    public boolean dadosValidos() {
        return true;
    }

    public boolean estaVazio(Collection<String> value) {
        if (value == null) {
            return true;
        }
        if (value.size() == 0) {
            return true;
        }
        return false;
    }

    public boolean estaVazio(String value) {
        if (value == null) {
            return true;
        }
        if (value.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
