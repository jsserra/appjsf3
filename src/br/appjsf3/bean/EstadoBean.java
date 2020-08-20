package br.appjsf3.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.appjsf3.dao.EstadoDao;
import br.appjsf3.model.address.Estado;
import br.appjsf3.util.appjsf3Exception;

//@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class EstadoBean {
	
	Estado estado = new Estado();
	EstadoDao dao = new EstadoDao();
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String novo() {
		this.estado = new Estado();
		return "estadoForm?faces-redirect=true";
	}
	
	
	public List<Estado> getLista() throws appjsf3Exception{
		return dao.listar();
	}
	
	public void gravar() throws appjsf3Exception{
		if(estado.getId() == null) {
			dao.salvar(estado);
		}else {
			dao.atualizar(estado);
		}		
		this.estado = new Estado();
	}
	
	public void editar(Estado estado) throws appjsf3Exception{
		dao.atualizar(estado);
	}
	
	public void excluir(Estado estado) throws appjsf3Exception{
		dao.excluir(estado);
	}
	

}
