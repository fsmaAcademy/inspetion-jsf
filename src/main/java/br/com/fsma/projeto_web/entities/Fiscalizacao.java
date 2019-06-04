package br.com.fsma.projeto_web.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_fiscalizacao")
public class Fiscalizacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "data")
	private LocalDate data;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@Column(name = "logradouro", length = 150, nullable = false)
	private String logradouro;

	@Column(name = "cep", length = 9, nullable = false)
	private String cep;

	@ManyToOne
	private Bairro bairro;

	@ManyToOne
	private Cidade cidade;

	@ManyToOne
	private Estado estado;

	@ManyToOne
	private Empresa empresa;

	@ManyToMany
	@JoinTable(
		   name="tb_fiscalizacao_fiscal",
		   joinColumns=@JoinColumn(name="fiscalizacao_id", referencedColumnName="id"),
		   inverseJoinColumns=@JoinColumn(name="fiscal_id", referencedColumnName="id"))
	private Set<Fiscal> fiscais;

	
	@ManyToMany
	@JoinTable(
			name="tb_fiscalizacao_ocorrencia",
			joinColumns=@JoinColumn(name="fiscalizacao_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="ocorrencia_id", referencedColumnName="id"))
	private Set<Ocorrencia> ocorrencias;

	
	public Set<Fiscal> getFiscais() {
		return fiscais;
	}

	public void setFiscais(Set<Fiscal> fiscais) {
		this.fiscais = fiscais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
		Fiscalizacao other = (Fiscalizacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(Set<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}


	

}
