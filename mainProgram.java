import java.io.File;
import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Escolha um número de pmed entre 1 e 40: ");
            int fileChoice = -1;
            fileChoice = Integer.parseInt(sc.nextLine());

            int[] params = new int[3];
            Grafo grafo = criarGrafo(fileChoice, params);
            int k = params[0];
            int qntVertices = params[1];
            int qntArestas = params[2];

            // grafo.print();

            long startTime = System.currentTimeMillis();

            int[][] dist = grafo.getMatrizAdjacencia();
            utils.floydWarshall(dist);

            int[] centers = exactSolution.solveKCenterExact(dist, k);
            System.out.print("Centros escolhidos: ");
            for (int c : centers) {
                System.out.print(c + " ");
            }
            System.out.println();

            long endTime = System.currentTimeMillis();
            System.out.println("Tempo de execução: " + (endTime - startTime) + " ms");

            sc.close();
        } catch (Exception e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
    }

    public static Grafo criarGrafo(int fileChoice, int[] params) {
        try {
            String arq = "./grafos/pmed" + fileChoice + ".txt";
            File file = new File(arq);

            if (!file.exists() || !file.isFile()) {
                System.out.println("Arquivo não encontrado: " + arq);
                return null;
            }

            Scanner sc_arq = new Scanner(file);
            String header = sc_arq.nextLine().trim();
            String[] header_parts = header.split("\\s+");

            int qntVertices = Integer.parseInt(header_parts[0]);
            int qntArestas = Integer.parseInt(header_parts[1]);
            int k = Integer.parseInt(header_parts[2]);

            params[0] = k;
            params[1] = qntVertices;
            params[2] = qntArestas;

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
            return grafo;
        } catch (Exception e) {
            System.out.println("Erro ao criar grafo");
            e.printStackTrace();
            return null;
        }
    }
}
