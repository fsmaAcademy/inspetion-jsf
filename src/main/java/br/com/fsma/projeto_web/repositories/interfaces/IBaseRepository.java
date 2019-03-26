package br.com.fsma.projeto_web.repositories.interfaces;

import java.util.List;

public interface IBaseRepository<T> {
	List<T> buscar();
	T buscarPorId(Long id);
	T criar(T t);
	T atualizar(T t);
	T remover(T t);
}
