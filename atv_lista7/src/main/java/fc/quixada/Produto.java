package fc.quixada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Produto {
    private int id;
    private String codigo;
    private double preco;
    private String descricao;
    private int qtdEstoque;
    private Date data;
    private String nome;
}