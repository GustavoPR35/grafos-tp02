public class mainProgram {
    public static void main(String[] args) {
        Grafo g = new Grafo(3);
        g.adicionar(1, 2, 5);
        g.adicionar(1, 3, 2);
        g.adicionar(2, 3, 1);

        g.print();
    }
}
