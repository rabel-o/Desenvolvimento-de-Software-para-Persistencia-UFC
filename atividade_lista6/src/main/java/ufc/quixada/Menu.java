package ufc.quixada;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoJDBCDAO produtoDAO = new ProdutoJDBCDAO();

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Inserir Produto");
            System.out.println("2 - Excluir Produto");
            System.out.println("3 - Consultar Produto por ID");
            System.out.println("4 - Consultar Produto por Código");
            System.out.println("5 - Consultar Produto por Descrição");
            System.out.println("6 - Consultar Produtos por Preço");
            System.out.println("7 - Consultar Produtos por Data de Entrada");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    // Inserir Produto
                    System.out.println("Digite o código do produto: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.println("Digite a descrição do produto: ");
                    String descricao = scanner.nextLine();
                    System.out.println("Digite o preço do produto: ");
                    float preco = scanner.nextFloat();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.println("Digite a quantidade em estoque: ");
                    int qtdEstoque = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.println("Digite a data de entrada (YYYY-MM-DD): ");
                    String data = scanner.nextLine();

                    Produto novoProduto = new Produto(0, codigo, descricao, preco, qtdEstoque, data);
                    produtoDAO.save(novoProduto);
                    System.out.println("Produto inserido com sucesso!");
                    break;
                case 2:
                    // Excluir Produto
                    System.out.println("Digite o ID do produto que deseja excluir: ");
                    int idExcluir = scanner.nextInt();
                    produtoDAO.delete(idExcluir);
                    System.out.println("Produto excluído com sucesso!");
                    break;
                case 3:
                    // Consultar Produto por ID
                    System.out.println("Digite o ID do produto que deseja consultar: ");
                    int idConsultar = scanner.nextInt();
                    Produto produtoPorId = produtoDAO.findById(idConsultar);
                    System.out.println(produtoPorId);
                    break;
                case 4:
                    // Consultar Produto por Código
                    System.out.println("Digite o código do produto que deseja consultar: ");
                    String codigoConsultar = scanner.nextLine();
                    Produto produtoPorCodigo = produtoDAO.findByCodigo(codigoConsultar);
                    System.out.println(produtoPorCodigo);
                    break;
                case 5:
                    // Consultar Produto por Descrição
                    System.out.println("Digite a parte da descrição que deseja consultar: ");
                    String descricaoConsulta = scanner.nextLine();
                    Produto produtoPorDescricao = produtoDAO.findByDescricao(descricaoConsulta);
                    System.out.println(produtoPorDescricao);
                    break;
                case 6:
                    // Consultar Produtos por Preço
                    System.out.println("Digite o preço máximo para consulta: ");
                    double precoMaximo = scanner.nextDouble();
                    List<Produto> produtosPorPreco = produtoDAO.findByPrecoMenorOuIgual(precoMaximo);
                    for (Produto produto : produtosPorPreco) {
                        System.out.println(produto);
                    }
                    break;
                case 7:
                    // Consultar Produtos por Data de Entrada
                    System.out.println("Digite a data inicial (YYYY-MM-DD): ");
                    String dataInicial = scanner.nextLine();
                    System.out.println("Digite a data final (YYYY-MM-DD): ");
                    String dataFinal = scanner.nextLine();
                    List<Produto> produtosPorData = produtoDAO.findByDatasEntrada(dataInicial, dataFinal);
                    for (Produto produto : produtosPorData) {
                        System.out.println(produto);
                    }
                    break;
                case 0:
                    System.out.println("Saindo da aplicação.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
