package quantum.play.store;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import quantum.play.store.DAO.*;
import quantum.play.store.Model.*;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Carrinho_has_JogoDAO carrinhoHasJogoDAO = new Carrinho_has_JogoDAO();
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        JogoDAO jogoDAO = new JogoDAO();
        while (flag) {
            System.out.println();
            System.out.println("Bem vindo ao sistema da Quantum Play Store!");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Pesquisar um usuário");
            System.out.println("3 - Listar todos os usuários");
            System.out.println("4 - Remover um usuário");
            System.out.println("5 - Atualizar um usuário");
            System.out.println("6 - Atualizar um endereço de usuário");
            System.out.println("7 - Adicionar jogo ao carrinho de um usuário");
            System.out.println("8 - Remover jogo do carrinho de um usuário");
            System.out.println("9 - Listar jogos do carrinho de um usuário");
            System.out.println("0 - Sair");
            System.out.println();

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    // Cadastrar usuário
                    System.out.println("Cadastrar usuário");
                    System.out.println("Primeiro, digite os dados pessoais:");
                    System.out.print("Nome: ");
                    sc.nextLine();
                    String nomeCadastro = sc.nextLine();
                    System.out.print("Sexo: ");
                    String sexoCadastro = sc.nextLine();
                    System.out.print("Idade: ");
                    int idadeCadastro = sc.nextInt();

                    System.out.println("Ótimo, agora, digite os dados do endereço de " + nomeCadastro + ":");
                    sc.nextLine();
                    System.out.print("Rua: ");
                    String ruaCadastro = sc.nextLine();
                    System.out.print("Número: ");
                    String numeroCadastro = sc.nextLine();
                    System.out.print("CEP: ");
                    String cepCadastro = sc.nextLine();
                    System.out.print("Cidade: ");
                    String cidadeCadastro = sc.nextLine();
                    System.out.print("Estado: ");
                    String estadoCadastro = sc.nextLine();
                    System.out.print("Complemento: ");
                    String complementoCadastro = sc.nextLine();

                    Endereco enderecoCadastro = new Endereco(0, ruaCadastro, numeroCadastro, cepCadastro,
                            cidadeCadastro, estadoCadastro, complementoCadastro, 0);

                    Usuario novoUsuarioCadastro = new Usuario(0, nomeCadastro, sexoCadastro, idadeCadastro, enderecoCadastro);
                    usuarioDAO.insertUsuario(novoUsuarioCadastro);
                    break;

                case 2:
                    System.out.println("Pesquisar usuário");
                    System.out.print("Digite o nome do usuário: ");
                    sc.nextLine();
                    String nomePesquisa = sc.nextLine();
                    List<Usuario> usuariosEncontrados = usuarioDAO.getUsuariosByNome(nomePesquisa);
                    if (!usuariosEncontrados.isEmpty()) {
                        System.out.println("Usuários encontrados:");
                        for (Usuario user : usuariosEncontrados) {
                            System.out.println();
                            System.out.println("ID: "+user.getId());
                            System.out.println("Nome: "+user.getNome());
                            System.out.println("Idade: "+user.getIdade());
                            System.out.println("Sexo: "+user.getSexo());
                            System.out.println("Endereço: "+user.getEndereco().getRua()+", "+user.getEndereco().getNumero()+", "+user.getEndereco().getCidade()+", "+user.getEndereco().getEstado()+", "+user.getEndereco().getCep()+", "+user.getEndereco().getComplemento());
                            System.out.println();
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;


                case 3:
                    // Listar todos os usuários
                    System.out.println("Listar todos os usuários");
                    List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
                    Collections.sort(usuarios);
                    for (Usuario user : usuarios) {
                        System.out.println();
                        System.out.println("ID: "+user.getId());
                        System.out.println("Nome: "+user.getNome());
                        System.out.println("Idade: "+user.getIdade());
                        System.out.println("Sexo: "+user.getSexo());
                        System.out.println("Endereço: "+user.getEndereco().getRua()+", "+user.getEndereco().getNumero()+", "+user.getEndereco().getCidade()+", "+user.getEndereco().getEstado()+", "+user.getEndereco().getCep()+", "+user.getEndereco().getComplemento());
                        System.out.println();
                    }
                    break;

                case 4:
                    // Remover um usuário
                    System.out.println("Remover usuário");
                    System.out.print("Digite o ID do usuário a ser removido: ");
                    int idRemocao = sc.nextInt();
                    boolean removido = usuarioDAO.deleteUsuario(idRemocao);
                    if (removido) {
                        System.out.println("Usuário removido com sucesso.");
                    } else {
                        System.out.println("Falha ao remover o usuário.");
                    }
                    break;

                case 5:
                    // Atualizar um usuário
                    System.out.println("Atualizar usuário");
                    System.out.print("Digite o ID do usuário a ser atualizado: ");
                    int idAtualizacao = sc.nextInt();
                    Usuario usuarioAtualizar = usuarioDAO.getUsuarioById(idAtualizacao);
                    if (usuarioAtualizar != null) {
                        sc.nextLine();
                        System.out.print("Novo nome: ");
                        usuarioAtualizar.setNome(sc.nextLine());
                        System.out.print("Novo sexo: ");
                        usuarioAtualizar.setSexo(sc.nextLine());
                        System.out.print("Nova idade: ");
                        usuarioAtualizar.setIdade(sc.nextInt());
                        boolean atualizado = usuarioDAO.updateUsuario(usuarioAtualizar);
                        if (atualizado) {
                            System.out.println("Usuário atualizado com sucesso.");
                        } else {
                            System.out.println("Falha ao atualizar o usuário.");
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 6:
                    // Atualizar um endereço de usuário
                    System.out.println("Atualizar endereço de usuário");
                    System.out.print("Digite o ID do usuário a ser atualizado: ");
                    int idAtualizacaoEndereco = sc.nextInt();
                    Usuario usuarioAtualizarEndereco = usuarioDAO.getUsuarioById(idAtualizacaoEndereco);
                    if (usuarioAtualizarEndereco != null) {
                        System.out.print("Nova rua: ");
                        sc.nextLine();
                        usuarioAtualizarEndereco.getEndereco().setRua(sc.nextLine());
                        System.out.print("Novo número: ");
                        usuarioAtualizarEndereco.getEndereco().setNumero(sc.nextLine());
                        System.out.print("Novo CEP: ");
                        usuarioAtualizarEndereco.getEndereco().setCep(sc.nextLine());
                        System.out.print("Nova cidade: ");
                        usuarioAtualizarEndereco.getEndereco().setCidade(sc.nextLine());
                        System.out.print("Novo estado: ");
                        usuarioAtualizarEndereco.getEndereco().setEstado(sc.nextLine());
                        System.out.print("Novo complemento: ");
                        usuarioAtualizarEndereco.getEndereco().setComplemento(sc.nextLine());
                        boolean atualizado = enderecoDAO.updateAddressByUserId(usuarioAtualizarEndereco.getId(),usuarioAtualizarEndereco.getEndereco());
                        if (atualizado) {
                            System.out.println("Endereço atualizado com sucesso.");
                        } else {
                            System.out.println("Falha ao atualizar o endereço.");
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 7:
                    System.out.println("Adicionar jogo ao carrinho de um usuário");
                    System.out.print("Digite o ID do usuário: ");
                    int _userId = sc.nextInt();

                    Usuario usuarioAddJogo = usuarioDAO.getUsuarioById(_userId);
                    if (usuarioAddJogo != null) {

                        List<Jogo> gamesInCart = jogoDAO.getAllJogos();
                        System.out.println("Jogos disponíveis:");
                        for (Jogo jogo : gamesInCart) {
                            System.out.println(jogo.getId() + " - " + jogo.getNome() + " - Preço: " + jogo.getPreco());
                        }

                        System.out.print("Digite o ID do jogo que deseja adicionar ao carrinho: ");
                        int jogoId = sc.nextInt();

                        carrinhoDAO.addJogoToCarrinho(usuarioAddJogo.getId(), jogoId);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 8:
                    // Remove jogo do carrinho
                    System.out.println("Remover jogo do carrinho");
                    System.out.print("Digite o ID do usuário: ");
                    int __userId = sc.nextInt();

                    Carrinho userCart = carrinhoDAO.getCarrinhoByUserId(__userId);
                    List<Jogo> gamesInCart = carrinhoHasJogoDAO.showGamesInCart(userCart, usuarioDAO.getUsuarioById(__userId));

                    if (!gamesInCart.isEmpty()) {
                        System.out.println("Jogos no carrinho:");
                        for (Jogo jogo : gamesInCart) {
                            System.out.println(jogo.getId() + ": " + jogo.getNome() + " - Preço: " + jogo.getPreco());
                        }

                        System.out.print("Digite o ID do jogo a ser removido: ");
                        int gameIdToRemove = sc.nextInt();

                        boolean gameInCart = gamesInCart.stream().anyMatch(game -> game.getId() == gameIdToRemove);
                        if (gameInCart) {
                            Carrinho_has_Jogo relationToRemove = new Carrinho_has_Jogo(userCart.getIdCarrinho(), __userId, gameIdToRemove);
                            carrinhoHasJogoDAO.removeGameFromCart(relationToRemove);
                        } else {
                            System.out.println("O jogo selecionado não está no carrinho.");
                        }
                    } else {
                        System.out.println("O carrinho está vazio.");
                    }
                    break;


                case 9:
                    // Listar jogos do carrinho de um usuário
                    System.out.println("Listar jogos do carrinho de um usuário");
                    System.out.print("Digite o ID do usuário: ");
                    int userIdListar = sc.nextInt();

                    Usuario usuarioListar = usuarioDAO.getUsuarioById(userIdListar);
                    if (usuarioListar != null) {
                        Carrinho userCartListar = carrinhoDAO.getCarrinhoByUserId(userIdListar);
                        List<Jogo> gamesInCartListar = carrinhoHasJogoDAO.showGamesInCart(userCartListar, usuarioListar);

                        if (!gamesInCartListar.isEmpty()) {
                            System.out.println("Jogos no carrinho:");
                            for (Jogo jogo : gamesInCartListar) {
                                System.out.println(jogo.getId() + ": " + jogo.getNome() + " - Preço: " + jogo.getPreco());
                            }
                        } else {
                            System.out.println("O carrinho está vazio.");
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 0:
                    // Sair
                    System.out.println("Saindo...");
                    flag = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
