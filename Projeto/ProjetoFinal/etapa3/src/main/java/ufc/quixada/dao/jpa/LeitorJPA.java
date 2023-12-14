package ufc.quixada.dao.jpa;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import ufc.quixada.dao.LeitorDAO;
import ufc.quixada.entity.Leitor;

@Repository
@Primary
public interface LeitorJPA extends LeitorDAO, JpaRepository<Leitor, String> {
    public Leitor findFirstByNome(String nome); //usado

    // Native Query
    @Query(value = "SELECT * FROM leitor WHERE contato LIKE :contato%", nativeQuery = true)
    public List<Leitor> findByContatoIniciandoCom(String contato);

    // JPQL
    @Query("SELECT l FROM Leitor l WHERE l.nome = :nome")
    public List<Leitor> findByNome(String nome); //usado

    @Query("SELECT l.nome FROM Leitor l JOIN l.reservas r WHERE r.livro.titulo = :titulo")
    public List<String> findLeitoresByTituloLivro(String titulo); //usado
}
