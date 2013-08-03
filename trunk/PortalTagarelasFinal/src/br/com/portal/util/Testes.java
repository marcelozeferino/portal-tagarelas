package br.com.portal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testes {

	public static void main(String[] args) {
		metodo();
	}

	public static void metodo() {
		try {
			DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date data = (Date) dataFormat.parse("04/06/2013");
			
			DateFormat horaFormat = new SimpleDateFormat("hh:mm");
			Date hora = (Date) horaFormat.parse("17:29");
			
			System.out.println(data);
			System.out.println(hora);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}