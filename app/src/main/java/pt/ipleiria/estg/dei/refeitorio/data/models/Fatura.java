package pt.ipleiria.estg.dei.refeitorio.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Fatura{
    public int id;
    public String data;
    @SerializedName("total_iliquido")
    public String totalIliquido;

    @SerializedName("total_iva")
    public String totalIVA;
    @SerializedName("total_doc")
    public String totalDoc;

    public Linhas[] linhas;


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

    public String getTotalIliquido() {
        return totalIliquido;
    }

    public void setTotalIliquido(String totalIliquido) {
        this.totalIliquido = totalIliquido;
    }

    public String getTotalIVA() {
        return totalIVA;
    }

    public void setTotalIVA(String totalIVA) {
        this.totalIVA = totalIVA;
    }

    public String getTotalDoc() {
        return totalDoc;
    }

    public void setTotalDoc(String totalDoc) {
        this.totalDoc = totalDoc;
    }

    public Linhas[] getLinhas() {
        return linhas;
    }

    public void setLinhas(Linhas[] linhas) {
        this.linhas = linhas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fatura)) return false;
        Fatura fatura = (Fatura) o;
        return getId() == fatura.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", total_iliquido='" + totalIliquido + '\'' +
                ", total_iva='" + totalIVA + '\'' +
                ", total_doc='" + totalDoc + '\'' +
                '}';
    }
}