/*2. Crie uma classe Java de nome SerializaJava para instanciar objetos da classe definida na Questão 1 e 
adicionar esses objetos em uma Lista. Depois percorrer a lista e Serializar os objetos em disco/ssd. Serialize
usando a Serialização de objetos da própria API Java. Ver também: Introduction to Java Serialization | Baeldung.*/

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SerializaJava {
    public static void main(String[] args) {
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

        //serializando objetos
        try {
            FileOutputStream fos = new FileOutputStream("bill.xml");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);
            oos.close();
            fos.close();
            System.out.println("Objetos serializados e salvos");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
