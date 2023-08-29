import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        String fonte = args[0];
        String destino = args[1];

        int tempoInicio = (int) System.currentTimeMillis();

        try {
            InputStream is = new FileInputStream(fonte);
            OutputStream os = new FileOutputStream(destino);

            int byteLido = 0;

            while ((byteLido = is.read()) != -1) {
                os.write(byteLido);
            }

            int tempoFinal = (int) System.currentTimeMillis();
            int tempoTotal = (int) tempoFinal - (int) tempoInicio;

            System.out.println("Arquivo copiado em: " + tempoFinal + "ms");
            is.close();
            os.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}