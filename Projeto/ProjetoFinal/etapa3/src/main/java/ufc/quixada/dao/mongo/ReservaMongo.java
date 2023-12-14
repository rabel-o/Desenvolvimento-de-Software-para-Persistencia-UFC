package ufc.quixada.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import ufc.quixada.dao.ReservaDAO;
import ufc.quixada.entity.Livro;
import ufc.quixada.entity.Reserva;

@Repository
public interface ReservaMongo extends ReservaDAO, MongoRepository<Reserva, String> {
    public List<Reserva> findByLivro(Livro livro);

    @Query("{ 'leitor.nome' : ?0 }")
    public List<Reserva> findReservasByLeitorNome(String nome);

    @Query("{ 'livro' : ?0 }")
    public List<Reserva> findByLivro2(Livro livro);

    @Query("{ 'livro.autor' : { $regex: ?0 } }")
    public List<Reserva> findReservasByLivroAutor(String autor);

    @Query("{ 'livro.titulo' : { $regex: ?0 } }")
    public List<Reserva> findByLivroTituloContendo(String titulo);

    @Query("{ 'leitor.idLeitor' : ?0 }")
    public List<Reserva> findByIdLeitor(String id);
}