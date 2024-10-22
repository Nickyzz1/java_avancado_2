import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        Path path = Paths.get("INFLUD21-01-05-2023.csv");

        // morreram mais pessoa com vacina ou sem vacina?

        System.out.println("\n ====== RELATORIO CORONAVIRUS ======\n");   

        try {

            List<String> lines = Files.readAllLines(path);

            long obitosSemVacina = lines.stream().map(line -> line.split(";"))
                    .filter(columns -> columns[154].equals("2"))
                    .filter(columns -> columns[106].equals("5"))
                    .filter(columns -> columns[109].equals("2"))
                    .count();
            long vivosSemVacina = lines.stream().map(line -> line.split(";"))
                    .filter(columns -> columns[154].equals("2"))
                    .filter(columns -> columns[106].equals("5"))
                    .filter(columns -> columns[109].equals("1"))
                    .count();

            long obitosComVacina = lines.stream().map(line -> line.split(";"))
                    .filter(columns -> columns[154].equals("1"))
                    .filter(columns -> columns[106].equals("5"))
                    .filter(columns -> columns[109].equals("2"))
                    .count();
            long vivosVacina = lines.stream().map(line -> line.split(";"))
                    .filter(columns -> columns[154].equals("2"))
                    .filter(columns -> columns[106].equals("5"))
                    .filter(columns -> columns[109].equals("1"))
                    .count();

            long totalObitos = obitosComVacina + obitosSemVacina;

            System.out.println("OBITOS\n\n==== VACINA ====\n");
    
            System.out.println("Morreu com vacina " + obitosComVacina);
            System.out.println("Morreu sem vacina " + obitosSemVacina);
            System.out.println("Total de mortes " + totalObitos);
    
            double percentualComVacina = obitosComVacina * 100 / totalObitos;
            double percentualSemVacina = obitosSemVacina * 100 / totalObitos;
    
            long totalVacina = vivosVacina + obitosComVacina;
            long totalSemVacina = vivosSemVacina + obitosSemVacina;
    
            double filterPercentualComVacina = vivosVacina * 100 / totalVacina;
            double filterPercentualSemVacina = vivosSemVacina  * 100 / totalSemVacina;
    
    
            System.out.println("\n=== PERCENTUAL DE MORTES ===");   
    
            System.out.println("Com vacina " + percentualComVacina + "%");
            System.out.println("Sem vacina " + percentualSemVacina + "%");
    
            if (percentualComVacina > percentualSemVacina) {
                System.out.println("\nPessoas com vacina morreram mais");
            }else {
                System.out.println("Pessoas sem vacina morreram mais");
            }
    
            System.out.println("\n=== PERCENTUAL PESSOAS QUE TOMARAM VACINA E MORRERAM ===");   
    
            System.out.println("Com vacina " + filterPercentualComVacina + "%");
    
            System.out.println("\n=== PERCENTUAL PESSOAS QUE NÃO TOMARAM VACINA E MORRERAM ===");   
    
            System.out.println("Sem vacina " + percentualSemVacina + "%");

            long childs = lines.stream()
            .map(line -> line.split(";"))
                .filter(columns -> columns[154].equals("2"))
                .filter(columns -> columns[106].equals("5"))
                .filter(columns -> columns[109].equals("2"))
                .skip(1)
                .filter(columns -> Integer.parseInt(columns[13]) < 13)
                .count();

            long teen = lines.stream()
            .map(line -> line.split(";"))
                .filter(columns -> columns[154].equals("2"))
                .filter(columns -> columns[106].equals("5"))
                .filter(columns -> columns[109].equals("2"))
                .skip(1)
                .filter(columns -> Integer.parseInt(columns[13]) >= 13 && Integer.parseInt(columns[13]) <= 18)
                .count();
            
            long adult = lines.stream()
            .map(line -> line.split(";"))
                .filter(columns -> columns[154].equals("2"))
                .filter(columns -> columns[106].equals("5"))
                .filter(columns -> columns[109].equals("2"))
                .skip(1)
                .filter(columns -> Integer.parseInt(columns[13]) > 18 && Integer.parseInt(columns[13]) <= 64)
                .count();


            long elderly = lines.stream()
            .map(line -> line.split(";"))
                .filter(columns -> columns[154].equals("2"))
                .filter(columns -> columns[106].equals("5"))
                .filter(columns -> columns[109].equals("2"))
                .skip(1)
                .filter(columns -> Integer.parseInt(columns[13]) >= 65)
                .count();

            long childsCV = lines.stream()
            .map(line -> line.split(";"))
                .filter(columns -> columns[154].equals("1"))
                .filter(columns -> columns[106].equals("5"))
                .filter(columns -> columns[109].equals("2"))
                .skip(1)
                .filter(columns -> Integer.parseInt(columns[13]) < 13)
                .count();

            long teenCV = lines.stream()
            .map(line -> line.split(";"))
                .filter(columns -> columns[154].equals("1"))
                .filter(columns -> columns[106].equals("5"))
                .filter(columns -> columns[109].equals("2"))
                .skip(1)
                .filter(columns -> Integer.parseInt(columns[13]) >= 13 && Integer.parseInt(columns[13]) <= 18)
                .count();
            
            long adultCV = lines.stream()
            .map(line -> line.split(";"))
                .filter(columns -> columns[154].equals("1"))
                .filter(columns -> columns[106].equals("5"))
                .filter(columns -> columns[109].equals("2"))
                .skip(1)
                .filter(columns -> Integer.parseInt(columns[13]) > 18 && Integer.parseInt(columns[13]) <= 64)
                .count();


            long elderlyCV = lines.stream()
            .map(line -> line.split(";"))
                .filter(columns -> columns[154].equals("1"))
                .filter(columns -> columns[106].equals("5"))
                .filter(columns -> columns[109].equals("2"))
                .skip(1)
                .filter(columns -> Integer.parseInt(columns[13]) >= 65)
                .count();

            System.out.println("\n ---- NUMERO DE OBITOS POR FAIXA ETARIA----");  
            
            System.out.println("\n-- Sem vacina --");   

            System.out.println("Crianças(0 - 12) " + childs);
            System.out.println("Adolescentes(12 - 18) " + teen);
            System.out.println("Adulto(18 - 64) " + adult);
            System.out.println("Idoso(65+) " + elderly);

            System.out.println("\n-- Com vacina --");   

            System.out.println("Crianças " + childsCV);
            System.out.println("Adolescentes " + teenCV);
            System.out.println("Adulto " + adultCV);
            System.out.println("Idoso " + elderlyCV);


            double percentualCriancaComVacinaFT = childsCV * 100 / totalObitos;
            double percentualAdolComVacinaFT = teenCV * 100 / totalObitos;
            double percentualAdultoComVacinaFT = adultCV * 100 / totalObitos;
            double percentualIdosoComVacinaFT = elderlyCV * 100 / totalObitos;

            double percentualCriancaSemVacinaFT = childs * 100 / totalObitos;
            double percentualAdolSemVacinaFT = teen * 100 / totalObitos;
            double percentualAdultoSemVacinaFT = adult * 100 / totalObitos;
            double percentualIdosoSemVacinaFT = elderly * 100 / totalObitos;
            
            System.out.println("\n==== PERCENTUAL OBITOS POR FAIXA ETARIA COM VACINA E SEM VACINA ==== ");   

            System.out.println("\n==== COM VACINA ==== ");   
    
            System.out.println("Criança com vacina " + percentualCriancaComVacinaFT + "%");
            System.out.println("Adolescente sem vacina " + percentualAdolComVacinaFT + "%");
            System.out.println("Adolescente sem vacina " + percentualAdultoComVacinaFT  + "%");
            System.out.println("Adolescente sem vacina " + percentualIdosoComVacinaFT + "%");

            System.out.println("\n==== SEM VACINA ==== ");   

            System.out.println("Criança com vacina " + percentualCriancaSemVacinaFT + "%");
            System.out.println("Adolescente sem vacina " + percentualAdolSemVacinaFT + "%");
            System.out.println("Adolescente sem vacina " + percentualAdultoSemVacinaFT  + "%");
            System.out.println("Adolescente sem vacina " + percentualIdosoSemVacinaFT + "%\n");
                
            }  catch(IOException e) {

                System.err.println(e);
            }
    }
}
