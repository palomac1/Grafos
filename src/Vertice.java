import java.util.ArrayList;

// Classe que representa um vértice do grafo e armazena informações sobre o vértice, como distância mínima, vértice anterior e vizinhos
public class Vertice implements Comparable<Vertice> {
    public final String nome;
    public double distanciaMinima = Double.POSITIVE_INFINITY; // Distância mínima do vértice até o vértice de origem (inicial)
    public Vertice anterior = null; 
    public ArrayList<Aresta> vizinho = new ArrayList<>(); 

    // Construtor da classe Vertice que recebe o nome do vértice
    public Vertice(String nome) {
        this.nome = nome;
    }

    // Adiciona um vizinho ao vértice
    public void adicionarVizinho(Vertice trajeto, double custo) {
        vizinho.add(new Aresta(trajeto, custo));
    }

    // Método compareTo que compara a distância mínima entre dois vértices
    @Override
    public int compareTo(Vertice outro) {
        return Double.compare(this.distanciaMinima, outro.distanciaMinima);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
