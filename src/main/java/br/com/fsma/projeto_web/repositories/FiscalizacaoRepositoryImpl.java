package br.com.fsma.projeto_web.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Fiscalizacao;
import br.com.fsma.projeto_web.repositories.interfaces.IFiscalizacaoRepository;

public class FiscalizacaoRepositoryImpl implements Serializable, IFiscalizacaoRepository {

	private static final long serialVersionUID = 1L;

	private Repository<Fiscalizacao> repository;

	@Inject
	private EntityManager em;

	@PostConstruct
	public void init() {
		repository = new Repository<Fiscalizacao>(em, Fiscalizacao.class);
	}

	@Override
	public void adiciona(Fiscalizacao fiscalizacao) {
		repository.adiciona(fiscalizacao);
	}

	@Override
	public void atualiza(Fiscalizacao fiscalizacao) {
		repository.atualiza(fiscalizacao);

	}

	@Override
	public void remove(Fiscalizacao fiscalizacao) {
		repository.remove(fiscalizacao);
	}

	@Override
	public List<Fiscalizacao> busca(LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT f FROM Fiscalizacao f ");
		sb.append("WHERE ");
		sb.append("    f.data ");
		sb.append("BETWEEN ");
		sb.append("    :pDataInicio");
		sb.append("    AND :pdataFim");
		
		TypedQuery<Fiscalizacao> query = em.createQuery(sb.toString(), Fiscalizacao.class);
		query.setParameter("pDataInicio", dataInicio);
		query.setParameter("pdataFim", dataFim);
		
		return query.getResultList();
	}
	
	@Override
	public List<Fiscalizacao> busca(Empresa empresa, LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT f FROM Fiscalizacao f ");
		sb.append("WHERE ");
		sb.append("    f.empresa = :pEmpresa");
		sb.append("    AND f.data ");
		sb.append("BETWEEN ");
		sb.append("    :pDataInicio");
		sb.append("    AND :pDataFim");
		
		TypedQuery<Fiscalizacao> query = em.createQuery(sb.toString(), Fiscalizacao.class);
		query.setParameter("pDataInicio", dataInicio);
		query.setParameter("pDataFim", dataFim);
		query.setParameter("pEmpresa", empresa);
		
		return query.getResultList();
	}

	@Override
	public Fiscalizacao buscaPorId(Long id) {
		return repository.buscaPorId(id);
	}

}
