import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        String fonte = args[0];
        String destino = args[1];

        int tempoInicio = (int) System.currentTimeMillis();

        try {
            InputStream is = new FileInputStream(fonte);
            InputStreamReader isr = new InputStreamReader(is, "ISO-8859-1");

            OutputStream os = new FileOutputStream(destino);
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

            int byteLido = 0;

            while ((byteLido = is.read()) != -1) {
                os.write(byteLido);
            }

            int tempoFinal = (int) System.currentTimeMillis();
            int tempoTotal = (int) tempoFinal - (int) tempoInicio;

            System.out.println("Arquivo copiado em: " + tempoFinal + "ms");
            is.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}