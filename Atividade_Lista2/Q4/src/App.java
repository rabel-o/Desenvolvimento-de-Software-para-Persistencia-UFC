import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder texto = new StringBuilder();

            System.out.println("Digite as linhas de texto. Use FIM para parar: ");

            while (true) {
                String linha = ler.readLine();
                if (linha.equalsIgnoreCase("FIM")) {
                    break;
                }
                texto.append(linha).append("\n");
            }

            System.out.print("Insira o nome do arquivo para salvar: ");
            String nomeArq = ler.readLine();

            if (!nomeArq.endsWith(".txt")) {
                nomeArq += ".txt";
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArq));
            writer.write(texto.toString());
            writer.close();

            System.out.println("Texto salvo com sucesso no arquivo: " + nomeArq);

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao manipular o arquivo: " + e.getMessage());
        }
    }
}
