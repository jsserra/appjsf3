package br.appjsf3.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.appjsf3.dao.RuaDao;
import br.appjsf3.model.address.Rua;
import br.appjsf3.util.appjsf3Exception;

//@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class RuaBean {

	private Rua rua = new Rua();
	private RuaDao dao = new RuaDao();

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public String novo() {
		this.rua = new Rua();
		return "ruaForm?faces-redirect=true";
	}

	public List<Rua> getLista() throws appjsf3Exception {
		return dao.listar();
	}

	public void gravar() throws appjsf3Exception {
		if (rua.getId() == null) {
			dao.salvar(rua);
		} else {
			dao.atualizar(rua);
		}
		this.rua = new Rua();
	}
	
	public void editar(Rua rua) throws appjsf3Exception{
		dao.atualizar(rua);
	}
	
	public void apagar(Rua rua) throws appjsf3Exception{
		dao.excluir(rua);
	}

}
