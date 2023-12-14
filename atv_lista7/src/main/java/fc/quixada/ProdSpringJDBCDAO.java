package fc.quixada;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Primary
@Slf4j
public class ProdSpringJDBCDAO implements ProdutoSpringDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Produto entity) {
        String insert = "insert into produto (nome, codigo, preco, descricao, qtdEstoque, data) values (:codigo, :preco, :descricao, :qtdEstoque, :data)";
        String update = "update produtos set codigo = :codigo, nome = :nome, preco = :preco, descricao = :descricao, qtdEstoque = :qtdEstoque where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("nome", entity.getNome())
                .addValue("codigo", entity.getCodigo())
                .addValue("preco", entity.getPreco())
                .addValue("descricao", entity.getDescricao())
                .addValue("qtdEstoque", entity.getQtdEstoque())
                .addValue("data", entity.getData());
        if (entity.getId() > 0) {
            namedParameterJdbcTemplate.update(insert, params);
        } else {
            params.addValue("id", entity.getId());
            namedParameterJdbcTemplate.update(update, params);
        }
    }

    public void delete(int id) {
        String sql = "delete from produtos where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public Produto findById(int id) {
        String sql = "select * from produto where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtdEstoque(rs.getInt("qtdEstoque"));
                produto.setData(rs.getDate("data"));
                return produto;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Produto> findAll() {
        String sql = "select * from produto";
        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQtdEstoque(rs.getInt("qtdEstoque"));
            produto.setData(rs.getDate("data"));
            return produto;
        });
    }

    public List<Produto> findByData(Date dataInicio, Date dataFim) {
        String sql = "select * from produto where data between :dataInicio and :dataFim";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("dataInicio", dataInicio)
                .addValue("dataFim", dataFim);
        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQtdEstoque(rs.getInt("qtdEstoque"));
            produto.setData(rs.getDate("data"));
            return produto;
        });
    }

    public Produto findByCodigo(String codigo) {
    String sql = "select * from produto where codigo = :codigo";
    MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("codigo", codigo);
    try {
        return namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQtdEstoque(rs.getInt("qtdEstoque"));
            produto.setData(rs.getDate("data"));
            return produto;
        });
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
}

    public void update(Produto entity) {
        String sql = "update produto set codigo = :codigo, preco = :preco, descricao = :descricao, qtdEstoque = :qtdEstoque, data = :data where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("codigo", entity.getCodigo())
                .addValue("preco", entity.getPreco())
                .addValue("descricao", entity.getDescricao())
                .addValue("qtdEstoque", entity.getQtdEstoque())
                .addValue("data", entity.getData());
        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<Produto> findByPreco(double preco) {
        String sql = "select * from produto where preco = :preco";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("preco", preco);
        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQtdEstoque(rs.getInt("qtdEstoque"));
            produto.setData(rs.getDate("data"));
            return produto;
        });
    }


    public List<Produto> findByDescricao(String descricao) {
        String sql = "select * from produto where descricao like :descricao";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("descricao", "%" + descricao + "%");
        return namedParameterJdbcTemplate.query(sql, params, (rs, linNum) -> {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQtdEstoque(rs.getInt("qtdEstoque"));
            produto.setData(rs.getDate("data"));
            return produto;
        });
    }

        public List<Produto> findPreco(double maxPreco) {
        String sql = "SELECT * FROM produto WHERE preco <= :maxPrice";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("maxPreco", maxPreco);
        return namedParameterJdbcTemplate.query(sql, params, (rs, linNum) -> {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQtdEstoque(rs.getInt("qtdEstoque"));
            produto.setData(rs.getDate("data"));
            return produto;
        });
    }

    public List<Produto> findByDataUltimaEntradaBetween(Date dataInicial, Date dataFinal) {
        String sql = "SELECT * FROM produto WHERE data BETWEEN :dataInicial AND :dataFinal";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("dataInicial", dataInicial)
                .addValue("dataFinal", dataFinal);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return namedParameterJdbcTemplate.query(sql, params, (rs, linNum) -> {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQtdEstoque(rs.getInt("qtdEstoque"));
            produto.setData(rs.getDate("data"));
            return produto;
        });
    }

    private Produto map(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getInt("id"));
        produto.setCodigo(rs.getString("codigo"));
        produto.setPreco(rs.getDouble("preco"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setQtdEstoque(rs.getInt("qtdEstoque"));
        produto.setData(rs.getDate("data"));
        return produto;
    }

}

