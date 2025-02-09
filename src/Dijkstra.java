import java.util.*;

public class Dijkstra {
    
    public void processaCaminho(Vertice origem) {
        origem.distanciaMinima = 0;
        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(); // Faz a ordenação dos vértices pelo menor custo
        filaPrioridade.add(origem);

        // Enquanto a fila de prioridade não estiver vazia (ainda há vértices para visitar) 
        while (!filaPrioridade.isEmpty()) {
            Vertice atual = filaPrioridade.poll(); // Remove o vértice com menor custo da fila de prioridade

            // Para cada vizinho do vértice atual faz a verificação do custo
            for (Aresta aresta : atual.vizinho) {
                Vertice vizinho = aresta.trajeto; // Vizinho do vértice atual
                double custo = aresta.custo; // Custo da aresta entre o vértice atual e o vizinho
                double novaDistancia = atual.distanciaMinima + custo; // Custo total do caminho até o vizinho 

                // Se a nova distância for menor que a distância mínima do vizinho, atualiza a distância mínima e o vértice anterior 
                if (novaDistancia < vizinho.distanciaMinima) {
                    vizinho.distanciaMinima = novaDistancia; // Atualiza a distância mínima do vizinho
                    vizinho.anterior = atual; 
                    filaPrioridade.add(vizinho); 
                }
            }
        }
    }

    // Faz a recuperação do menor caminho a partir do vértice de destino
    public ArrayList<Vertice> getMenorCaminho(Vertice destino) { 
        ArrayList<Vertice> caminho = new ArrayList<>();
        for (Vertice v = destino; v != null; v = v.anterior) {
            caminho.add(v); // Adiciona o vértice ao caminho
        }
        Collections.reverse(caminho); // Inverte a ordem dos vértices para mostrar o caminho correto 
        return caminho;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Vertice> grafo = new HashMap<>(); // Faz o mapeamento dos vértices para facilitar a busca

        System.out.println("Insira a quantidade de vértices: ");
        int numVertices = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        // Faz a leitura dos vértices e os adiciona ao grafo
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Nome do vértice " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            grafo.put(nome, new Vertice(nome));
        }

        System.out.println("\nQuantas arestas deseja adicionar?");
        int numArestas = scanner.nextInt();
        scanner.nextLine(); 

        // Criando as arestas
        for (int i = 0; i < numArestas; i++) {
            System.out.println("\nAresta " + (i + 1) + ":");
            System.out.print("Vértice de origem: ");
            String origem = scanner.nextLine();
            System.out.print("Vértice de destino: ");
            String destino = scanner.nextLine();
            System.out.print("Peso da aresta: ");
            double peso = scanner.nextDouble();
            scanner.nextLine(); // Limpar buffer

            // Faz a verificação se os vértices de origem e destino existem no grafo e adiciona a aresta
            if (grafo.containsKey(origem) && grafo.containsKey(destino)) {
                grafo.get(origem).adicionarVizinho(grafo.get(destino), peso);
            } else {
                System.out.println("Erro: Um dos vértices não existe!");
            }
        }

    
        System.out.print("\nEscolha o vértice inicial: ");
        String nomeOrigem = scanner.nextLine();
        Vertice origem = grafo.get(nomeOrigem);

        // Se o vértice de origem não existir, encerra o programa 
        if (origem == null) {
            System.out.println("Vértice não encontrado. Encerrando o programa.");
            scanner.close();
            return;
        }

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.processaCaminho(origem);

        System.out.print("\nEscolha o vértice de destino: ");
        String nomeDestino = scanner.nextLine();
        Vertice destino = grafo.get(nomeDestino);

        if (destino == null) {
            System.out.println("Vértice não encontrado. Encerrando o programa.");
            scanner.close();
            return;
        }

        // Mostra o menor caminho e a distância
        ArrayList<Vertice> caminho = dijkstra.getMenorCaminho(destino);
        System.out.println("\nMenor caminho de " + origem + " até " + destino + ": " + caminho);
        System.out.println("Distância mínima: " + destino.distanciaMinima);

        scanner.close();
    }
}
 
