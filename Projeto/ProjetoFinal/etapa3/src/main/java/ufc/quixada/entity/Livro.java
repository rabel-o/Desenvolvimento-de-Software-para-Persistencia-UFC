package ufc.quixada.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "livros")
@Table(name = "livros")
@NoArgsConstructor
@AllArgsConstructor
@Data

@NamedQueries({
    @NamedQuery (name = "findByAutor", query = "select l from Livro l where l.autor = :autor"),
    @NamedQuery (name = "findByEditora", query = "select l from Livro l where l.editora = :editora")
})

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String titulo;

    private String autor;
    private String editora;
    private String anoPublicacao;
    private String isbn;
    
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @ManyToOne
    @JoinColumn(name = "leitor_id")
    @NonNull private Leitor leitor;

    @Override
    public String toString() {
        return "LivroId: " + id + ", " +
            "Título: " + titulo + ", " +
            "Autor: " + autor + ", " +
            "Editora: " + editora;
    }

}
