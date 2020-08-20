package br.appjsf3.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NaturalId;

import br.appjsf3.model.enums.Desempenho;
import br.appjsf3.model.enums.EstadoCivil;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false, length = 20)
	private String nome;
	@Column(nullable = false, length = 30)
	private String sobreNome;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@Column(length = 1)
	private char sexo;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private EstadoCivil estadoCivil;
	@Column(length = 14)
	private String rg;
	@NaturalId
	@Column(length = 15)
	private String cpf;
	
	@Embedded
	private Endereco endereco;
	@Column(length = 50)
	private String email;
	@Column(length = 15)
	private String tel;
	
	@OneToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;	
	private boolean pago;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Desempenho desempenho;
	@Column(precision = 5, scale = 2)
	private Double frequencia;
	
	private String obs;


	public Aluno() {
		super();
	}
	
	public Aluno(String nome, String sobreNome, Date dataNascimento, char sexo, EstadoCivil estadoCivil, String rg, String cpf,
			Endereco endereco, String email, String tel, Curso curso, boolean pago, Desempenho desempenho,
			Double frequencia, String obs) {
		super();
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		this.tel = tel;
		this.curso = curso;
		this.pago = pago;
		this.desempenho = desempenho;
		this.frequencia = frequencia;
		this.obs = obs;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobreNome() {
		return this.sobreNome;
	}
	
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public Desempenho getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(Desempenho desempenho) {
		this.desempenho = desempenho;
	}

	public Double getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Double frequencia) {
		this.frequencia = frequencia;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
