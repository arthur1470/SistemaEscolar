package br.com.escolar.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.com.escolar.dao.AlunoDAO;
import br.com.escolar.dao.TurmaDAO;
import br.com.escolar.model.Aluno;
import br.com.escolar.model.Log.TipoMensagem;
import br.com.escolar.model.Turma;

public class AlunoService extends Service {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoDAO alunoDAO;

	@Inject
	private TurmaDAO turmaDAO;

	@Inject
	private LogService logService;

	public void inserir(Aluno aluno) {
		try {
			beginTransaction();

			alunoDAO.salvar(aluno);
			logService.log("Aluno inserido: " + aluno, TipoMensagem.INFO);

			commitTransaction();
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}

	public void alterar(Aluno aluno) {
		try {
			beginTransaction();

			alunoDAO.alterar(aluno);
			logService.log("Aluno alterado: " + aluno, TipoMensagem.INFO);

			commitTransaction();
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}

	public void excluir(String numMatricula) {
		try {
			beginTransaction();

			Aluno aluno = alunoDAO.carregar(numMatricula, Aluno.class);
			alunoDAO.remover(aluno);
			logService.log("Aluno excluído: " + numMatricula, TipoMensagem.INFO);

			commitTransaction();
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}

	public List<Aluno> listarAlunos() {
		return alunoDAO.listarAlunos();
	}

	public List<Aluno> obterAlunosTurma(Integer turmaId) {
		return alunoDAO.obterAlunosTurma(turmaId);
	}

	public void associarAlunosTurma(String[] numMatriculas, Integer turmaId) {
		try {
			beginTransaction();
			
			List<Aluno> alunos = alunoDAO.obterAlunosTurma(turmaId);
			
			for(Aluno aluno : alunos) {
				aluno.setTurma(null);
			}
			
			Turma turma = turmaDAO.carregar(turmaId, Turma.class);
			
			for(String numMatricula: numMatriculas) {
				Aluno aluno = alunoDAO.carregar(numMatricula, Aluno.class);
				aluno.setTurma(turma);
			}
			
			logService.log("Alunos associados à turma " + turmaId + ":" + Arrays.toString(numMatriculas), TipoMensagem.INFO);
			
			commitTransaction();
		}catch(RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
}
