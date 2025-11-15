import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            Grafo grafo;
            int[] params = new int[3];
            while (true) {
                System.out.print("Escolha um número de pmed entre 1 e 40 (0 para encerrar): ");
                int fileChoice = -1;
                fileChoice = Integer.parseInt(sc.nextLine());

                grafo = criarGrafo(fileChoice, params);
                if (grafo != null) {
                    break;
                }
            }

            int k = params[0];
            int qntVertices = params[1];
            int qntArestas = params[2];

            // Pergunta ao usuário qual solução deseja executar
            System.out.println("Escolha o tipo de solução:");
            System.out.println("1 - Solução Exata");
            System.out.println("2 - Solução Aproximada (Algoritmo de Gonzalez)");
            System.out.print("Opção: ");
            int opcao = Integer.parseInt(sc.nextLine());
            System.out.println();

            // Calcula Floyd-Warshall (necessário para ambas as soluções)
            int[][] dist = grafo.getMatrizAdjacencia();
            utils.floydWarshall(dist);

            int[] centers = null;
            long startTime = System.currentTimeMillis();

            if (opcao == 1) {
                // Solução Exata
                BigInteger totalCombinations = utils.nCr(qntVertices, k);
                System.out.println("Total de combinações de " + k + " centros entre " + qntVertices + " vértices: "
                        + totalCombinations + "\n");

                centers = exactSolution.solveKCenterExact(dist, k);
            } else if (opcao == 2) {
                // Solução Aproximada
                centers = approximateSolution.solveKCenterApproximate(dist, k);
            } else {
                System.out.println("Opção inválida!");
                sc.close();
                return;
            }

            long endTime = System.currentTimeMillis();

            System.out.print("Centros escolhidos: ");
            for (int c : centers) {
                System.out.print(c + " ");
            }
            System.out.println();

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
            System.out.println("Arquivo encontrado: " + arq);
            System.out.println("Carregando grafo...");

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

            System.out.println("Grafo carregado com sucesso.");
            System.out.println("\nNúmero de vértices: " + qntVertices);
            System.out.println("Número de arestas: " + qntArestas);
            System.out.println("Valor de k: " + k + "\n");

            return grafo;
        } catch (Exception e) {
            System.out.println("Erro ao criar grafo");
            e.printStackTrace();
            return null;
        }
    }
}
