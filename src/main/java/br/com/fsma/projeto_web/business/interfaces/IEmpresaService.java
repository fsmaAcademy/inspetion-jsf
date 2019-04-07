package br.com.fsma.projeto_web.business.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Estado;

public interface IEmpresaService {
	
	public void adiciona(Empresa empresa);
	public void atualiza(Empresa empresa);
	public void remove(Empresa empresa);
	public List<Empresa> busca(String criterio);
	Empresa buscaPorId(Long id);
	List<Empresa> buscaPorBairro(Bairro bairro);
	Empresa buscaPorNome(String criterio);
	

}
