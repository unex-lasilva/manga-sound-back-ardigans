package br.com.mangarosa;

import java.io.IOException;
import java.nio.file.*;

public class MangaController {
    private ListaEncadeada repositorioMusica = new ListaEncadeada();
    private ListaEncadeada listasReproducao = new ListaEncadeada();
    private ReprodutorLista reprodutorLista = new ReprodutorLista();
    private final String REPOSITORIO_PATH = "repositorio/";

    public void adicionarMusica(String titulo, String path, String artista) {
        try {
            Path origem = Paths.get(path);
            String novoPath = REPOSITORIO_PATH + origem.getFileName();
            Files.copy(origem, Paths.get(novoPath), StandardCopyOption.REPLACE_EXISTING);
            Musica musica = new Musica(titulo, artista, novoPath);
            repositorioMusica.append(musica);
            System.out.println("Música adicionada ao repositório.");
        } catch (IOException e) {
            System.out.println("Erro ao copiar arquivo: " + e.getMessage());
        }
    }

    public void criarListaReproducao(String titulo) {
        listasReproducao.append(new ListaReproducao(titulo));
        System.out.println("Lista criada.");
    }

    public void adicionarMusicaListaReproducao(String tituloMusica, String tituloLista) {
        Musica m = buscarMusica(tituloMusica);
        ListaReproducao lista = buscarLista(tituloLista);
        if (m != null && lista != null) {
            lista.addMusica(m);
        }
    }

    public void adicionarMusicaListaReproducaoEmPosicao(String tituloMusica, String tituloLista, int posicao) {
        Musica m = buscarMusica(tituloMusica);
        ListaReproducao lista = buscarLista(tituloLista);
        if (m != null && lista != null) {
            lista.inserirMusicaEm(posicao, m);
        }
    }

    public void removerMusicaListaReproducao(String tituloMusica, String tituloLista) {
        ListaReproducao lista = buscarLista(tituloLista);
        if (lista != null) {
            for (int i = 0; i < lista.tamanho(); i++) {
                Musica m = lista.obterMusica(i);
                if (m.getTitulo().equalsIgnoreCase(tituloMusica)) {
                    lista.removerMusica(i);
                    return;
                }
            }
        }
    }

    public void removerMusicaListaReproducaoEmPosicao(String tituloLista, int posicao) {
        ListaReproducao lista = buscarLista(tituloLista);
        if (lista != null) {
            lista.removerMusica(posicao);
        }
    }

    public void reproduzirListaDeReproducao(String tituloLista) {
        ListaReproducao lista = buscarLista(tituloLista);
        if (lista != null) {
            reprodutorLista.setListaReproducao(lista);
            reprodutorLista.play();
        }
    }

    public void pausarMusica() {
        reprodutorLista.pause();
    }

    public void voltarMusica() {
        reprodutorLista.voltarMusica();
    }

    public void passarMusica() {
        reprodutorLista.proximaMusica();
    }

    public void pararLista() {
        reprodutorLista.stop();
    }

    private Musica buscarMusica(String titulo) {
        for (int i = 0; i < repositorioMusica.size(); i++) {
            Musica m = (Musica) repositorioMusica.get(i);
            if (m.getTitulo().equalsIgnoreCase(titulo)) return m;
        }
        return null;
    }

    private ListaReproducao buscarLista(String titulo) {
        for (int i = 0; i < listasReproducao.size(); i++) {
            ListaReproducao l = (ListaReproducao) listasReproducao.get(i);
            if (l.getTitulo().equalsIgnoreCase(titulo)) return l;
        }
        return null;
    }
    public ListaReproducao getLista(String titulo) {
        for (int i = 0; i < listasReproducao.size(); i++) {
            ListaReproducao lista = (ListaReproducao) listasReproducao.get(i);
            if (lista.getTitulo().equalsIgnoreCase(titulo)) {
                return lista;
            }
        }
        return null;
    }

}
