public class Main {

    public static void main(String[] args) {

        String file = "alunos.csv";
        Aluno aluno = Uffmail.ProcuraMatricula(file, "102658");
        aluno = Uffmail.CriarUFFMail(aluno);
        String uffmails = Uffmail.UffmailsExistentes(file);

    }
}
