package ufc.quixada.dao.jpa;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import ufc.quixada.dao.ReservaDAO;
import ufc.quixada.entity.Livro;
import ufc.quixada.entity.Reserva;

@Repository
@Primary
public interface ReservaJPA extends ReservaDAO, JpaRepository<Reserva, String> {
    // Consulta baseada no nome do método
    List<Reserva> findByLivro(Livro livro);


    // Named Query
    public List<Reserva> findReservasByLeitorNome(String nome); //usado

    @Query(name = "findByLivro2")
    public List<Reserva> findByLivro2(Livro livro);


    // Native Query
    @Query(value = "SELECT r.* FROM reservas r INNER JOIN livros l ON r.livro_id = l.id_livro WHERE l.autor LIKE :autor", nativeQuery = true)
    public List<Reserva> findReservasByLivroAutor(String autor); //usado
    

    // JPQL
    @Query("SELECT r FROM Reserva r WHERE r.livro.titulo LIKE %:titulo%")
    public List<Reserva> findByLivroTituloContendo(String titulo); //usado

    @Query("SELECT r FROM Reserva r WHERE r.leitor.id = :id")
    public List<Reserva> findByIdLeitor(String id); //usado

}
