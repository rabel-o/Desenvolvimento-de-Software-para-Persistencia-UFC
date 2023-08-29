import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        String fonte = args[0];
        String destino = args[1];

        int tempoInicio = (int) System.currentTimeMillis();

        try {
            InputStream is = new FileInputStream(fonte);
            OutputStream os = new FileOutputStream(destino);

            byte[] buffer = new byte[8192];
            int byteLido;

            while ((byteLido = is.read(buffer)) != -1) {
                os.write(buffer, 0, 8192);
            }

            int tempoFinal = (int) System.currentTimeMillis();
            int tempoTotal = (int) tempoFinal - (int) tempoInicio;

            System.out.println("Arquivo copiado em: " + tempoFinal + "ms");

            if(tempoFinal < 709120703) {
                System.out.println("O tempo de execução do código da Q2 é mais rápido que o da Q1.");
                System.out.println("Q1: 709120703ms || " + "Q2: " + tempoFinal);
            } else {
                System.out.println("O tempo de execução do código da Q2 é mais lento que o da Q1.");
                System.out.println("Q1: 709120703ms || " + "Q2: " + tempoFinal);
            }
            is.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}