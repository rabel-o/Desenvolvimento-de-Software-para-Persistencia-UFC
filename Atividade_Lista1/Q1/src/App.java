import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {
        String nomeArquivo = args[0];
       
        InputStream is = new FileInputStream(nomeArquivo);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);    
        String linha = bf.readLine();
        
        int count = 1;

       while (count <= 10) {
        System.out.println(linha);
        linha = bf.readLine();
        count++;
       }

        bf.close();
    }
}