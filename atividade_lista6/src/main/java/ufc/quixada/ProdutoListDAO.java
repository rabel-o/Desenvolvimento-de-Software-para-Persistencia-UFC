package ufc.quixada;

import java.util.ArrayList;
import java.util.List;

public class ProdutoListDAO implements ProdutoDAO {
    private List<Produto> produtos;

    public ProdutoListDAO() {
        this.produtos = new ArrayList<Produto>();
    }

    @Override
    public void save(Produto produto) {
        if (produto.getId() == 0) {
            produto.setId(produtos.size() + 1); // Gere um ID automático
            produtos.add(produto);
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getId() == produto.getId()) {
                    produtos.set(i, produto);
                    break;
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        Produto produtoParaRemover = null;
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtoParaRemover = produto;
                break;
            }
        }
        if (produtoParaRemover != null) {
            produtos.remove(produtoParaRemover);
        }
    }

    @Override
    public Produto findById(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public Produto findByCodigo(String codigo) {
        for (Produto produto : produtos) {
            if (String.valueOf(produto.getCodigo()).equals(codigo)) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public Produto findByDescricao(String descricao) {
        for (Produto produto : produtos) {
            if (produto.getDescricao().contains(descricao)) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public List<Produto> findByPrecoMenorOuIgual(double preco) {
        List<Produto> produtosEncontrados = new ArrayList<Produto>();
        for (Produto produto : produtos) {
            if (produto.getPreco() <= preco) {
                produtosEncontrados.add(produto);
            }
        }
        return produtosEncontrados;
    }

    @Override
    public List<Produto> findByDatasEntrada(String dataInicial, String dataFinal) {
        List<Produto> produtosEncontrados = new ArrayList<Produto>();
        for (Produto produto : produtos) {
            if (produto.getData().compareTo(dataInicial) >= 0 && produto.getData().compareTo(dataFinal) <= 0) {
                produtosEncontrados.add(produto);
            }
        }
        return produtosEncontrados;
    }

    @Override
    public List<Produto> findAll() {
        return produtos;
    }
}
