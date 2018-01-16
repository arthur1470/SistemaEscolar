package br.com.escolar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TURMA")
public class Turma {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "NOME", length = 15, nullable = false)
	private String nome;

	@Column(name = "NUMERO_SALA", nullable = false)
	private Integer numSala;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumSala() {
		return numSala;
	}

	public void setNumSala(Integer numSala) {
		this.numSala = numSala;
	}

	@Override
	public String toString() {
		return "Turma [id=" + id + ", nome=" + nome + ", numSala=" + numSala + "]";
	}
}
