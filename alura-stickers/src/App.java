import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexão HTTP e buscar top 250 filmes

        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // extrair título, poster, avaliação

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
  
        // exibir e manipular os dados
        
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String titulo = filme.get("title");
            

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = titulo + ".png";

            var gerador = new GeradorSticker();
            gerador.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }

    }
}
