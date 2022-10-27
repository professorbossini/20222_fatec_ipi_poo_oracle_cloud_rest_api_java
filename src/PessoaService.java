import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.*;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PessoaService {
  private HttpClient client = HttpClient.newHttpClient();
  private final String url;

  public void listar(){
    try {
      // design pattern: builder
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url)).build();
      var response = client.send(request, BodyHandlers.ofString());
      JSONObject raiz = new JSONObject(response.body());
      JSONArray items = raiz.getJSONArray("items");
      JSONObject primeiro = items.getJSONObject(0);
      // System.out.println(primeiro);
      String nome = primeiro.getString("nome");
      System.out.println(nome);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

}
