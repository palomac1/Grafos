import java.util.*;

public class BuscaCegaProfundidade {

    public static class No {
        int valor;
        No esquerdo;
        No direito;

        public No(int valor) {
            this.valor = valor;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    public static void buscaEmProfundidade(No raiz) {
        if (raiz == null) {
            System.out.println("A árvore está vazia.");
            return;
        }

        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        System.out.println("\n=== Iniciando Busca Cega por Profundidade ===");
        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            System.out.println("Visitando nó: " + atual.valor);

            if (atual.direito != null) {
                pilha.push(atual.direito);
            }
            if (atual.esquerdo != null) {
                pilha.push(atual.esquerdo);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Trabalho de Grafos - Busca Cega por Profundidade ===\n");

        System.out.print("Digite a quantidade de nós: ");
        int quantidade = scanner.nextInt();

        Map<Integer, No> nos = new HashMap<>();
        for (int i = 1; i <= quantidade; i++) {
            nos.put(i, new No(i));
        }

        System.out.println("\nInsira a ligação entre os nós (digite -1 -1 para encerrar):");
        System.out.println("Exemplo: '1 2' representa que 2 será filho de 1.\n");

        while (true) {
            System.out.print("Conectar (pai filho): ");
            int pai = scanner.nextInt();
            int filho = scanner.nextInt();

            if (pai == -1 && filho == -1) break;

            if (nos.containsKey(pai) && nos.containsKey(filho)) {
                No paiNo = nos.get(pai);
                No filhoNo = nos.get(filho);

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

        buscaEmProfundidade(nos.get(1));

        scanner.close();
    }
}
