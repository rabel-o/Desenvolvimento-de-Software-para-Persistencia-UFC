/*2. Crie uma aplicação em Java que recebe via linha de comando (1) o nome
de um arquivo a ser encriptado, (2) o nome do arquivo encriptado a ser criado e (3) a
chave de encriptação.

java Main text.txt naoleia.enc morcegaomorcegao
 */

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String arqEntrada = args[0];
        String arqSaida = args[1];
        String keyS = args[2];
        byte[] keyB = keyS.getBytes("UTF-8");
        SecretKeySpec chave = new SecretKeySpec(keyB, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, chave);

        FileInputStream fis = new FileInputStream(arqEntrada);
        FileOutputStream fos = new FileOutputStream(arqSaida);
        byte[] buffer = new byte[4096];
        int bytesLidos;

        while ((bytesLidos = fis.read(buffer)) != -1) {
            byte[] saida = cipher.update(buffer, 0, bytesLidos);
            if (saida != null) {
                fos.write(saida);
            }
        }

        byte[] saidaFinal = cipher.doFinal();
        if (saidaFinal != null) {
            fos.write(saidaFinal);
        }

        fis.close();
        fos.close();
    }
}
