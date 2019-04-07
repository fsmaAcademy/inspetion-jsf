package br.com.fsma.projeto_web.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.fsma.projeto_web.business.interfaces.IEmpresaService;
import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.EmpresaRepositoryImpl;
import br.com.fsma.projeto_web.validators.EmpresaValidator;

public class EmpresaServiceImpl  implements Serializable, IEmpresaService {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaRepositoryImpl empresaRepository;
	
	@Override
	public void adiciona(Empresa empresa) {
		empresa = setCnpjECep(empresa);
		empresaRepository.adiciona(empresa);
	}

	@Override
	public void atualiza(Empresa empresa) {
		empresa = setCnpjECep(empresa);
		empresaRepository.atualiza(empresa);
	}

	@Override
	public void remove(Empresa empresa) {
		empresaRepository.remove(empresa);
	}

	@Override
	public List<Empresa> busca(String criterio) {
		return empresaRepository.busca(criterio);
	}
	
	private Empresa setCnpjECep(Empresa empresa) {
		empresa.setCnpj(
				empresa.getCnpj()
					.trim()
					.replace("/", "")
					.replace("-", "")
					.replace(".", "")
				);
		
		empresa.setCep(
				empresa.getCep()
					.trim()
					.replace("-", "")
					.replace(".", "")
				);
		return empresa;
	}

}
