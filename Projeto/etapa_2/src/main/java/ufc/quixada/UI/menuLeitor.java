package ufc.quixada.UI;

import ufc.quixada.entity.Leitor;
import ufc.quixada.dao.LeitorDAO;
import ufc.quixada.entity.Livro;
import ufc.quixada.dao.LivroDAO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class menuLeitor {

    @Autowired
    public LeitorDAO baseLeitores;

    @Autowired
    public LivroDAO baseLivros;

    public void obterLeitor(Leitor leitor) {
        String nome = JOptionPane.showInputDialog("Nome", leitor.getNome());
        String endereco = JOptionPane.showInputDialog("Endereço", leitor.getEndereco());
        String contato = JOptionPane.showInputDialog("Contato", leitor.getContato());
        leitor.setNome(nome);
        leitor.setEndereco(endereco);
        leitor.setContato(contato);
    }
    
@Transactional
public void listarLeitores(List<Leitor> leitores) {
    StringBuilder listagem = new StringBuilder();
    for(Leitor leitor : leitores) {
       listagem.append(leitor.toString()).append("\n");
    }
    JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum leitor encontrado" : listagem.toString());
}

public void listarLeitor(Leitor leitor) {
    JOptionPane.showMessageDialog(null, leitor == null ? "Nenhum leitor encontrado" : leitor.toString());
}

    public void menu() {
        StringBuilder menu = new StringBuilder("Menu Leitores\n")
            .append(1 + " - Cadastrar Leitor\n")
            .append(2 + " - Listar Leitores\n")
            .append(3 + " - Listar por nome\n")
            .append(4 + " - Atualizar por nome\n")
            .append(5 + " - Deletar Leitor\n")
            .append(6 + " - Listar leitores por titulo do livro\n")
            .append(7 + " - Listar leitor por ID\n")
            .append(8 + " - Voltar\n");
            char opcao = '0';
            do {
                try {
                    Leitor leitor;
                    String nome;
                    String titulo;
                    Integer id;
                    opcao = JOptionPane.showInputDialog(menu.toString()).charAt(0);
                    switch (opcao) {
                        case '1':
                            leitor = new Leitor();
                            obterLeitor(leitor);
                            baseLeitores.save(leitor);
                            break;

                        case '2':
                            listarLeitores(baseLeitores.findAll());
                            break;

                        case '3':
                            nome = JOptionPane.showInputDialog("Leitores");
                            leitor = baseLeitores.findByNome(nome).get(0);
                            listarLeitor(leitor);
                            break;

                        case '4':
                            nome = JOptionPane.showInputDialog("Digite o nome do leitor que deseja atualizar");
                            leitor = baseLeitores.findFirstByNome(nome);
                            if(leitor != null) {
                                obterLeitor(leitor);
                                baseLeitores.save(leitor);
                            } else {
                                JOptionPane.showMessageDialog(null, "Leitor não encontrado");
                            }
                            break;

                        case '5':
                            nome = JOptionPane.showInputDialog("Nome");
                            leitor = baseLeitores.findFirstByNome(nome);
                            if(leitor != null) {
                                baseLeitores.deleteById(leitor.getIdLeitor());
                            } else {
                                JOptionPane.showMessageDialog(null, "Leitor não encontrado");
                            }
                            break;

                        case '6':
                            titulo = JOptionPane.showInputDialog("Digite o titulo do livro");
                            Set<String> leitores = new HashSet<>(baseLeitores.findLeitoresByTituloLivro(titulo));
                            StringBuilder listagem = new StringBuilder();
                            for(String l : leitores) {
                                listagem.append(l).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum leitor encontrado" : listagem.toString());
                            break;

                        case '7':
                            id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do leitor"));
                            leitor = baseLeitores.findByIdLeitor(id);
                            listarLeitor(leitor);
                            break;

                        case '8':
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida");
                            break;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    JOptionPane.showMessageDialog(null, "Erro ao executar operação");
                }
            } while (opcao != '8');
    }
}
