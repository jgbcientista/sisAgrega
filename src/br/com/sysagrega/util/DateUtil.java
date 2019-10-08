package br.com.sysagrega.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class DateUtil {

	static final int					DAY_GRANULARITY			= 1;
	static final int					MONTH_GRANULARITY		= 2;
	static final int					YEAR_GRANULARITY		= 3;
	static final int					MILLIS_IN_SEC			= 1000;
	static final int					MILLIS_IN_MIN			= MILLIS_IN_SEC * 60;
	static final int					MILLIS_IN_HOU			= MILLIS_IN_MIN * 60;
	static final int					MILLIS_IN_DAY			= MILLIS_IN_HOU * 24;
	private static DateFormat			dateFormat				= new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat			dateTimeFormat			= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final TimeZone		DEFAULT_TIME_ZONE		= TimeZone.getTimeZone("Brazil/East");
	private static final DateTimeZone	DEFAULT_DATE_TIME_ZONE	= DateTimeZone.forTimeZone(DEFAULT_TIME_ZONE);
	private static final Locale			DEFAULT_LOCALE			= Locale.forLanguageTag("pt-BR");

	/**
	 * Unidades de medida para intervalos de data
	 * 
	 * @author Henrique DateIntervalUnit
	 */
	public enum DateIntervalUnit {
		MILISSECOND, SECOND, MINUTE, HOUR, DAY, MONTH, YEAR;
	}

	/**
	 * 
	 * @return Retorna um objeto DateTime contendo a hora corrente
	 */
	private static DateTime now() {
		return DateTime.now(DEFAULT_DATE_TIME_ZONE);
	}

	/**
	 * Converte um objeto DateTime em um onbjeto Calendar
	 * 
	 * @param datetime
	 *            Objeto DateTime contendo a data e a hora a serem cnvertidas em
	 *            Calendar
	 * @return Retorna um objeto Calendar contendo a data e a hora especificados
	 */
	private static Calendar dateTimeToCalendar(DateTime datetime) {
		return datetime.toCalendar(DEFAULT_LOCALE);
	}

	/* returns the diff in millisecs between date a and date b, ie: a-b */
	// ZALMOXIS - Verificar se tem que zerar os campos pra calcular... acho que
	// nao
	@Deprecated
	/**
	 * Método depreciado. Utilizar getDateDiff(b,a,DateIntervalUnit.MILISSECNDS)
	 * @param a
	 * @param b
	 * @return
	 */
	public static Long getDiffDatesInMillis(Date a, Date b) {

		/*
		 * Efraim Machado - 17/04/2013 - cada um resolve seu null pointer como
		 * quiser *if (a == null || b == null) { Message m = new
		 * Message(SeverityType.ERROR, TokenType.EX_299); throw new
		 * BusinessObjectException(m); }
		 */

		/*
		 * Luis Henrique Editado em 19/02/2013 às 13:14 Mera otimização
		 */
		/*
		 * Calendar aCalendar = Calendar.getInstance(); aCalendar.setTime(a);
		 * Calendar bCalendar = Calendar.getInstance(); bCalendar.setTime(b);
		 * return (aCalendar.getTimeInMillis() - bCalendar.getTimeInMillis());
		 */
		/*
		 * Luis Henrique return a.getTime() - b.getTime();
		 */
		return getDateDiff(b, a, DateIntervalUnit.MILISSECOND);
	}

	/**
	 * Retorna a data e a hora correntes
	 * 
	 * @return Objeto Calendar contendo a data e a hora correntes
	 */
	public static Calendar getCurrentCalendarDateTime() {
		return now().toCalendar(DEFAULT_LOCALE);
	}

	/**
	 * Retorna a data corrente na hora 0:00:00
	 * 
	 * @return Objeto Date contendo a data corrente
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Date getCurrentDate() {
		DateTime now = now();
		return now.toLocalDate().toDate();
	}

	/**
	 * Retorna a data especificada desprezando a hora (hora 00:00:00)
	 * 
	 * @param date
	 *            Objeto Date contendo a data a ser truncada
	 * @return Objeto Date contendo a data corrente
	 * @author Henrique
	 * @since 06/03/2013 13:42
	 */
	public static Date getDate(Date date) {
		DateTime now = new DateTime(date, DEFAULT_DATE_TIME_ZONE);
		return now.toLocalDate().toDate();
	}

	/**
	 * Retorna a data e a hora correntes
	 * 
	 * @return Objeto Date contendo a data e a hora correntes
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Date getCurrentDateTime() {
		return getCurrentCalendarDateTime().getTime();
	}

	/**
	 * Retorna a data especificada acrescida de um incremento positivo ou
	 * negativo
	 * 
	 * @param incrementType
	 *            Tipo de incremento a ser aplicado
	 * @param startDate
	 *            DateTime contendo a data inicial a partir do qual o incremento
	 *            será aplicado
	 * @param amount
	 *            valor positivo ou negativo que representa o incremento
	 * @return Objeto DateTime contendo a data incrementada
	 * @author Henrique
	 * @since 07/05/2013 17:31
	 **/
	private static Calendar getDateTimeIncrement(DateIntervalUnit incrementType, Date startDate, int amount) {
		Calendar ret = Calendar.getInstance();
		ret.setTime(startDate);
		switch (incrementType) {
		case MILISSECOND:
			ret.add(Calendar.MILLISECOND, amount);
			break;
		case SECOND:
			ret.add(Calendar.SECOND, amount);
			break;
		case MINUTE:
			ret.add(Calendar.MINUTE, amount);
			break;
		case HOUR:
			ret.add(Calendar.HOUR, amount);
			break;
		case DAY:
			ret.add(Calendar.DAY_OF_MONTH, amount);
			break;
		case MONTH:
			ret.add(Calendar.MONTH, amount);
			break;
		case YEAR:
			ret.add(Calendar.YEAR, amount);
			break;
		default:
			ret.add(Calendar.DAY_OF_MONTH, amount);
		}
		return ret;
	}

	/*
	 * private static DateTime getDateTimeIncrement(DateIntervalUnit
	 * incrementType, DateTime startDate, int amount) { DateTime ret = null;
	 * switch (incrementType) { case MILISSECOND: ret =
	 * startDate.plusMillis(amount); break; case SECOND: ret =
	 * startDate.plusSeconds(amount); break; case MINUTE: ret =
	 * startDate.plusMinutes(amount); break; case HOUR: ret =
	 * startDate.plusHours(amount); break; case DAY: ret =
	 * startDate.plusDays(amount); break; case MONTH: ret =
	 * startDate.plusMonths(amount); break; case YEAR: ret =
	 * startDate.plusYears(amount); break; default: ret =
	 * startDate.plusDays(amount); } return ret; }
	 */

	/**
	 * Retorna a a data especificada acrescida de um incremento positivo ou
	 * negativo
	 * 
	 * @param incrementType
	 *            Tipo de incremento a ser aplicado
	 * @param startDate
	 *            Calendar contendo a data inicial a partir do qual o incremento
	 *            será aplicado
	 * @param amount
	 *            valor positivo ou negativo que representa o incremento
	 * @return Objeto Calendar contendo a data incrementada
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 **/
	public static Calendar getCalendarIncrement(DateIntervalUnit incrementType, Calendar startDate, int amount) {
		/*
		 * DateTime date = new DateTime(startDate, DEFAULT_DATE_TIME_ZONE);
		 * return getDateTimeIncrement(incrementType, date,
		 * amount).toCalendar(DEFAULT_LOCALE);
		 */
		return getDateTimeIncrement(incrementType, startDate.getTime(), amount);
	}

	/**
	 * Retorna uma data especificada acrescida de um incremento positivo ou
	 * negativo
	 * 
	 * @param incrementType
	 *            Tipo de incremento
	 * @param startDate
	 *            objeto Date contendo a data a ser incrementada
	 * @param amount
	 *            valor positivo ou negativo a ser incrementado
	 * @return objeto Calendar contendo a data resultante
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Calendar getCalendarIncrement(DateIntervalUnit incrementType, Date startDate, int amount) {
		/*
		 * DateTime date = new DateTime(startDate, DEFAULT_DATE_TIME_ZONE);
		 * return getDateTimeIncrement(incrementType, date,
		 * amount).toCalendar(DEFAULT_LOCALE);
		 */
		return getDateTimeIncrement(incrementType, startDate, amount);
	}

	/**
	 * Retorna uma data especificada acrescida de um incremento positivo ou
	 * negativo
	 * 
	 * @param incrementType
	 *            Tipo de incremento a ser aplicado
	 * @param startDate
	 *            Data iniciar a partir da qual o incremento será aplicado
	 * @param amount
	 *            valor positivo ou negativo que representa o incremento
	 * @return Objeto Date contendo a data incrementada
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Date getDateIncrement(DateIntervalUnit incrementType, Date startDate, int amount) {
		/*
		 * DateTime date = new DateTime(startDate, DEFAULT_DATE_TIME_ZONE);
		 * return getDateTimeIncrement(incrementType, date, amount).toDate();
		 */
		return getDateTimeIncrement(incrementType, startDate, amount).getTime();
	}

	/**
	 * Retorna uma data especificada acrescida de um incremento positivo ou
	 * negativo
	 * 
	 * @param incrementType
	 *            Tipo de incremento a ser aplicado
	 * @param startDate
	 *            Data iniciar a partir da qual o incremento será aplicado
	 * @param amount
	 *            valor positivo ou negativo que representa o incremento
	 * @return Objeto Date contendo a data incrementada
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Date getDateIncrement(DateIntervalUnit incrementType, Date startDate, long amount) {
		/*
		 * DateTime date = new DateTime(startDate, DEFAULT_DATE_TIME_ZONE);
		 * return getDateTimeIncrement(incrementType, date, (int)
		 * amount).toDate();
		 */
		return getDateTimeIncrement(incrementType, startDate, (int) amount).getTime();
	}

	/**
	 * Calcula a diferença entre duas datas. Para meses e anos são retornados
	 * apenas intervalos cheios, mesmo que o intervalo esteja incompleto apenas
	 * por um dia.<br>
	 * <p style="color: red; font-weight: bold;">
	 * Seguro para períodos de tempo de até 100.000.000 de anos.
	 * </p>
	 * 
	 * @param start
	 *            Data inicial do intervalo
	 * @param end
	 *            Data final do intervalo
	 * @param intervalUnit
	 *            Unidade de medida usada na comparação
	 * @return Retorna o tempo decorrido desde a data inicial até a data final
	 *         medido na unidade de tempo especificada. Se a data final for
	 *         menor que a data inicial o número resultante será negativo.
	 * @author Henrique
	 * @since 22/04/2013 18:40
	 */
	public static long getDateDiff(Date start, Date end, DateIntervalUnit intervalUnit) {
		DateTime startDate = new DateTime(start, DEFAULT_DATE_TIME_ZONE);
		DateTime endtDate = new DateTime(end, DEFAULT_DATE_TIME_ZONE);
		Period period;
		/*
		 * O joda-date não consegue trabalhar com intervalos grandes em
		 * milissegundos, pois ela usa valores do tipo int que são
		 * insuficientes. Por este motivo, será usada a comparação direta entre
		 * objetos Date para cálculos mais simples.
		 */
		switch (intervalUnit) {
		case MILISSECOND:
			/*
			 * period = new Period(startDate, endtDate, PeriodType.millis());
			 * return period.getMillis();
			 */
			return end.getTime() - start.getTime();
		case SECOND:
			/*
			 * period = new Period(startDate, endtDate, PeriodType.seconds());
			 * return period.getSeconds();
			 */
			return (end.getTime() - start.getTime()) / MILLIS_IN_SEC;
		case MINUTE:
			/*
			 * period = new Period(startDate, endtDate, PeriodType.minutes());
			 * return period.getMinutes();
			 */
			return (end.getTime() - start.getTime()) / MILLIS_IN_MIN;
		case HOUR:
			/*
			 * period = new Period(startDate, endtDate, PeriodType.hours());
			 * return period.getHours();
			 */
			return (end.getTime() - start.getTime()) / MILLIS_IN_HOU;
		case DAY:
			/*
			 * period = new Period(startDate, endtDate, PeriodType.days());
			 * return period.getDays();
			 */
			return (end.getTime() - start.getTime()) / MILLIS_IN_DAY;
		case MONTH:
			period = new Period(startDate, endtDate, PeriodType.months());
			return period.getMonths();
		case YEAR:
			period = new Period(startDate, endtDate, PeriodType.years());
			return period.getYears();
			/*
			 * int startDay, endDay, startMonth, endMonth, startYear, endYear,
			 * result; Calendar calendar = Calendar.getInstance();
			 * 
			 * calendar.setTime(start); startDay =
			 * calendar.get(Calendar.DAY_OF_YEAR); startMonth =
			 * calendar.get(Calendar.MONTH); startYear =
			 * calendar.get(Calendar.YEAR);
			 * 
			 * calendar.setTime(end); endDay =
			 * calendar.get(Calendar.DAY_OF_YEAR); endMonth =
			 * calendar.get(Calendar.MONTH); endYear =
			 * calendar.get(Calendar.YEAR);
			 * 
			 * result = (endYear - startYear); if (intervalUnit ==
			 * DateIntervalUnit.MONTH) { result = result * 30 + endMonth -
			 * startMonth; }
			 * 
			 * if (startDay > endDay) result--; return result;
			 */
		default:
			return 0;
		}
	}

	@Deprecated
	/**
	 * Método depreciado. Utilizar getDateDiff(b,a,DateIntervalUnit.SECONDS)
	 * @param a
	 * @param b
	 * @return
	 */
	public static Long getDiffDatesInSeconds(Date a, Date b) {
		// return (getDiffDatesInMillis(a, b) / MILLIS_IN_SEC);
		return getDateDiff(b, a, DateIntervalUnit.SECOND);
	}

	@Deprecated
	/**
	 * Método depreciado. Utilizar getDateDiff(b,a,DateIntervalUnit.MINUTES)
	 * @param a
	 * @param b
	 * @return
	 */
	public static Long getDiffDatesInMinuts(Date a, Date b) {
		// return (getDiffDatesInMillis(a, b) / MILLIS_IN_MIN);
		return getDateDiff(b, a, DateIntervalUnit.MINUTE);
	}

	@Deprecated
	/**
	 * Método depreciado. Utilizar getDateDiff(b,a,DateIntervalUnit.HOURS)
	 * @param a
	 * @param b
	 * @return
	 */
	public static Long getDiffDatesInHours(Date a, Date b) {
		// return (getDiffDatesInMillis(a, b) / MILLIS_IN_HOU);
		return getDateDiff(b, a, DateIntervalUnit.HOUR);
	}

	@Deprecated
	/**
	 * Método depreciado. Utilizar getDateDiff(b,a,DateIntervalUnit.DAYS)
	 * @param a
	 * @param b
	 * @return
	 */
	public static Long getDiffDatesInDays(Date a, Date b) {
		// return (getDiffDatesInMillis(a, b) / MILLIS_IN_DAY);
		return getDateDiff(b, a, DateIntervalUnit.DAY);
	}

	@Deprecated
	/**
	 * Método depreciado. Utilizar getDateDiff(b,a,DateIntervalUnit.YEARS)
	 * @param a
	 * @param b
	 * @return
	 */
	public static Long getDiffDatesInYears(Date a, Date b) {
		/*
		 * int dayA, yearA, dayB, yearB; Long result; Calendar calendar =
		 * Calendar.getInstance();
		 * 
		 * calendar.setTime(a); dayA = calendar.get(Calendar.DAY_OF_YEAR); yearA
		 * = calendar.get(Calendar.YEAR);
		 * 
		 * calendar.setTime(b); dayB = calendar.get(Calendar.DAY_OF_YEAR); yearB
		 * = calendar.get(Calendar.YEAR);
		 * 
		 * result = (long) (yearA - yearB);
		 * 
		 * if (dayA < dayB) result--; return result;
		 */
		return getDateDiff(b, a, DateIntervalUnit.YEAR);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearsFromDate(Date date) {
		int dayToday, yearToday, dayAtDate, yearAtDate, result;
		Date today = DateUtil.getCurrentDate();
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(today);
		dayToday = calendar.get(Calendar.DAY_OF_YEAR);
		yearToday = calendar.get(Calendar.YEAR);

		calendar.setTime(date);
		dayAtDate = calendar.get(Calendar.DAY_OF_YEAR);
		yearAtDate = calendar.get(Calendar.YEAR);

		result = yearToday - yearAtDate;

		if (dayToday < dayAtDate)
			result--;

		return result;
	}
	
	/**
	 * @param date
	 *            Objeto Date contendo a data para retornar o mÊs
	 * @return Retorna o mês referente a data passada como parametro. O primeiro mês (Janeiro) é igual a 1. 
	 * Caso seja informado uma data vazia, sera retornado -1
	 *
	 * @author Elton
	 * @since 28/02/2013 17:00
	 */	
	
	public static int getMonthFromDate(Date date) {
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.get(Calendar.MONTH) + 1;
		} else {
			return -1;
		}

	}
	
	/**
	 * @param date
	 *            Objeto Date contendo a data para retornar o ano
	 * @return Retorna o ano referente a data passada como parametro. Caso seja informado uma data vazia, sera retornado -1
	 *
	 * @author Elton
	 * @since 28/02/2013 17:00
	 */	
	
	public static int getYearsFromDateFormated(Date date) {
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.get(Calendar.YEAR);
		} else {
			return -1;
		}
	}

	/*
	 * Luis Henrique 19/02/2012 15:57 As funções abaixo retornam o tmpo
	 * decorrido em horas para os objetos Date fornecidos
	 */

	/**
	 * @param startDate
	 *            Objeto Date contendo a data/hora ser testada
	 * @return Retorna o tempo decorrido desde startDate até a data/hora atual
	 *         do sistema
	 * @exception BusinessObjectException
	 *                Ocorre caso a startDate contenha null
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static long getElapsedHours(Date startDate) {
		return getDateDiff(startDate, getCurrentDate(), DateIntervalUnit.HOUR);
	}

	/**
	 * @param startDate
	 *            Objeto Date contendo a data/hora inicial do intervalo aser
	 *            testado
	 * @param endDate
	 *            Objeto Date contendo a data/hora final do intervalo aser
	 *            testado
	 * @return Retorna o tempo decorrido desde startDate até endDate
	 * @exception BusinessObjectException
	 *                Ocorre caso a startDate e/ou endDate contenham null
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static long getElapsedHours(Date startDate, Date endDate) {
		return getDateDiff(startDate, endDate, DateIntervalUnit.HOUR);
	}

	// ZALMOXIS - Funcao temporaria. A migração para JodaTime calculará da forma
	// correta
	@Deprecated
	/**
	 * Método depreciado, utilizar getDateDiff(b, a, DateIntervalUnit.MONTH)
	 * @param a
	 * @param b
	 * @return
	 */
	public static Long getDiffDatesInMonths(Date a, Date b) {
		/*
		 * long result = getDiffDatesInDays(a, b); if (result != 0) { return
		 * result / 30; } else { return 0L; }
		 */
		return getDateDiff(b, a, DateIntervalUnit.MONTH);
	}

	public static String getTwoLastDigitsCurrentYear() {
		DateFormat df = new SimpleDateFormat("yyyy");
		return df.format(DateUtil.getCurrentDate()).substring(2);
	}

	/**
	 * Retorna a data e a hora correntes
	 * 
	 * @return Objeto Calendar contendo a data e a hora correntes
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Calendar getCurrentCalendar() {
		return now().toCalendar(DEFAULT_LOCALE);
	}

	/**
	 * Retorna a data corrente sem informação de hora
	 * 
	 * @return Objeto Calendar contendo a data corrente
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Calendar getCurrentCalendarDate() {
		DateTime now = now();
		return new DateTime(now.toLocalDate()).toCalendar(DEFAULT_LOCALE);
		/*
		 * Calendar ret = Calendar.getInstance();
		 * ret.set(ret.get(Calendar.YEAR), ret.get(Calendar.MONTH),
		 * ret.get(Calendar.DATE), 0, 0, 0); return getDate(date);
		 */
	}

	/**
	 * Retorna a data corrente acrescida de um incremento positivo ou negativo
	 * 
	 * @param type
	 *            Tipo de incremento
	 * @param increment
	 *            valor positivo ou negativo a ser incrementado
	 * @return objeto Date contendo a data resultante
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Date getDateIncrement(DateIntervalUnit type, int increment) {
		return getDateIncrement(type, getCurrentCalendar(), increment);
	}

	/**
	 * Retorna uma data especificada acrescida de um incremento positivo ou
	 * negativo
	 * 
	 * @param type
	 *            Tipo de incremento
	 * @param startDate
	 *            objeto Calendar contendo a data a ser incrementada
	 * @param increment
	 *            valor positivo ou negativo a ser incrementado
	 * @return objeto Date contendo a data resultante
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Date getDateIncrement(DateIntervalUnit type, Calendar startDate, int increment) {
		return getCalendarIncrement(type, startDate, increment).getTime();
	}

	/**
	 * Retorna a data corrente acrescida de um incremento positivo ou negativo
	 * 
	 * @param type
	 *            Tipo de incremento
	 * @param increment
	 *            valor positivo ou negativo a ser incrementado
	 * @return objeto Calendar contendo a data resultante
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static Calendar getCalendarIncrement(DateIntervalUnit type, int increment) {
		return getCalendarIncrement(type, getCurrentCalendar(), increment);
	}

	/**
	 * Compara a data passada como argumento com a data corrente. Nesta
	 * comparação a hora corrente também é considerada.
	 * 
	 * @param date
	 *            objeto Date contendo a data a ser comparada
	 * @return Em relação à data corrente: < 0 quando date for menor, 0 quando
	 *         date for igual e > 0 quando date for maior.
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static int compareToCurrentDateTime(Date date) {
		return compareDates(date, getCurrentDateTime());
	}

	/**
	 * Compara as datas passadas como argumentos</br></br> <b>É importante
	 * observar que as datas serão comparadas em todos os seus detalhes,
	 * incluindo os milissegundos.</br> Caso deseje desprezar algum detalhe ele
	 * deverá ser descartado ou equalizado antes da comparação.</b> Para
	 * simplifcar o uso neste caso, utilize o método compareDatesIgnoreDetails.
	 * 
	 * @param date1
	 *            data de referência
	 * @param date2
	 *            data a ser usada como critério de comparação
	 * @return Menor que 0 quando date1 for menor que date2<br>
	 *         Igual a 0 quando date1 for igual a date2<br>
	 *         Maior que 0 quando date1 for maior que date2.
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static int compareDates(Date date1, Date date2) {
		/*
		 * Henrique 07/05/2013 19:00 O código original foi restaurado, pois o
		 * comportamento estava correto, porém o uso estava errado. Foi
		 * adicionada no JavaDoc uma observação para deixar claro que nenhum
		 * detalhe é ignorado na comparação Modificar este comportamento que foi
		 * originalmente planejado causará erro grave em outras partes do código
		 * que necessitam da comparação precisa, sendo esta modificação não
		 * apenas desencorajada como inadequada. Para comparações ignorando
		 * detalhes específicos foi criado um novo método chamado
		 * compareDatesIgnoreDetails
		 */
		return toGMTDate(date1).compareTo(toGMTDate(date2));
	}

	/**
	 * Compara as datas passadas como argumentos ignorando os campos
	 * especificados.
	 * 
	 * @param date1
	 *            data de referência
	 * @param date2
	 *            data a ser usada como critério de comparação
	 * @param ignoreDetails
	 *            detalhes a serem ignorados
	 * @return < 0 quando date1<date2, 0 quando date1 = date2 e > 0 quando date1
	 *         > date2.
	 * @author Henrique
	 * @since 07/05/2013 18:56
	 */
	public static int compareDates(Date date1, Date date2, DateIntervalUnit... ignoreDetails) {
		Calendar date01 = Calendar.getInstance(DEFAULT_LOCALE);
		date01.setTime(date1);
		Calendar date02 = Calendar.getInstance(DEFAULT_LOCALE);
		date02.setTime(date2);

		for (DateIntervalUnit detail : ignoreDetails) {
			switch (detail) {
			case MILISSECOND:
				date01.set(Calendar.MILLISECOND, 0);
				date02.set(Calendar.MILLISECOND, 0);
				break;
			case SECOND:
				date01.set(Calendar.SECOND, 0);
				date02.set(Calendar.SECOND, 0);
				break;
			case MINUTE:
				date01.set(Calendar.MINUTE, 0);
				date02.set(Calendar.MINUTE, 0);
				break;
			case HOUR:
				date01.set(Calendar.HOUR, 0);
				date02.set(Calendar.HOUR, 0);
				break;
			case DAY:
				date01.set(Calendar.DAY_OF_MONTH, 0);
				date02.set(Calendar.DAY_OF_MONTH, 0);
				break;
			case MONTH:
				date01.set(Calendar.MONTH, 0);
				date02.set(Calendar.MONTH, 0);
				break;
			case YEAR:
				date01.set(Calendar.YEAR, 0);
				date02.set(Calendar.YEAR, 0);
				break;
			}
		}
		return compareDates(date01.getTime(), date02.getTime());
	}

	/**
	 * Compara a data passada como argumento com a data corrente. Nesta
	 * comparação a hora corrente é desprezada.
	 * 
	 * @param date
	 *            objeto Date contendo a data a ser comparada
	 * @return Em relação à data corrente: < 0 quando date for menor, 0 quando
	 *         date for igual e > 0 quando date for maior.
	 * @author Henrique
	 * @since 28/02/2013 17:00
	 */
	public static int compareToCurrentDate(Date date) {
		return compareDates(date, getCurrentDate(), DateIntervalUnit.HOUR, DateIntervalUnit.MINUTE, DateIntervalUnit.SECOND, DateIntervalUnit.MILISSECOND);
	}

	public static boolean isAfter(Date dateA, Date dateB) {
		dateA = getDate(dateA);
		dateB = getDate(dateB);
		return dateA.after(dateB);
	}

	public static boolean isBefore(Date dateA, Date dateB) {
		dateA = getDate(dateA);
		dateB = getDate(dateB);
		return dateA.before(dateB);
	}
	
	/**
	 * Verifica se dateA é posterior a dateB
	 * 
	 * @param dateA
	 *          objeto Date contendo a data a ser comparada
	 * @param dateB
	 * 			objeto Date contendo a data a ser comparada	
	 * @return true se dateA for posterior a dateB
	 * @author Elton
	 * 
	 */
	public static boolean isAfterDateTime(Date dateA, Date dateB) {
		return toGMTDate(dateB).before(toGMTDate(dateA));
	}
	
	public static boolean isBeforeDateTime(Date dateA, Date dateB) {
		return toGMTDate(dateA).before(toGMTDate(dateB));
	}

	public static String formattedDate(Date date) {
		if (date != null) {
			return dateFormat.format(toGMTDate(date));
		}
		return "";
	}
	
	public static String formattedDateTime(Date date) {
		if (date != null) {
			return dateTimeFormat.format(toGMTDate(date));
		}
		return "";
	}

	public static Integer calculateAge(Date dateOfBirth) {
		if (dateOfBirth != null) {
			/*
			 * Henrique 07/05/2013 19:13 A diferença em anos é suficiente para
			 * determinar a idade.
			 */
			int age = (int) getDateDiff(dateOfBirth, getCurrentDate(), DateIntervalUnit.YEAR);
			/*
			 * Integer age = 0; Calendar born = Calendar.getInstance(); Calendar
			 * now = Calendar.getInstance(); now.setTime(new Date());
			 * born.setTime(dateOfBirth); if (born.after(now)) { throw new
			 * BusinessObjectException(new Message(TokenType.EX_429)); } age =
			 * now.get(Calendar.YEAR) - born.get(Calendar.YEAR); if
			 * (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))
			 * { age -= 1; }
			 */
			return age;
		}
		return null;
	}

	/**
	 * Informa se um determinado intervalo está completo para um período
	 * especificado
	 * 
	 * @param interval
	 *            Intervalo a ser testado
	 * @param start
	 *            Data inicial do período
	 * @param end
	 *            Data final do período
	 * @param intervalUnit
	 *            unidade de tempo a ser usada na comparação
	 * @return Retorna True se o intervalo for maior ou igual à diferença entre
	 *         as datas fornecidas
	 */
	public static boolean isComplete(long interval, Date start, Date end, DateIntervalUnit intervalUnit) {
		return getDateDiff(start, end, intervalUnit) >= interval;
	}

	/**
	 * Informa se um determinado intervalo corresponde exatamente a um período
	 * especificado
	 * 
	 * @param interval
	 *            Intervalo a ser testado
	 * @param start
	 *            Data inicial do período
	 * @param end
	 *            Data final do período
	 * @param intervalUnit
	 *            unidade de tempo a ser usada na comparação
	 * @return True se o intervalo for maior ou igual à diferença entre as datas
	 *         fornecidas
	 */
	public static boolean isCompleteExact(int interval, Date start, Date end, DateIntervalUnit intervalUnit) {
		Date reference = getDateIncrement(intervalUnit, start, interval);
		Date endDate = toGMTDate(end);
		return compareDates(reference, endDate) == 0;
	}

	/**
	 * Informa se um determinado intervalo corresponde exatamente a um período
	 * especificado ignorando as horas
	 * 
	 * @param interval
	 *            Intervalo a ser testado
	 * @param start
	 *            Data inicial do período
	 * @param end
	 *            Data final do período
	 * @param intervalUnit
	 *            unidade de tempo a ser usada na comparação
	 * @return True se o intervalo for maior ou igual à diferença entre as datas
	 *         fornecidas
	 */
	public static boolean isCompleteExactIgnoreTime(int interval, Date start, Date end, DateIntervalUnit intervalUnit) {
		Date reference = getDateIncrement(intervalUnit, getDate(start), interval);
		Date endDate = getDate(end);
		return compareDates(reference, endDate) == 0;
	}

	public static Date toGMTDate(Date date) {
		DateTime dateTime = new DateTime(date, DEFAULT_DATE_TIME_ZONE);
		return dateTime.toDate();
	}

	public static Integer getLastYear() {
		return getCalendarIncrement(DateIntervalUnit.YEAR, -1).get(Calendar.YEAR);//+1900;
	}

	public static Date getLastSecondOfYearDate(int year) {
		DateTime dateTime = new DateTime(year, 1, 1, 1, 1);
		DateTime lastDate = dateTime.dayOfYear().withMaximumValue().secondOfDay().withMaximumValue();
		return lastDate.toDate();
		
	}
	
	public static Date getFirstSecondOfYearDate(int year) {
		DateTime dateTime = new DateTime(year, 1, 1, 1, 1);
		DateTime lastDate = dateTime.dayOfYear().withMinimumValue().secondOfDay().withMinimumValue();
		return lastDate.toDate();
		
	}
	
	public static boolean isLeapYear() {
		int ano;
		Calendar cal = Calendar.getInstance();
		ano = cal.get(Calendar.YEAR);
		return new GregorianCalendar().isLeapYear(ano);
		
	}
	
	/**
	 * retorna o m�s e ano corrente
	 * @return String MM/yyyy
	 * @author Elton
	 */
	public static String getCurrentMonthAndYear() {
		
		return new SimpleDateFormat("MM.yyyy").format(new Date());
		
	}
	
	public static String getFormatedCurrentDate() {
		
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		
	}
	
	/**
	 * retorna o m�s e ano corrente
	 * @return String dd.MM.yyyy
	 * @author Paulo
	 */
	public static String getCurrentDayMonthAndYear() {
		
		return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		
	}
	
	/**
	 * Verifica se data inicial e menor que data final
	 * @return boolean
	 * @author Paulo
	 */
	public static Boolean validaDataInicialDataFinal(Date dataInicial, Date dataFinal){
		if (dataInicial.before(dataFinal)){
			return true;
		}else if(dataInicial.after(dataFinal)){
			return false;
		}else{
			return true;
		}
	}
}
