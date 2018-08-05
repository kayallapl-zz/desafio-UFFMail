import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Uffmail {

    public static Aluno ProcuraMatricula(String caminho, String matricula){
        try {
            FileReader arquivo = new FileReader(caminho);
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            try {
                String linha = lerArquivo.readLine();
                while(linha != null){
                    String[] conteudo = linha.split(",");
                    Boolean status = conteudo[5].equalsIgnoreCase("Ativo");
                    if(matricula.equals(conteudo[1])){
                        Aluno a = new Aluno(conteudo[0], conteudo[1], conteudo[2], conteudo[3], conteudo[4], status);
                        return a;
                    }
                    linha = lerArquivo.readLine();
                }
                arquivo.close();
                System.out.println("Aluno não encontrado!");
                return null;

            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return null;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return null;
        }
    }

    public static String UffmailsExistentes(String caminho){
        try {
            FileReader arquivo = new FileReader(caminho);
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            try {
                String linha = lerArquivo.readLine();
                String uffmails = "";
                while(linha != null){
                    String[] conteudo = linha.split(",");
                    if (!(conteudo[4]).equals("")){
                        uffmails += conteudo[4] + ",";
                    }
                    linha = lerArquivo.readLine();
                }
                arquivo.close();
                return uffmails;

            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return null;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return null;
        }
    }

    public static Aluno CriarUFFMail(Aluno a) {

        if (a.getUffmail().equals("")){
            System.out.println("Aluno não tem Uffmail.");
            if (!a.getStatus()) {
                System.out.println("Aluno Inativo, não é possível criar o Uffmail.");
                return a;
            }
            /*
                Produção das opções aqui
            */
            return a;
        }
        System.out.println("Aluno Ativo, mas já tem Uffmail.");
        return a;
    }
}
