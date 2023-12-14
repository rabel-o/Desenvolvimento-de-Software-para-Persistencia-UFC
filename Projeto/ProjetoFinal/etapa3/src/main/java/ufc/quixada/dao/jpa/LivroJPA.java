package ufc.quixada.dao.jpa;

import java.util.List;

import ufc.quixada.dao.LivroDAO;
import ufc.quixada.entity.Livro;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Primary
public interface LivroJPA extends LivroDAO, JpaRepository<Livro, String> {
    // Consulta baseada no nome do método
    public List<Livro> findByAutor(String autor); //usado

    public Livro findFirstByTitulo(String titulo);

    // Named Query
    @Query(name = "Livro.findByEditora")
    public List<Livro> findByEditora(String editora); //usado

    // JPQL
    @Query("SELECT l FROM Livro l WHERE l.titulo LIKE %:titulo%")
    public List<Livro> findByTituloContendo(String titulo);

    // JPQL
    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo")
    public List<Livro> findByTitulo(String titulo); //usado

    @Query("SELECT l FROM Livro l JOIN l.reservas r WHERE r.leitor.nome = :nome") //usado
    public List<Livro> findLivrosByLeitor(String nome);

}
