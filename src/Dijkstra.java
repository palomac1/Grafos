public import java.util.*;

public class Dijkstra {

    public static class No {
        int id;
        int distancia;

        public No(int id, int distancia) {
            this.id = id;
            this.distancia = distancia;
        }
    }

    public static int[] dijkstra(Map<Integer, List<No>> grafo, int origem, int n) {
        int[] distancias = new int[n];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origem] = 0;
        
        PriorityQueue<No> fila = new PriorityQueue<>(Comparator.comparingInt(no -> no.distancia));
        fila.add(new No(origem, 0));
        
        while (!fila.isEmpty()) {
            No atual = fila.poll();
            int idAtual = atual.id;
            int distanciaAtual = atual.distancia;
            
            if (distanciaAtual > distancias[idAtual]) continue;
            
            for (No vizinho : grafo.getOrDefault(idAtual, new ArrayList<>())) {
                int novaDistancia = distancias[idAtual] + vizinho.distancia;
                if (novaDistancia < distancias[vizinho.id]) {
                    distancias[vizinho.id] = novaDistancia;
                    fila.add(new No(vizinho.id, novaDistancia));
                }
            }
        }
        return distancias;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o número de vértices: ");
        int n = scanner.nextInt();
        
        Map<Integer, List<No>> grafo = new HashMap<>();
        System.out.println("Digite as arestas no formato 'origem destino peso' (-1 -1 -1 para encerrar):");
        
        while (true) {
            int origem = scanner.nextInt();
            int destino = scanner.nextInt();
            int peso = scanner.nextInt();
            
            if (origem == -1 && destino == -1 && peso == -1) break;
            grafo.computeIfAbsent(origem, k -> new ArrayList<>()).add(new No(destino, peso));
            grafo.computeIfAbsent(destino, k -> new ArrayList<>()).add(new No(origem, peso)); // Para grafo não direcionado
        }
        
        System.out.print("Digite o vértice de origem: ");
        int origem = scanner.nextInt();
        
        int[] distancias = dijkstra(grafo, origem, n);
        
        System.out.println("Menor distância a partir do vértice " + origem + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("Vértice " + i + ": " + (distancias[i] == Integer.MAX_VALUE ? "Infinito" : distancias[i]));
        }
        
        scanner.close();
    }
}
 {
    
}
