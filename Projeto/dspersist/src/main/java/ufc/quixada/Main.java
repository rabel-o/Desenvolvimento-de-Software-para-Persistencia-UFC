package ufc.quixada;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String caminhoArquivo = "C:/Users/conta/Desktop/Atividades_Faculdade/PERSISTENCIA/dspersist/Livros.csv";

        System.out.println("Escolha uma funcionalidade:");
        System.out.println("1. Calcular o hash do arquivo");
        System.out.println("2. Compactar o arquivo");
        System.out.println("3. Contar entidades no arquivo");
        System.out.println("4. Converter dados para JSON e XML");
        System.out.println("5. Inserir nova entidade no arquivo");
        System.out.println("6. Sair");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                calculaHash calculadorDeHash = new calculaHash(caminhoArquivo);
                String hash = calculadorDeHash.calcularHash();
                System.out.println("Hash SHA-256 do arquivo: " + hash);
                break;
            case 2:
                compactaArquivo compactador = new compactaArquivo(caminhoArquivo);
                compactador.compactarArquivo();
                System.out.println("Arquivo compactado com sucesso.");
                break;
            case 3:
                contaEntidades contador = new contaEntidades(caminhoArquivo);
                int numEntidades = contador.contarEntidades();
                System.out.println("Número de entidades no arquivo: " + numEntidades);
                break;
            case 4:
                converteDados conversor = new converteDados(caminhoArquivo);
                conversor.converteJSONeXML();
                System.out.println("Dados convertidos para JSON e XML.");
                break;
            case 5:
                inserirEntidade inseridor = new inserirEntidade(caminhoArquivo);
                inseridor.inserirLivros();
                System.out.println("Nova entidade inserida no arquivo.");
                break;
            case 6:
                System.out.println("Saindo do programa.");
                break;
            default:
                System.out.println("Opção inválida. Escolha novamente.");
                break;
        }

        scanner.close();
    }
}
