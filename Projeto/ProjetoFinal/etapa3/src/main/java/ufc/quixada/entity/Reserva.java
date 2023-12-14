package ufc.quixada.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Document(collection = "reservas")
@Table(name = "reservas")
@NoArgsConstructor
@AllArgsConstructor
@Data

@NamedQueries({
    @NamedQuery(name = "reservaPorId", query = "select r from Reserva r where r.id = :id"),
    @NamedQuery(name = "findReservasByLeitorNome", query = "select r from Reserva r where r.leitor.nome = :nome"),
    @NamedQuery(name = "findByLivro2", query = "select r from Reserva r where r.livro.id = :id")

})

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reserva_id")
    private String id;

    private String dataReserva;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    @NonNull private Livro livro;

    @ManyToOne
    @JoinColumn(name = "leitor_id")
    @NonNull private Leitor leitor;

    @Override
    public String toString() {
        return "ReservaId: " + id + ", " +
            "Data: " + dataReserva + ", " +
            "Livro: " + livro + ", " +
            "Leitor: " + leitor;
    }
}
