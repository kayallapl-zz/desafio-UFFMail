public class Main {

    public static void main(String[] args) {

        String file = "alunos.csv";
        boolean achou = ArquivoCSV.Search(file, "23898");
        if(achou) System.out.println("achou");
        else System.out.println("não achou");
    }
}
