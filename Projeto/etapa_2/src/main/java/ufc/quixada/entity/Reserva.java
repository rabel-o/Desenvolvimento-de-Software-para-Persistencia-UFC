package ufc.quixada.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private Integer reservaId;

    private String dataReserva;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    @NonNull private Livro livro;

    @ManyToOne
    @JoinColumn(name = "leitor_id")
    @NonNull private Leitor leitor;

    @Override
    public String toString() {
        return "ReservaId: " + reservaId + ", " +
            "Data: " + dataReserva + ", " +
            "Livro: " + livro + ", " +
            "Leitor: " + leitor;
    }
}
