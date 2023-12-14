/*1. Crie uma aplicação em Java que recebe via linha de comando: (1) o nome de um arquivo CSV;
(2) o delimitador usado para separar os campos do arquivo; (3) uma lista de nomes das colunas do arquivo CSV que serão processados.
Considere que o arquivo CSV (1) deva ter um cabeçalho com os nomes das colunas em sua primeira linha e que não tenha internamente
colunas com Strings contendo o mesmo caractere usado como delimitador (2). A aplicação deve exibir a soma e a média das colunas
selecionadas em (3), caso tenham dados numéricos. Se não tiverem dados numéricos, somente exibir que aquela coluna não é numérica.
Não usar bibliotecas externas para resolver esta questão (usar Java puro). Sugere-se navegar apenas uma única vez em cada linha do arquivo CSV.
Fazer a aplicação de modo que ela possa processar arquivos CSV extremamente grandes, mesmo que não caibam na memória
RAM. Dica: usar o método split da classe String para separar os valores das colunas em cada linha do arquivo CSV.*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Obtém os argumentos
        String arquivo = args[0];
        String delimitador = args[1];
        String[] colunas = Arrays.copyOfRange(args, 2, args.length);

        // Cria um mapa para armazenar as somas e as contagens das colunas selecionadas
        Map<String, Double> somas = new HashMap<>();
        Map<String, Integer> contagens = new HashMap<>();
        for (String coluna : colunas) {
            somas.put(coluna, 0.0);
            contagens.put(coluna, 0);
        }

        // Cria um buffer para ler o arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            // Lê a primeira linha do arquivo, que contém o cabeçalho
            String linha = br.readLine();
            if (linha == null) {
                System.out.println("Arquivo vazio");
                return;
            }

            // Separa os nomes das colunas pelo delimitador
            String[] nomes = linha.split(delimitador);

            // Cria um mapa para armazenar os índices das colunas selecionadas
            Map<String, Integer> indices = new HashMap<>();
            for (int i = 0; i < nomes.length; i++) {
                if (somas.containsKey(nomes[i])) {
                    indices.put(nomes[i], i);
                }
            }

            // Verifica se todas as colunas selecionadas foram encontradas no cabeçalho
            if (indices.size() != colunas.length) {
                System.out.println("Algumas colunas selecionadas não foram encontradas no arquivo");
                return;
            }

            // Lê as demais linhas do arquivo, que contêm os dados
            while ((linha = br.readLine()) != null) {
                // Separa os valores das colunas pelo delimitador
                String[] valores = linha.split(delimitador);

                // Para cada coluna selecionada, tenta converter o valor para um número e atualiza a soma e a contagem
                for (String coluna : colunas) {
                    int indice = indices.get(coluna);
                    try {
                        double valor = Double.parseDouble(valores[indice]);
                        somas.put(coluna, somas.get(coluna) + valor);
                        contagens.put(coluna, contagens.get(coluna) + 1);
                    } catch (NumberFormatException e) {
                        // Ignora valores que não são numéricos
                    }
                }
            }

            // Exibe a soma e a média das colunas selecionadas, ou informa que não são numéricas
            for (String coluna : colunas) {
                double soma = somas.get(coluna);
                int contagem = contagens.get(coluna);
                if (contagem > 0) {
                    double media = soma / contagem;
                    System.out.printf("Coluna %s: soma = %.2f, média = %.2f%n", coluna, soma, media);
                } else {
                    System.out.printf("Coluna %s: não é numérica%n", coluna);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}