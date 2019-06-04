package br.com.fsma.projeto_web.business.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Fiscal;

public interface IFiscalService {
	public List<Fiscal> buscar();
	public void adiciona(Fiscal fiscal);
	public void atualiza(Fiscal fiscal);
	public void remove(Fiscal fiscal);
}
