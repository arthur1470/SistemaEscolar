package br.com.escolar.service;

import javax.inject.Inject;

import br.com.escolar.dao.LogDAO;
import br.com.escolar.model.Log;
import br.com.escolar.model.Log.TipoMensagem;

public class LogService extends Service {
	private static final long serialVersionUID = 1L;

	@Inject
	private LogDAO logDAO;

	public void log(String mensagem, TipoMensagem tipo) {
		try {
			beginTransaction();

			Log log = new Log();
			log.setMensagem(mensagem);
			log.setTipo(tipo);

			logDAO.salvar(log);

			commitTransaction();
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}

}
