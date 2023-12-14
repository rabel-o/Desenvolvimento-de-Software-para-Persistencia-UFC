package ufc.quixada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produto {
    private int id;
    private int codigo;
    private String descricao;
    private float preco;
    private int qtdEstoque;
    private String data;
}