/*4. Crie uma aplicação em Java que recebe via linha de comando o nome de um
arquivo para geração/armazenamento dos hashes  md5, sha1 e sha256 do arquivo especificado.
A aplicação deve mostrar o tempo de execução de cada uma dessas operações. Dica: veja o
seguinte tutorial: MD5 Hashing in Java | Baeldung

java Main text.txt
 */

import java.io.*;
import java.security.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String nomeArquivo = args[0];

        long tInicio = System.nanoTime();
        String md5Hash = calculateHash(nomeArquivo, "MD5");
        long tFinal = System.nanoTime();
        System.out.println("MD5: " + md5Hash + ", Tempo de execução: " + (tFinal - tInicio) + " nanosegundos");

        tInicio = System.nanoTime();
        String sha1Hash = calculateHash(nomeArquivo, "SHA-1");
        tFinal = System.nanoTime();
        System.out.println("SHA-1: " + sha1Hash + ", Tempo de execução: " + (tFinal - tInicio) + " nanosegundos");

        tInicio = System.nanoTime();
        String sha256Hash = calculateHash(nomeArquivo, "SHA-256");
        tFinal = System.nanoTime();
        System.out.println("SHA-256: " + sha256Hash + ", Tempo de execução: " + (tFinal - tInicio) + " nanosegundos");
    }

    public static String calculateHash(String filename, String algorithm) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(Files.readAllBytes(Paths.get(filename)));
        byte[] digest = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }
}
