package ufc.quixada.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import ufc.quixada.dao.LeitorDAO;
import ufc.quixada.entity.Leitor;

@Repository
public interface LeitorMongo extends LeitorDAO, MongoRepository<Leitor, String> {
    public Leitor findFirstByNome(String nome);

    @Query("{ 'contato' : { $regex: ?0 } }")
    public List<Leitor> findByContatoIniciandoCom(String titulo);

    public List<Leitor> findByNome(String nome);

    @Query("{ 'reservas.livro.titulo' : ?0 }")
    List<String> findLeitoresByTituloLivro(String titulo);
}