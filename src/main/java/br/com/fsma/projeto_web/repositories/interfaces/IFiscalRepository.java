package br.com.fsma.projeto_web.repositories.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Fiscal;

public interface IFiscalRepository {
	public List<Fiscal> buscar();
	public void adiciona(Fiscal fiscal);
	public void atualiza(Fiscal fiscal);
	public void remove(Fiscal fiscal);
}
