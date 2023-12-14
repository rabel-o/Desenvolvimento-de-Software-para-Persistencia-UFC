package ufc.quixada;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class calculaHash {
    private String caminhoArquivo = "C:/Users/conta/Desktop/Atividades_Faculdade/PERSISTENCIA/dspersist/Livros.csv";

    public calculaHash(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String calcularHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(caminhoArquivo);

            byte[] dadosBytes = new byte[1024];
            int bytesLidos;

            while((bytesLidos = fis.read(dadosBytes)) != -1) {

                md.update(dadosBytes, 0, bytesLidos);
            }

            byte[] mdBytes = md.digest();

            //Convertendo os bytes para hexadecimal
            StringBuilder sb = new StringBuilder();

            for(byte mdByte : mdBytes) {
            
                sb.append(Integer.toString((mdByte & 0xff) + 0x100, 16).substring(1));
            
            }

            return sb.toString();

        } catch(NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
        
    }
}
