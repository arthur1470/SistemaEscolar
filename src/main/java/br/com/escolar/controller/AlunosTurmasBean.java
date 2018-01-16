package br.com.escolar.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.escolar.model.Aluno;
import br.com.escolar.model.Turma;
import br.com.escolar.service.AlunoService;
import br.com.escolar.service.TurmaService;

@Named("alunosTurmas")
@SessionScoped
public class AlunosTurmasBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	@Inject
	private TurmaService turmaService;
	
	private List<Turma> turmas;
	private List<Aluno> alunos;
	
	private Integer turmaId;
	private String[] alunosSelecionados;
	
	public void mudouTurma(ValueChangeEvent event) {
		Integer turmaId = (Integer) event.getNewValue();
		
		List<Aluno> alunosTurma = alunoService.obterAlunosTurma(turmaId);
		
		alunosSelecionados = new String[alunosTurma.size()];
		int i = 0;
		
		for(Aluno aluno : alunosTurma) {
			alunosSelecionados[i++] = aluno.getNumMatricula();
		}
		
		this.turmaId = turmaId;
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		navHandler.handleNavigation(context, null, "alunos_turmas");
	}
	
	public String associarAlunos() {
		alunoService.associarAlunosTurma(alunosSelecionados, turmaId);
		turmas = null;
		alunos = null;
		turmaId = null;
		alunosSelecionados = null;
		
		return "index";
	}
	
	public List<Turma> getTurmas() {
		if(turmas == null) {
			turmas = turmaService.listarTurmas();
		}
		return turmas;
	}
	
	public List<Aluno> getAlunos(){
		if(alunos == null) {
			alunos = alunoService.listarAlunos();			
		}
		return alunos;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public Integer getTurmaId() {
		return turmaId;
	}
	
	public void setTurmaId(Integer turmaId) {
		this.turmaId = turmaId;
	}
	
	public String[] getAlunosSelecionados() {
		return alunosSelecionados;
	}
	
	public void setAlunosSelecionados(String[] alunosSelecionados) {
		this.alunosSelecionados = alunosSelecionados;
	}
}
