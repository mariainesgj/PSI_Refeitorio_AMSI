package pt.ipleiria.estg.dei.refeitorio.data.network;

public class ApiEndpoints {
    public static final String BASE_URL = "http://10.10.10.95/refeitorio-api/backend/web/api";
    public static final String LOGIN = BASE_URL + "/user/login";
    public static final String REGISTER = BASE_URL + "/user/register";

    public static final String FATURA_FETCH = BASE_URL + "/fatura/by-user";

    public static final String PRATO_DIA_FETCH = BASE_URL + "/senha/pratos-sopa?data=%s";


    public static final String PRATOS_SENHAS_FETCH = BASE_URL + "/ementa/pratos-sopa-com-senhas";
}
