/* 4. Crie uma classe Java de nome SerializaXML para instanciar objetos da classe definida na Questão 1 e 
adicionar esses objetos em uma Lista. Depois percorrer a lista e Serializar os objetos em disco/ssd. 
Serialize usando a Serialização de objetos da biblioteca Jackson. Ver também: XML Serialization and 
Deserialization with Jackson | Baeldung. */


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SerializaXML {
    public static void main(String[] args) throws Exception {
        //criando objetos
        Biblioteca biblioteca = new Biblioteca(1, "Biblioteca 1", "Rua Roxeda, 157", "123456789");
        Livro livro = new Livro("O Homem da Motinha", "Ze DaManga", "1234567890123", "Editora 1", 1954);
        Leitor leitor = new Leitor("João Silva", "Rua B, 171", "987654321", 1);
        Emprestimo emprestimo = new Emprestimo(new Date(), new Date(), livro, leitor);

        //adicionando objetos a uma lista
        List<Object> lista = new ArrayList<>();
        lista.add(biblioteca);
        lista.add(livro);
        lista.add(leitor);
        lista.add(emprestimo);

        try {
            XmlMapper xm = new XmlMapper();
            xm.writeValue(new File("objetos.xml"), lista);
            System.out.println("Objetos serializados foram salvos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

