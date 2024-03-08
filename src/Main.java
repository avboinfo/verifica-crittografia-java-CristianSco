import java.io.*;
import java.util.Scanner;



 public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("static/data.txt"));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            br.close();

            String data = content.toString();
            System.out.println("Contenuto del file:");
            System.out.println(data);

            // Menu
            System.out.println("\nScegli un'azione:");
            System.out.println("1. Cripta il contenuto");
            System.out.println("2. Decripta il contenuto");
            System.out.println("3. Stampa il contenuto");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Inserisci lo shift per l'algoritmo di Cesare:");
                    int shift = scanner.nextInt();
                    System.out.println("Inserisci la chiave per l'algoritmo XOR:");
                    String key = scanner.next();
                    String cesareCriptato = CriptoAlgoritmi.Cesare(data, shift);
                    String xorCriptato = CriptoAlgoritmi.xorAlgo(cesareCriptato, key);
                    FileWriter criptatoWriter = new FileWriter("data.txt");
                    criptatoWriter.write(xorCriptato);
                    criptatoWriter.close();
                    System.out.println("Contenuto criptato e salvato nel file 'data.txt'");
                    break;
                case 2:
                    System.out.println("Inserisci la chiave per l'algoritmo XOR:");
                    key = scanner.next();
                    String xorDecriptato = CriptoAlgoritmi.xorAlgo(data, key);
                    String cesareDecriptato = CriptoAlgoritmi.Cesare(xorDecriptato, -1); 
                    FileWriter decriptatoWriter = new FileWriter("data.txt");
                    decriptatoWriter.write(cesareDecriptato);
                    decriptatoWriter.close();
                    System.out.println("Contenuto decriptato e salvato nel file 'data.txt'");
                    break;
                case 3:
                   
                    System.out.println("Contenuto decriptato:");
                    System.out.println(data);
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }

            scanner.close();

        } catch (IOException e) {
            System.err.println("Errore durante la lettura/scrittura del file.");
            e.printStackTrace();
        }
    }
}