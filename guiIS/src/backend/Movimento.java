package backend;
import backend.clientpkg.Cliente;

import java.util.Date;
import java.util.Objects;

public abstract class Movimento {
    private Date data;
    private Cliente cliente;

    public Movimento(Date data, Cliente cliente) {
        this.data =Objects.requireNonNull(data);
        this.cliente = Objects.requireNonNull(cliente);
    }


    public Date getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }


}