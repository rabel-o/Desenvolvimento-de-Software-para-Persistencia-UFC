package ufc.quixada.dao;

import java.util.List;
import java.util.Optional;

import ufc.quixada.entity.Leitor;

public interface LeitorDAO {

    public Leitor findFirstByNome(String nome); //usado

    public List<Leitor> findByContatoIniciandoCom(String prefix);

    public List<Leitor> findByNome(String nome); //usado

    public List<String> findLeitoresByTituloLivro(String titulo); //usado

    public void save(Leitor leitor);

    public void deleteById(String id);

    public Optional<Leitor> findById(String id);

    public List<Leitor> findAll();

}
