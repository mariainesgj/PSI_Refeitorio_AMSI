package pt.ipleiria.estg.dei.refeitorio.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class EmentaMenu{
    public int id;

    @SerializedName("cozinha_id")
    public int cozinhaId;

    public String data;

    @SerializedName("prato_normal_id")
    public int pratoNormalId;

    @SerializedName("prato_normal_nome")
    public String pratoNormalNome;

    @SerializedName("prato_vegetariano_id")
    public int pratoVegetarianoId;

    @SerializedName("prato_vegetariano_nome")
    public String pratoVegetarianoNome;

    @SerializedName("sopa_nome")
    public String sopaNome;

    @SerializedName("senha_id")
    public int senhaId;

    @SerializedName("senha_prato")
    public int senhaPrato;

    @SerializedName("senha_prato_nome")
    public String senhaPratoNome;

    @SerializedName("senha_prato_tipo")
    public String senhaPratoTipo;

    @SerializedName("linha_carrinho_normal_id")
    public int linhaCarrinhoNormalId;

    @SerializedName("linha_carrinho_vegetariano_id")
    public int linhaCarrinhoVegetarianoId;

    @SerializedName("carrinho_status")
    public String carrinhoStatus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCozinhaId() {
        return cozinhaId;
    }

    public void setCozinhaId(int cozinhaId) {
        this.cozinhaId = cozinhaId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPratoNormalId() {
        return pratoNormalId;
    }

    public void setPratoNormalId(int pratoNormalId) {
        this.pratoNormalId = pratoNormalId;
    }

    public String getPratoNormalNome() {
        return pratoNormalNome;
    }

    public void setPratoNormalNome(String pratoNormalNome) {
        this.pratoNormalNome = pratoNormalNome;
    }

    public int getPratoVegetarianoId() {
        return pratoVegetarianoId;
    }

    public void setPratoVegetarianoId(int pratoVegetarianoId) {
        this.pratoVegetarianoId = pratoVegetarianoId;
    }

    public String getPratoVegetarianoNome() {
        return pratoVegetarianoNome;
    }

    public void setPratoVegetarianoNome(String pratoVegetarianoNome) {
        this.pratoVegetarianoNome = pratoVegetarianoNome;
    }

    public String getSopaNome() {
        return sopaNome;
    }

    public void setSopaNome(String sopaNome) {
        this.sopaNome = sopaNome;
    }

    public int getSenhaId() {
        return senhaId;
    }

    public void setSenhaId(int senhaId) {
        this.senhaId = senhaId;
    }

    public int getSenhaPrato() {
        return senhaPrato;
    }

    public void setSenhaPrato(int senhaPrato) {
        this.senhaPrato = senhaPrato;
    }

    public String getSenhaPratoNome() {
        return senhaPratoNome;
    }

    public void setSenhaPratoNome(String senhaPratoNome) {
        this.senhaPratoNome = senhaPratoNome;
    }

    public String getSenhaPratoTipo() {
        return senhaPratoTipo;
    }

    public void setSenhaPratoTipo(String senhaPratoTipo) {
        this.senhaPratoTipo = senhaPratoTipo;
    }

    public int getLinhaCarrinhoNormalId() {
        return linhaCarrinhoNormalId;
    }

    public void setLinhaCarrinhoNormalId(int linhaCarrinhoNormalId) {
        this.linhaCarrinhoNormalId = linhaCarrinhoNormalId;
    }

    public int getLinhaCarrinhoVegetarianoId() {
        return linhaCarrinhoVegetarianoId;
    }

    public void setLinhaCarrinhoVegetarianoId(int linhaCarrinhoVegetarianoId) {
        this.linhaCarrinhoVegetarianoId = linhaCarrinhoVegetarianoId;
    }

    public String getCarrinhoStatus() {
        return carrinhoStatus;
    }

    public void setCarrinhoStatus(String carrinhoStatus) {
        this.carrinhoStatus = carrinhoStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmentaMenu)) return false;
        EmentaMenu that = (EmentaMenu) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}