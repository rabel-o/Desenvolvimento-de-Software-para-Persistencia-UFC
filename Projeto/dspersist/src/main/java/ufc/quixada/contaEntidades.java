package ufc.quixada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class contaEntidades {
    private String caminhoArquivo = "C:/Users/conta/Desktop/Atividades_Faculdade/PERSISTENCIA/dspersist/Livros.csv";

    public contaEntidades(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public int contarEntidades() {
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
            while(br.readLine() != null) {
                count++;
            }
            br.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}