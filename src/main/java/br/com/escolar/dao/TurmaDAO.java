package br.com.escolar.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.escolar.model.Turma;

public class TurmaDAO extends DAO{
	private static final long serialVersionUID = 1L;

	public List<Turma> listarTurmas(){
		Query query = criarQuery("SELECT t FROM Turma t");
		return query.getResultList();
	}
}
