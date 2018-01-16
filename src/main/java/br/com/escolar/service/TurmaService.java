package br.com.escolar.service;

import java.util.List;

import javax.inject.Inject;

import br.com.escolar.dao.TurmaDAO;
import br.com.escolar.model.Log.TipoMensagem;
import br.com.escolar.model.Turma;

public class TurmaService extends Service {
	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaDAO turmaDAO;

	@Inject
	private LogService logService;

	public void inserir(Turma turma) {
		try {
			beginTransaction();

			turmaDAO.salvar(turma);
			logService.log("Turma inserida: " + turma, TipoMensagem.INFO);

			commitTransaction();
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}

	public void alterar(Turma turma) {
		try {
			beginTransaction();
			
			turmaDAO.alterar(turma);
			logService.log("Turma alterada: " + turma, TipoMensagem.INFO);
			
			commitTransaction();
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}

	public void excluir(Integer id) {
		try {
			beginTransaction();
			
			Turma turma = turmaDAO.carregar(id, Turma.class);
			turmaDAO.remover(turma);
			logService.log("Turma excluída: " + id, TipoMensagem.INFO);
			
			commitTransaction();
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}

	public List<Turma> listarTurmas() {
		return turmaDAO.listarTurmas();
	}
}
