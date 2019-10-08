package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IUsuario;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.repository.IUsuarioRepository;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.util.cdi.Transactional;

public class UsuarioService implements IUsuarioService  {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IUsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public IUsuario salvar(IUsuario usuario) {
		if (usuario != null && (usuario.getNome()!=null && !usuario.getNome().isEmpty()) &&
			usuario.getLogin()!=null && !usuario.getLogin().isEmpty()){
			return this.usuarioRepository.saveOrMerge(usuario);
		}else {
			throw new NegocioException("Os Dados do usuário estão incompletos! ");
		}
	}

	@Override
	public List<Usuario> getAll() {
		return usuarioRepository.getAll();
	}
 
	@Override
	@Transactional
	public IUsuario atualizar(IUsuario usuario) {
		if (usuario != null && (usuario.getNome()!=null && !usuario.getNome().isEmpty()) &&
			usuario.getLogin()!=null && !usuario.getLogin().isEmpty()){
			return this.usuarioRepository.saveOrMerge(usuario);
		}else {
			throw new NegocioException("Os Dados do usuário estão incompletos!");
		}
	}

	@Override
	@Transactional
	public void excluir(IUsuario usuario) {
		if (usuario != null) {
			this.usuarioRepository.remover(usuario);
		} else {
			throw new NegocioException("Favor selecionar um usuario!");
		}
	}
	
	@Override
	public List<Usuario> getByFilter(String login, String nome, String perfil) {
		return this.usuarioRepository.getByFilter(login, nome, perfil);
	}

	@Override
	public Usuario getById(Long id) {
		return (Usuario) this.usuarioRepository.getById(id);
	}
	public Usuario getUserPerfilByLogin(String login) {
		return usuarioRepository.getUserPerfilByLogin(login);
	}

}
