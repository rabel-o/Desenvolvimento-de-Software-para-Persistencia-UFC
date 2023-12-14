package ufc.quixada.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ufc.quixada.entity.Leitor;

@Repository
public interface LeitorDAO extends JpaRepository<Leitor, Integer>{
    public Leitor findFirstByNome(String nome); //usado

    // Named Query
    @Query(name = "Leitor.findByIdLeitor")
    public Leitor findByIdLeitor(Integer idLeitor); //usado

    // Native Query
    @Query(value = "SELECT * FROM leitor WHERE contato LIKE :contato%", nativeQuery = true)
    public List<Leitor> findByContatoIniciandoCom(@Param("contato") String contato);

    // JPQL
    @Query("SELECT l FROM Leitor l WHERE l.nome = :nome")
    public List<Leitor> findByNome(@Param("nome") String nome); //usado

    @Query("SELECT l.nome FROM Leitor l JOIN l.reservas r WHERE r.livro.titulo = :titulo")
    public List<String> findLeitoresByTituloLivro(@Param("titulo") String titulo); //usado
}
