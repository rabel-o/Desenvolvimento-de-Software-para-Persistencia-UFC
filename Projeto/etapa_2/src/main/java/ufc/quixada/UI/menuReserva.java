package ufc.quixada.UI;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ufc.quixada.dao.ReservaDAO;
import ufc.quixada.dao.LeitorDAO;
import ufc.quixada.dao.LivroDAO;
import ufc.quixada.entity.Reserva;
import ufc.quixada.entity.Livro;
import ufc.quixada.entity.Leitor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class menuReserva {
    @Autowired
    private ReservaDAO baseReservas;

    @Autowired
    private LivroDAO baseLivros;

    @Autowired
    private LeitorDAO baseLeitores;

    private Leitor leitor;

    public void obterReserva(Reserva reserva) {
        List<Livro> livros = baseLivros.findAll();
        Livro[] arrayLivros = livros.toArray(new Livro[0]);
        Livro livroEscolhido = (Livro) JOptionPane.showInputDialog(null, "Selecione um livro", "Livros", JOptionPane.PLAIN_MESSAGE, null, arrayLivros, reserva.getLivro());
        reserva.setLivro(livroEscolhido);
    
        String dataReserva = JOptionPane.showInputDialog("Data da Reserva", reserva.getDataReserva());
        reserva.setDataReserva(dataReserva);
    }

    public void listarReservas(List<Reserva> reservas) {
        StringBuilder listagem = new StringBuilder();
        for(Reserva r : reservas) {
            listagem.append(r.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma reserva encontrada" : listagem.toString());
    }

    public static void listaReserva(Reserva r) {
        JOptionPane.showMessageDialog(null, r == null ? "Nenhuma reserva encontrada" : r.toString());
    }

    public StringBuilder getStringReserva() {
        List<Reserva> reservas = baseReservas.findByIdLeitor(this.leitor.getIdLeitor());
        StringBuilder sb = new StringBuilder();
        for(Reserva r : reservas) {
            sb.append(r).append("\n");
        }
        return sb.length() == 0 ? new StringBuilder("Nenhuma reserva encontrada (id=").append(leitor.getIdLeitor()).append(")\n") : sb;
    }

    public void menu(Leitor leitor) {
        this.leitor = leitor;
        char opcao = '0';
        do {
            try {
                StringBuilder menu = new StringBuilder("Menu de reservas (id=)").append(leitor.getIdLeitor()).append(")\n")
                    .append(getStringReserva())
                    .append("1 - Cadastrar Reserva\n")
                    .append("2 - Listar Reservas por nome do leitor\n")
                    .append("3 - Atualizar Reserva\n")
                    .append("4 - Deletar Reserva\n")
                    .append(5 + " - Listar todas as reservas\n")
                    .append(6 + " - Listar reservas por titulo do livro\n")
                    .append(7 + " - Listar reservas por autor do livro\n")
                    .append("8 - Voltar\n");
                Reserva reserva;
                Integer id;
                String nome;
                String dataReserva;
                String titulo;
                String autor;
                opcao = JOptionPane.showInputDialog(menu).charAt(0);
                switch(opcao) {
                    case '1':
                    nome = JOptionPane.showInputDialog("Digite o nome do leitor");
                    List<Leitor> leitores = baseLeitores.findByNome(nome);
                    if (!leitores.isEmpty()) {
                        dataReserva = JOptionPane.showInputDialog("Digite a data da reserva");
                        leitor = leitores.get(0);
                        reserva = new Reserva();
                        reserva.setLeitor(leitor);
                        reserva.setDataReserva(dataReserva);

                        List<Livro> livros = baseLivros.findAll();
                        Livro[] arrayLivros = livros.toArray(new Livro[0]);
                        Livro livroEscolhido = (Livro) JOptionPane.showInputDialog(null, "Selecione um livro", "Livros", JOptionPane.PLAIN_MESSAGE, null, arrayLivros, null);
                        reserva.setLivro(livroEscolhido);

                        baseReservas.save(reserva);
                        } else {
                            JOptionPane.showMessageDialog(null, "Leitor não encontrado");
                        }
                        break;

                    case '2':
                        nome = JOptionPane.showInputDialog("Digite o nome do leitor");
                        List<Reserva> reservasPorLeitor = baseReservas.findReservasByLeitorNome(nome);
                        if(reservasPorLeitor.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada para o leitor " + nome);
                        } else {
                            listarReservas(reservasPorLeitor);
                        }
                        break;

                    case '3':
                        id = Integer.valueOf(JOptionPane.showInputDialog("Digite o ID da reserva a ser atualizada"));
                        reserva = baseReservas.findById(id).orElse(null);
                        if(reserva != null) {
                            leitor = reserva.getLeitor();
                            if(leitor != null) {
                                if(leitor.getIdLeitor() != leitor.getIdLeitor()) {
                                    JOptionPane.showMessageDialog(null, "Reserva não pertence ao leitor de id" + id);
                                } else {
                                    obterReserva(reserva);
                                    baseReservas.save(reserva);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Reserva não tem um leitor associado");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Reserva não encontrada para o id" + id);
                        }
                        break;


                    case '4':
                        id = Integer.valueOf(JOptionPane.showInputDialog("Digite o ID da reserva a ser deletada"));
                        reserva = baseReservas.findById(id).orElse(null);
                        if(reserva != null) {
                            baseReservas.deleteById(reserva.getReservaId());
                        } else {
                            JOptionPane.showMessageDialog(null, "Reserva não encontrada para o id" + id);
                        }
                        break;

                    case '5':
                        listarReservas(baseReservas.findAll());
                        break;

                    case '6':
                        titulo = JOptionPane.showInputDialog("Digite o titulo do livro");
                        List<Reserva> reservasPorTitulo = baseReservas.findByLivroTituloContendo(titulo);
                        if(reservasPorTitulo.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada para o titulo" + titulo);
                        } else {
                            listarReservas(reservasPorTitulo);
                        }
                        break;

                    case '7':
                        autor = JOptionPane.showInputDialog("Digite o nome do autor do livro");
                        List<Reserva> reservasPorAutor = baseReservas.findReservasByLivroAutor(autor);
                        if(reservasPorAutor.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada para o autor " + autor);
                        } else {
                            listarReservas(reservasPorAutor);
                        }
                        break;

                    case '8':
                        break;
                    
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                        break;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while(opcao != '8');
    }
}