package br.appjsf3.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import br.appjsf3.model.address.Bairro;
import br.appjsf3.util.HibernateUtil;
import br.appjsf3.util.appjsf3Exception;

public class BairroDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EntityManager em = HibernateUtil.getEntityManager();

	public void salvar(Bairro bairro) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.persist(bairro);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				throw new appjsf3Exception("Erro ao salvar bairro!" + he.getMessage());
			}
		}
	}

	public void atualizar(Bairro bairro) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.merge(bairro);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao atualizar bairro!" + he.getMessage());
		}
	}

	public void excluir(Bairro bairro) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.remove(bairro);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Não é possível excluir esse bairro! Está sendo utilizado por outro cadastro"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<Bairro> listar() throws appjsf3Exception {
		List<Bairro> bairros = null;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("from Bairro");
			bairros = q.getResultList();
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erros ao listar os bairros!" + he);
		}
		return bairros;
	}
	
	public Bairro buscarPorId(Integer id) throws appjsf3Exception{
		Bairro bairro = null;
		try {
			em.getTransaction().begin();
			bairro = em.find(Bairro.class, id);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao localizar Bairro" + he.getMessage());
		}
		return bairro;
	}

}
