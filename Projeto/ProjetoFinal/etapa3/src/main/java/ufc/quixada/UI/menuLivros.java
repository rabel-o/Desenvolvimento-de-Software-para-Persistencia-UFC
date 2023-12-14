package ufc.quixada.UI;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ufc.quixada.dao.LivroDAO;
import ufc.quixada.entity.Livro;

@Slf4j
@Component
public class menuLivros {
    @Autowired
    private LivroDAO baseLivros;

    public void obterLivros(Livro livro) {
        String titulo = JOptionPane.showInputDialog("Titulo", livro.getTitulo());
        String autor = JOptionPane.showInputDialog("Autor", livro.getAutor());
        String editora = JOptionPane.showInputDialog("Editora", livro.getEditora());
        String anoPublicacao = JOptionPane.showInputDialog("Ano de Publicação", livro.getAnoPublicacao());
        String isbn = JOptionPane.showInputDialog("ISBN", livro.getIsbn());
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setEditora(editora);
        livro.setAnoPublicacao(anoPublicacao);
        livro.setIsbn(isbn);
    }

    public void listarLivros(List<Livro> livros) {
        StringBuilder listagem = new StringBuilder();
        for(Livro livro : livros) {
            listagem.append(livro.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum livro encontrado" : listagem.toString());
    }

    public void listarLivro(Livro livro) {
        JOptionPane.showMessageDialog(null, livro == null ? "Nenhum livro encontrado" : livro.toString());

    }

    public void menu() {
        StringBuilder menu = new StringBuilder("Menu Livros\n")
            .append(1 + " - Cadastrar Livro\n")
            .append(2 + " - Listar Livros\n")
            .append(3 + " - Listar por titulo\n")
            .append(4 + " - Atualizar Livro\n")
            .append(5 + " - Listar por autor\n")
            .append(6 + " - Listar por editora\n")
            .append(7 + " - Listar todos os títulos de Livro de um determinado Leitor\n")
            .append(8 + " - Deletar Livro\n")
            .append(9 + " - Voltar\n");
            char opcao = '0';
            do {
                try {
                    Livro livro;
                    String titulo;
                    String autor;
                    String editora;
                    String leitor;
                    List<Livro> allLivros;
                    JComboBox<Livro> livrosComboBox;
                    opcao = JOptionPane.showInputDialog(menu.toString()).charAt(0);
                    switch(opcao) {
                        case '1':
                            livro = new Livro();
                            obterLivros(livro);
                            baseLivros.save(livro);
                            break;

                        case '2':
                            listarLivros(baseLivros.findAll());
                            break;

                        case '3':
                            titulo = JOptionPane.showInputDialog("Livros");
                            livro = baseLivros.findByTitulo(titulo).get(0);
                            listarLivro(livro);
                            break;

                        case '4':
                            allLivros = baseLivros.findAll();
                            livrosComboBox = new JComboBox<>(allLivros.toArray(new Livro[0]));
                            JOptionPane.showMessageDialog(null, livrosComboBox, "Selecione o livro que deseja atualizar", JOptionPane.QUESTION_MESSAGE);
                            livro = (Livro) livrosComboBox.getSelectedItem();
                            if (livro != null) {
                                obterLivros(livro);
                                baseLivros.save(livro);
                            } else {
                                JOptionPane.showMessageDialog(null, "Livro não selecionado");
                            }

                        case '5':
                            autor = JOptionPane.showInputDialog("Digite o nome do autor");
                            livro = baseLivros.findByAutor(autor).get(0);
                            listarLivro(livro);
                            break;

                        case '6':
                            editora = JOptionPane.showInputDialog("Digite o nome da editora");
                            List<Livro> livrosDaEditora = baseLivros.findByEditora(editora);
                            if (livrosDaEditora.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Nenhum livro encontrado para a editora informada");
                            } else {
                                listarLivros(livrosDaEditora);
                            }
                            break;

                        case '7':
                            leitor = JOptionPane.showInputDialog("Digite o nome do leitor");
                            List<Livro> livrosDoLeitor = baseLivros.findLivrosByLeitor(leitor);
                            if (livrosDoLeitor.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Nenhum livro encontrado para o leitor informado");
                            } else {
                                listarLivros(livrosDoLeitor);
                            }
                            break;
                        
                        case '8':
                            allLivros = baseLivros.findAll();
                            livrosComboBox = new JComboBox<>(allLivros.toArray(new Livro[0]));
                            JOptionPane.showMessageDialog(null, livrosComboBox, "Selecione o livro que deseja atualizar", JOptionPane.QUESTION_MESSAGE);
                            livro = (Livro) livrosComboBox.getSelectedItem();
                            if (livro != null) {
                                baseLivros.deleteById(livro.getId());
                            } else {
                                JOptionPane.showMessageDialog(null, "Livro não selecionado");
                            }

                        case '9':
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida");
                    }
                } catch(Exception e) {
                    log.error(e.getMessage(), e);
                    JOptionPane.showMessageDialog(null, "Erro ao executar operação");
             
                }
        } while(opcao != '9');
    }
}
