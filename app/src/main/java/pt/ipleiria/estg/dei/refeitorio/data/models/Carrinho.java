package pt.ipleiria.estg.dei.refeitorio.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Carrinho{
    public int id;
    public String numero;
    public String subtotal;

    @SerializedName("user_id")
    public int userId;

    public String status;

    public LinhasSimple[] linhas;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LinhasSimple[] getLinhas() {
        return linhas;
    }

    public void setLinhas(LinhasSimple[] linhas) {
        this.linhas = linhas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carrinho)) return false;
        Carrinho carrinho = (Carrinho) o;
        return getId() == carrinho.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
