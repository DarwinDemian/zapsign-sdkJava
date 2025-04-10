package request;

import services.HttpRequestFactory;
import services.JsonConverter;

import java.net.http.HttpResponse;

public class Request {
    protected final String apiRoute = "https://api.zapsign.com.br/api/v1/";
    protected final JsonConverter jsonConverter = new JsonConverter();
    protected String apiToken;

    public Request(String apiToken) {
        this.apiToken = apiToken;
    }

    protected <T> T createRequest(Object payload, String endpoint, Class<T> responseType) throws Exception {
        String uri = new StringBuilder()
                .append(apiRoute).append(endpoint).append("/")
                .append("?api_token=").append(apiToken)
                .toString();

        HttpResponse<String> response = payload == null
                ? new HttpRequestFactory().getRequest(uri)
                : new HttpRequestFactory().postRequest(uri, jsonConverter.convertToJson(payload));

        return jsonConverter.jsonToType(response.body(), responseType);
    }

    public String getTokenApi() {
        return apiToken;
    }

    public void setTokenApi(String apiToken) {
        this.apiToken = apiToken;
    }
}
