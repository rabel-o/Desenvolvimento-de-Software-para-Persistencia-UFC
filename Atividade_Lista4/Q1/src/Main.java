/*1. Crie uma aplicação em Java que recebe via linha de comando (1) o nome de
 um arquivo compactado a ser criado e (2) uma pasta. Compactar todos os arquivos e
 subpastas em um arquivo compactado com extensão zip.

java Main "zip.zip" np
*/

import java.io.*;
import java.util.zip.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String nomeArquivo = args[0];
        String nomePasta = args[1];

        FileOutputStream fos = new FileOutputStream(nomeArquivo);
        ZipOutputStream zos = new ZipOutputStream(fos);

        File pasta = new File(nomePasta);
        zipar("", pasta, zos);

        zos.close();
        fos.close();
    }

    public static void zipar(String caminho, File pasta, ZipOutputStream zos) throws IOException {
        for(File arquivo : pasta.listFiles()) {
            if(arquivo.isDirectory()) {
                zipar(caminho + arquivo.getName() + "/", arquivo, zos);
            } else {
                FileInputStream fis = new FileInputStream(arquivo);
                ZipEntry ze = new ZipEntry(caminho + arquivo.getName());
                zos.putNextEntry(ze);

                byte[] buffer = new byte[1024];
                int tam;
                while((tam = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, tam);
                }

                zos.closeEntry();
                fis.close();
            }
        }
    }
}