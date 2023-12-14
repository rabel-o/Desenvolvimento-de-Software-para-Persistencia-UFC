import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        //carregando arquivo de propriedade em um obj "properties"
        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        }

        //acessando as propriedades do arquivo
        String arquivo = props.getProperty("arquivo");
        int linhaInicial = Integer.parseInt(props.getProperty("linha_inicial"));
        int linhaFinal = Integer.parseInt(props.getProperty("linha_final"));

        //exibindo as linhas de acordo com as propriedades definidas, obtendo o fluxo por
        //meio do metodo "lines" e a classe "java.nio.file.Files"
        try(Stream<String> linhas = Files.lines(Paths.get(arquivo))) {
            linhas.skip(linhaInicial - 1)
                    .limit(linhaFinal - linhaInicial + 1)
                    .forEach(System.out::println);
        }
    }
}