package util;

import java.text.SimpleDateFormat;

public class DateUtils {
	private static String dataAtualFormatada;

	public static String getDataHoraAtual() {
		dataAtualFormatada = new SimpleDateFormat("ddMMyyyyHHmmss")
                .format(System.currentTimeMillis());
		
		return dataAtualFormatada;
	}

}
