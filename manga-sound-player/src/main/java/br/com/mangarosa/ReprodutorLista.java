package br.com.mangarosa;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ReprodutorLista {
    private ListaReproducao listaReproducao;
    private String status = "stopped";
    private Clip clip;
    private int musicaAtual = 0;

    public void setListaReproducao(ListaReproducao listaReproducao) {
        this.listaReproducao = listaReproducao;
        musicaAtual = 0;
    }

    public void play() {
        if (listaReproducao == null || listaReproducao.isVazia()) {
            System.out.println("Nenhuma música para tocar.");
            return;
        }

        if (musicaAtual >= listaReproducao.tamanho()) {
            musicaAtual = 0;
        }

        Musica musica = listaReproducao.obterMusica(musicaAtual);
        try {
            File audioFile = new File(musica.getPath());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            status = "playing";

            System.out.println("Tocando: " + musica.getTitulo() + " de " + musica.getArtista());
        } catch (Exception e) {
            System.out.println("Erro ao tocar música: " + e.getMessage());
        }
    }

    public void pause() {
        if (clip != null && status.equals("playing")) {
            clip.stop();
            status = "paused";
        }
    }

    public void restartMusica() {
        if (clip != null) {
            clip.setMicrosecondPosition(0);
            clip.start();
            status = "playing";
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            status = "stopped";
        }
    }

    public void proximaMusica() {
        stop();
        if (musicaAtual < listaReproducao.tamanho() - 1) {
            musicaAtual++;
        } else {
            musicaAtual = 0;
        }
        play();
    }

    public void voltarMusica() {
        if (clip != null && clip.getMicrosecondPosition() > 10_000_000) {
            restartMusica();
        } else {
            stop();
            if (musicaAtual > 0) {
                musicaAtual--;
            } else {
                musicaAtual = listaReproducao.tamanho() - 1;
            }
            play();
        }
    }
}
