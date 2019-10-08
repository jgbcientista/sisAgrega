package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.service.IPropostaService;
import br.com.sysagrega.util.DateUtil;

@Named
@ViewScoped
public class GraficoPropostaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IPropostaService propostaService;
	@Inject
	private IClienteService clienteService;
	
	private Boolean pesquisar = false;
    private BarChartModel horizontalBarModel; 
    private BarChartModel horizontalBarModel2;
    private BarChartModel horizontalBarModelSemestre;
    private BarChartModel horizontalGraficoNegocio;
    private BarChartModel horizontalGraficoTipoNegocio;
    private List<Proposta> propostas = new ArrayList<Proposta>();
    private List<Cliente> clientes = new ArrayList<Cliente>();
	
	@PostConstruct
	public void inicializar() {
		pesquisar = false;
		horizontalBarModel = new BarChartModel();
		horizontalBarModel2 = new BarChartModel();
		horizontalBarModelSemestre = new BarChartModel();
		horizontalGraficoNegocio = new BarChartModel();
		horizontalGraficoTipoNegocio = new BarChartModel();
		createBarModels();
	}
	
	 
   	     
    public void createBarModels() {
       createHorizontalBarModel();
       createHorizontalBarModelTrimetre();
       createHorizontalBarModelSemetre();
       graficoPorAreaNegocio();
       graficoPorTipoNegocio();
     }
	     
   public void createHorizontalBarModel() {
      //  horizontalBarModel = new BarChartModel();
        propostas = propostaService.getAllPropostas();
        clientes = clienteService.getAllClientes();
        Boolean temProposta; 
        Integer pesquisaAno = 2018;
        
        for (Cliente cliente : clientes) {
        	temProposta = false; 
        	Integer jan = 0, fev = 0, mar = 0, abr = 0, mai = 0, jun = 0, jul = 0, 
            ago = 0, set = 0, out = 0, nov =0, dez = 0;
        	ChartSeries cli = new ChartSeries();
        	cli.setLabel(cliente.getSigla());
        	for (Proposta proposta : propostas) {
        		if(cliente.getNome().equalsIgnoreCase(proposta.getCliente().getNome())){
        			Integer mes = DateUtil.getMonthFromDate(proposta.getDataInclusao());
        			Integer ano = DateUtil.getYearsFromDateFormated(proposta.getDataInclusao());
        			if(pesquisaAno.equals(ano)){
        				temProposta = true;
        				if(mes.equals(1)){
        					jan=jan+1;
        				}else if (mes.equals(2)){
        					fev=fev+1;
        				}else if (mes.equals(3)){
        					mar=mar+1;
        				}else if (mes.equals(4)){
        					abr=abr+1;
        				}else if (mes.equals(5)){
        					mai=mai+1;
        				}else if (mes.equals(6)){
        					jun=jun+1;
        				}else if (mes.equals(7)){
        					jul=jul+1;
        				}else if (mes.equals(8)){
        					ago=ago+1;
        				}else if (mes.equals(9)){
        					set=set+1;
        				}else if (mes.equals(10)){
        					out=out+1;
        				}else if (mes.equals(11)){
        					nov=nov+1;
        				}else if (mes.equals(12)){
        					dez=dez+1;
        				}else{
        					temProposta = true;
        				}
        			}
        		}
        	}
	        	if(temProposta){
	        		cli.set("Jan", jan);
	        		cli.set("Fev", fev);
	        		cli.set("Mar", mar);
	        		cli.set("Abr", abr);
	        		cli.set("Mai", mai);
	        		cli.set("Jun", jun);
	        		cli.set("Jul", jul);
	        		cli.set("Ago", ago);
	        		cli.set("Set", set);
	        		cli.set("Out", out);
	        		cli.set("Nov", nov);
	        		cli.set("Dez", dez);
	        		horizontalBarModel.addSeries(cli);
	        	}
			}
		
        horizontalBarModel.setTitle("Proposta emitida por Cliente em "+ pesquisaAno);
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Meses");
        xAxis.setMin(0);
        xAxis.setMax(20);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Propostas");     
	}
   
   public void createHorizontalBarModelTrimetre() {
	        propostas = propostaService.getAllPropostas();
	        clientes = clienteService.getAllClientes();
	        Integer pesquisaAno = 2018;
	        
	       	Integer jan = 0, fev = 0, mar = 0;
	       	ChartSeries propo = new ChartSeries();
	       	propo.setLabel("Propostas");
	       	for (Proposta proposta : propostas) {
       			Integer mes = DateUtil.getMonthFromDate(proposta.getDataInclusao());
       			Integer ano = DateUtil.getYearsFromDateFormated(proposta.getDataInclusao());
       			if(pesquisaAno.equals(ano)){
       				if(mes.equals(1)){
      					jan=jan+1;
       				}else if (mes.equals(2)){
       					fev=fev+1;
       				}else if (mes.equals(3)){
       					mar=mar+1;
       				}
       			}
        	}
	       	propo.set("Jan", jan);
	       	propo.set("Fev", fev);
	       	propo.set("Mar", mar);
       		horizontalBarModel2.addSeries(propo);
			
	        horizontalBarModel2.setTitle("Proposta emitidas no Trimestr de "+ pesquisaAno);
	        horizontalBarModel2.setLegendPosition("e");
	        horizontalBarModel2.setStacked(true);
	         
	        Axis xAxis = horizontalBarModel2.getAxis(AxisType.X);
	        xAxis.setLabel("Meses");
	        xAxis.setMin(0);
	        xAxis.setMax(20);
	         
	        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Quantidade");     
		}
   
   	public void createHorizontalBarModelSemetre() {
        propostas = propostaService.getAllPropostas();
        clientes = clienteService.getAllClientes();
        Integer pesquisaAno = 2018;
        
       	Integer jan = 0, fev = 0, mar = 0, abr = 0, mai = 0, jun = 0;
       	ChartSeries semestre = new ChartSeries();
       	semestre.setLabel("Propostas");
       	for (Proposta proposta : propostas) {
			Integer mes = DateUtil.getMonthFromDate(proposta.getDataInclusao());
			Integer ano = DateUtil.getYearsFromDateFormated(proposta.getDataInclusao());
			if(pesquisaAno.equals(ano)){
				if(mes.equals(1)){
					jan=jan+1;
				}else if (mes.equals(2)){
					fev=fev+1;
				}else if (mes.equals(3)){
					mar=mar+1;
				}else if (mes.equals(4)){
					abr=abr+1;
				}else if (mes.equals(5)){
					mai=mai+1;
				}else if (mes.equals(6)){
					jun=jun+1;
				}
			}
       	}
       	semestre.set("Jan", jan);
       	semestre.set("Fev", fev);
       	semestre.set("Mar", mar);
       	semestre.set("Abr", abr);
       	semestre.set("Mai", mai);
       	semestre.set("Jun", jun);
		
       	horizontalBarModelSemestre.addSeries(semestre);
		
		horizontalBarModelSemestre.setTitle("Proposta emitidas no Semestre de "+ pesquisaAno);
		horizontalBarModelSemestre.setLegendPosition("e");
		horizontalBarModelSemestre.setStacked(true);
         
        Axis xAxis = horizontalBarModelSemestre.getAxis(AxisType.X);
        xAxis.setLabel("Meses");
        xAxis.setMin(0);
        xAxis.setMax(20);
         
        Axis yAxis = horizontalBarModelSemestre.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade");     
	}
   	
   	public void graficoPorAreaNegocio() {
	        propostas = propostaService.getAllPropostas();
	        Integer pesquisaAno = 2018;
	        
	       	Integer ma = 0, qp = 0, sst = 0, rs = 0, sgi =0;
	       	ChartSeries propo = new ChartSeries();
	       	propo.setLabel("Propostas");
	       	for (Proposta proposta : propostas) {
     			Integer ano = DateUtil.getYearsFromDateFormated(proposta.getDataInclusao());
     			if(pesquisaAno.equals(ano)){
     				if(proposta.getIdArea() != null){
	     				if(proposta.getIdArea().equals(1L)){
	     					ma = ma+1;
	     				}else if (proposta.getIdArea().equals(2L)){
	     					qp=qp+1;
	     				}else if (proposta.getIdArea().equals(3L)){
	     					sst=sst+1;
	     				}else if (proposta.getIdArea().equals(4L)){
	     					rs=rs+1;
	     				}else if (proposta.getIdArea().equals(4L)){
	     					sgi=sgi+1;
	     				}
     				}
     			}
      	}
	       	propo.set("MA", ma);
	       	propo.set("Q/P", qp);
	       	propo.set("SST", sst);
	       	propo.set("RS", rs);
	       	propo.set("SGI", sgi);
	       	horizontalGraficoNegocio.addSeries(propo);
			
	       	horizontalGraficoNegocio.setTitle("Proposta emitidas por Area de Negocio - "+ pesquisaAno);
	       	horizontalGraficoNegocio.setLegendPosition("e");
	       	horizontalGraficoNegocio.setStacked(true);
	         
	        Axis xAxis = horizontalGraficoNegocio.getAxis(AxisType.X);
	        xAxis.setLabel("Area de Negocio");
	        xAxis.setMin(0);
	        xAxis.setMax(20);
	         
	        Axis yAxis = horizontalGraficoNegocio.getAxis(AxisType.Y);
	        yAxis.setLabel("Quantidade");     
	}
   	
	public void graficoPorTipoNegocio() {
        propostas = propostaService.getAllPropostas();
        Integer pesquisaAno = 2018;
        
       	Integer gp = 0, tc = 0, qsssma = 0;
       	ChartSeries propo = new ChartSeries();
       	propo.setLabel("Propostas");
       	for (Proposta proposta : propostas) {
 			Integer ano = DateUtil.getYearsFromDateFormated(proposta.getDataInclusao());
 			if(pesquisaAno.equals(ano)){
 				if(proposta.getIdServicoNegocio() != null){
     				if(proposta.getIdServicoNegocio().equals(2L)){
     					gp = gp+1;
     				}else if (proposta.getIdServicoNegocio().equals(3L)){
     					tc=tc+1;
     				}else if (proposta.getIdServicoNegocio().equals(1L)){
     					qsssma=qsssma+1;
     				}
 				}
 			}
  	}
       	propo.set("Gerenciamento de Projeto", gp);
       	propo.set("Treinamento e Capacitação", tc);
       	propo.set("Conusltoria e Acessoria", qsssma);
       	horizontalGraficoTipoNegocio.addSeries(propo);
		
       	horizontalGraficoTipoNegocio.setTitle("Proposta emitidas por Tipo de Negocio - "+ pesquisaAno);
       	horizontalGraficoTipoNegocio.setLegendPosition("e");
       	horizontalGraficoTipoNegocio.setStacked(true);
         
        Axis xAxis = horizontalGraficoTipoNegocio.getAxis(AxisType.X);
        xAxis.setLabel("Tipo de Negocio");
        xAxis.setMin(0);
        xAxis.setMax(20);
         
        Axis yAxis = horizontalGraficoTipoNegocio.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade");     
}

 

	public Boolean getPesquisar() {
		return pesquisar;
	}

	public void setPesquisar(Boolean pesquisar) {
		this.pesquisar = pesquisar;
	}
		
	public BarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
	
	public BarChartModel getHorizontalBarModel2() {
	    return horizontalBarModel2;
	}
	
	public BarChartModel getHorizontalBarModelSemestre() {
	    return horizontalBarModelSemestre;
	}



	public BarChartModel getHorizontalGraficoNegocio() {
		return horizontalGraficoNegocio;
	}



	public void setHorizontalGraficoNegocio(BarChartModel horizontalGraficoNegocio) {
		this.horizontalGraficoNegocio = horizontalGraficoNegocio;
	}



	public BarChartModel getHorizontalGraficoTipoNegocio() {
		return horizontalGraficoTipoNegocio;
	}



	public void setHorizontalGraficoTipoNegocio(BarChartModel horizontalGraficoTipoNegocio) {
		this.horizontalGraficoTipoNegocio = horizontalGraficoTipoNegocio;
	}

}
