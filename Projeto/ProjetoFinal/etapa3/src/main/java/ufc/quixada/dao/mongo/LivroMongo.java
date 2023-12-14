package ufc.quixada.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import ufc.quixada.dao.LivroDAO;
import ufc.quixada.entity.Livro;

@Repository
public interface LivroMongo extends LivroDAO, MongoRepository<Livro, String> {
    public List<Livro> findByAutor(String autor);

    public Livro findFirstByTitulo(String titulo);

    @Query("{ 'editora' : ?0 }")
    public List<Livro> findByEditora(String editora);

    @Query("{ 'titulo' : { $regex: ?0 } }")
    public List<Livro> findByTituloContendo(String titulo);

    public List<Livro> findByTitulo(String titulo);

    @Query("{ 'reservas.leitor.nome' : ?0 }")
    public List<Livro> findLivrosByLeitor(String nome);
}