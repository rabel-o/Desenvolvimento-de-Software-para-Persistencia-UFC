package fc.quixada;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ProdutoSpringDAO {
    
    public void save(Produto entity);
    
    public void delete(int id);
    
    public void update(Produto entity);
    
    public Produto findById(int id);
    
    public Produto findByCodigo(String codigo);
    
    public List<Produto> findByDescricao(String descricao);
    
    public List<Produto> findByPreco(double preco);
    
    public List<Produto> findByData(Date dataInicial, Date dataFinal);

    public List<Produto> findAll();
}
