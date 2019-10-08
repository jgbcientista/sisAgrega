package br.com.sysagrega.controller.vo;

import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import br.com.sysagrega.model.imp.Anexo;

public class AnexoVO {
	
	private List<FileUploadEvent> listaArquAnexados;
	private List<Anexo> anexos;
	private List<Anexo> listAnexos;
	private FileUploadEvent fileUploadEvent;
	private Anexo excluirAnexo;
	private StreamedContent file;
	private String caminhoAnexoProjeto = "C:\\DADOS_SISTEMA\\AGREGA\\ANEXOS\\PROJETO\\";
	private String varAmbiente = "DADOS_SISTEMA";

	
	
	
	public List<FileUploadEvent> getListaArquAnexados() {
		return listaArquAnexados;
	}
	public void setListaArquAnexados(List<FileUploadEvent> listaArquAnexados) {
		this.listaArquAnexados = listaArquAnexados;
	}
	public List<Anexo> getAnexos() {
		return anexos;
	}
	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}
	public List<Anexo> getListAnexos() {
		return listAnexos;
	}
	public void setListAnexos(List<Anexo> listAnexos) {
		this.listAnexos = listAnexos;
	}
	public FileUploadEvent getFileUploadEvent() {
		return fileUploadEvent;
	}
	public void setFileUploadEvent(FileUploadEvent fileUploadEvent) {
		this.fileUploadEvent = fileUploadEvent;
	}
	public Anexo getExcluirAnexo() {
		return excluirAnexo;
	}
	public void setExcluirAnexo(Anexo excluirAnexo) {
		this.excluirAnexo = excluirAnexo;
	}
	public StreamedContent getFile() {
		return file;
	}
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	public String getCaminhoAnexoProjeto() {
		return caminhoAnexoProjeto;
	}
	public void setCaminhoAnexoProjeto(String caminhoAnexoProjeto) {
		this.caminhoAnexoProjeto = caminhoAnexoProjeto;
	}
	public String getVarAmbiente() {
		return varAmbiente;
	}
	public void setVarAmbiente(String varAmbiente) {
		this.varAmbiente = varAmbiente;
	}
	
}
