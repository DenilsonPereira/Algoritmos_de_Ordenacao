package app;

import java.util.*;

public class AlgoritmosOrdenacao {

	public static void main(String[] args) {
		//valor da quantidade de numeros do array
		int tamanho = 200000;

		double tempoB = 0.0;
		double tempoM = 0.0;
		double tempoQ = 0.0;
		
		int maximo = 10000;
		int i, j;
		Random rand = new Random();
		int x[] = new int[tamanho];

		for (i = 0; i < 30; i++) {
			for (j = 0; j <= x.length - 1; j++) {
				x[j] = rand.nextInt(maximo);
			}

			int[] xBubble = Arrays.copyOf(x, x.length);
			int[] xMerge = Arrays.copyOf(x, x.length);
			int[] xQuick = Arrays.copyOf(x, x.length);

			tempoB += bubbleSort(xBubble);
			tempoM += mergeSort(xMerge);
			tempoQ += quickSort(xQuick);
		}

		System.out.println(
				"Média bubble:" + (tempoB / 30) + "\nMédia merge:" + (tempoM / 30) + "\nMédia quick:" + (tempoQ / 30));
	}

	public static double bubbleSort(int vetor[]) {
		int x[] = vetor;
		int n, i, aux;

		long tinicial = System.nanoTime();
		for (n = 1; n <= x.length - 1; n++) {

			for (i = x.length - 1; i >= n; i--) {
				if (x[i] < x[i - 1]) {
					aux = x[i];
					x[i] = x[i - 1];
					x[i - 1] = aux;
				}
			}
		}
		long tfinal = System.nanoTime();

		return (tfinal - tinicial) / 1_000_000_000.0;
	}
	// Quick Sort

	public static double quickSort(int vetor[]) {
		int x[] = vetor;

		long tinicial = System.nanoTime();
		quicksort(x, 0, (x.length - 1));
		long tfinal = System.nanoTime();

		return (tfinal - tinicial) / 1_000_000_000.0;
	}

	public static void troca(int x[], int i, int j) {
		int aux;
		aux = x[i];
		x[i] = x[j];
		x[j] = aux;
	}

	public static int particao(int x[], int p, int r) {
		int pivo, i, j;
		pivo = x[(p + r) / 2];
		i = p - 1;
		j = r + 1;
		while (i < j) {
			do {
				j = j - 1;
			} while (x[j] > pivo);

			do {
				i = i + 1;
			} while (x[i] < pivo);

			if (i < j)
				troca(x, i, j);
		}
		return j;
	}

	public static void quicksort(int x[], int p, int r) {
		int q;
		if (p < r) {
			q = particao(x, p, r);
			quicksort(x, p, q);
			quicksort(x, q + 1, r);
		}
	}

	// MergeSort

	public static double mergeSort(int vetor[]) {
		int x[] = vetor;

		long tinicial = System.nanoTime();
		merge(x, 0, (x.length - 1));
		long tfinal = System.nanoTime();

		return (tfinal - tinicial) / 1_000_000_000.0;
	}

	public static void merge(int x[], int inicio, int fim) {
		int meio;
		if (inicio < fim) {
			meio = (inicio + fim) / 2;
			merge(x, inicio, meio);
			merge(x, meio + 1, fim);
			intercala(x, inicio, fim, meio);
		}
	}

	public static void intercala(int x[], int inicio, int fim, int meio) {
		int poslivre, inicioVetor1, inicioVetor2, i;
		int aux[] = new int[x.length];
		inicioVetor1 = inicio;
		inicioVetor2 = meio + 1;
		poslivre = inicio;

		while (inicioVetor1 <= meio && inicioVetor2 <= fim) {
			if (x[inicioVetor1] <= x[inicioVetor2]) {
				aux[poslivre] = x[inicioVetor1];
				inicioVetor1 = inicioVetor1 + 1;
			} else {
				aux[poslivre] = x[inicioVetor2];
				inicioVetor2 = inicioVetor2 + 1;
			}
			poslivre += 1;
		}

		for (i = inicioVetor1; i <= meio; i++) {
			aux[poslivre] = x[i];
			poslivre += 1;
		}

		for (i = inicioVetor2; i <= fim; i++) {
			aux[poslivre] = x[i];
			poslivre += 1;
		}

		for (i = inicio; i <= fim; i++) {
			x[i] = aux[i];
		}
	}
}
