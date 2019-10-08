package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ICltMensal;
import br.com.sysagrega.model.imp.CltMensal;
import br.com.sysagrega.repository.ICltMensalRepository;
import br.com.sysagrega.service.ICltMensalService;
import br.com.sysagrega.util.cdi.Transactional;

public class CltMensalService implements ICltMensalService  {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICltMensalRepository cltMensalRepository;

	@Override
	@Transactional
	public ICltMensal salvar(ICltMensal cltMensal) {
		ICltMensal cltMensalCriado = new CltMensal();
		try {
			cltMensalCriado = this.cltMensalRepository.saveOrMerge(cltMensal);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados (cltMensal)!");
		}
		return cltMensalCriado;
	}

	@Override
	public List<CltMensal> getAllCltMensal() {
		return cltMensalRepository.getAllCltMensal();
	}

	@Override
	@Transactional
	public ICltMensal atualizarCliente(ICltMensal cltMensal) {
		ICltMensal cltMensalAtual = new CltMensal();
		try {
			cltMensalAtual = this.cltMensalRepository.saveOrMerge(cltMensal);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");
		}
		return cltMensalAtual;
	}

	@Override
	@Transactional
	public void excluirCltMensal(CltMensal cltMensal) {
		try{
			if (cltMensal != null) {
				this.cltMensalRepository.remover(cltMensal);
			} else {
				throw new NegocioException("Favor selecionar um Celetista Mensalista!");
			}
		} catch (Exception e) {
			throw new NegocioException("Erro ao tentar excluir - banco de dados!");
		}
	}
	
	@Override
	public CltMensal getCltMensalById(Long cod) {
		return (CltMensal) this.cltMensalRepository.getCltMensalById(cod);
	}


}
