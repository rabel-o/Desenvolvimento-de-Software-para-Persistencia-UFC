package ufc.quixada;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class inserirEntidade {
    private String caminhoArquivo = "C:/Users/conta/Desktop/Atividades_Faculdade/PERSISTENCIA/dspersist/Livros.csv";

        public inserirEntidade(String caminhoArquivo) {
            this.caminhoArquivo = caminhoArquivo;
        }

        public void inserirLivros() {
            Scanner sc = new Scanner(System.in);

            System.out.println("Digite o titulo do livro:");
            String titulo = "";
            if(sc.hasNextLine()) {
                titulo = sc.nextLine();
            } else {
                System.out.println("Nenhum elemento digitado");
            }

            System.out.println("Digite o autor do livro:");
            String autor = "";
            if(sc.hasNextLine()) {
                autor = sc.nextLine();
            } else {
                System.out.println("Nenhum elemento digitado");
            }

            System.out.println("Digite o ISBN do livro:");
            String isbn = "";
            if(sc.hasNextLine()) {
                isbn = sc.nextLine();
            } else {
                System.out.println("Nenhum elemento digitado");
            }

            System.out.println("Digite a editora do livro:");
            String editora = "";
            if(sc.hasNextLine()) {
                editora = sc.nextLine();
            } else {
                System.out.println("Nenhum elemento digitado");
            }

            System.out.println("Digite o ano de publicação do livro:");
            String anoPublicacao = "";
            if(sc.hasNextLine()) {
                anoPublicacao = sc.nextLine();
            } else {
                System.out.println("Nenhum elemento digitado");
            }

            Livro livro = new Livro(titulo, autor, isbn, editora, anoPublicacao);

            try {
                File arquivo = new File(caminhoArquivo);
                if(!arquivo.exists()) {
                    arquivo.createNewFile();
                }

                FileWriter escreveCSV = new FileWriter(arquivo, true);
                escreveCSV.append(livro.getTitulo());
                escreveCSV.append(",");
                escreveCSV.append(livro.getAutor());
                escreveCSV.append(",");
                escreveCSV.append(livro.getIsbn());
                escreveCSV.append(",");
                escreveCSV.append(livro.getEditora());
                escreveCSV.append(",");
                escreveCSV.append(String.valueOf(livro.getAnoPublicacao()));
                escreveCSV.append("\n");

                escreveCSV.flush();
                escreveCSV.close();
                sc.close();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }
