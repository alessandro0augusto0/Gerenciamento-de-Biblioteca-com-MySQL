package controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoMysql;
import entidades.Autor;
import entidades.Editora;
import entidades.Funcionario;
import entidades.Usuario;
import itens.Livro;

public class Biblioteca {

	private ConexaoMysql conexao;

	private List<Usuario> usuarios = new ArrayList<>();
	private List<Funcionario> funcionarios = new ArrayList<>();
	private List<Livro> livros = new ArrayList<>();
	private List<Editora> editoras = new ArrayList<>();
	private List<Autor> autores = new ArrayList<>();

	public Biblioteca() {
		this.conexao = new ConexaoMysql();
		conexao.OpenDatabase();
	}

	public void adicionarUsuario(Usuario usuario) {
		usuarios.add(usuario);
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("INSERT INTO Usuario (Nome, CPF, Endereco, Telefone) VALUES (?, ?, ?, ?)");
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getCpf());
			stmt.setString(3, usuario.getEndereco());
			stmt.setString(4, usuario.getTelefone());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Usuário adicionado com sucesso!");
			} else {
				System.out.println("Falha ao adicionar usuário.");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarUsuario(String cpfUsuario, String novoEndereco, String novoTelefone) {
		PreparedStatement stmt = null;
		try {
			for (Usuario usuario : usuarios) {
				if (usuario.getCpf().equals(cpfUsuario)) {
					usuario.setEndereco(novoEndereco);
					usuario.setTelefone(novoTelefone);
					break;
				}
			}
			String sql = "UPDATE Usuario SET Endereco = ?, Telefone = ? WHERE CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, novoEndereco);
			stmt.setString(2, novoTelefone);
			stmt.setString(3, cpfUsuario);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Informações do usuário atualizadas com sucesso!");
			} else {
				System.out.println("Falha ao atualizar informações do usuário.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar informações do usuário: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removerUsuario(String cpfUsuario) {
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement("DELETE FROM Usuario WHERE CPF = ?");
			stmt.setString(1, cpfUsuario);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Usuário removido com sucesso!");
				usuarios.removeIf(usuario -> usuario.getCpf().equals(cpfUsuario));
			} else {
				System.out.println("Falha ao remover usuário.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao remover usuário: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void adicionarFuncionario(Funcionario funcionario) {
		funcionarios.add(funcionario);
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("INSERT INTO Funcionario (Nome, CPF, Funcao, Salario) VALUES (?, ?, ?, ?)");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getFuncao());
			stmt.setDouble(4, funcionario.getSalario());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Funcionário adicionado com sucesso!");
			} else {
				System.out.println("Falha ao adicionar funcionário ao banco de dados.");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarFuncionario(String cpfFuncionario, String novaFuncao, double novoSalario) {
		PreparedStatement stmt = null;
		try {
			for (Funcionario funcionario : funcionarios) {
				if (funcionario.getCpf().equals(cpfFuncionario)) {
					funcionario.setFuncao(novaFuncao);
					funcionario.setSalario(novoSalario);
					break;
				}
			}
			String sql = "UPDATE Funcionario SET Funcao = ?, Salario = ? WHERE CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, novaFuncao);
			stmt.setDouble(2, novoSalario);
			stmt.setString(3, cpfFuncionario);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Informações do funcionário atualizadas com sucesso!");
			} else {
				System.out.println("Falha ao atualizar informações do funcionário.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar informações do funcionário: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removerFuncionario(String cpfFuncionario) {
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement("DELETE FROM Funcionario WHERE CPF = ?");
			stmt.setString(1, cpfFuncionario);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Funcionário removido com sucesso!");
				funcionarios.removeIf(funcionario -> funcionario.getCpf().equals(cpfFuncionario));
			} else {
				System.out.println("Falha ao remover funcionário.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao remover funcionário: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void adicionarLivro(Livro livro) {
		livros.add(livro);
		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"INSERT INTO Livro (Codigo, Titulo, Status, Edicao, Genero, AnoDePublicacao, CodigoEditora) VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, livro.getCodigo());
			stmt.setString(2, livro.getTitulo());
			stmt.setString(3, livro.getStatus());
			stmt.setString(4, livro.getEdicao());
			stmt.setString(5, livro.getGenero());
			stmt.setInt(6, livro.getAnoDePublicacao());
			stmt.setInt(7, livro.getCodigoEditora());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Livro adicionado com sucesso!");
			} else {
				System.out.println("Falha ao adicionar livro.");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarLivro(String codigoLivro, String novaEdicao, String novoGenero, int novoAnoDePublicacao,
			int novoCodigoEditora) {
		PreparedStatement stmt = null;
		try {
			for (Livro livro : livros) {
				if (livro.getCodigo().equals(codigoLivro)) {
					livro.setEdicao(novaEdicao);
					livro.setGenero(novoGenero);
					livro.setAnoDePublicacao(novoAnoDePublicacao);
					livro.setCodigoEditora(novoCodigoEditora);
					break;
				}
			}
			String sql = "UPDATE Livro SET Edicao = ?, Genero = ?, AnoDePublicacao = ?, CodigoEditora = ? WHERE Codigo = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, novaEdicao);
			stmt.setString(2, novoGenero);
			stmt.setInt(3, novoAnoDePublicacao);
			stmt.setInt(4, novoCodigoEditora);
			stmt.setString(5, codigoLivro);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Informações do livro atualizadas com sucesso!");
			} else {
				System.out.println("Falha ao atualizar informações do livro.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar informações do livro: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removerLivro(String codigoLivro) {
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement("DELETE FROM Livro WHERE Codigo = ?");
			stmt.setString(1, codigoLivro);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Livro removido com sucesso!");
				livros.removeIf(livro -> livro.getCodigo().equals(codigoLivro));
			} else {
				System.out.println("Falha ao remover livro.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao remover livro: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void adicionarEditora(Editora editora) {
		editoras.add(editora);
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("INSERT INTO Editora (Codigo, Nome, Contato) VALUES (?, ?, ?)");
			stmt.setString(1, editora.getCodigo());
			stmt.setString(2, editora.getNome());
			stmt.setString(3, editora.getContato());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Editora adicionada com sucesso!");
			} else {
				System.out.println("Falha ao adicionar editora.");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarEditora(String codigoEditora, String novoNome, String novoContato) {
		PreparedStatement stmt = null;
		try {
			for (Editora editora : editoras) {
				if (editora.getCodigo().equals(codigoEditora)) {
					editora.setNome(novoNome);
					editora.setContato(novoContato);
					break;
				}
			}
			String sql = "UPDATE Editora SET Nome = ?, Contato = ? WHERE Codigo = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, novoNome);
			stmt.setString(2, novoContato);
			stmt.setString(3, codigoEditora);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Informações da editora atualizadas com sucesso!");
			} else {
				System.out.println("Falha ao atualizar informações da editora.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar informações da editora: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removerEditora(String codigoEditora) {
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement("DELETE FROM Editora WHERE Codigo = ?");
			stmt.setString(1, codigoEditora);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Editora removida com sucesso!");
				editoras.removeIf(editora -> editora.getCodigo().equals(codigoEditora));
			} else {
				System.out.println("Falha ao remover editora.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao remover editora: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void adicionarAutor(Autor autor) {
		autores.add(autor);
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("INSERT INTO Autor (Codigo, Nome, Nacionalidade) VALUES (?, ?, ?)");
			stmt.setString(1, autor.getCodigo());
			stmt.setString(2, autor.getNome());
			stmt.setString(3, autor.getNacionalidade());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Autor adicionado com sucesso!");
			} else {
				System.out.println("Falha ao adicionar autor.");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarAutor(String codigoAutor, String novoNome, String novaNacionalidade) {
		PreparedStatement stmt = null;
		try {
			for (Autor autor : autores) {
				if (autor.getCodigo().equals(codigoAutor)) {
					autor.setNome(novoNome);
					autor.setNacionalidade(novaNacionalidade);
					break;
				}
			}
			String sql = "UPDATE Autor SET Nome = ?, Nacionalidade = ? WHERE Codigo = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, novoNome);
			stmt.setString(2, novaNacionalidade);
			stmt.setString(3, codigoAutor);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Informações do autor atualizadas com sucesso!");
			} else {
				System.out.println("Falha ao atualizar informações do autor.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar informações do autor: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removerAutor(String codigoAutor) {
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement("DELETE FROM Autor WHERE Codigo = ?");
			stmt.setString(1, codigoAutor);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Autor removido com sucesso!");
				autores.removeIf(autor -> autor.getCodigo().equals(codigoAutor));
			} else {
				System.out.println("Falha ao remover autor.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao remover autor: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void emprestarLivro(String codigo, String cpfUsuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT Status FROM Livro WHERE Codigo = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, codigo);
			rs = stmt.executeQuery();
	
			if (rs.next()) {
				String status = rs.getString("Status");
				if ("disponivel".equalsIgnoreCase(status)) {
					sql = "UPDATE Livro SET Status = ?, CPFUsuario = ? WHERE Codigo = ?";
					stmt = conexao.prepareStatement(sql);
					stmt.setString(1, "emprestado");
					stmt.setString(2, cpfUsuario);
					stmt.setString(3, codigo);
					int rowsAffected = stmt.executeUpdate();
					if (rowsAffected > 0) {
						System.out.println("Livro emprestado com sucesso!");
					} else {
						System.out.println("Falha ao emprestar livro no banco de dados.");
					}
				} else {
					System.out.println("O livro não consta como disponível para empréstimo.");
				}
			} else {
				System.out.println("Livro não encontrado no banco de dados.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao emprestar livro: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void devolverLivro(String codigo) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT Status FROM Livro WHERE Codigo = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, codigo);
			rs = stmt.executeQuery();
	
			if (rs.next()) {
				String status = rs.getString("Status");
				if ("emprestado".equalsIgnoreCase(status)) {
					sql = "UPDATE Livro SET Status = ?, CPFUsuario = NULL WHERE Codigo = ?";
					stmt = conexao.prepareStatement(sql);
					stmt.setString(1, "disponivel");
					stmt.setString(2, codigo);
					int rowsAffected = stmt.executeUpdate();
					if (rowsAffected > 0) {
						System.out.println("Livro devolvido com sucesso!");
					} else {
						System.out.println("Falha ao devolver livro no banco de dados.");
					}
				} else {
					System.out.println("O livro não consta como emprestado.");
				}
			} else {
				System.out.println("Livro não encontrado no banco de dados.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao devolver livro: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	public void consultarLivros() {
		String query = "SELECT l.Codigo, l.Titulo, l.Status, l.Edicao, l.Genero, l.AnoDePublicacao, l.CodigoEditora, u.Nome AS UsuarioNome " +
					   "FROM Livro l LEFT JOIN Usuario u ON l.CPFUsuario = u.CPF";
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				String nomeUsuario = rs.getString("UsuarioNome");
				String status = "disponivel".equalsIgnoreCase(rs.getString("Status")) ? "Não emprestado" : "Emprestado para: " + (nomeUsuario != null ? nomeUsuario : "Desconhecido");
				
				Livro livro = new Livro(
					rs.getString("Codigo"),
					rs.getString("Titulo"),
					status,
					rs.getString("Edicao"),
					rs.getString("Genero"),
					rs.getInt("AnoDePublicacao"),
					rs.getInt("CodigoEditora")
				);
				System.out.println(livro);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao processar os resultados: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void consultarUsuarios() {
		String query = "SELECT * FROM Usuario";
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				Usuario usuario = new Usuario(rs.getString("Nome"), rs.getString("CPF"), rs.getString("Endereco"),
						rs.getString("Telefone"));
				System.out.println(usuario);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao processar os resultados: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void consultarFuncionarios() {
		String query = "SELECT * FROM Funcionario";
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				Funcionario funcionario = new Funcionario(rs.getString("Nome"), rs.getString("CPF"),
						rs.getString("Funcao"), rs.getDouble("Salario"));
				System.out.println(funcionario);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao processar os resultados: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void fecharConexao() {
		conexao.CloseDatabase();
	}
}
