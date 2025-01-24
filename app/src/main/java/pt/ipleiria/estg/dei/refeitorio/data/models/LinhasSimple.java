package pt.ipleiria.estg.dei.refeitorio.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class LinhasSimple{
    public int id;

    @SerializedName("carrinho_id")
    public int carrinhoId;

    @SerializedName("ementa_id")
    public int ementaId;

    @SerializedName("prato_id")
    public int pratoId;

    @SerializedName("prato_nome")
    public String pratoNome;

    public String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(int carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public int getEmentaId() {
        return ementaId;
    }

    public void setEmentaId(int ementaId) {
        this.ementaId = ementaId;
    }

    public int getPratoId() {
        return pratoId;
    }

    public void setPratoId(int pratoId) {
        this.pratoId = pratoId;
    }

    public String getPratoNome() {
        return pratoNome;
    }

    public void setPratoNome(String pratoNome) {
        this.pratoNome = pratoNome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinhasSimple)) return false;
        LinhasSimple that = (LinhasSimple) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
