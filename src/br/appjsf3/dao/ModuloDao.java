package br.appjsf3.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import br.appjsf3.model.Modulo;
import br.appjsf3.util.HibernateUtil;
import br.appjsf3.util.appjsf3Exception;

public class ModuloDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EntityManager em = HibernateUtil.getEntityManager();

	public void salvar(Modulo modulo) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.persist(modulo);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao salvar Modulo! " + he.getMessage());
		}
	}

	public void atualizar(Modulo modulo) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.merge(modulo);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao atualizar Modulo!" + he.getMessage());
		}
	}

	public void excluir(Modulo modulo) throws appjsf3Exception {
		try {
			em.getTransaction().begin();
			em.remove(modulo);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao excluir Modulo! " + he.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Modulo> listar() throws appjsf3Exception {
		Query q = null;
		try {
			em.getTransaction().begin();
			q = em.createQuery("from Modulo");
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao listar Modulos! " + he.getMessage());
		}
		return q.getResultList();
	}

	public Modulo BuscarId(Integer id) throws appjsf3Exception {
		Modulo modulo = null;
		try {
			em.getTransaction().begin();
			modulo = em.find(Modulo.class, id);
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao localizar Modulo!" + he.getMessage());
		}
		return modulo;
	}

	@SuppressWarnings("unchecked")
	public List<Modulo> listaModuloPorCurso(Integer id) throws appjsf3Exception {
		List<Modulo> lista = null;
		Query q = null;
		try {
			em.getTransaction().begin();
			q = em.createQuery("select m from Modulo m join m.curso curso where curso.id = :id");
			q.setParameter("id", id);
			lista = q.getResultList();
			for (Modulo m : lista) {
				System.out.println("id . " + m.getIdModulo());
				System.out.println("descrição . " + m.getDescricao());
			}
			em.getTransaction().commit();
		} catch (HibernateException he) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		return q.getResultList();
	}

}
