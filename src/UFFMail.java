import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UFFMail {

    public static Aluno Create(String Path, String matricula){
        try {

            FileReader file = new FileReader(Path);
            BufferedReader readFile = new BufferedReader(file);

            try {
                String line = readFile.readLine();
                while(line != null){
                    String[] content = line.split(",");
                    Boolean status = content[5].equalsIgnoreCase("Ativo");
                    if(matricula.equals(content[1])){
                        Aluno a = new Aluno(content[0], content[1], content[2], content[3], content[4], status);
                        return a;
                    }
                    line = readFile.readLine();
                }
                file.close();
                System.out.println("Aluno não encontrado!");
                return null;

            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return null;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Erro: UFFMail não encontrado!");
            return null;
        }
    }
}