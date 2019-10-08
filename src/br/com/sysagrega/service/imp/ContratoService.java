package br.com.sysagrega.service.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.repository.IContratoRepository;
import br.com.sysagrega.service.IContratoService;
import br.com.sysagrega.util.cdi.Transactional;

public class ContratoService implements IContratoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IContratoRepository contratoRepository;

	
	@Override
	public Contrato getContratoById(Long id) {
		
		return this.contratoRepository.getContratoById(id);
		
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IContratoService#salvar(br.com.sysagrega.model.imp.Contrato)
	 */
	@Override
	@Transactional
	public void salvar(IContrato contrato) {

		try {

			// Validação para campos obrigatórios e datas
			validaCamposObrigatorios(contrato);
			
			this.contratoRepository.saveOrMerge(contrato);
			

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}

	private void validaCamposObrigatorios(IContrato contrato) {

		if (contrato == null) {

			throw new NegocioException("Favor informar um contrato!");

		} else if (contrato.getNrContrato().isEmpty() || contrato.getNrContrato() == null) {

			throw new NegocioException("Favor informar o número do contrato!");
		}
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IContratoService#getAllContratos()
	 */
	@Override
	public List<Contrato> getAllContratos() {

		return contratoRepository.getAllContratos();

	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IContratoService#atualizarContrato(br.com.sysagrega.model.imp.Contrato)
	 */
	@Override
	@Transactional
	public IContrato atualizarContrato(Contrato contrato) {

		IContrato contratoNew = new Contrato();

		try {

			// Validação para campos obrigatórios
			validaCamposObrigatorios(contrato);

			contratoNew = this.contratoRepository.saveOrMerge(contrato);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

		return contratoNew;

	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IContratoService#excluirContrato(br.com.sysagrega.model.imp.Contrato)
	 */
	@Override
	@Transactional
	public void excluirContrato(Contrato contrato) {

		if (contrato != null) {

			// Removendo a proposta
			this.contratoRepository.remover(contrato);

		} else {

			throw new NegocioException("Favor selecionar um contrato!");

		}

	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IContratoService#getContratoByFilter(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Contrato> getContratoByFilter(String nrContrato, String filtroCliente, String filtroStatus,
				Date filtroDataInicial, Date filtroDataFinal) {
		return this.contratoRepository.getContratoByFilter(nrContrato, filtroCliente, filtroStatus, filtroDataInicial, filtroDataFinal);

	}
}
