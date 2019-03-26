import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.business.interfaces.IEstadoService;
import br.com.fsma.projeto_web.entities.Estado;

class EstadoTest {
	
	private Estado estado;
	private IEstadoService _service;
	
	@BeforeEach
	void setUp() throws Exception {
		estado = new Estado();
		_service = new EstadoServiceImpl();
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}


	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void adicionaUmNovoEstadoTest() {
		estado.setNome("Espirito Santo");
		estado.setUf("ES");
		_service.adiciona(estado);
		assertEquals("Espirito Santo", estado.getNome());
		assertEquals("ES", estado.getUf());		
	}

}
