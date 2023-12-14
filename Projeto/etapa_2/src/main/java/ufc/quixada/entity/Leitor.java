package ufc.quixada.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@NamedQueries({
		@NamedQuery(name = "leitorPorId", query = "select l from Leitor l where l.id = :id")
})

@Entity
@Table(name = "leitores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Leitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLeitor;

    @Column(unique = true, nullable = false)
    private String nome;

    private String endereco;
    private String contato;

    @OneToMany(mappedBy = "leitor")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "leitor")
    private List<Livro> livros;

    @Override
    public String toString() {
        return "LeitorId: " + idLeitor + ", " +
            "Nome: " + nome + ", " +
            "Endereço: " + endereco + ", " +
            "Contato: " + contato;
    }

}
