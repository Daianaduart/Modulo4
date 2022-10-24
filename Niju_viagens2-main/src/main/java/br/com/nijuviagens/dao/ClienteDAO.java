package br.com.nijuviagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.nijuviagens.factory.ConnectionFatory;
import br.com.nijuviagens.model.Cliente;

public class ClienteDAO {
	public void save(Cliente cliente) {
		/*
		 * Isso � uma sql comum, os ? s�o os par�metros que n�s vamos adicionar na base
		 * de dados
		 */

		String sql = "INSERT INTO Cliente(Nome, idade, Cpf, Email)" + "VALUES(?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria uma conex�o com o banco
			conn = (Connection) ConnectionFatory.createConnectionMySQL();

			// Cria um PreparedStatement, classe usada para executar a query
			pstm = conn.prepareStatement(sql);

			// Adicionar o valor do primeiro par�metro da sql
			pstm.setString(1, cliente.getNome());

			// Adicionar o valor do segundo par�metro da sql
			pstm.setInt(2, cliente.getIdade());

			// Adicionar o valor do terceiro par�metro da sql
			pstm.setString(3, cliente.getCpf());

			pstm.setString(4, cliente.getEmail());
			// Executar a sql para inser��o dos dados
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fecha as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void removeById(int IdCliente) {
		String sql = "DELETE FROM Cliente WHERE IdCliente = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = (Connection) ConnectionFatory.createConnectionMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, IdCliente);

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Cliente cliente) {
		String sql = "UPDATE Cliente SET Nome = ?, idade = ?, Cpf = ?, Email = ? WHERE IdCliente = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria uma conex�o com o banco
			conn = (Connection) ConnectionFatory.createConnectionMySQL();

			// Cria um PreparedStatement, classe usada para executar a query
			pstm = conn.prepareStatement(sql);

			// Adiciona o valor do primeiro par�metro da sql
			pstm.setString(1, cliente.getNome());

			// Adicionar o valor do segundo par�metro da sql
			pstm.setInt(2, cliente.getIdade());

			// Adicionar o valor do terceiro par�metro da sql
			pstm.setString(3, cliente.getCpf());

			pstm.setString(4, cliente.getEmail());

			pstm.setInt(5, cliente.getIdCliente());

			// Executa a sql para inser��o dos dados
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Cliente> getCliente() {

		String sql = "SELECT * FROM Cliente";

		List<Cliente> clientes = new ArrayList<Cliente>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conn = (Connection) ConnectionFatory.createConnectionMySQL();

			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			// Enquanto existir dados no banco de dados, fa�a
			while (rset.next()) {
				Cliente cliente = new Cliente();

				// Recupera o id do banco e atribui ele ao objeto
				cliente.setIdCliente(rset.getInt("IdCliente"));

				// Recupera o nome do banco e atribui ele ao objeto
				cliente.setNome(rset.getString("Nome"));

				// Recupera a idade do banco e atribui ele ao objeto
				cliente.setIdade(rset.getInt("Idade"));

				// Recupera a data do banco e atribui ela ao objeto
				cliente.setCpf(rset.getString("Cpf"));

				cliente.setEmail(rset.getString("Email"));

				// Adiciona o contato recuperado, a lista de contatos
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}

	
	}
