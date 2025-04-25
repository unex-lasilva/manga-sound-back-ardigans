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

    public boolean remove(int posicao) {
        if (posicao < 0 || posicao >= tamanho) return false;
        if (posicao == 0) {
            cabeca = cabeca.getProx();
        } else {
            No atual = cabeca;
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.getProx();
            }
            atual.setProx(atual.getProx().getProx());
        }
        tamanho--;
        return true;
    }

    public void insertAt(int posicao, Object valor) {
        if (posicao < 0 || posicao > tamanho) return;
        No novo = new No(valor);
        if (posicao == 0) {
            novo.setProx(cabeca);
            cabeca = novo;
        } else {
            No atual = cabeca;
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.getProx();
            }
            novo.setProx(atual.getProx());
            atual.setProx(novo);
        }
        tamanho++;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int size() {
        return tamanho;
    }

    public void clear() {
        cabeca = null;
        tamanho = 0;
    }

    public Object get(int posicao) {
        if (posicao < 0 || posicao >= tamanho) return null;
        No atual = cabeca;
        for (int i = 0; i < posicao; i++) {
            atual = atual.getProx();
        }
        return atual.getValor();
    }

    public int indexOf(Object value) {
        No atual = cabeca;
        int index = 0;
        while (atual != null) {
            if (atual.getValor().equals(value)) {
                return index;
            }
            atual = atual.getProx();
            index++;
        }
        return -1;
    }

    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    public void addAll(ListaEncadeada outraLista) {
        for (int i = 0; i < outraLista.size(); i++) {
            this.append(outraLista.get(i));
        }
    }
}
