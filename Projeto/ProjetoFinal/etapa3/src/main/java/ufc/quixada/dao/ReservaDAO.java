package ufc.quixada.dao;

import java.util.List;
import java.util.Optional;

import ufc.quixada.entity.Livro;
import ufc.quixada.entity.Reserva;

public interface ReservaDAO {
    
    List<Reserva> findByLivro(Livro livro);

    public List<Reserva> findReservasByLeitorNome(String nome); //usado

    public List<Reserva> findByLivro2(Livro livro);

    public List<Reserva> findReservasByLivroAutor(String autor); //usado
    
    public List<Reserva> findByLivroTituloContendo(String titulo); //usado

    public List<Reserva> findByIdLeitor(String id); //usado

     public void save(Reserva reserva);

    public void deleteById(String id);

    public Optional<Reserva> findById(String id);

    public List<Reserva> findAll();


}
