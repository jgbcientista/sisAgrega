package br.com.sysagrega.util;



import java.io.Serializable;

public class InterfaceConstants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3184797548011700267L;

	//CPF
	public static final String CPF_MASK = "999.999.999-99";//ValidateUtil.REGEX_CPF;
	public static final String CPF_MASK_MAX_LENGTH = Integer.valueOf(CPF_MASK.length()).toString();
	
	//NAME
	public static final String NAME_MASK = "";//ValidateUtil.REGEX_NAME;
	public static final String NAME_MAX_LENGTH = "100";
	
	//PHONE
	public static final String PHONE_MASK = "(99)99999-9999";
	public static final String PHONE_MAX_LENGTH = Integer.valueOf(PHONE_MASK.length()).toString();
	
	//BRANCH
	public static final String BRANCH_MASK = "9999";
	public static final String BRANCH_MAX_LENGTH = Integer.valueOf(BRANCH_MASK.length()).toString();

	
	//ORDINANCE NUMBER
	public static final String ORDINANCE_NUMBER_MAX_VALUE = "999999999999999";
	public static final String ORDINANCE_NUMBER_MASK = ORDINANCE_NUMBER_MAX_VALUE.replace(".", "");
	public static final String ORDINANCE_NUMBER_MAX_LENGTH = Integer.valueOf(ORDINANCE_NUMBER_MASK.length()).toString();;
	
	
	//EMAIL
	public static final String EMAIL_MASK = "";
	public static final String EMAIL_MAX_LENGTH = Integer.valueOf(EMAIL_MASK.length()).toString();

	public static final String CEP_MASK = "99999-999";
	
	public static final String DATE_MASK = "dd/MM/yyyy";
	
	public static final String TIME_MASK = "HH:mm:ss";
	
	public static final String HOUR_MINUTES_MASK = "99:99";
	
	public static final String DATE_TIME_MASK = "dd/MM/yyyy HH:mm:ss";
	
	public static final String MIN_DATE = "01/01/1900";
	
	public static final String LOCALE_DATE = "pt_BR";
	
	//Tratamento para limite de data corrente desprezando horas
	public static final String MAX_DATE = DateUtil.formattedDate(DateUtil.getCurrentDate());
	
	//Tratamento para limite de data e hora corrente
	public static final String MAX_DATE_TIME = DateUtil.formattedDateTime(DateUtil.getCurrentDateTime());
	
	//Diretorio arquivos ireport SO Windows
	public static final String IREPORT_PATH_WIN = "C:\\temp\\";

	public static final String IREPORT_PATH_JASPER = "/jasper/";
	public static final String IREPORT_PATH_IMAGE = "/resources/images/logoAgrega2.jpg";
	
	
	
	

	
}
