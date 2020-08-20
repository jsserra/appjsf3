package br.appjsf3.dao;

import java.io.Serializable;
//import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import br.appjsf3.model.address.Rua;
import br.appjsf3.util.HibernateUtil;
import br.appjsf3.util.appjsf3Exception;

public class RuaDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EntityManager em = HibernateUtil.getEntityManager();

	public void salvar(Rua rua) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.persist(rua);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Erro ao salvar Rua: " + he.getMessage());
		}
	}

	public void atualizar(Rua rua) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.merge(rua);
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro de ataulização da Rua: " + e.getMessage());
		}

	}

	public void excluir(Rua rua)  throws appjsf3Exception{
		try {
			em.getTransaction().begin();
			em.remove(rua);
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Não é possível excluir esta rua! Está em uso por outro cadastro"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<Rua> listar() throws appjsf3Exception {
		List<Rua> list = null;
		Query consulta = null;
		try {
			em.getTransaction().begin();
			consulta = em.createQuery("from Rua");
			list = consulta.getResultList();
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erros na listagem das Ruas: " + he.getMessage());
		}
		return list;
	}

	public Rua buscarPorId(Integer id) throws appjsf3Exception {
		Rua rua = null;
		try {
			em.getTransaction().begin();
			rua = em.find(Rua.class, id);
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		return rua;

	}

}
