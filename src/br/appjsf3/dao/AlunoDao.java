package br.appjsf3.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import br.appjsf3.model.Aluno;
import br.appjsf3.util.HibernateUtil;
import br.appjsf3.util.appjsf3Exception;

public class AlunoDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EntityManager em = HibernateUtil.getEntityManager();

	public void salvar(Aluno aluno) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.persist(aluno);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao salvar Aluno! " + he.getMessage());
		}
	}

	public void atualizar(Aluno aluno) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.merge(aluno);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao atualizar Aluno!" + he.getMessage());
		}
	}

	public void excluir(Aluno aluno) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.remove(aluno);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao excluir Aluno! " + he.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> listar() throws appjsf3Exception {
		Query q = null;
		try {
			em.getTransaction().begin();
			q = em.createQuery("from Aluno");
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao listar Alunos! " + he.getMessage());
		}
		return q.getResultList();
	}
	
	public Aluno BuscarId(Integer id) throws appjsf3Exception{
		Aluno aluno = null;
		try {
			em.getTransaction().begin();
			aluno = em.find(Aluno.class, id);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao localizar aluno!" + he.getMessage());
		}
		return aluno;
	}

}
