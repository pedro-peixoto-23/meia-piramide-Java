/*
     * Disciplina: Programação Orientada a Objetos (POO)
     * Autor: Pedro Peixoto Viana de Oliveira
     * Data: 03/04/2023
     * Curso: Ciência da Computação | Período: 2° período
 */

import java.util.Arrays; // Classe utilizada para aproveitar a função "Arrays.toString(array) que retorna o array no formato '[1,2,3]'"
import java.util.Scanner; // Classe utilizada para pedir os dados necessários ao usuário

public class ProjetoPOOPedroPeixoto {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // --- Pedindo os dados ao usuário --- //
        System.out.println("------------ Insira todos os dados corretamente ------------");
        System.out.print("| Insira a quantidade de elementos: ");
        int quantidade_elementos_usuario = input.nextInt();
        input.nextLine();

        String lista_elementos;
        do {
            System.out.print("| Insira os elementos separados por espaço: ");
            lista_elementos = input.nextLine();
        } while ((verificarAdicaoEspacosIndevidos(lista_elementos) == false) || (verificarQuantidadeUsuarioIgualQuantidadeInserida(quantidade_elementos_usuario, lista_elementos)) == false);
        System.out.println("------------------------------------------------------------");

        // --- Quebrando os valores que o usuário inseriu com base nos espaços e inserindo em um vetor --- //
        String[] vetor_lista_elementos = lista_elementos.split(" ");

        // --- Verificando a quantidade de linhas a meia pirâmide vai ter e criando um vetor que vai comportar essas linhas --- //
        int quantidade_linhas_piramide = verificarQuantidadeDeLinhasPiramide(quantidade_elementos_usuario);
        String[][] linhas_piramide = new String[quantidade_linhas_piramide][];

        // --- Adicionando para cada linha os seus elementos usando um for para percorrer as linhas e outro for para os elementos --- //
        int posicao_vetor_elementos = 0;
        int quantidade_elementos_linha_for = 1;
        for (int i = 0; i < quantidade_linhas_piramide; i++) {
            linhas_piramide[i] = new String[quantidade_elementos_linha_for];
            for (int j = 0; j < quantidade_elementos_linha_for; j++) {
                linhas_piramide[i][j] = vetor_lista_elementos[posicao_vetor_elementos];
                posicao_vetor_elementos += 1;
            }
            quantidade_elementos_linha_for += 1;
        }

        // --- Imprimindo a pirâmide utilizando-se de um for para percorrer as linhas da pirâmide --- //
        System.out.println("-------- Pirâmide --------");
        for (int i = 0; i < quantidade_linhas_piramide; i++) {
            System.out.println(Arrays.toString(linhas_piramide[i]));
        }
        System.out.println("--------------------------");

        // --- Criando um vetor inteiro que vai comportar o que antes estava em um vetor do tipo string e inserindo as linhas e elementos --- //
        // --- O primeiro for serve para iterar as linhas da pirâmide e o segundo for para iterar os elementos de cada linha --- //
        quantidade_elementos_linha_for = 1;
        int[][] linhas_piramide_int = new int[quantidade_linhas_piramide][];
        for (int i = 0; i < quantidade_linhas_piramide; i++) {
            linhas_piramide_int[i] = new int[quantidade_elementos_linha_for];
            for (int j = 0; j < quantidade_elementos_linha_for; j++) {
                linhas_piramide_int[i][j] = Integer.parseInt(linhas_piramide[i][j]);
            }
            quantidade_elementos_linha_for += 1;
        }

        // --- Somando os menores termos da cada linha da pirâmide. Além de salvar os menores termos em um vetor --- //
        quantidade_elementos_linha_for = 1;
        int[] vetor_menores_linhas = new int[quantidade_linhas_piramide];
        int soma = 0;
        for (int i = 0; i < quantidade_linhas_piramide; i++) {
            int menor = linhas_piramide_int[i][0];
            for (int j = 0; j < quantidade_elementos_linha_for; j++) {
                if (linhas_piramide_int[i][j] < menor) {
                    menor = linhas_piramide_int[i][j];
                }
            }
            vetor_menores_linhas[i] = menor;
            soma += menor;
            quantidade_elementos_linha_for += 1;
        }

        // --- Imprimindo as somas e o vetor com os menores valores de cada linha --- //
        System.out.println("------------------------ Resultados ------------------------");
        System.out.println("| Menores números de cada linha: " + Arrays.toString(vetor_menores_linhas));
        System.out.println("| A soma dos menores números de cada linha é igual a: " + soma);
        System.out.println("------------------------------------------------------------");
    }


    // --- Função responsável por verificar a quantidade de linhas da pirâmide através da quantidade de seus elementos --- //
    public static int verificarQuantidadeDeLinhasPiramide(int quantidade_elementos) {
        int soma_elementos = 1;
        int quantidade_elementos_proxima_linha = 2;
        int quantidade_linhas = 0;

        while (soma_elementos <= quantidade_elementos) {
            // System.out.printf("Ao entrar no While: #contador: %d   #soma_elementos: %d  #quantidade_linhas: %d\n", quantidade_elementos_proxima_linha, soma_elementos, quantidade_linhas);
            soma_elementos += quantidade_elementos_proxima_linha;
            quantidade_elementos_proxima_linha += 1;
            quantidade_linhas += 1;
            // System.out.printf("Ao sair no While: #contador: %d   #soma_elementos: %d  #quantidade_linhas: %d\n\n", quantidade_elementos_proxima_linha, soma_elementos, quantidade_linhas);
        }

        return quantidade_linhas;
    }


    // --- Função responsável por verificar se existem espaços indevidos nos valores que o usuário inseriu --- //
    public static boolean verificarAdicaoEspacosIndevidos(String elementos) {
        char[] array_elementos = elementos.toCharArray();
        int tamanho_array = array_elementos.length;

        if ((array_elementos[0] == ' ') || (array_elementos[tamanho_array - 1] == ' ')) {
            return false;
        }

        for (int i = 0; i < tamanho_array; i++) {
            if (i != (tamanho_array - 1)) {
                if (array_elementos[i] == ' ' && array_elementos[i + 1] == ' ') // verificando se tem dois espaços vazios seguidos
                    return false;
            }
        }

        return true;
    }


    // --- Função responsável por verificar se o usuário inseriu apenas a quantidade de números que havia dito que colocaria --- //
    public static boolean verificarQuantidadeUsuarioIgualQuantidadeInserida(int qtd_elementos, String elementos) {
        String[] elementos_separados_espaco = elementos.split(" ");
        if (elementos_separados_espaco.length > qtd_elementos || elementos_separados_espaco.length < qtd_elementos)
            return false;
        else
            return true;
    }
}
