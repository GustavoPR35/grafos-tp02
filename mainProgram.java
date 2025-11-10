import java.io.File;
import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Escolha um número de pmed entre 1 e 40: ");
            int fileChoice = -1;
            fileChoice = Integer.parseInt(sc.nextLine());

            // ========= COLOCAR A LEITURA DE ARQUIVO EM UMA FUNÇÃO SEPARADA DEPOIS =========

            String arq = "./grafos/pmed" + fileChoice + ".txt";
            File file = new File(arq);

            if (!file.exists() || !file.isFile()) {
                System.out.println("Arquivo não encontrado: " + arq);
                sc.close();
                return;
            }

            Scanner sc_arq = new Scanner(file);
            String header = sc_arq.nextLine().trim();
            String[] header_parts = header.split("\\s+");

            int qntVertices = Integer.parseInt(header_parts[0]);
            int qntArestas = Integer.parseInt(header_parts[1]);
            int k = Integer.parseInt(header_parts[2]);

            Grafo grafo = new Grafo(qntVertices);

            for (int i = 0; i < qntArestas; i++) {
                String line = sc_arq.nextLine().trim();
                String[] line_parts = line.split("\\s+");
                int origem = Integer.parseInt(line_parts[0]);
                int destino = Integer.parseInt(line_parts[1]);
                int peso = Integer.parseInt(line_parts[2]);

                grafo.adicionar(origem, destino, peso);
            }
            sc_arq.close();

            // ========= COLOCAR A LEITURA DE ARQUIVO EM UMA FUNÇÃO SEPARADA DEPOIS =========

            grafo.print();

            sc.close();
        } catch (Exception e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
    }
}
