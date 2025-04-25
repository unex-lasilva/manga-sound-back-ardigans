package br.com.mangarosa;

import java.util.Scanner;

public class MangaSoundApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MangaController controller = new MangaController();

        while (true) {
            System.out.println("\n===== MANGA SOUND =====");
            System.out.println("1. Adicionar Música ao Repositório");
            System.out.println("2. Criar Lista de Reprodução");
            System.out.println("3. Editar Lista de Reprodução");
            System.out.println("4. Executar Lista de Reprodução");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Título da música: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Caminho do arquivo (.wav): ");
                    String path = scanner.nextLine();
                    System.out.print("Nome do artista: ");
                    String artista = scanner.nextLine();
                    controller.adicionarMusica(titulo, path, artista);
                    break;

                case 2:
                    System.out.print("Nome da nova lista: ");
                    controller.criarListaReproducao(scanner.nextLine());
                    break;

                case 3:
                    System.out.print("Nome da lista para editar: ");
                    String nomeListaEdit = scanner.nextLine();
                    System.out.println("1 - Adicionar música");
                    System.out.println("2 - Adicionar música em posição");
                    System.out.println("3 - Remover música por título");
                    System.out.println("4 - Remover música por posição");
                    int edicao = scanner.nextInt();
                    scanner.nextLine();

                    switch (edicao) {
                        case 1:
                            System.out.print("Título da música: ");
                            controller.adicionarMusicaListaReproducao(scanner.nextLine(), nomeListaEdit);
                            break;
                        case 2:
                            System.out.print("Título da música: ");
                            String musica = scanner.nextLine();
                            System.out.print("Posição: ");
                            controller.adicionarMusicaListaReproducaoEmPosicao(musica, nomeListaEdit, scanner.nextInt());
                            scanner.nextLine();
                            break;
                        case 3:
                            System.out.print("Título da música: ");
                            controller.removerMusicaListaReproducao(scanner.nextLine(), nomeListaEdit);
                            break;
                        case 4:
                            System.out.print("Posição da música: ");
                            controller.removerMusicaListaReproducaoEmPosicao(nomeListaEdit, scanner.nextInt());
                            scanner.nextLine();
                            break;
                    }
                    break;

                case 4:
                    System.out.print("Nome da lista a executar: ");
                    String nomeListaExec = scanner.nextLine();
                    ListaReproducao lista = controller.getLista(nomeListaExec);

                    if (lista == null || lista.isVazia()) {
                        System.out.println("Lista inválida ou vazia.");
                        break;
                    }

                    for (int i = 0; i < lista.tamanho(); i++) {
                        Musica musicaAtual = lista.obterMusica(i);
                        System.out.println("\nReproduzindo: " + musicaAtual.getTitulo() + " de " + musicaAtual.getArtista());

                        try {
                            SimpleAudioPlayer player = new SimpleAudioPlayer(musicaAtual.getPath());
                            player.play();

                            boolean executando = true;
                            while (executando) {
                                System.out.println("\n1 - Pausar");
                                System.out.println("2 - Retomar");
                                System.out.println("3 - Reiniciar");
                                System.out.println("4 - Pular para tempo específico");
                                System.out.println("5 - Parar e ir para próxima");
                                System.out.print("Ação: ");
                                int acao = scanner.nextInt();

                                switch (acao) {
                                    case 1: player.pause(); break;
                                    case 2: player.resumeAudio(); break;
                                    case 3: player.restart(); break;
                                    case 4:
                                        System.out.print("Tempo (em microssegundos): ");
                                        long tempo = scanner.nextLong();
                                        player.jump(tempo);
                                        break;
                                    case 5:
                                        player.stop();
                                        executando = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Erro ao executar a música: " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Encerrando o MangaSound. Até mais!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
