package br.appjsf3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import br.appjsf3.model.address.Estado;
import br.appjsf3.util.HibernateUtil;
import br.appjsf3.util.appjsf3Exception;

public class EstadoDao {
	
	EntityManager em = HibernateUtil.getEntityManager();
	
	public void salvar(Estado estado) throws appjsf3Exception{
		try {
			em.getTransaction().begin();
			em.persist(estado);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();				
			}
			throw new appjsf3Exception("Erro ao salvar Estado! " + he.getMessage() );
			
		}
	}
	
	public void atualizar(Estado estado) throws appjsf3Exception{
		try {
			em.getTransaction().begin();
			em.merge(estado);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao atualizar Estado! " + he.getMessage());
		}
	}
	
	public void excluir(Estado estado) throws appjsf3Exception{
		try {
			em.getTransaction().begin();
			em.remove(estado);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erros ao excluir Estado! " + he.getMessage()); 
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> listar() throws appjsf3Exception {
		List<Estado> lista = null;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("from Estado");
			lista = q.getResultList();
			em.getTransaction().commit();			
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao listar Estados! " + he.getMessage());
		}
		return lista;
	}
	
	public Estado buscarPorId(Integer id) throws appjsf3Exception{
		Estado estado = null;
		try {
			em.getTransaction().begin();
			estado = em.find(Estado.class, id);
			em.getTransaction().commit();
		}catch(HibernateException he) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new appjsf3Exception("Erro ao localizar Estado! " + he.getMessage());
		}
		return estado;
	}

}
