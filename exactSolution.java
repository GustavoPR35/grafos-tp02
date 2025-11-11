public class exactSolution {
    static class Best {
        int radius = Integer.MAX_VALUE;
        int[] centers = null;
    }

    public static int[] solveKCenterExact(int[][] dist, int k) {
        int n = dist.length - 1;
        Best best = new Best();
        combinationEvaluate(dist, new int[k], 1, n, 0, k, best);
        System.out.println("Melhor raio: " + best.radius);
        return best.centers;
    }

    static int computeRadius(int[][] dist, int[] centers) {
        int n = dist.length - 1;
        int radius = 0;

        for (int v = 0; v < n; v++) {
            int minDist = Integer.MAX_VALUE;
            for (int c : centers)
                minDist = Math.min(minDist, dist[v][c]);
            radius = Math.max(radius, minDist);
        }

        return radius;
    }

    static void combinationEvaluate(int[][] dist, int[] data, int start, int end, int index, int r, Best best) {
        if (index == r) {
            int radius = computeRadius(dist, data);
            if (radius < best.radius) {
                best.radius = radius;
                best.centers = data.clone();
            }
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = i;
            combinationEvaluate(dist, data, i + 1, end, index + 1, r, best);
        }
    }
}