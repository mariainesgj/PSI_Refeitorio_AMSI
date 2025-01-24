package pt.ipleiria.estg.dei.refeitorio.data.models;

import com.google.gson.annotations.SerializedName;

public class LinhasSimple{
    public int id;

    @SerializedName("carrinho_id")
    public int carrinhoId;

    @SerializedName("ementa_id")
    public int ementaId;

    @SerializedName("prato_id")
    public int pratoId;
}
