package ufc.quixada;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class converteDados {
    private String caminhoArquivo = "C:/Users/conta/Desktop/Atividades_Faculdade/PERSISTENCIA/dspersist/Livros.csv";

    public converteDados(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public void converteJSONeXML() {
        List<Livro> livros = new ArrayList<>();

        try {
            BufferedReader lerCSV = new BufferedReader(new FileReader(caminhoArquivo));
            String linha;
            while((linha = lerCSV.readLine()) != null) {
                String[] dados = linha.split(",");
                Livro livro = new Livro(dados[0], dados[1], dados[2], dados[3], dados[4]);
                livros.add(livro);
            }
            lerCSV.close();

            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonMapper.writeValue(new File("livros.json"), livros);

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(new File("livros.xml"), livros);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
