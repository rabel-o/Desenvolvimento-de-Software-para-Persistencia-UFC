package ufc.quixada.dao;

import java.util.List;
import java.util.Optional;

import ufc.quixada.entity.Livro;

public interface LivroDAO {
    
    public List<Livro> findByAutor(String autor); //usado

    public Livro findFirstByTitulo(String titulo);

    public List<Livro> findByEditora(String editora); //usado

    public List<Livro> findByTituloContendo(String titulo);

    public List<Livro> findByTitulo(String titulo); //usado

    public List<Livro> findLivrosByLeitor(String nome);

    public void save(Livro livro);

    public void deleteById(String id);

    public Optional<Livro> findById(String id);

    public List<Livro> findAll();

}
