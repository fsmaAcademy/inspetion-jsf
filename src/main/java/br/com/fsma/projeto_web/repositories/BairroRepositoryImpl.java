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
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.interfaces.IBairroRepository;

public class BairroRepositoryImpl implements Serializable, IBairroRepository {

	private static final long serialVersionUID = 1L;
	
	private Repository<Bairro> repository;

	@Inject
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		repository = new Repository<Bairro>(this.em, Bairro.class);
	}

	@Override
	public void adiciona(Bairro bairro) {
		repository.adiciona(bairro);
		
	}

	@Override
	public void atualiza(Bairro bairro) {
		repository.atualiza(bairro);
		
	}

	@Override
	public void remove(Bairro bairro) {
		repository.remove(bairro);
		
	}

	@Override
	public List<Bairro> busca() {
		return null;
	}

	@Override
	public List<Bairro> busca(String criterio) {
		return repository.buscar();
	}

	@Override
	public Bairro buscaPorId(Long id) {
		return repository.buscaPorId(id);
	}

	@Override
	public Bairro buscaPorNome(String nome) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM Cidade c ");
		sb.append("WHERE ");
		sb.append("   c.nome = :pNome");
		
		TypedQuery<Bairro> query = em.createQuery(sb.toString(), Bairro.class);
		
		query.setParameter("pNome", nome);

		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public List<Bairro> buscaBairroPorCriterioEmCidadeEstado(String criterio, Cidade cidade) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT b FROM Bairro b ");
		sb.append(" WHERE ");
		sb.append("   b.cidade = :pCidade");
		sb.append("   and b.nome LIKE :pCriterio");
		
		TypedQuery<Bairro> query = em.createQuery(sb.toString(), Bairro.class);
		query.setParameter("pCriterio", criterio + "%");
		query.setParameter("pCidade", cidade);
		
		return query.getResultList();
	}

	
}
