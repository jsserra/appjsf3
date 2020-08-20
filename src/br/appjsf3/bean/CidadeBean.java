package br.appjsf3.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.appjsf3.dao.CidadeDao;
import br.appjsf3.model.address.Cidade;
import br.appjsf3.util.appjsf3Exception;


//@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class CidadeBean {
	
	Cidade cidade = new Cidade();
	CidadeDao dao = new CidadeDao();
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public String novo() {
		this.cidade = new Cidade();
		return "cidadeForm?faces-redirect=true";
	}
	
	public List<Cidade> getLista() throws appjsf3Exception{
		return dao.listar();
	}
	
	public void gravar() throws appjsf3Exception{
		if(cidade.getId() == null) {
			dao.salvar(cidade);
		}else {
			dao.atualizar(cidade);
		}
		this.cidade = new Cidade();
	}
	
	public void editar(Cidade cidade) throws appjsf3Exception{
		dao.atualizar(cidade);
	}
	
	public void excluir(Cidade cidade) throws appjsf3Exception{
		dao.deletar(cidade);
	}
	

}
