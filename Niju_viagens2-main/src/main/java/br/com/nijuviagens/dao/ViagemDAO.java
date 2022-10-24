package br.com.nijuviagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.nijuviagens.factory.ConnectionFatory;
import br.com.nijuviagens.model.Viagem;

public class ViagemDAO {
	public void save(Viagem viagem) {
		String sql = "INSERT INTO Viagem(Destino, Ida, Volta)" + "VALUES(?,?,?)";

		ConnectionFatory conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria uma conex�o com o banco
			conn = ConnectionFatory.createConnectionMySQL();

			// Cria um PreparedStatement, classe usada para executar a query
			pstm = conn.prepareStatement(sql);

			// Adicionar o valor do primeiro par�metro da sql
			pstm.setString(1, viagem.getDestino());

			// Adicionar o valor do segundo par�metro da sql
			pstm.setString(2, viagem.getIda());

			// Adicionar o valor do terceiro par�metro da sql
			pstm.setString(3, viagem.getVolta());

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
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * Isso � uma sql comum, os ? s�o os par�metros que n�s vamos adicionar na base
	 * de dados
	 */

	public void removeById(int IdViagem) {
		String sql = "DELETE FROM Viagem WHERE IdViagem = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = (Connection) ConnectionFatory.createConnectionMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, IdViagem);

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

	public void update(Viagem viagem) {
		String sql = "UPDATE Viagem SET Destino = ?, Ida = ?, Volta = ?  WHERE IdViagem = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria uma conex�o com o banco
			conn = (Connection) ConnectionFatory.createConnectionMySQL();

			// Cria um PreparedStatement, classe usada para executar a query
			pstm = conn.prepareStatement(sql);

			// Adiciona o valor do primeiro par�metro da sql
			pstm.setString(1, viagem.getDestino());

			// Adicionar o valor do segundo par�metro da sql
			pstm.setString(2, viagem.getIda());

			// Adicionar o valor do terceiro par�metro da sql
			pstm.setString(3, viagem.getVolta());

			pstm.setInt(4, viagem.getIdViagem());

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

	public List<Viagem> getViagem() {

		String sql = "SELECT * FROM Viagem";

		List<Viagem> viagens = new ArrayList<Viagem>();

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
				Viagem viagem = new Viagem();

				// Recupera o id do banco e atribui ele ao objeto
				viagem.setIdViagem(rset.getInt("IdViagem"));

				// Recupera o nome do banco e atribui ele ao objeto
				viagem.setDestino(rset.getString("Destino"));

				// Recupera a idade do banco e atribui ele ao objeto
				viagem.setIda(rset.getString("Ida"));

				// Recupera a data do banco e atribui ela ao objeto
				viagem.setVolta(rset.getString("Volta"));

				// Adiciona o contato recuperado, a lista de contatos
				viagens.add(viagem);
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
		return viagens;
	}



}
