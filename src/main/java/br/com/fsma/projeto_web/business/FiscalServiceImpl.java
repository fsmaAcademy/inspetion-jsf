package br.com.fsma.projeto_web.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.interfaces.IFiscalService;
import br.com.fsma.projeto_web.entities.Fiscal;
import br.com.fsma.projeto_web.repositories.FiscalRepositoryImpl;

@Named
@RequestScoped
public class FiscalServiceImpl implements Serializable, IFiscalService {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private FiscalRepositoryImpl fiscalRepository;
	
	@Override
	public List<Fiscal> buscar() {
		return this.fiscalRepository.buscar();
	}
	
	@Override
	public void adiciona(Fiscal fiscal) {
		this.fiscalRepository.adiciona(fiscal);
	}

	@Override
	public void atualiza(Fiscal fiscal) {
		this.fiscalRepository.atualiza(fiscal);
	}

	@Override
	public void remove(Fiscal fiscal) {
		this.fiscalRepository.remove(fiscal);
	}

	public Fiscal busca(Fiscal fiscal) {
		return this.fiscalRepository.busca(fiscal);
	}

	public List<Fiscal> buscar(String criterio) {
		return this.fiscalRepository.buscar(criterio);
	}

	

}
