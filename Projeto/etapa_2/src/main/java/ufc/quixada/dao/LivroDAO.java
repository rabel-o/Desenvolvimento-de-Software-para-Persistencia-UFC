package ufc.quixada.dao;

import java.util.List;

import ufc.quixada.entity.Leitor;
import ufc.quixada.entity.Livro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LivroDAO extends JpaRepository<Livro, Integer> {
    // Consulta baseada no nome do método
    public List<Livro> findByAutor(String autor); //usado

    public Livro findFirstByTitulo(String titulo);

    // Named Query
    @Query(name = "Livro.findByEditora")
    public List<Livro> findByEditora(String editora); //usado

    // JPQL
    @Query("SELECT l FROM Livro l WHERE l.titulo LIKE %:titulo%")
    public List<Livro> findByTituloContendo(@Param("titulo"  ) String titulo);

    // JPQL
    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo")
    public List<Livro> findByTitulo(@Param("titulo") String titulo); //usado

    @Query("SELECT l FROM Livro l JOIN l.reservas r WHERE r.leitor.nome = :nome") //usado
    public List<Livro> findLivrosByLeitor(@Param("nome") String nome);

}
