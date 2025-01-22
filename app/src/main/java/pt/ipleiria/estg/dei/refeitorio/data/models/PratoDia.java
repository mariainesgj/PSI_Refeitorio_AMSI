package pt.ipleiria.estg.dei.refeitorio.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PratoDia{
    public int id;
    public String data;
    public int consumido;
    public String criado;
    public String alterado;
    public String descricao;
    @SerializedName("user_id")
    public int userId;

    @SerializedName("ementa_id")
    public int ementaId;

    @SerializedName("prato_id")
    public int pratoId;
    public String lido;
    public int pago;
    public String valor;
    public String iva;

    @SerializedName("nome_prato")
    public String nomePrato;
    @SerializedName("nome_sopa")
    public String nomeSopa;
    @SerializedName("tipo_prato")
    public String tipoPrato;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getConsumido() {
        return consumido;
    }

    public void setConsumido(int consumido) {
        this.consumido = consumido;
    }

    public String getCriado() {
        return criado;
    }

    public void setCriado(String criado) {
        this.criado = criado;
    }

    public String getAlterado() {
        return alterado;
    }

    public void setAlterado(String alterado) {
        this.alterado = alterado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getLido() {
        return lido;
    }

    public void setLido(String lido) {
        this.lido = lido;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public String getNomeSopa() {
        return nomeSopa;
    }

    public void setNomeSopa(String nomeSopa) {
        this.nomeSopa = nomeSopa;
    }

    public String getTipoPrato() {
        return tipoPrato;
    }

    public void setTipoPrato(String tipoPrato) {
        this.tipoPrato = tipoPrato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PratoDia)) return false;
        PratoDia pratoDia = (PratoDia) o;
        return getId() == pratoDia.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
