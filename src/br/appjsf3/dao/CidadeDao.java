package br.appjsf3.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import br.appjsf3.model.address.Cidade;
import br.appjsf3.util.HibernateUtil;
import br.appjsf3.util.appjsf3Exception;

public class CidadeDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EntityManager em = HibernateUtil.getEntityManager();

	public void salvar(Cidade cidade) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.persist(cidade);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				throw new appjsf3Exception("Erro ao gravar cidade " + he.getMessage());
			}
		}
	}

	public void atualizar(Cidade cidade) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.merge(cidade);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				throw new appjsf3Exception("Erro ao atualizar Cidade:" + he.getMessage());
			}
		}
	}

	public void deletar(Cidade cidade) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.remove(cidade);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Não é possível excluir esta cidade! Está sendo usado em outro cadastro"));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> listar() throws appjsf3Exception{
		List<Cidade> cidades = null;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("from Cidade");
			cidades = q.getResultList();
			em.getTransaction().commit();			
		}catch (HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
				throw new appjsf3Exception("Erro ao listar cidades " + he.getMessage()); 
		}
		return cidades;
	}
	
	public Cidade buscarPorId(Integer id) throws appjsf3Exception{
		Cidade cidade = null;
		try {
			em.getTransaction().begin();
			cidade = em.find(Cidade.class, id);
			em.getTransaction().commit();
		}catch (HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao localizar cidade" + he.getMessage());
		}
		return cidade;
	}

}
