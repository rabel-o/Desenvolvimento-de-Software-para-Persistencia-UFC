package fc.quixada;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {

    @Autowired
    private ProdutoSpringDAO baseProdutos;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        String menu = "Escolha uma opção:\n1 - Inserir\n2 - Atualizar por ID\n3 - Remover por ID\n4 - Exibir por ID\n5 - Exibir todos\n6 - Exibir todos que contêm determinado nome\n7 - Sair";
        char opcao = '0';
        do {
            try {
                Produto prod;
                int id;
                opcao = JOptionPane.showInputDialog(menu).charAt(0);
                switch (opcao) {
                    case '1':     // Inserir
                        prod = new Produto();
                        obterProduto(prod);
                        baseProdutos.save(prod);
                        break;
                    case '2':     // Atualizar por ID
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto a ser alterado"));
                        prod = baseProdutos.findById(id);
                        if (prod != null) {
                            obterProduto(prod);
                            baseProdutos.save(prod);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível atualizar, pois o produto não foi encontrado.");
                        }
                        break;
                    case '3':     // Remover por ID
                        id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
                        prod = baseProdutos.findById(id);
                        if (prod != null) {
                            baseProdutos.delete(id);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o produto não foi encontrado.");
                        }
                        break;
                    case '4':     // Exibir por ID
                        id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
                        prod = baseProdutos.findById(id);
                        listaProduto(prod);
                        break;
                    case '5':     // Exibir todos
                        listaProdutos(baseProdutos.findAll());
                        break;
                    case '6':     // Exibir todos que contêm determinado nome
                        String nome = JOptionPane.showInputDialog("Nome");
                        listaProdutos(baseProdutos.findByDescricao(nome));
                        break;
                    case '7':     // Sair
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } catch (NumberFormatException e) {
                log.error("Erro: {}", e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Entrada inválida: " + e.getMessage());

            } catch (Exception e) {
                log.error("Erro: {}", e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while(opcao != '7');
    }
    
    public static void obterProduto(Produto prod) {
        String nome = null;
        String codigo = null;
        double preco = 0;
        int qtdEstoque = 0;
        String descricao = null;
        Date data = new Date(System.currentTimeMillis());
    
        boolean inputValid = false;
        while (!inputValid) {
            try {
                nome = JOptionPane.showInputDialog("Nome", prod.getNome());
                if (nome == null || nome.trim().isEmpty()) {
                    throw new IllegalArgumentException("Nome inválido");
                }
    
                codigo = JOptionPane.showInputDialog("Código", prod.getCodigo());
                if (codigo == null || codigo.trim().isEmpty()) {
                    throw new IllegalArgumentException("Código inválido");
                }
    
                preco = Double.parseDouble(JOptionPane.showInputDialog("Preço", String.valueOf(prod.getPreco())));
                if (preco <= 0) {
                    throw new IllegalArgumentException("Preço inválido");
                }
    
                qtdEstoque = Integer.parseInt(JOptionPane.showInputDialog("Quantidade em estoque", String.valueOf(prod.getQtdEstoque())));
                if (qtdEstoque < 0) {
                    throw new IllegalArgumentException("Quantidade em estoque inválida");
                }
    
                descricao = JOptionPane.showInputDialog("Descrição", prod.getDescricao());
                if (descricao == null || descricao.trim().isEmpty()) {
                    throw new IllegalArgumentException("Descrição inválida");
                }
    
                inputValid = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (Exception e) {
                log.error("Erro: {}", e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    
        prod.setNome(nome);
        prod.setCodigo(codigo);
        prod.setPreco(preco);
        prod.setQtdEstoque(qtdEstoque);
        prod.setDescricao(descricao);
        prod.setData(data);
    }

    public static void listaProdutos(List<Produto> produtos) {
        StringBuilder listagem = new StringBuilder();
        for(Produto prod : produtos) {
            listagem.append(prod).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum produto encontrado" : listagem);
    }

    public static void listaProduto(Produto prod) {
        JOptionPane.showMessageDialog(null, prod == null ? "Nenhum produto encontrado" : prod);
    }
}