public class utils {
    /**
     * Algoritmo de Floyd-Warshall para encontrar os menores caminhos entre todos os pares de vértices em um grafo ponderado.
     * Encontrado em: https://www.geeksforgeeks.org/dsa/floyd-warshall-algorithm-dp-16/
     * @param dist Matriz de adjacência representando o grafo, onde dist[i][j] é o peso da aresta de i para j. Use um valor grande (ex: 1e8) para representar ausência de aresta.
     */
    static void floydWarshall(int[][] dist) {
        int V = dist.length;

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(dist[i][k] != 1e8 && dist[k][j]!= 1e8) {
                        dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }

    static void binarySearch() {
        // Implementação futura
    }
}   
