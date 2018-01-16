package br.com.escolar.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.escolar.model.Aluno;
import br.com.escolar.service.AlunoService;

@Named("alunos")
@SessionScoped
public class AlunosBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	private List<Aluno> alunos;
	
	private Aluno aluno;
	
	private boolean alterar;
	
	public List<Aluno> getAlunos(){
		if(alunos == null) {
			alunos = alunoService.listarAlunos();
		}
		return alunos;
	}
	
	public String alterar(Aluno aluno) {
		this.aluno = aluno;
		this.alterar = true;
		return "editar_aluno";
	}
	
	public String novoAluno() {
		aluno = new Aluno();
		alterar = false;
		return "editar_aluno";
	}
	
	public String excluir(Aluno aluno) {
		alunoService.excluir(aluno.getNumMatricula());
		alunos = null;
		return "listar_alunos?faces-redirect=true";
	}
	
	public String salvar() {
		if(alterar) {
			alunoService.alterar(aluno);
		}else {
			alunoService.inserir(aluno);
		}
		aluno = null;
		alunos = null;
		alterar = false;
		return "listar_alunos?faces-redirect=true";
	}
	
	public Aluno getAluno() {
		if(aluno == null) {
			aluno = new Aluno();
		}
		return aluno;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
