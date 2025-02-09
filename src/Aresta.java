// Classe que representa uma aresta de um grafo e realiza a ligação entre dois vértices para formar um caminho
public class Aresta {
    public Vertice trajeto; // Vértice de destino
    public double custo; // Custo da aresta

    public Aresta(Vertice trajeto, double custo) {
        this.trajeto = trajeto;
        this.custo = custo;
    }
}
