/*3. Crie uma aplicação em Java que recebe via linha de comando (1) o nome
 de um arquivo a ser decriptado e (2) o nome do arquivo resultante da decriptação
 e (3) a chave de decriptação.

 java Main naoleia.enc text.txt morcegaomorcegao
 */

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Main {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        String chaveString = args[2];

        try {
            byte[] chaveBytes = chaveString.getBytes("UTF-8");

            SecretKeySpec chave = new SecretKeySpec(chaveBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            FileInputStream inputStream = new FileInputStream(inputFile);

            byte[] ivBytes = new byte[16]; // O IV tem 16 bytes
            inputStream.read(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.DECRYPT_MODE, chave, iv);

            try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
                    outputStream.write(decryptedBytes);
                }

                byte[] finalBytes = cipher.doFinal();
                outputStream.write(finalBytes);
            }

            System.out.println("Arquivo decriptado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao decriptar o arquivo.");
        }
    }
}
