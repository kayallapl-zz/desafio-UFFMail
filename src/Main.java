public class Main {

    public static void main(String[] args) {

        String file = "alunos.csv";
        Aluno aluno = UFFMail.SearchMatricula(file, "102658");
        aluno = UFFMail.CreateUFFMail(aluno);
    }
}
