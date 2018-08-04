public class Main {

    public static void main(String[] args) {

        String file = "teste.txt";
        String text = ArquivoCSV.Read(file);
        System.out.println(text);
    }
}
