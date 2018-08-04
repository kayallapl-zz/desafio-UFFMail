import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoCSV {

    public static boolean Search(String Path, String matricula){
        try {

            FileReader file = new FileReader(Path);
            BufferedReader readFile = new BufferedReader(file);

            try {
                String line = readFile.readLine();
                while(line != null){
                    String[] content = line.split(",");
                    if(matricula.equals(content[1])){
                        return true;
                    }
                    line = readFile.readLine();
                }
                file.close();
                return false;

            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return false;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return false;
        }
    }
}
