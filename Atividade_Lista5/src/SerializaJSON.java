/* 6. Crie uma classe Java de nome SerializaJSON para instanciar objetos da classe definida na 
Questão 1 e adicionar esses objetos em uma Lista. Depois percorrer a lista e Serializar os objetos 
em disco/ssd. Serialize usando a Serialização de objetos da biblioteca Jackson. 
Ver também: Serialization and Deserialization in Java using Jackson A practical guide on how to 
serialize and deserialize objects to JSON in Java using Jackson. */


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SerializaJSON {
    public static void main(String[] args) {
        //criando objetos
        Biblioteca biblioteca = new Biblioteca(1, "Biblioteca Central", "Rua Principal, 123", "123456789");
        Livro livro = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "1234567890123", "Editora X", 1954);
        Leitor leitor = new Leitor("João Silva", "Rua Secundária, 456", "987654321", 1);
        Emprestimo emprestimo = new Emprestimo(new Date(), new Date(), livro, leitor);

        //adicionando objetos a uma lista
        List<Object> lista = new ArrayList<>();
        lista.add(biblioteca);
        lista.add(livro);
        lista.add(leitor);
        lista.add(emprestimo);

        //serializando objetos em JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("objetos.json"), lista);
            System.out.println("Objetos serializados foram salvos em objetos.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
