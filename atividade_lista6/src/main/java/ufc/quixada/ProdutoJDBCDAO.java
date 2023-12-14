package ufc.quixada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoJDBCDAO implements ProdutoDAO {

    public ProdutoJDBCDAO() { }

    @Override
    public void save(Produto produto) {
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = "INSERT INTO produtos (codigo, descricao, preco, qtdEstoque, data) VALUES (?, ?, ?, ?, ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, produto.getCodigo());
                stmt.setString(2, produto.getDescricao());
                stmt.setFloat(3, produto.getPreco());
                stmt.setInt(4, produto.getQtdEstoque());
                stmt.setString(5, produto.getData());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = "DELETE FROM produtos WHERE id = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produto findById(int id) {
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = "SELECT * FROM produtos WHERE id = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getInt("codigo"), rs.getString("descricao"),
                            rs.getFloat("preco"), rs.getInt("qtdEstoque"), rs.getString("data"));
                    return produto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Produto findByCodigo(String codigo) {
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = "SELECT * FROM produtos WHERE codigo = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, codigo);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getInt("codigo"), rs.getString("descricao"),
                            rs.getFloat("preco"), rs.getInt("qtdEstoque"), rs.getString("data"));
                    return produto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Produto findByDescricao(String descricao) {
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = "SELECT * FROM produtos WHERE descricao LIKE ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, "%" + descricao + "%");
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getInt("codigo"), rs.getString("descricao"),
                            rs.getFloat("preco"), rs.getInt("qtdEstoque"), rs.getString("data"));
                    return produto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Produto> findByPrecoMenorOuIgual(double preco) {
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = "SELECT * FROM produtos WHERE preco <= ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDouble(1, preco);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getInt("codigo"), rs.getString("descricao"),
                            rs.getFloat("preco"), rs.getInt("qtdEstoque"), rs.getString("data"));
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public List<Produto> findByDatasEntrada(String dataInicial, String dataFinal) {
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = "SELECT * FROM produtos WHERE data BETWEEN ? AND ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, dataInicial);
                stmt.setString(2, dataFinal);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getInt("codigo"), rs.getString("descricao"),
                            rs.getFloat("preco"), rs.getInt("qtdEstoque"), rs.getString("data"));
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = ConectionFactory.getConnection()) {
            String sql = "SELECT * FROM produtos";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getInt("codigo"), rs.getString("descricao"),
                            rs.getFloat("preco"), rs.getInt("qtdEstoque"), rs.getString("data"));
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}
