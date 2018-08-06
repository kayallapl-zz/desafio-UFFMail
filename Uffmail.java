import java.io.*;
import java.util.Scanner;

public class Uffmail {

    public static Aluno procuraMatricula(String caminho, String matricula){
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

    private static String uffmailsExistentes(String caminho){
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

    private static String[] gerarOpcoes(String caminho, Aluno a){
        String nome = a.getNome();
        String[] nomeSeparado = nome.split(" ");
        String listadeuffmails = uffmailsExistentes(caminho);
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

    public static void reescrever(String caminho, Aluno a){
        String temp = "temp.csv";
        File arquivo = new File(caminho);
        File novoarquivo = new File(temp);
        try{
            FileWriter fw = new FileWriter(temp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(caminho));
            String status = "";
            x.useDelimiter("[\n]");
            while(x.hasNext()){
                String linha = x.next();
                String[] conteudo = linha.split(",");
                if(a.getMatricula().equals(conteudo[1])) {
                    if (a.getStatus()) status = "Ativo";
                    else status = "Inativo";
                    pw.println(a.getNome() + "," + a.getMatricula() + "," + a.getTelefone() + "," + a.getEmail() + "," + a.getUffmail() + "," + status);
                }else{
                    pw.print(linha + "\n");
                }
            }
            x.close();
            pw.close();
            arquivo.delete();
            File novo = new File("alunos.csv");
            novoarquivo.renameTo(novo);
            return;

        } catch (IOException ex) {
            System.out.println("Erro: Não foi possível ler o arquivo!");
            return;
        }

    }

    public static void criarUFFMail(String caminho, Aluno a) {

        if (a.getUffmail().equals("")){
            if (!a.getStatus()) {
                System.out.println("Aluno Inativo, não é possível criar o Uffmail.");
                return;
            }
            Scanner teclado = new Scanner(System.in);
            String[] opcoes = gerarOpcoes(caminho, a);

            System.out.println(a.getNome().split(" ")[0] + ", por favor escolha uma das opções abaixo para o seu UFFMail: ");
            for (int i=0; i<opcoes.length; i++){
                System.out.println(i+1 + " - " + opcoes[i]);
            }
            int escolhido = teclado.nextInt() - 1;
            teclado.close();
            a.setUffmail(opcoes[escolhido]);
            reescrever(caminho, a);
            System.out.println("A criação de seu e-mail ("+a.getUffmail()+") será feita nos próximos minutos.");
            System.out.println("Um SMS foi enviado para "+a.getTelefone()+" com a sua senha de acesso.");
            return;
        }
        System.out.println("Seu Uffmail é " + a.getUffmail() + ".");
        return;
    }

}
