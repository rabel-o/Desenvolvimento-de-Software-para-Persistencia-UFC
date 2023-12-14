/*3. Crie uma classe java de nome DesserializaJava para ler / desserializar os objetos Serializados na 
Questão 2 e exibi-los.*/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class DesserializaJava {
    public static void main(String[] args) {
        List<Object> lista = null;

        //desserializando objetos
        try {
            FileInputStream fis = new FileInputStream("bill.xml");
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Classe não encontrada");
            c.printStackTrace();
            return;
        }

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
    }
}
