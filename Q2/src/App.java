import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
       
        String filename = args[0];
        String subString = args[1];

        InputStream is = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);

        int numLinha = 0;
        boolean achou = false;

        String linha;
        while ((linha = bf.readLine()) != null) {
            numLinha++;

            if (linha.contains(subString)) {
                System.out.println(numLinha + ": " + linha);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("A frase não foi encontrada");
        }

        bf.close();
    }
}
