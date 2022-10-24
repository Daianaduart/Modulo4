package br.com.nijuviagens.model;

public class Viagem {
	
	private int IdViagem;
	private String Destino;
	private String Ida;
	private String volta;
	public int getIdViagem() {
		return IdViagem;
	}
	public void setIdViagem(int idViagem) {
		IdViagem = idViagem;
	}
	public String getDestino() {
		return Destino;
	}
	public void setDestino(String destino) {
		Destino = destino;
	}
	public String getIda() {
		return Ida;
	}
	public void setIda(String ida) {
		Ida = ida;
	}
	public String getVolta() {
		return volta;
	}
	public void setVolta(String volta) {
		this.volta = volta;
	}

}
