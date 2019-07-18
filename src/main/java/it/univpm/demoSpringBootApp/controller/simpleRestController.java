package it.univpm.demoSpringBootApp.controller;

import java.io.File;
import java.util.Scanner;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.univpm.demoSpringBootApp.Terminale.Terminale;
import it.univpm.demoSpringBootApp.Terminale.Terminale.Data;
import it.univpm.demoSpringBootApp.serialization.Serialization;
/** 
	 * @author Federico Tartabini

	 * @author Nicola Montesi

	 */
@RestController
public class simpleRestController {	



	
@SuppressWarnings("static-access")
public static void main(String[] args) throws Exception {

			Terminale download = new Terminale();

			Serialization ser = new Serialization();
            
			it.univpm.demoSpringBootApp.Metadati.MetaDati metadata=new it.univpm.demoSpringBootApp.Metadati.MetaDati();
			Data data = new Data();

			;
			Scanner on = new Scanner(System.in);
			int scelta = 0;
			boolean select=false;
			final String csvfile = "http://cidportal.jrc.ec.europa.eu/ftp/jrc-opendata/JRCOD/RES-DATA/10001/LATEST/JRC-GEOPP-DB.csv";
			File exists = new File(csvfile);
			boolean exit = exists.exists();
			if(exit==true) {
				System.out.println("Benvenuto,il file che si desidera scaricare esite gia'\n");

				System.out.println("Dato l'avvio del programma, proseguiamo con l'esecuzione.\n");

			}else {

				System.out.println("Benvenuto \n");

				System.out.println("Dopo aver scaricato il file  http://cidportal.jrc.ec.europa.eu/ftp/jrc-opendata/JRCOD/RES-DATA/10001/LATEST/JRC-GEOPP-DB.csv\"");

				System.out.println("Il programma salvera' i dati avviando la serializzazione");

				download.main(args);

				ser.serialize();	
			}

			System.out.println("Azione consentite:");

			do {

			System.out.println("N1) Serializzazione dei dati\n"+
			                   "N2) visualizzazione Metadata con ripettivi nomi e tipo di attributi/n"+
					           "N3) Visualizza i Data salvati\n"+
					           "N4) Data filtrati a seconda del tipo di dato String, Int o Float\n"+
					           "N5) Salvare le statistiche riguardanti i valori numerici"+
					           "N6)-----Exit--------");
			scelta=on.nextInt();

			switch(scelta) {

			case 1:

				ser.outputfile(ser.lista);

				break;

			case 2:
             
			metadata = MetaDati();
				
				break;

			case 3:

				data.toJsonData();

				break;

			case 4:

				data.jsonDataFilter();

				break;

			case 5:
				data.jsonDataSumAvgMinMaxCount();
				break;
			case 0:
				select=true;
				break;
			}

			}while(!select);

			}
private static String MetaDati() throws Exception{
	ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	mapper.toString();
	String json = mapper.writeValueAsString(MetaDati());
	return json;	
}}
	
	
	



	
		
	