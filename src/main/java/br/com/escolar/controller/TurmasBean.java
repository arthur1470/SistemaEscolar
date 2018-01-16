package br.com.escolar.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.escolar.model.Turma;
import br.com.escolar.service.TurmaService;

@Named("turmas")
@SessionScoped
public class TurmasBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaService turmaService;
	
	private List<Turma> turmas;
	
	private Turma turma;
	
	private boolean alterar;

	public String alterar(Turma turma) {
		this.turma = turma;
		this.alterar = true;
		return "editar_turma";
	}
	
	public String novaTurma() {
		turma = new Turma();
		alterar = false;
		return "editar_turma";
	}
	
	public String excluir(Turma turma) {
		turmaService.excluir(turma.getId());
		turmas = null;
		
		return "listar_turmas?faces-redirect=true";
	}
	
	public String salvar() {
		if(alterar) {
			turmaService.alterar(turma);
		}else {
			turmaService.inserir(turma);
		}
		
		turma = null;
		turmas = null;
		alterar = false;
		
		return "listar_turmas?faces-redirect=true";	
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public List<Turma> getTurmas() {
		if(turmas == null) {
			turmas = turmaService.listarTurmas();
		}
		return turmas;
	}
	
}
