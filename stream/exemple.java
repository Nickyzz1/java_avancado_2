import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class exemple {
    public static void main(String[] args) throws Exception{
        // ArrayList<Integer> list = new ArrayList<>();
        // for (int i = 0; i < 6; i++) {
        //     list.add(i);
        // }

        // List<Integer> result = list.stream()
        //     .map(n -> n * n) // Para cada valor n, ele transforma n ao quadrado
        //     .filter(n -> n % 2 == 1) // Filtra apenas números pares
        //     .sorted((n, m) -> n - m) // Organiza o array
        //     .limit(2)
        //     .collect(Collectors.toList());

        // for (Integer value : result)
        //     System.out.println(value);

        // Integer sum = Stream.of(1, 2, 3, 4, 5) 
        //     .map(n -> n * n)
        //     .filter(n -> n % 2 == 0)
        //     .sorted()
        //     .skip(1) // Pula o primeiro elemento
        //     .reduce(0, (acc, crr) -> acc + crr); // É tipo um fatoramento, que pega o primeiro número e soma com o segundo, fazendo com que o número atual seja a soma deles, dai ele pega o próximo número e faz a mesma coisa
        
        // boolean allPrimes = Stream.of(2, 3, 5, 7, 11)
        //     .allMatch(n -> {
        //         Integer a = n / 2;
        //         Integer r = ((int)Math.pow(a, n - 1) % n);
        //         return r == 1;
        //     });

        // Stream.of(3, 5, 7, 8, 11)
        //     .filter(n -> n % 2 == 0)
        //     .findAny()
        //     .ifPresent(n -> System.out.println(n));

        // long odds = IntStream.range(0, 100) // Gera números de 0 a 100
        //     .filter(n -> n % 2 == 0)
        //     .count();

            Path path = Paths.get("Main.java");

            long lines = Files.lines(path, Charset.defaultCharset())
             .filter(line -> line.contains("filter"))
             .count();
            
            System.out.println(lines);
    }
}