public class Main {

    public static void main(String[] args) {

        String file = "alunos.csv";
        Aluno aluno = Uffmail.ProcuraMatricula(file, "104659");
        aluno = Uffmail.CriarUFFMail(file, aluno);
        String uffmails = Uffmail.UffmailsExistentes(file);

    }
}
