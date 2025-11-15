// Ideia de gerar todas as combinações possíveis de centros e calcular o raio pra cada combinação dada pelo ChatGPT.
// Ajustes foram feitos para adequar ao código já existente e o limite de memória.
public class exactSolution {
    // Como a heap pode acabar estourando, criamos uma classe pra armazenar a melhor solução encontrada até o momento
    private static class Best {
        int radius = Integer.MAX_VALUE;
        int[] centers = null;
    }

    // private static BigInteger iterations = BigInteger.ZERO;

    /**
     * Resolve o problema do k-center exatamente avaliando todas as combinações de k centros.
     * @param dist matriz de distâncias
     * @param k número de centros a escolher
     * @return melhor combinação de centros encontrada
     */
    public static int[] solveKCenterExact(int[][] dist, int k) {
        Best best = new Best();

        combinationEvaluate(dist, new int[k], 1, dist.length, 0, k, best);
        System.out.println("Melhor raio: " + best.radius);
        // System.out.println("Número de iterações: " + iterations + "\n");
        return best.centers;
    }

    /**
     * Gera todas as combinações possíveis de k centros e avalia o raio para cada combinação.
     * @param dist matriz de distâncias
     * @param centers combinação atual de centros
     * @param start índice inicial do vértice para a geração da combinação
     * @param end índice final do vértice para a geração da combinação
     * @param index índice atual na combinação que está sendo gerada
     * @param k número de centros a escolher
     * @param best melhor solução encontrada até o momento
     */
    private static void combinationEvaluate(int[][] dist, int[] centers, int start, int end, int index, int k, Best best) {
        // iterations = iterations.add(BigInteger.ONE);
        if (index == k) {
            // for (int c : centers) {
            //     System.out.print(c + " ");
            // }
            // System.out.println();
            int radius = utils.computeRadius(dist, centers);
            if (radius < best.radius) {
                best.radius = radius;
                best.centers = centers.clone();
            }
            return;
        }

        // i = vértice
        // index = index em centers
        // end - i >= k - index verificação pra parar quando não tiver mais vértices suficientes pra completar uma combinação
        for (int i = start; i < end && end - i >= k - index; i++) {
            centers[index] = i;
            combinationEvaluate(dist, centers, i + 1, end, index + 1, k, best);
        }
    }
}