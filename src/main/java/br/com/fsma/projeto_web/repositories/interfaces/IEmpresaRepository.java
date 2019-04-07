package br.com.fsma.projeto_web.repositories.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Estado;

public interface IEmpresaRepository {
	public void adiciona(Empresa empresa);
	public void atualiza(Empresa empresa);
	public void remove(Empresa empresa);
	public List<Empresa> busca();
	public List<Empresa> busca(String criterio);
	public Empresa buscaPorId(Long id);
	public Empresa buscaPorNome(String nome);
	public List<Empresa> buscaPorCriterioEmBairroCidadeEstado(String criterio, Bairro bairro, Cidade cidade, Estado estado);
	public boolean existe(Empresa empresa);
	Empresa buscaPorCnpj(String cnpj);
}
