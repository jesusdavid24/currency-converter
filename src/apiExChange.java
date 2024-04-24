import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class apiExChange {

  private String apiKey = "a79b4a649290dce8fd9fda6d";
  public ExChange getExchangeRate(String fromCurrency, String toCurrency) {
    URI uri = URI.create(
      "https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+fromCurrency+"/"+toCurrency
    );

    HttpClient client = HttpClient.newBuilder()
      .followRedirects(HttpClient.Redirect.NORMAL)
      .build();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(uri)
      .build();

    try {
      HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
      return new Gson().fromJson(response.body(), ExChange.class);
    } catch (Exception e) {
      throw new RuntimeException("I didn't find that currency.");
    }
  }
}
