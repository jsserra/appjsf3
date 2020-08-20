package br.appjsf3.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import br.appjsf3.model.Curso;
import br.appjsf3.util.HibernateUtil;
import br.appjsf3.util.appjsf3Exception;

public class CursoDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	EntityManager em = HibernateUtil.getEntityManager();
	
	public void salvar(Curso curso) throws appjsf3Exception{
		try {
			em.getTransaction().begin();
			em.persist(curso);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao salvar Curso! " + he.getMessage());
		}
	}
	
	public void atualizar(Curso curso) throws appjsf3Exception{
		try {
			em.getTransaction().begin();
			em.merge(curso);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao atualizar Curso!" + he.getMessage());
		}
	}
	
	public void excluir(Curso curso) throws appjsf3Exception{
		try {
			em.getTransaction().begin();
			em.remove(curso);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Não é possível excluir o curso: " + curso + ". Ele está em uso por outro cadastro!"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<Curso> listar() throws appjsf3Exception{
		List<Curso> lista = null;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("from Curso");
			lista = q.getResultList();
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao listar Cursos! " + he.getMessage());
		}
		return lista;
	}
	
	public Curso cursoPorId(Integer id) throws appjsf3Exception{
		Curso curso = null;
		try {
			em.getTransaction().begin();
			curso = em.find(Curso.class, id);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao localizar o curso! " + he.getMessage());
		}
		return curso;
	}
}
