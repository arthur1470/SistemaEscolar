package br.com.escolar.service;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

@RequestScoped
public abstract class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private UserTransaction ut;

	protected void beginTransaction() {
		try {
			if (ut.getStatus() != Status.STATUS_ACTIVE) {
				ut.begin();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void commitTransaction() {
		try {
			if (ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.commit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void rollbackTransaction() {
		try {
			if (ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.rollback();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
