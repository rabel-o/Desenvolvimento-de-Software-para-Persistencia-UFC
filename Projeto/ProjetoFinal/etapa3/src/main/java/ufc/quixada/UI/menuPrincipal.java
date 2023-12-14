package ufc.quixada.UI;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lombok.extern.slf4j.Slf4j;
import ufc.quixada.entity.Leitor;

@SpringBootApplication(scanBasePackages = "ufc.quixada")
@EntityScan(basePackages = "ufc.quixada.entity")
@EnableJpaRepositories(basePackages = "ufc.quixada.dao.jpa")
//@EnableMongoRepositories(basePackages = "ufc.quixada.dao.mongo")
@Slf4j
public class menuPrincipal implements CommandLineRunner {
    @Autowired
    private menuLeitor menuLeitor;

    @Autowired
    private menuLivros menuLivros;

    @Autowired
    private menuReserva menuReserva;

    public static void main(String[] args) {
        SpringApplicationBuilder sab = new SpringApplicationBuilder(menuPrincipal.class);
        sab.headless(false).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        StringBuilder menu = new StringBuilder("Menu Principal\n")
            .append("1 - Menu Leitores\n")
            .append("2 - Menu Livros\n")
            .append("3 - Menu Reservas\n")
            .append("4 - Sair\n");
        char opcao = '0';
            do{
                try {
                    opcao = JOptionPane.showInputDialog(menu).charAt(0);
                    switch(opcao) {
                        case '1':
                            menuLeitor.menu();
                            break;
                        case '2':
                            menuLivros.menu();
                            break;
                        case '3':
                            menuReserva.menu(new Leitor());
                            break;
                        case '4':
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida");
                            break;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());

            }
        } while(opcao != '4');
    }
}