package pt.ipleiria.estg.dei.refeitorio.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Linhas {

    public int id;
    @SerializedName("senha_id")
    public int senhaId;
    public String preco;
    @SerializedName("taxa_iva")
    public String taxaIva;
    public int quantidade;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenhaId() {
        return senhaId;
    }

    public void setSenhaId(int senha_id) {
        this.senhaId = senha_id;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getTaxaIva() {
        return taxaIva;
    }

    public void setTaxaIva(String taxaIva) {
        this.taxaIva = taxaIva;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Linhas)) return false;
        Linhas linhas = (Linhas) o;
        return getId() == linhas.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Linhas{" +
                "id=" + id +
                ", senha_id='" + senhaId + '\'' +
                ", preco='" + preco + '\'' +
                ", taxa_iva='" + taxaIva + '\'' +
                ", quantidade='" + quantidade + '\'' +
                '}';
    }
}
