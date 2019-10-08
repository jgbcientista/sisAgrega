package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IProfissional extends Serializable {

	boolean isNovo();

	boolean isExistente();

	Long getId();

	void setId(Long id);

	String getNome();

	void setNome(String nome);

	String getProfissao();

	void setProfissao(String profissao);

	String getCpf();

	void setCpf(String cpf);

	String getRg();

	void setRg(String rg);

	String getPisPasep();

	void setPisPasep(String pisPasep);

	String getTamanhoCamisa();

	void setTamanhoCamisa(String tamanhoCamisa);

	String getTamanhoCalca();

	void setTamanhoCalca(String tamanhoCalca);

	String getNumeroSapato();

	void setNumeroSapato(String numeroSapato);

	Date getDataNascimento();

	void setDataNascimento(Date dataNascimento);

	String getTelefone();

	void setTelefone(String telefone);

	String getCelular();

	void setCelular(String celular);

	String getEmail();
	
	void setEmail(String email);

	IEndereco getEndereco();

	void setEndereco(IEndereco endereco);

	IDadosBancarios getDadosBancarios();

	void setDadosBancarios(IDadosBancarios dadosBancarios);

	Date getAsoValidade();

	void setAsoValidade(Date asoValidade);

	String getDisponibilidade();

	void setDisponibilidade(String disponibilidade);

	String getDescricaoDiversa();

	void setDescricaoDiversa(String descricaoDiversa);

	String getCnh();

	void setCnh(String cnh);

	String getTipoColaborador();

	String getEspecializacao();

	String getTipoContratacao();

	String getClasseCTPS();

	String getSerieCTPS();

	String getEscolaridade();

	String getGraduacao();

	String getPosgraducao();

	Date getInicioAtividade();

	Date getFimAtividade();

	void setTipoColaborador(String tipoColaborador);

	void setEspecializacao(String especializacao);

	void setTipoContratacao(String tipoContratacao);

	void setClasseCTPS(String classeCTPS);

	void setSerieCTPS(String serieCTPS);

	void setEscolaridade(String escolaridade);

	void setGraduacao(String graduacao);

	void setPosgraducao(String posgraducao);

	void setInicioAtividade(Date inicioAtividade);

	void setFimAtividade(Date fimAtividade);

	String getTipoSanguineo();

	void setTipoSanguineo(String tipoSanguineo);

	void setNumeroCTPS(String numeroCTPS);

	String getNumeroCTPS();

	String getFuncaoCargo();

	void setFuncaoCargo(String funcaoCargo);

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	void setDataInclusao(Date dataInclusao);

	Date getDataInclusao();

	boolean isAtivo();

	void setAtivo(boolean ativo);

	String getCnpj();

	void setCnpj(String cnpj);

	Long getInscricaoEstadual();

	void setInscricaoEstadual(Long inscricaoEstadual);

	Long getInscricaoMunicipal();

	void setInscricaoMunicipal(Long inscricaoMunicipal);

	String getCnae();

	void setCnae(String cnae);

	String getSigla();

	void setSigla(String sigla);

	void setRazaoSocial(String razaoSocial);

	String getRazaoSocial();

	String getTelefonePrincipal();

	void setTelefonePrincipal(String telefonePrincipal);

	IEndereco getEnderecoPj();

	void setEnderecoPj(IEndereco enderecoPj);

	String getEmailPj();

	void setEmailPj(String emailPj);

	Usuario getUsuarioAcesso();

	void setUsuarioAcesso(Usuario usuarioAcesso);

	String getNumeroConselho();

	void setNumeroConselho(String numeroConselho);

	Boolean getAcessarIntegra();

	void setAcessarIntegra(Boolean acessarIntegra);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
