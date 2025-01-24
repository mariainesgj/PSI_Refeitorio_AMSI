package pt.ipleiria.estg.dei.refeitorio.data.network;

public class ApiEndpoints {
    public static final String BASE_URL = "http://10.10.10.95/refeitorio-api/backend/web/api";
    public static final String LOGIN = BASE_URL + "/user/login";
    public static final String REGISTER = BASE_URL + "/user/register";

    public static final String FATURA_FETCH = BASE_URL + "/fatura/by-user";

    public static final String PRATO_DIA_FETCH = BASE_URL + "/senha/pratos-sopa?data=%s";

    public static final String PRATOS_SENHAS_FETCH = BASE_URL + "/ementa/pratos-sopa-com-senhas";

    public static final String CARRINHO_FETCH = BASE_URL + "/carrinho/carrinho-ativo";

    public static final String CARRINHO_ADD_ITEM_POST = BASE_URL + "/linhascarrinho/adicionar-item";

    public static final String CARRINHO_EXCLUIR_ITEM_DELETE = BASE_URL + "/linhascarrinho/excluir-item?id=%s";

    public static final String CHECKOUT_POST = BASE_URL + "/carrinho/checkout";
    public static final String COZINHA_FETCH = BASE_URL + "/cozinhas";
}
