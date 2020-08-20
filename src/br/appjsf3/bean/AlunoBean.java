package br.appjsf3.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

import org.primefaces.event.ToggleEvent;

import br.appjsf3.dao.AlunoDao;
import br.appjsf3.dao.BairroDao;
import br.appjsf3.dao.CidadeDao;
import br.appjsf3.dao.CursoDao;
import br.appjsf3.dao.EstadoDao;
import br.appjsf3.dao.RuaDao;
import br.appjsf3.model.Aluno;
import br.appjsf3.model.Curso;
import br.appjsf3.model.Endereco;
import br.appjsf3.util.appjsf3Exception;

//@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class AlunoBean {

	private Aluno aluno = new Aluno();
	private Endereco endereco = new Endereco();
	private Curso curso = new Curso();

	private AlunoDao daoAluno = new AlunoDao();
	private RuaDao daoRua = new RuaDao();
	private BairroDao daoBairro = new BairroDao();
	private CidadeDao daoCidade = new CidadeDao();
	private EstadoDao daoEstado = new EstadoDao();
	private CursoDao daoCurso = new CursoDao();

	private Integer ruaId;
	private Integer bairroId;
	private Integer cidadeId;
	private Integer estadoId;
	private String cep;
	private Integer cursoId;
	private Double cursoValor;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Integer getRuaId() {
		return ruaId;
	}

	public void setRuaId(Integer ruaId) {
		this.ruaId = ruaId;
	}

	public Integer getBairroId() {
		return bairroId;
	}

	public void setBairroId(Integer bairroId) {
		this.bairroId = bairroId;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public Double getCursoValor() {
		return cursoValor;
	}

	public void setCursoValor(Double cursoValor) {
		this.cursoValor = cursoValor;
	}

	public String novo() {
		aluno = new Aluno();
		endereco = new Endereco();
		curso = new Curso();

		aluno.setEndereco(endereco);
		aluno.setCurso(curso);

		return "alunoForm?faces-redirect=true";
	}

	public String gravar() throws appjsf3Exception {
		endereco = new Endereco(
				daoRua.buscarPorId(this.ruaId),
				daoBairro.buscarPorId(this.bairroId),
				daoCidade.buscarPorId(this.cidadeId),
				daoEstado.buscarPorId(this.estadoId),
				this.cep);

		curso = daoCurso.cursoPorId(cursoId);

		aluno.setEndereco(endereco);
		aluno.setCurso(curso);

		if (aluno.getId() == null) {
			daoAluno.salvar(aluno);
		} else {
			daoAluno.atualizar(aluno);
		}

		aluno = new Aluno();

		return "alunoLista?faces-redirect=true";
		
	}
	
	public String editar(Aluno aluno) throws appjsf3Exception{
		this.aluno = aluno;
		
		Boolean on = true;
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		
		flash.put("aluno", this.aluno);
		
		flash.put("rua", aluno.getEndereco().getRua().getId());
		flash.put("bairro", aluno.getEndereco().getBairro().getId());
		flash.put("cidade", aluno.getEndereco().getCidade().getId());
		flash.put("estado", aluno.getEndereco().getEstado().getId());
		flash.put("cep", aluno.getEndereco().getCep());
		
		flash.put("curso", aluno.getCurso().getId());
		
		flash.put("on", on);
		
		return "alunoForm?faces-redirect=true";
	}
	
	public void carregar() throws appjsf3Exception{
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Boolean on = (Boolean) flash.get("on");
		if(on != null && on == true) {
			this.aluno = (Aluno) flash.get("aluno");
			this.ruaId = (Integer) flash.get("rua");
			this.bairroId = (Integer) flash.get("bairro");
			this.cidadeId = (Integer) flash.get("cidade");
			this.estadoId = (Integer) flash.get("estado");
			this.cep = (String) flash.get("cep");
			this.cursoId = (Integer) flash.get("curso");
			
			endereco = new Endereco(					
					daoRua.buscarPorId(this.ruaId),
					daoBairro.buscarPorId(this.bairroId),
					daoCidade.buscarPorId(this.cidadeId),
					daoEstado.buscarPorId(this.estadoId),
					aluno.getEndereco().getCep());
			
			curso = daoCurso.cursoPorId(cursoId);
			
			aluno.setEndereco(endereco);
			aluno.setCurso(curso);
		}
	}
	
	public String apagar(Aluno aluno) throws appjsf3Exception{
		daoAluno.excluir(aluno);
		return "alunoLista?faces-redirect=true";
	}
	
	public List<Aluno> getLista() throws appjsf3Exception{
		return daoAluno.listar();
	}
	
	public void confereData(FacesContext fc, UIComponent c, Object value) {
		Date hoje = new Date();
		Date dataNova = new Date();
		dataNova = (Date) value;
		
		if(hoje.compareTo(dataNova) <= 0) {
			throw new ValidatorException(new FacesMessage("A data deve ser inferior a de hoje"));
		}
	}
	
	public void atualizarCurso(AjaxBehaviorEvent e) throws appjsf3Exception{
		curso = daoCurso.cursoPorId(this.cursoId);		
	}
	
	public void alterarSecao(ToggleEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Toggled",
				"Visibility:" + event.getVisibility());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
