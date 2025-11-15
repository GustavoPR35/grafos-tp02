import java.math.BigInteger;

public class utils {
    /**
     * Algoritmo de Floyd-Warshall para encontrar os menores caminhos entre todos os pares de vértices em um grafo ponderado.
     * Encontrado em: https://www.geeksforgeeks.org/dsa/floyd-warshall-algorithm-dp-16/
     * @param dist Matriz de adjacência representando o grafo, onde dist[i][j] inicialmente é o peso da aresta de i para j. Se não houver aresta direta de i para j, o valor é 1e8.
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

    /**
     * Calcula o raio para uma dada combinação de centros.
     * @param dist matriz de distâncias
     * @param centers combinação atual de centros
     * @return raio da combinação atual de centros
     */
    static int computeRadius(int[][] dist, int[] centers) {
        int n = dist.length;
        int radius = 0;

        for (int v = 0; v < n; v++) {
            int minDist = Integer.MAX_VALUE;
            for (int c : centers)
                minDist = Math.min(minDist, dist[v][c]);
            radius = Math.max(radius, minDist);
        }

        return radius;
    }

    static BigInteger nCr(int n, int r) {
        if (r > n) {
            return BigInteger.ZERO;
        }
        return fact(n).divide(fact(r).multiply(fact(n - r)));
    }

    private static BigInteger fact(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
}
