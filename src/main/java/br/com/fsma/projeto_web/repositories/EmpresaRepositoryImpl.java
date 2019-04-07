package br.com.fsma.projeto_web.repositories;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.interfaces.IEmpresaRepository;

public class EmpresaRepositoryImpl implements Serializable, IEmpresaRepository {

	private static final long serialVersionUID = 1L;
	
	private Repository<Empresa> repository;
	
	@Inject
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		repository = new Repository<Empresa>(em, Empresa.class);
	}

	@Override
	public void adiciona(Empresa empresa) {
		repository.adiciona(empresa);
		
	}

	@Override
	public void atualiza(Empresa empresa) {
		repository.atualiza(empresa);
		
	}

	@Override
	public void remove(Empresa empresa) {
		repository.remove(empresa);
	}

	@Override
	public List<Empresa> busca() {
		return null;
	}

	@Override
	public List<Empresa> busca(String criterio) {
		return repository.buscar();
	}

	@Override
	public Empresa buscaPorId(Long id) {
		return repository.buscaPorId(id);
	}

	@Override
	public Empresa buscaPorNome(String nome) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e FROM Empresa e ");
		sb.append("WHERE ");
		sb.append("   e.nome = :pNome");
		
		TypedQuery<Empresa> query = em.createQuery(sb.toString(), Empresa.class);
		query.setParameter("pNome", nome);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
		
	}

	@Override
	public List<Empresa> buscaPorCriterioEmBairroCidadeEstado(String criterio, Bairro bairro, Cidade cidade, Estado estado) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e FROM Empresa e ");
		sb.append("WHERE ");
		sb.append("    e.bairro = :pBairro");
		sb.append("    and e.cidade = :pCidade");
		sb.append("    and e.estado = :pEstado");
		sb.append("    and e.nome LIKE :pCriterio");
		
		TypedQuery<Empresa> query = em.createQuery(sb.toString(), Empresa.class);
		query.setParameter("pBairro", bairro);
		query.setParameter("pCidade", cidade);
		query.setParameter("pEstado", estado);
		query.setParameter("pCriterio", criterio + "%");
		
		return query.getResultList();
	}

	@Override
	public boolean existe(Empresa empresa) {
		return
				buscaPorNome(empresa.getNome()) != null ||
				buscaPorCnpj(empresa.getCnpj()) != null;
	}

	@Override
	public Empresa buscaPorCnpj(String cnpj) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e FROM Empresa e ");
		sb.append("WHERE ");
		sb.append("    e.cnpj = :pCnpj");
		
		TypedQuery<Empresa> query = em.createQuery(sb.toString(), Empresa.class);
		query.setParameter("pCnpj", cnpj);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	

}
