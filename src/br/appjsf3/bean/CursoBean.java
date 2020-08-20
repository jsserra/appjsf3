package br.appjsf3.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.appjsf3.dao.CursoDao;
import br.appjsf3.dao.ModuloDao;
import br.appjsf3.model.Curso;
import br.appjsf3.model.Modulo;
import br.appjsf3.util.appjsf3Exception;

//@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class CursoBean {

	private Curso curso = new Curso();
	private List<Modulo> modulosDisponiveis = new ArrayList<>();
	private String moduloItem;
	private boolean modulosDisponiveisEstaVazia = true;

	public CursoDao daoCurso = new CursoDao();
	public ModuloDao daoModulo = new ModuloDao();

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Modulo> getModulosDisponiveis() {
		return modulosDisponiveis;
	}

	public String getModuloItem() {
		return moduloItem;
	}

	public void setModuloItem(String moduloItem) {
		this.moduloItem = moduloItem;
	}

	public boolean isModulosDisponiveisEstaVazia() {
		return modulosDisponiveisEstaVazia;
	}

	public void setModulosDisponiveisEstaVazia(boolean modulosDisponiveisEstaVazia) {
		this.modulosDisponiveisEstaVazia = modulosDisponiveisEstaVazia;
	}

	public String novo() {
		curso = new Curso();
		this.modulosDisponiveis = new ArrayList<Modulo>();

		return "cursoForm?faces-redirect=true";
	}

	public String gravar() throws appjsf3Exception {

		if (!modulosDisponiveis.isEmpty()) {
			curso.setModulos(new HashSet<>(modulosDisponiveis));

			if (curso.getId() == null) {
				daoCurso.salvar(curso);
			} else {
				daoCurso.atualizar(curso);
			}

			curso = new Curso();
			this.modulosDisponiveis = new ArrayList<Modulo>();

			return "listaCurso?faces-redirect=true";

		} else {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("Modulos",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lista de Modulos vazia!", ""));
			return "";
		}
	}
	
	public String editar(Curso curso) throws appjsf3Exception{
		this.curso = curso;
		this.modulosDisponiveis = daoModulo.listaModuloPorCurso(curso.getId());
		this.modulosDisponiveisEstaVazia = false;
		
		Boolean on = true;
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("curso", curso);
		flash.put("modulos", modulosDisponiveis);
		flash.put("on", on);
		
		return "cursoForm?faces-redirect=true";		
	}
	
	
	@SuppressWarnings("unchecked")
	public void carregar() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Boolean on = (Boolean) flash.get("on");
		if(on != null && on == true) {
			curso = (Curso) flash.get("curso");
			this.modulosDisponiveis = (List<Modulo>) flash.get("modulos");
			this.modulosDisponiveisEstaVazia = false;
		}
	}
	
	public String apagar(Curso curso) throws appjsf3Exception {
		daoCurso.excluir(curso);
		return "formCurso?faces-redirect=true";
	}
	
	public List<Curso> getLista() throws appjsf3Exception{
		return daoCurso.listar();
	}
	
	public void adicionarModulosDisponiveis() {
		this.modulosDisponiveis.add(new Modulo(this.moduloItem));
		this.moduloItem = null;
		setModulosDisponiveisEstaVazia(false);
	}
	
	public void apagarModulosDisponiveis(Modulo modulo) {
		this.modulosDisponiveis.remove(modulo);
		
		if(this.modulosDisponiveis.isEmpty()) {
			setModulosDisponiveisEstaVazia(true);
		}
	}

}
