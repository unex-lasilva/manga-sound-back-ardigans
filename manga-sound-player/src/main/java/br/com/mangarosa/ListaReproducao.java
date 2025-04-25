package br.com.mangarosa;

public class ListaReproducao {
    private String titulo;
    private ListaEncadeada lista;

    public ListaReproducao(String titulo) {
        this.titulo = titulo;
        this.lista = new ListaEncadeada();
    }

    public void addMusica(Musica musica) {
        lista.append(musica);
    }

    public boolean removerMusica(int posicao) {
        return lista.remove(posicao);
    }

    public void inserirMusicaEm(int posicao, Musica musica) {
        lista.insertAt(posicao, musica);
    }

    public boolean isVazia() {
        return lista.isEmpty();
    }

    public int tamanho() {
        return lista.size();
    }

    public void criarListaAPartirDe(ListaReproducao outra) {
        this.lista.clear();
        this.lista.addAll(outra.lista);
    }

    public int posicaoDa(Musica musica) {
        return lista.indexOf(musica);
    }

    public boolean contemMusica(Musica musica) {
        return lista.contains(musica);
    }

    public Musica obterMusica(int posicao) {
        return (Musica) lista.get(posicao);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
