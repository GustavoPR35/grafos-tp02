import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Aresta {
    int destino;
    int peso; // Peso da aresta entre o vértice pai da lista de adjacência e o vértice de destino 

    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

// não direcionado
public class Grafo {
    private Map<Integer, List<Aresta>> lista;

    public Grafo(int qntVertices) {
        this.lista = new HashMap<>();
    }

    public void adicionar(int origem, int destino, int peso) {
        lista.putIfAbsent(origem, new ArrayList<>());
        lista.putIfAbsent(destino, new ArrayList<>());

        // nos dois pq é não direcionado
        lista.get(origem).add(new Aresta(destino, peso));
        lista.get(destino).add(new Aresta(origem, peso));
    }

    // começa de 0
    public int[][] getMatrizAdjacencia() {
        int n = lista.size();
        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matriz[i][j] = 0;
                } else {
                    matriz[i][j] = (int)1e8;
                }
            }
        }

        for (Entry<Integer, List<Aresta>> v : lista.entrySet()) {
            int origem = v.getKey();
            for (Aresta vw : v.getValue()) {
                matriz[origem-1][vw.destino-1] = vw.peso;
            }
        }

        return matriz;
    }

    public void print() {
        for (Entry<Integer, List<Aresta>> v : lista.entrySet()) {
            System.out.print("[" + v.getKey() + "]: ");
            for (Aresta vw : v.getValue()) {
                System.out.print("{" + vw.destino + ", " + vw.peso + "} ");
            }
            System.out.println();
        }
    }

    public void printListaAdjVertice(int v) {
        List<Aresta> arestas = lista.get(v);
        System.out.print("[" + v + "]: ");
        for (Aresta vw : arestas) {
            System.out.print("{" + vw.destino + ", " + vw.peso + "} ");
        }
        System.out.println();
    }
}