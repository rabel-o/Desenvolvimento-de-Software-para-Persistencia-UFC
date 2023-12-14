/* 5. Crie uma classe java de nome DesserializaXML para ler / desserializar os objetos Serializados 
na Questão 4 e exibi-los. */


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DesserializaXML {
    public static void main(String[] args) throws Exception {
        //desserializando objetos em XML
        try {
            XmlMapper xm = new XmlMapper();
            List<Object> lista = xm.readValue(new File("objetos.xml"), ArrayList.class);
            System.out.println("Objetos desserializados foram lidos de objetos.xml");

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
