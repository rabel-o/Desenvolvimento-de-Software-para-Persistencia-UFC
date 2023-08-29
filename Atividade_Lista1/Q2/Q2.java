import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;

public class Q2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        String nomeArquivo;
        nomeArquivo = sc.nextLine();

        InputStream is = new FileInputStream(nomeArquivo);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);

        String subString = args[0];
        sc.nextLine();

        String linha;

        int numLinha = 0;
        boolean achou = false;

        while ((linha = bf.readLine()) != null) {
            numLinha++;
            
            if (subString.equals(linha)) {
                System.out.println(numLinha + ": " + linha);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("A frase não foi encontrada");
        }

        bf.close();
        sc.close();
    }
}
