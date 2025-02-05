import java.util.*;

class No {
    int valor;
    List<No> filhos;

    public No(int valor) {
        this.valor = valor;
        this.filhos = new ArrayList<>();
    }
}

public class BuscaEmLargura {

    // Método para busca em largura
    public static void buscaEmLargura(No raiz, int alvo) {
        if (raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.println("Visitando nó: " + atual.valor);

            if (atual.valor == alvo) {
                System.out.println("Encontrado: " + alvo);
                return;
            }

            fila.addAll(atual.filhos);
        }

        System.out.println("Valor não encontrado: " + alvo);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nTrabalho de Grafos - Busca em Largura\n");

        System.out.print("Digite a quantidade de nós: ");
        int quantidade = scanner.nextInt();

        Map<Integer, No> nos = new HashMap<>();

        for (int i = 1; i <= quantidade; i++) {
            nos.put(i, new No(i));
        }

        System.out.println("Defina as ligação entre os nós (digite -1 -1 para sair):");
        // Exemplo:
        //1(pai) 2(filho)
        //1 3
        //2 4
        //2 5
        //-1 -1

        while (true) {
            int pai = scanner.nextInt();
            int filho = scanner.nextInt();
            if (pai == -1 && filho == -1) break;

            if (nos.containsKey(pai) && nos.containsKey(filho)) {
                nos.get(pai).filhos.add(nos.get(filho));
            } else {
                System.out.println("Inserção inválida, tente novamente.");
            }
        }

        System.out.print("\nDigite o valor do nó que deseja procurar: ");
        int alvo = scanner.nextInt();

        buscaEmLargura(nos.get(1), alvo);

        scanner.close();
    }
}
