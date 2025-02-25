import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
// import java.io.FileInputStream;
import java.io.InputStream;
// import java.net.URL;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;
// import javax.swing.plaf.FontUIResource;

public class GeradorSticker {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura de imagem
        // InputStream inputStream = new FileInputStream(new File("entrada/filme.png"));
        // new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        // copiar  a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura-100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
         
    }  
}
