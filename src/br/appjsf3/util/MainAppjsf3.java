package br.appjsf3.util;

import java.util.List;
import javax.persistence.EntityManager;

import br.appjsf3.dao.BairroDao;
import br.appjsf3.dao.RuaDao;
import br.appjsf3.model.address.Bairro;
import br.appjsf3.model.address.Rua;

public class MainAppjsf3 {

	public static void main(String[] args)   {
		// TODO Auto-generated method stub

		// ruaSalvar();
		//ruaAtualizar();
		//ruaListar();
		//ruaListarPorId();
		bairroListarPorId();
	}

	
	/*private static void ruaSalvar() throws appjsf3Exception {
		 RuaDao dao = new RuaDao();
		 Rua rua = new Rua("Av Pedro Costa"); 
		 dao.salvar(rua); }
	

 
	private static void ruaAtualizar() {
		try {
		EntityManager em = HibernateUtil.getEntityManager();
		
		
			//em.getTransaction().begin();
			Rua r = em.find(Rua.class, 1);
			RuaDao dao = new RuaDao();
			
			System.out.println(r);
			r.setNome("Vereador José Carlos");
			System.out.println(r);
			dao.atualizar(r);
		} catch (Exception e) {
			e.getMessage();
		}
	}
*/
	private static void ruaListar() {
		try {
			List<Rua> ruas = new RuaDao().listar();
			for (Rua r : ruas) {
				System.out.println(r.getNome());
			}
			System.out.println("Quantidade de Ruas: " + ruas.size());
			System.out.println(ruas.stream().findFirst());
		} catch (appjsf3Exception e) {

		}

	}/*
	
	private static void ruaListarPorId() {
		try {
		Rua rua = new RuaDao().buscarPorId(3);
		System.out.println(rua);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}*/
	
	@SuppressWarnings("unused")
	private static void bairroListarPorId() {
		try {
			Bairro bairro = new BairroDao().buscarPorId(2);
			System.out.println(bairro.getNome());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
