import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String file = "alunos.csv";
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite sua matrícula: ");
        String matricula = teclado.next();
        Aluno aluno = Uffmail.ProcuraMatricula(file, matricula);
        if(aluno != null)
            Uffmail.CriarUFFMail(file, aluno);
    }
}
