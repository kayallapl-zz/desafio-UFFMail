import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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

    private static String UffmailsExistentes(String caminho){
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

    private static String[] GerarOpcoes(String caminho, Aluno a){
        String nome = a.getNome();
        String[] nomeSeparado = nome.split(" ");
        String listadeuffmails = UffmailsExistentes(caminho);
        String emails = "";

        String opcao = nomeSeparado[0].toLowerCase() + "_" + nomeSeparado[nomeSeparado.length - 1].toLowerCase() + "@id.uff.br";
        if (!(listadeuffmails.contains(opcao)))
            emails += opcao + ",";

        opcao = nomeSeparado[0].toLowerCase() + "" + nomeSeparado[1].substring(0, 1).toLowerCase() + "" + nomeSeparado[2].substring(0, 1).toLowerCase() + "@id.uff.br";
        if (!(listadeuffmails.contains(opcao)))
            emails += opcao + ",";

        opcao = nomeSeparado[0].toLowerCase() + "." + nomeSeparado[1].toLowerCase() + "@id.uff.br";
        if (!(listadeuffmails.contains(opcao)))
            emails += opcao + ",";

        opcao = nomeSeparado[0].toLowerCase() + "" + nomeSeparado[1].toLowerCase() + "" + nomeSeparado[2].toLowerCase() + "@id.uff.br";
        if (!(listadeuffmails.contains(opcao)))
            emails += opcao + ",";

        opcao = nomeSeparado[0].substring(0, 1).toLowerCase() + "" + nomeSeparado[1].substring(0, 1).toLowerCase() + "" + nomeSeparado[2].toLowerCase() + "@id.uff.br";
        if (!(listadeuffmails.contains(opcao)))
            emails += opcao + ",";

        opcao = nomeSeparado[2].toLowerCase() + "" + nomeSeparado[0].toLowerCase() + "@id.uff.br";
        if (!(listadeuffmails.contains(opcao)))
            emails += opcao + ",";

        String[] lista = emails.split(",");

        return lista;
    }

    public static void CriarUFFMail(String caminho, Aluno a) {

        if (a.getUffmail().equals("")){
            if (!a.getStatus()) {
                System.out.println("Aluno Inativo, não é possível criar o Uffmail.");
                return;
            }
            Scanner teclado = new Scanner(System.in);
            String[] opcoes = GerarOpcoes(caminho, a);

            System.out.println(a.getNome().split(" ")[0] + ", por favor escolha uma das opções abaixo para o seu UFFMail: ");
            for (int i=0; i<opcoes.length; i++){
                System.out.println(i+1 + " - " + opcoes[i]);
            }
            int escolhido = teclado.nextInt() - 1;
            teclado.close();
            a.setUffmail(opcoes[escolhido]);

            return;
        }
        System.out.println("Seu Uffmail é " + a.getUffmail() + ".");
        return;
    }

}
