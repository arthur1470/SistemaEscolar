package br.com.escolar.dao;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@RequestScoped
public abstract class DAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public <T> T carregar(Object key, Class<T> clazz) {
		return manager.find(clazz, key);
	}
	
	public <T> void salvar(T entity) {
		manager.persist(entity);
	}
	
	public <T> void alterar(T entity) {
		manager.merge(entity);
	}
	
	public <T> void remover(T entity) {
		manager.remove(entity);
	}
	
	protected Query criarQuery(String query) {
		return manager.createQuery(query);
	}
}
