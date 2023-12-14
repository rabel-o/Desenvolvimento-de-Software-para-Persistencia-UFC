/*1. Crie uma classe Java de entidade. Escolha uma entidade que você já propôs para seu Trabalho Prático. Exemplo: classe Filme 
(id, titulo, sinopse, diretor). A classe deve implementar a interface java.io.Serializable. Crie também uma classe que possua uma lista de
objetos da entidade escolhida. Exemplo: classe Filmes, possuindo uma lista de Filme (List<Filme> filmes). Veja, nos slides sobre XML, os
exemplos das classes Pessoa e Pessoas.*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//entidade Biblioteca
public class Biblioteca implements Serializable {
    private int Id;
    private String nome;
    private String endereco;
    private String telefone;

    //construtor, getters e setters

    public Biblioteca(int Id, String nome, String endereco, String telefone) {
        this.telefone = telefone;
        this.Id = Id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getId() {
        return Id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}

//entidade Livro
class Livro implements Serializable {
    private String titulo;
    private String autor;
    private String isbn;
    private String editora;
    private int anoPublicacao;

    //construtor, getters e setters

    public Livro(String titulo, String autor, String isbn, String editora, int anoPublicacao) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.isbn = isbn;
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEditora() {
        return editora;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}

//entidade Leitor
class Leitor implements Serializable {
    private String nome;
    private int idLeitor;
    private String endereco;
    private String contato;

    //construtor, getters e setters

    public Leitor(String nome, String endereco, String contato, int idLeitor) {
        this.nome = nome;
        this.contato = contato;
        this.endereco = endereco;
        this.idLeitor = idLeitor;
    }

    public String getNome() {
        return nome;
    }

    public String getContato() {
        return contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getIdLeitor() {
        return idLeitor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setIdLeitor(int idLeitor) {
        this.idLeitor = idLeitor;
    }
}

//entidade Empréstimo
class Emprestimo implements Serializable {
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Livro livro;
    private Leitor leitor;

    //construtor, getters e setters

    public Emprestimo(Date dataEmprestimo, Date dataDevolucao, Livro livro, Leitor leitor) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.leitor = leitor;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }
}

//entidade Reserva
class Reserva implements Serializable {
    private Date dataReserva;
    private Livro livro;
    private Leitor leitor;

    //construtor, getters e setters
    public Reserva(Date dataReserva, Livro livro, Leitor leitor) {
        this.dataReserva = dataReserva;
        this.livro = livro;
        this.leitor = leitor;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public Livro getLivro() {
        return livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }
}

//classe que cadastra livros
class CadastroLivros {
    private List<Livro> livros;

    public CadastroLivros() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        this.livros.remove(livro);
    }

    public void atualizarLivro(Livro livroAntigo, Livro livroNovo) {
        int index = this.livros.indexOf(livroAntigo);
        if (index != -1) {
            this.livros.set(index, livroNovo);
        }
    }
}

//classe que cadastra leitores
class CadastroLeitores {
    private List<Leitor> leitores;

    public CadastroLeitores() {
        this.leitores = new ArrayList<>();
    }

    public void adicionarLeitor(Leitor leitor) {
        this.leitores.add(leitor);
    }

    public void removerLeitor(Leitor leitor) {
        this.leitores.remove(leitor);
    }

    public void atualizarLeitor(Leitor leitorAntigo, Leitor leitorNovo) {
        int index = this.leitores.indexOf(leitorAntigo);
        if (index != -1) {
            this.leitores.set(index, leitorNovo);
        }
    }
}

//classe que controla empréstimos
class ControleEmprestimos {
    private List<Emprestimo> emprestimos;

    public ControleEmprestimos() {
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.remove(emprestimo);
    }

    public void atualizarEmprestimo(Emprestimo emprestimoAntigo, Emprestimo emprestimoNovo) {
        int index = this.emprestimos.indexOf(emprestimoAntigo);
        if (index != -1) {
            this.emprestimos.set(index, emprestimoNovo);
        }
    }
}

//classe que controla reservas
class ControleReservas {
    private List<Reserva> reservas;

    public ControleReservas() {
        this.reservas = new ArrayList<>();
    }

     public void adicionarReserva(Reserva reserva) {
         this.reservas.add(reserva);
     }

     public void removerReserva(Reserva reserva) {
         this.reservas.remove(reserva);
     }

     public void atualizarReserva(Reserva reservaAntiga, Reserva reservaNova) {
         int index = this.reservas.indexOf(reservaAntiga);
         if (index != -1) {
             this.reservas.set(index, reservaNova);
         }
     }
}
