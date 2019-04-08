package br.com.fsma.projeto_web.repositories;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.interfaces.IEstadoRepository;

@Named
@RequestScoped
public class EstadoRepositoryImpl implements Serializable, IEstadoRepository {
	
	private static final long serialVersionUID = 1L;

	private Repository<Estado> repository;
	
	@Inject
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		repository = new Repository<Estado>(this.em, Estado.class);
	}
	
	@Override
	public List<Estado> buscar() {
		CriteriaQuery<Estado> query = em.getCriteriaBuilder().createQuery(Estado.class);
		query.select(query.from(Estado.class));
		List<Estado> lista = em.createQuery(query).getResultList();

		return lista;
	}
	
	@Override
	public void adiciona(Estado estado) {
		System.out.println(estado);
		repository.adiciona(estado);
	}

	@Override
	public void atualiza(Estado estado){
		em.merge(estado);
	}
	
	@Override
	public void remove(Estado estado) {
		repository.remove(estado);
	}

	@Override
	public Estado buscarPorId(Long id) {
		Estado estado = repository.buscaPorId(id);
		return estado;
	}

	@Override
	public List<Estado> buscarPorNomeOuUf(String criterio) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e FROM Estado e ");
		sb.append("WHERE ");
		sb.append("   e.nome LIKE CONCAT('%',:pCriterio,'%')");
		sb.append("   or e.uf LIKE CONCAT('%',:pCriterio,'%')");
		
		TypedQuery<Estado> query = em.createQuery(sb.toString(), Estado.class);
		
		query.setParameter("pCriterio", criterio);

		try {
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public Estado buscaPorUf(String uf) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select e from Estado e ");
		sb.append(" where ");
		sb.append("   e.uf = :puf");
		
		TypedQuery<Estado> query = em.createQuery(sb.toString(), Estado.class);
		
		query.setParameter("puf", uf);

		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public boolean existe(Estado estado) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select e from Estado e ");
		sb.append(" where ");
		sb.append("   e.uf = :puf");
		sb.append("   and e.nome = :pNome");
		
		TypedQuery<Estado> query = em.createQuery(sb.toString(), Estado.class);
		
		query.setParameter("puf", estado.getUf());
		query.setParameter("pNome", estado.getNome());

		try {
			estado = query.getSingleResult();
			return estado != null;
		} catch (NoResultException ex) {
			return false;
		}
	}

	@Override
	public boolean existePorId(Long id) {
		return buscarPorId(id) != null;
	}

}
