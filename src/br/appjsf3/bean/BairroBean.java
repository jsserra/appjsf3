package br.appjsf3.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.appjsf3.dao.BairroDao;
import br.appjsf3.model.address.Bairro;
import br.appjsf3.util.appjsf3Exception;

//@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class BairroBean {
	
	Bairro bairro = new Bairro();
	BairroDao dao = new BairroDao();
	
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String novo() {
		this.bairro = new Bairro();
		return "bairroForm?faces-redirect=true";
	}
	
	public List<Bairro> getLista() throws appjsf3Exception{
		return dao.listar();
	}
	
	public void gravar() throws appjsf3Exception{
		if(bairro.getId() == null) {
			dao.salvar(bairro);
		}else {
			dao.atualizar(bairro);			
		}
		this.bairro = new Bairro();
	}
	
	public void editar(Bairro bairro) throws appjsf3Exception{
		dao.atualizar(bairro);
	}
	
	public void excluir(Bairro bairro) throws appjsf3Exception{
		dao.excluir(bairro);
	}
	
	@Override
	public String toString() {
		return "BairroBean [bairro=" + bairro + ", dao=" + dao + "]";
	}
	
	
}
