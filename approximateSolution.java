// Implementação do algoritmo de Gonzalez para o problema de k-centers
// Este é um algoritmo de aproximação 2-ótimo, ou seja, a solução encontrada tem raio no máximo 2 vezes o raio ótimo
public class approximateSolution {

    /**
     * Resolve o problema do k-center usando o algoritmo de Gonzalez (aproximação 2-ótima).
     * Algoritmo:
     * 1. Escolhe um vértice arbitrário como primeiro centro
     * 2. Para cada novo centro, escolhe o vértice que está mais distante de todos os centros já escolhidos
     * 3. Repete até ter k centros
     * 
     * @param dist matriz de distâncias entre todos os vértices
     * @param k número de centros a escolher
     * @return array com os índices dos k centros escolhidos
     */
    public static int[] solveKCenterApproximate(int[][] dist, int k) {
        int n = dist.length;
        int[] centers = new int[k];
        boolean[] isCenter = new boolean[n];

        // Array para armazenar a distância mínima de cada vértice ao centro mais próximo
        int[] minDistToCenter = new int[n];

        // Inicializa todas as distâncias como infinito
        for (int i = 0; i < n; i++) {
            minDistToCenter[i] = Integer.MAX_VALUE;
        }

        System.out.println("Executando algoritmo de Gonzalez (aproximação 2-ótima)...\n");

        // Escolhe o primeiro centro arbitrariamente (vértice 0)
        centers[0] = 0;
        isCenter[0] = true;

        // Atualiza as distâncias mínimas com base no primeiro centro
        for (int v = 0; v < n; v++) {
            minDistToCenter[v] = dist[v][centers[0]];
        }

        // Escolhe os k-1 centros restantes
        for (int i = 1; i < k; i++) {
            int farthestVertex = -1;
            int maxDist = -1;

            // Encontra o vértice que está mais distante de todos os centros já escolhidos
            for (int v = 0; v < n; v++) {
                if (!isCenter[v] && minDistToCenter[v] > maxDist) {
                    maxDist = minDistToCenter[v];
                    farthestVertex = v;
                }
            }

            // Adiciona o vértice mais distante como novo centro
            centers[i] = farthestVertex;
            isCenter[farthestVertex] = true;

            // Atualiza as distâncias mínimas com base no novo centro
            for (int v = 0; v < n; v++) {
                minDistToCenter[v] = Math.min(minDistToCenter[v], dist[v][farthestVertex]);
            }
        }

        // Calcula o raio final da solução
        int radius = utils.computeRadius(dist, centers);
        System.out.println("Melhor raio (aproximado): " + radius);

        return centers;
    }
}
