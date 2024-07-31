/*
 * 
 * Este código foi criado pelo aluno Alessandro Augusto no 5º período do curso de Engenharia de Computação
 * a fim de implementar um sistema de "Gerenciamento de Biblioteca".
 * Professor: Thiago Caproni Tavares
 * Matéria: Programação Orientada a Objetos I
 * 
 * */

package program;

import java.util.Locale;
import java.util.Scanner;

import conexao.ConexaoMysql;
import controle.Biblioteca;
import entidades.Autor;
import entidades.Editora;
import entidades.Funcionario;
import entidades.Usuario;
import itens.Livro;

public class App {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		ConexaoMysql conexao = new ConexaoMysql();
		conexao.OpenDatabase();

		Biblioteca biblioteca = new Biblioteca();

		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);

		int menuOpcao;

		do {

			System.out.println("=== MENU ===");
			System.out.println("1. Cadastro e Atualizacao");
			System.out.println("2. Gestao de Emprestimos");
			System.out.println("3. Consultas");
			System.out.println("4. Sair");
			System.out.print("Selecione uma opcao: ");

			menuOpcao = sc.nextInt();

			switch (menuOpcao) {
			case 1:
				cadastroEatualizacao(sc, biblioteca, conexao);
				break;
			case 2:
				gestaoDeEmprestimos(sc, biblioteca, conexao);
				break;
			case 3:
				consultas(sc, biblioteca, conexao);
				break;
			case 4:
				System.out.println();
				System.out.println("Saindo ...");
				System.out.println();
				break;
			default:
				System.out.println();
				System.out.println("Opcao invalida");
				System.out.println();
				break;
			}

		} while (menuOpcao != 4);

		sc.close();
		conexao.CloseDatabase();

	}

	public static void cadastroEatualizacao(Scanner sc, Biblioteca biblioteca, ConexaoMysql conexao) {
		int opcao1;
		do {
			System.out.println("1. Adicionar usuario");
			System.out.println("2. Editar usuario");
			System.out.println("3. Remover usuario");
			System.out.println("4. Adicionar funcionario");
			System.out.println("5. Editar funcionario");
			System.out.println("6. Remover funcionario");
			System.out.println("7. Adicionar livro");
			System.out.println("8. Editar livro");
			System.out.println("9. Remover livro");
			System.out.println("10. Adicionar editora");
			System.out.println("11. Editar editora");
			System.out.println("12. Remover editora");
			System.out.println("13. Adicionar autor");
			System.out.println("14. Editar autor");
			System.out.println("15. Remover autor");
			System.out.println("16. Voltar");
			System.out.println("17. Sair");
			System.out.print("Selecione uma opcao: ");

			opcao1 = sc.nextInt();

			switch (opcao1) {

			case 1:
				System.out.println("Adicionando um novo usuario");
				sc.nextLine();
				System.out.println("Informe o nome:");
				String nomeUsuario = sc.nextLine();
				System.out.println("Informe o CPF:");
				String cpfUsuario = sc.nextLine();
				System.out.println("Informe o endereco:");
				String enderecoUsuario = sc.nextLine();
				System.out.println("Informe o telefone:");
				String telefoneUsuario = sc.next();
				Usuario novoUsuario = new Usuario(nomeUsuario, cpfUsuario, enderecoUsuario, telefoneUsuario);
				biblioteca.adicionarUsuario(novoUsuario);
				break;

			case 2:
				System.out.println("Editando um usuario");
				sc.nextLine();
				System.out.println("Informe o CPF do usuario que deseja editar:");
				cpfUsuario = sc.nextLine();
				System.out.println("Informe o novo endereco:");
				String novoEndereco = sc.nextLine();
				System.out.println("Informe o novo telefone:");
				String novoTelefone = sc.nextLine();
				biblioteca.editarUsuario(cpfUsuario, novoEndereco, novoTelefone);
				break;

			case 3:
				System.out.println("Removendo um usuario");
				sc.nextLine();
				System.out.println("Informe o CPF do usuario a ser removido:");
				cpfUsuario = sc.nextLine();
				biblioteca.removerUsuario(cpfUsuario);
				break;

			case 4:
				System.out.println("Adicionando um novo funcionario");
				sc.nextLine();
				System.out.println("Informe o nome:");
				String nomeFuncionario = sc.nextLine();
				System.out.println("Informe o CPF:");
				String cpfFuncionario = sc.nextLine();
				System.out.println("Informe a funcao:");
				String funcaoFuncionario = sc.nextLine();
				System.out.println("Informe o salario:");
				double salarioFuncionario = sc.nextDouble();
				sc.nextLine();
				Funcionario novoFuncionario = new Funcionario(nomeFuncionario, cpfFuncionario, funcaoFuncionario,
						salarioFuncionario);
				biblioteca.adicionarFuncionario(novoFuncionario);
				break;

			case 5:
				System.out.println("Editando um funcionario");
				sc.nextLine();
				System.out.println("Informe o CPF do funcionario que deseja editar:");
				cpfFuncionario = sc.nextLine();
				System.out.println("Informe sua nova funcao:");
				String novaFuncao = sc.nextLine();
				System.out.println("Informe seu novo salario:");
				double novoSalario = sc.nextDouble();
				biblioteca.editarFuncionario(cpfFuncionario, novaFuncao, novoSalario);
				break;

			case 6:
				System.out.println("Removendo um funcionario");
				sc.nextLine();
				System.out.println("Informe o CPF do funcionario a ser removido:");
				cpfFuncionario = sc.nextLine();
				biblioteca.removerFuncionario(cpfFuncionario);
				break;

			case 7:
				System.out.println("Adicionando um novo livro");
				sc.nextLine();
				System.out.println("Informe o codigo do livro:");
				String codigoLivro = sc.nextLine();
				System.out.println("Informe o titulo do livro:");
				String tituloLivro = sc.nextLine();
				System.out.println("Informe o status do livro (disponivel/emprestado):");
				String statusLivro = sc.nextLine();
				System.out.println("Informe a edicao do livro:");
				String edicaoLivro = sc.nextLine();
				System.out.println("Informe o genero do livro:");
				String generoLivro = sc.nextLine();
				System.out.println("Informe o ano de publicacao do livro:");
				int publicacaoLivro = sc.nextInt();
				System.out.println("Informe o codigo da editora do livro:");
				int codigoEditoraLivro = sc.nextInt();
				Livro novoLivro = new Livro(codigoLivro, tituloLivro, statusLivro, edicaoLivro, generoLivro,
						publicacaoLivro, codigoEditoraLivro);
				biblioteca.adicionarLivro(novoLivro);
				break;

			case 8:
				System.out.println("Editando um livro");
				sc.nextLine();
				System.out.println("Informe o codigo do livro a ser editado:");
				codigoLivro = sc.nextLine();
				System.out.println("Digite a nova edicao do livro:");
				String novaEdicaoLivro = sc.nextLine();
				System.out.println("Digite o novo genero do livro:");
				String novoGeneroLivro = sc.nextLine();
				System.out.println("Digite o novo ano de publicacao do livro:");
				int novoAnoPublicacaoLivro = sc.nextInt();
				System.out.println("Digite o novo codigo da editora do livro:");
				int novoCodigoEditoraLivro = sc.nextInt();
				biblioteca.editarLivro(codigoLivro, novaEdicaoLivro, novoGeneroLivro, novoAnoPublicacaoLivro,
						novoCodigoEditoraLivro);
				break;

			case 9:
				System.out.println("Removendo um livro");
				sc.nextLine();
				System.out.println("Digite o codigo do livro a ser removido:");
				codigoLivro = sc.nextLine();
				biblioteca.removerLivro(codigoLivro);
				break;

			case 10:
				System.out.println("Adicionando uma nova editora");
				sc.nextLine();
				System.out.println("Digite o codigo da editora:");
				String codigoEditora = sc.nextLine();
				System.out.println("Digite o nome da editora:");
				String nomeEditora = sc.nextLine();
				System.out.println("Digite o contato da editora:");
				String contatoEditora = sc.nextLine();
				Editora novaEditora = new Editora(codigoEditora, nomeEditora, contatoEditora);
				biblioteca.adicionarEditora(novaEditora);
				break;

			case 11:
				System.out.println("Editando uma editora");
				sc.nextLine();
				System.out.println("Digite o codigo da editora a ser editada");
				codigoEditora = sc.nextLine();
				System.out.println("Digite o novo nome da editora:");
				String novoNome = sc.nextLine();
				System.out.println("Digite o novo contato da editora:");
				String novoContato = sc.nextLine();
				biblioteca.editarEditora(codigoEditora, novoNome, novoContato);
				break;

			case 12:
				System.out.println("Removendo uma editora");
				sc.nextLine();
				System.out.println("Digite o codigo da editora a ser removida");
				codigoEditora = sc.nextLine();
				biblioteca.removerEditora(codigoEditora);
				break;

			case 13:
				System.out.println("Adicionando um novo autor");
				sc.nextLine();
				System.out.println("Digite o codigo do autor:");
				String codigoAutor = sc.nextLine();
				System.out.println("Digite o nome do autor:");
				String nomeAutor = sc.nextLine();
				System.out.println("Digite a nacionalidade do autor:");
				String nacionalidadeAutor = sc.next();
				Autor novoAutor = new Autor(codigoAutor, nomeAutor, nacionalidadeAutor);
				biblioteca.adicionarAutor(novoAutor);
				break;

			case 14:
				System.out.println("Editando um autor");
				sc.nextLine();
				System.out.println("Digite o codigo do autor a ser editado:");
				codigoAutor = sc.nextLine();
				System.out.println("Digite o novo nome do autor:");
				String novoNomeAutor = sc.nextLine();
				System.out.println("Digite a nova nacionalidade do autor:");
				String novaNacionalidadeAutor = sc.nextLine();
				biblioteca.editarAutor(codigoAutor, novoNomeAutor, novaNacionalidadeAutor);
				break;

			case 15:
				System.out.println("Removendo um autor");
				sc.nextLine();
				System.out.println("Digite o codigo do autor a ser removido");
				codigoAutor = sc.nextLine();
				biblioteca.removerAutor(codigoAutor);
				break;

			case 16:
				return;

			case 17:
				System.out.println();
				System.out.println("Saindo ...");
				System.out.println();
				System.exit(0);
				break;
			default:
				System.out.println("Opcao invalida");

			}

		} while (opcao1 != 17);
	}

	public static void gestaoDeEmprestimos(Scanner sc, Biblioteca biblioteca, ConexaoMysql conexao) {
		int opcao2;
		do {
			System.out.println("1. Emprestimo");
			System.out.println("2. Devolucao");
			System.out.println("3. Voltar");
			System.out.println("4. Sair");
			System.out.print("Selecione uma opcao: ");

			opcao2 = sc.nextInt();

			switch (opcao2) {

			case 1:
				System.out.println("Emprestimo de livro");
				sc.nextLine();
				System.out.println("Digite o codigo do livro:");
				String codigoEmprestimo = sc.nextLine();
				System.out.println("Digite o CPF do usuario:");
				String cpfEmprestimo = sc.nextLine();
				biblioteca.emprestarLivro(codigoEmprestimo, cpfEmprestimo);
				break;

				case 2:
				System.out.println("Devolucao de livro");
				sc.nextLine();
				System.out.println("Digite o codigo do livro:");
				codigoEmprestimo = sc.nextLine();
				biblioteca.devolverLivro(codigoEmprestimo);
				break;
			

			case 3:
				return;
			case 4:
				System.out.println();
				System.out.println("Saindo ...");
				System.out.println();
				System.exit(0);
				break;

			default:
				System.out.println();
				System.out.println("Opcao invalida");
				System.out.println();
				break;
			}
		} while (opcao2 != 4);
	}

	public static void consultas(Scanner sc, Biblioteca biblioteca, ConexaoMysql conexao) {
		int opcao3;
		do {
			System.out.println("1. Consultar livros");
			System.out.println("2. Consultar usuarios");
			System.out.println("3. Consultar funcionarios");
			System.out.println("4. Voltar");
			System.out.println("5. Sair");
			System.out.print("Selecione uma opcao: ");

			opcao3 = sc.nextInt();

			switch (opcao3) {

			case 1:
				biblioteca.consultarLivros();
				break;

			case 2:
				biblioteca.consultarUsuarios();
				break;

			case 3:
				biblioteca.consultarFuncionarios();
				break;

			case 4:
				return;
			case 5:
				System.out.println();
				System.out.println("Saindo ...");
				System.out.println();
				System.exit(0);
				break;
			default:
				System.out.println("Opcao invalida");

			}
		} while (opcao3 != 5);
	}
}
