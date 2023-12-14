package ufc.quixada;

import java.util.List;

public interface ProdutoDAO {
    void save(Produto produto);
    void delete(int id);
    Produto findById(int id);
    Produto findByCodigo(String codigo);
    Produto findByDescricao(String descricao);
    List<Produto> findByPrecoMenorOuIgual(double preco);
    List<Produto> findByDatasEntrada(String dataInicial, String dataFinal);
    List<Produto> findAll();
}
