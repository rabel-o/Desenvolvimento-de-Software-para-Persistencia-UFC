import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q3 {
   public static void lerArquivos(String nomeArquivo) throws IOException {
        InputStream is = new FileInputStream(nomeArquivo);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);
        String linha = bf.readLine();

        while (linha != null){
            System.out.println(linha);
            linha = bf.readLine();
        }

        bf.close();

    }
    public static void main(String[] args) throws IOException {
       
        System.out.println("Arquivo 1: ");
        lerArquivos("StillLovingYou.txt");

        System.out.println("\n");

        System.out.println("Arquivo 2: ");
        lerArquivos("LivinOnAPrayer.txt");
    }
}
