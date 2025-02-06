import java.util.*;

public class BuscaCegaLargura {

    // Classe que representa o nó que será usado na árvore para a busca
    public static class No {
        int valor;
        No esquerdo; // Filho à esquerda que será visitado primeiro na busca
        No direito;  // Filho à direita que será visitado após o filho à esquerda (se existir)

        public No(int valor) {
            this.valor = valor;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    // Função para a busca cega por largura
    public static boolean buscaEmLargura(No raiz, int alvo) {
        if (raiz == null) {
            return false;
        }

        Queue<No> fila = new LinkedList<>(); // Fila para controlar a ordem de visita nos nós da árvore
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll(); // Remove o nó da frente da fila para que seja visitado em seguida
            System.out.println("Visitando nó: " + atual.valor); // Exibe o processo de busca em largura

            if (atual.valor == alvo) {
                System.out.println("\nNó encontrado: " + alvo);
                return true;
            }

            // Enfileira os filhos se existirem para que sejam visitados depois
            if (atual.esquerdo != null) {
                fila.add(atual.esquerdo);
            }
            if (atual.direito != null) {
                fila.add(atual.direito);
            }
        }

        System.out.println("\nNó " + alvo + " não encontrado na árvore.");
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Trabalho de Grafos - Busca Cega por Largura ===\n");

        System.out.print("Digite a quantidade de nós: ");
        int quantidade = scanner.nextInt();

        Map<Integer, No> nos = new HashMap<>(); // Armazena os nós criados em um mapa para facilitar a busca quando for necessário

        // Cria os nós com valores de 1 até 'quantidade' (que é definida pelo usuário) e os armazena no mapa para que possa ser acessado depois
        for (int i = 1; i <= quantidade; i++) {
            nos.put(i, new No(i));
        }

        // Definição das ligações entre os nós que formarão a árvore e seus respectivos filhos (esquerdo e direito)
        System.out.println("\nInsira a ligação entre os nós (digite -1 -1 para encerrar):");
        System.out.println("Exemplo: '1 2' representa que 2 será filho de 1.\n");

        while (true) {
            System.out.print("Conectar (pai filho): ");
            int pai = scanner.nextInt();
            int filho = scanner.nextInt();

            if (pai == -1 && filho == -1) break; // Encerra a entrada de ligações entre os nós caso o usuário digite -1 -1

            if (nos.containsKey(pai) && nos.containsKey(filho)) {
                No paiNo = nos.get(pai);
                No filhoNo = nos.get(filho);

                // Adiciona o filho à esquerda se estiver vazio, senão à direita para manter a ordem de visita
                if (paiNo.esquerdo == null) {
                    paiNo.esquerdo = filhoNo;
                } else if (paiNo.direito == null) {
                    paiNo.direito = filhoNo;
                } else {
                    System.out.println("Erro: Nó " + pai + " já possui dois filhos.");
                }
            } else {
                System.out.println("Erro: Nó pai ou filho inválido. Insira novamente.");
            }
        }

        // Solicita o nó a ser buscado para o usuário
        System.out.print("\nDigite o nó que deseja buscar: ");
        int alvo = scanner.nextInt();

        System.out.println("\n=== Iniciando Busca Cega por Largura ===");
        buscaEmLargura(nos.get(1), alvo);

        scanner.close();
    }
}
