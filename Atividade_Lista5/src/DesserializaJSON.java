/* 7. Crie uma classe java de nome DesserializaJSON para ler / desserializar os objetos Serializados na 
Questão 6 e exibi-los. */


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DesserializaJSON {
    public static void main(String[] args) {
        //desserializando objetos em JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Object> lista = objectMapper.readValue(new File("objetos.json"), ArrayList.class);
            System.out.println("Objetos desserializados foram lidos de objetos.json");

            //exibindo objetos
            for (Object obj : lista) {
                if (obj instanceof Biblioteca) {
                    Biblioteca biblioteca = (Biblioteca) obj;
                    System.out.println("Biblioteca: " + biblioteca.getNome());
                } else if (obj instanceof Livro) {
                    Livro livro = (Livro) obj;
                    System.out.println("Livro: " + livro.getTitulo());
                } else if (obj instanceof Leitor) {
                    Leitor leitor = (Leitor) obj;
                    System.out.println("Leitor: " + leitor.getNome());
                } else if (obj instanceof Emprestimo) {
                    Emprestimo emprestimo = (Emprestimo) obj;
                    System.out.println("Empréstimo: " + emprestimo.getLeitor().getNome() + " - " + emprestimo.getLivro().getTitulo());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
