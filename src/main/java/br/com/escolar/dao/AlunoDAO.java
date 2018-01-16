package br.com.escolar.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.escolar.model.Aluno;

public class AlunoDAO extends DAO{
	private static final long serialVersionUID = 1L;
	
	public List<Aluno> listarAlunos(){
		Query query = criarQuery("SELECT a FROM Aluno a");
		return query.getResultList();
	}
	
	public List<Aluno> obterAlunosTurma(Integer turmaId){
		Query query = criarQuery("SELECT a FROM Aluno a WHERE a.turma.id = " + turmaId);
		return query.getResultList();
	}
}
