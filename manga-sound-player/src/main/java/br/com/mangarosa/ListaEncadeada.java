package br.com.mangarosa;

public class ListaEncadeada {
    private No cabeca;
    private int tamanho;

    public void append(Object valor) {
        No novo = new No(valor);
        if (cabeca == null) {
            cabeca = novo;
        } else {
            No atual = cabeca;
            while (atual.getProx() != null) {
                atual = atual.getProx();
            }
            atual.setProx(novo);
        }
        tamanho++;
    }
