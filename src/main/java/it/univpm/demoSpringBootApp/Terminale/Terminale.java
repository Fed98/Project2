package it.univpm.demoSpringBootApp.Terminale;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
	import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
	import java.net.URL;
	import java.net.URLConnection;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.Vector;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
	import org.json.simple.JSONObject;
	import org.json.simple.JSONValue;
	import org.json.simple.parser.ParseException;
	import org.springframework.stereotype.Component;
	import java.io.BufferedInputStream;
	import java.io.BufferedOutputStream;
	import java.io.FileOutputStream;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import it.univpm.demoSpringBootApp.Centrali.Centrali;
import it.univpm.demoSpringBootApp.serialization.Serialization;

import java.lang.reflect.Field;
	/** Classe utilizzare per la dichiarazione del vettore su cui poi vado ad interagire con le varie richieste. Tramite l'url poi
	 * effettua la lettura e il parsing tramite i metodi readdata e insertdata
	 * @author Federico Tartabini
	 * @author Nicola Montesi
	 */
	@Component
	public class Terminale
	{
		public class DownloadFile {



			private static final String csvfile = "http://cidportal.jrc.ec.europa.eu/ftp/jrc-opendata/JRCOD/RES-DATA/10001/LATEST/JRC-GEOPP-DB.csv";

			public static void downloadFileFromUrl(String url, String filePathName, int readTimeoutInSeconds)

					throws MalformedURLException, IOException {
				
				      // create connection

				URLConnection urlConn = new URL(url).openConnection();

				if (readTimeoutInSeconds > 0)

					urlConn.setReadTimeout(readTimeoutInSeconds * 1000);  // setting a timeout

				  // create the stream

				BufferedInputStream in = new BufferedInputStream(urlConn.getInputStream());

				FileOutputStream fos = new FileOutputStream(filePathName);

				   // read the file

				BufferedOutputStream bout = new BufferedOutputStream(fos, 1024); //create a buffer output stream

				byte[] data = new byte[1024];

				int x = 0;

				while ((x = in.read(data, 0, 1024)) >= 0) {

					bout.write(data, 0, x);
				}

				bout.close(); //close output stream

				in.close(); //close input stream

			}

			/**

			 * Use the connection for download file csv

			 * @param args

			 */

			public static void main(String args[]) {

		        //url of data-set assigned 

				String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=jrc-10128-10001";
				if (args.length == 1)

					url = args[0];

				try {

		            //establishes a connection

					URLConnection openConnection = new URL(url).openConnection();

					openConnection.addRequestProperty("User-Agent",

							"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

					InputStream in = openConnection.getInputStream();  //open connection

					String data = "";  //creates and initialized String "data"

					String line = "";//creates and initialized String "line"

					try {

						InputStreamReader inR = new InputStreamReader(in);

						BufferedReader buf = new BufferedReader(inR);



						while ((line = buf.readLine()) != null) {  //while line is empty read line

							data += line;

							System.out.println(line);//print "line"
						}

					} finally {

						in.close();   //close input stream

					}

					/*        the download link search starts!

					         this is the path taken on the website

					        result->resources->desired format->url of csv       */

					JSONObject obj = (JSONObject) JSONValue.parseWithException(data);

					JSONObject objI = (JSONObject) (obj.get("result"));  // 

					JSONArray objA = (JSONArray) (objI.get("resources"));


					for (Object o : objA) {

						if (o instanceof JSONObject) {

							JSONObject o1 = (JSONObject) o;

							String format = (String) o1.get("format");

							String urlD = (String) o1.get("url");

							System.out.println(format + " | " + urlD);

							if (format.equals("csv")) {

								downloadFileFromUrl(urlD, csvfile , 1000);

							}

						}

					}

					System.out.println("OK");  //Print 'OK' if finds everything and all done

				} catch (IOException | ParseException e) {

					e.printStackTrace();

				} catch (Exception e) {

					e.printStackTrace();
				}

				System.out.println("File scaricato" + csvfile);
}
}
		
public class Data {

	// created and initialized a private String JSON_FILE_NAME

	private static final String JSON_FILE_NAME = "getData.json";

	// created and initialized a private String JSON_FILE_NAME_FILTER

	private static final String JSON_FILE_NAME_FILTER = "getDataFilter";

	// created and initialized a String JSON_FILE_NAME_FILTER_SUMMINMAXAVG

	private static final String JSON_FILE_NAME_FILTER_SUMMINMAXAVG = "getDataFilterMAXMINAVGSUM";

	private Serialization s = new Serialization ();

	List<Centrali> call = s.serialize(); // list of type Appartamento called "call"

	/**

	 * Method void that print to a json file the get Data

	 * @author Federico Tartabini, Nicola Montesi

	 */

	public void toJsonData() {

		try {

			// open a buffer writer of JSON_FILE_NAME

			BufferedWriter w = new BufferedWriter(new FileWriter(JSON_FILE_NAME));

			List<String> l = call.stream().map(a -> a.toString()).collect(Collectors.toList());

			Iterator<String> it = l.iterator();

			while (it.hasNext()) { // while there is other elements keep going

				w.write("{");

				w.newLine();

				w.write(it.next());

				w.write("},\n");

			}

			w.newLine();

			w.close(); // close the BuffreWriter

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**

	 * Method that return an int for the switch

	 * @param a is the data chosen by user

	 * @return a int for a correct selection

	 */

	public int ritornaDato(int a) {
		boolean flag1 = false;

		Scanner in = new Scanner(System.in);

		do {

			flag1 = true;

			try {

				a = in.nextInt();

			} catch (Exception e) {

				System.out.println("Inserire un numero intero");

				flag1 = false;

				in.nextLine();

			}

		} while (!flag1);

		return a;
	}

	/**

	 * Method that return an String for the switch

	 * @param Centr is the data chosen by user

	 * @return a String for a correct selection

	 */

	public String ritornaStringa(String centr) {

		Scanner in = new Scanner(System.in);

		centr = in.nextLine();

		return centr;
	}

	public float ritornaFloat(float z) {
		Scanner in = new Scanner(System.in);
		z=in.nextFloat();
		return z;
	}
	

	/**

	 * Return a creation of a file with a name chosen by user

	 * @param a iterator for a correct cycle

	 * @param str string chosen by user

	 */

	  public void ritornaFile(Iterator<Centrali> a,String str) { 

		  try {

			  System.out.println("Dammi il nome che vuoi mettere al file");

			  String appoggio = null;

			  appoggio=this.ritornaStringa(str);

			  BufferedWriter w = new BufferedWriter(new FileWriter(JSON_FILE_NAME_FILTER+appoggio+".json"));

			  while (a.hasNext()) {// while there is other elements keep going

			    	w.write("{");

					w.newLine();

					w.write(a.next().toString()); //put the data on file

					w.write("},\n");

			 }

		 	w.newLine();

	 		w.close();	

     		} catch (IOException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

		}

	  }

	  

/**

 * Method that create a json file with a data filter

 */

	  public void jsonDataFilter() {

		boolean select = false;

		boolean select2 = false;

		boolean select3 = false;
		
		boolean select4 = false;

		int choice = 0;

		int choice2 = 0;

		int choice3 = 0;

		int choice4=0;
		
		int primo = 0;

		int secondo = 0;

		String str="NULL";

		do {

			System.out.println("Scegli che tipo di filtro vuoi fare" + "Si posssono utilizzare questi campi:"

					+ "\n1-Filtro Su Stringa\n2-Filtro su numero-\n3-Filtro su Float");

			choice = this.ritornaDato(choice);

			switch (choice) {

				case 1:

					do {

							System.out.println("Scegliere il campo di utilizzo del filrto\n"

									+ "Si posssono utilizzare questi campi:" + "\n1-Name_powerplant\n2-name_owner");

							choice2 = this.ritornaDato(choice2);

							switch (choice2) {

							case 1:

								String a;

								System.out.println("Dammi la stringa da ricercare per il name_plant");

								a = this.ritornaStringa(str);

								List <Centrali> list = call.stream().filter(e -> e.getName_powerplant().equals(a)).collect(Collectors.toList());

								Iterator<Centrali> iter = list.iterator(); // Iterator creation to scroll the Centrali list

								System.out.println("\nGli elementi trovati per questo filtro sono: \t" + list.size()); // print the created items

								this.ritornaFile(iter,str);	

								select2 = true;

								break;

							case 2:

								String b;

								System.out.println("Dammi la stringa da ricercare per il name_owner");

								b = this.ritornaStringa(str);

								List <Centrali> list2 = call.stream().filter(e -> e.getName_owner().equals(b)).collect(Collectors.toList());

								Iterator<Centrali> iter2 = list2.iterator(); // Iterator creation to scroll the Centrali list

								System.out.println("\nGli elementi trovati per questo filtro sono: \t" + list2.size()); // print the  created items

								this.ritornaFile(iter2,str);							

								select2 = true;

								break;

							}

						} while (!select2);

						select=true;

						break;

				case 2:

					do {

						System.out.println("Scegliere il campo di utilizzo del filtro\n"

								+ "Si posssono utilizzare questi campi:" + "\n1-Gross_cap_ele\n2-Ini_cap_ele");

						choice3 = this.ritornaDato(choice3);

						switch(choice3) {

						case 1: 

							int dato;

							int dato2;

							System.out.println("\nSara' una compare >=primonumero <=secondonumero (scelto) \nPrimo numero:\t");

							dato = this.ritornaDato(primo);

							System.out.println("Secondo numero:\t");

							dato2 = this.ritornaDato(secondo);

							List<Centrali> l = call.stream().filter(a -> a.getGross_cap_ele() >= dato && a.getGross_cap_ele()<=dato2).collect(Collectors.toList());// Use of stream and collections to create a filter of Appartamento list

							Iterator<Centrali> it = l.iterator(); // Iterator creation to scroll the Centrali list

							System.out.println("\nGli elementi trovati per questo filtro sono: \t" + l.size()); // print the size of created items

							this.ritornaFile(it,str);

							select3=true;

							break;

						case 2:

							int dato3;

							int dato4;

							System.out.println("\nSara una compare >=primonumero <=secondonumero (scelto) \nPrimo numero:\t");

							dato3 = this.ritornaDato(primo);

							System.out.println("Secondo numero:\t");

							dato4 = this.ritornaDato(secondo);

							List<Centrali> l2 = call.stream().filter(a -> a.getIni_cap_ele() >= dato3 && a.getIni_cap_ele()<=dato4).collect(Collectors.toList());// Use of stream and collections to create a filter of Appartamento list

							Iterator<Centrali> it2 = l2.iterator(); // Iterator creation to scroll the Centrali list

							System.out.println("\nGli elementi trovati per questo filtro sono: \t" + l2.size()); // print the size of created items

							this.ritornaFile(it2,str);

							select3=true;

							break;

						}
						} while (!select3);

					select=true;

					break;
			case 3:
						do {

							System.out.println("Scegliere il campo di utilizzo del filtro\n"

									+ "Si posssono utilizzare questi campi:" + "\n1-latitude\n2-longitude");

							choice4 = this.ritornaDato(choice4);

							switch(choice4) {

							case 1: 

								float float1;

								float float2;

								System.out.println("\nSara una compare >=primonumero <=secondonumero (scelto) \nPrimo numero:\t");

								float1 = this.ritornaFloat(primo);

								System.out.println("Secondo numero:\t");

								float2 = this.ritornaFloat(secondo);

								List<Centrali> l = call.stream().filter(a -> a.getLatitude() >= float1 && a.getLatitude()<=float2).collect(Collectors.toList());// Use of stream and collections to create a filter of Centrali list

								Iterator<Centrali> it = l.iterator(); // Iterator creation to scroll the Centrali list

								System.out.println("\nGli elementi trovati per questo filtro sono: \t" + l.size()); // print the size of created items

								this.ritornaFile(it,str);

								select4=true;

								break;

							case 2:

								float float3;

								float float4;

								System.out.println("\nSara' una compare >=primonumero <=secondonumero (scelto) \nPrimo numero:\t");

								float3 = this.ritornaFloat(primo);

								System.out.println("Secondo numero:\t");

								float4 = this.ritornaFloat(secondo);

								List<Centrali> l2 = call.stream().filter(a -> a.getLongitude() >= float3 && a.getLongitude()<=float4).collect(Collectors.toList());// Use of stream and collections to create a filter of Appartamento list

								Iterator<Centrali> it2 = l2.iterator(); // Iterator creation to scroll the Centrali list

								System.out.println("\nGli elementi trovati per questo filtro sono: \t" + l2.size()); // print the size of created items

								this.ritornaFile(it2,str);

								select4=true;

								break;

							}

					}while(!select4);

				select=true;}}

			 while (!select);

			System.out.println("E' stato creato il file json con il filtro ");}

	

	

/**

 * return a String due the choice of the user

 * @param a the choice of user

 * @return the choice of user

 */

	public String scelta(int a) {

		String str = "";

		if (a == 1) {

			str = " name_powerplant";

		} else if (a == 2) {

			str = "name_owner ";

		} else if (a == 3) {

			str = " Gross_cap_ele";

		} else if (a == 4) {

			str = " Ini_cap_ele";

		}else if (a == 5) {

			str = "latitude ";
		
		}else if (a == 6) {

				str = "longitude ";}
		
		return str;
	}

/**

 * Method that create a json file with a special statistic of the List<Centrali>

 * @throws IOException

 */

	public void jsonDataSumAvgMinMaxCount() throws IOException {

		// initialized variables sum, count, choise to 0 //json file

		// initialized variables avg, max, min to null

		int sum = 0;

		OptionalDouble avg = null;

		OptionalInt max = null;

		OptionalInt min = null;

		long count = 0;

		int choice = 0;

		Scanner in = new Scanner(System.in);

		boolean validSelection = false;

		// let you choose between different filter fields

		do {

			System.out.println("Scegliere il campo di utilizzo del filto\n" + "Si posssono utilizzare questi campi:"

					+ "\n1-name_powerplant\n2-name_owner\n3-gross_cap_ele\n4-ini_cap_ele\n5-latitude\n6-longitude");

			choice = in.nextInt(); // let write your choice

			// based on the choice switch case of 'choice' starts with the 6 possible cases

			switch (choice) {

			case 1:

				sum = call.stream().filter(p -> p.getName_powerplant() != -1).mapToInt(Centrali::getName_powerplant).sum();

				avg = call.stream().filter(p -> p.getName_powerplant() != -1).mapToInt(Centrali::getName_powerplant).average();

				max = call.stream().filter(p -> p.getName_powerplant() != -1).mapToInt(Centrali::getName_powerplant).max();

				min = call.stream().filter(p -> p.getName_powerplant() != -1).mapToInt(Centrali::getName_powerplant).min();

				validSelection = true;

				break;

			case 2:

				sum = call.stream().filter(p -> p.getName_owner() != -1).mapToInt(Centrali::getName_owner).sum();

				avg = call.stream().filter(p -> p.getName_owner() != -1).mapToInt(Centrali::getName_owner).average();

				max = call.stream().filter(p -> p.getName_owner() != -1).mapToInt(Centrali::getName_owner).max();

				min = call.stream().filter(p -> p.getName_owner() != -1).mapToInt(Centrali::getName_owner).min();

				count = call.stream().filter(p -> p.getName_owner() != -1).mapToInt(Centrali::getName_owner)

						.count();

				validSelection = true;

				break;

			case 3:

				sum = call.stream().filter(p -> p.getGross_cap_ele() != -1).mapToInt(Centrali::getGross_cap_ele).sum();

				avg = call.stream().filter(p -> p.getGross_cap_ele() != -1).mapToInt(Centrali::getGross_cap_ele).average();

				max = call.stream().filter(p -> p.getGross_cap_ele() != -1).mapToInt(Centrali::getGross_cap_ele).max();

				min = call.stream().filter(p -> p.getGross_cap_ele() != -1).mapToInt(Centrali::getGross_cap_ele).min();

				count = call.stream().filter(p -> p.getGross_cap_ele() != -1).mapToInt(Centrali::getGross_cap_ele).count();

				validSelection = true;

				break;

			case 4:

				sum = call.stream().filter(p -> p.getIni_cap_ele() != -1).mapToInt(Centrali::getIni_cap_ele).sum();

				avg = call.stream().filter(p -> p.getIni_cap_ele() != -1).mapToInt(Centrali::getIni_cap_ele).average();

				max = call.stream().filter(p -> p.getIni_cap_ele() != -1).mapToInt(Centrali::getIni_cap_ele).max();

				min = call.stream().filter(p -> p.getIni_cap_ele() != -1).mapToInt(Centrali::getIni_cap_ele).min();

				count = call.stream().filter(p -> p.getIni_cap_ele() != -1).mapToInt(Centrali::getIni_cap_ele).count();

				validSelection = true;

				break;

			case 5:

				sum = call.stream().filter(p -> p.getLatitude() != -1).mapToInt(Centrali::getLatitude).sum();

				avg = call.stream().filter(p -> p.getLatitude() != -1).mapToInt(Centrali::getLatitude).average();

				max = call.stream().filter(p -> p.getLatitude() != -1).mapToInt(Centrali::getLatitude).max();

				min = call.stream().filter(p -> p.getLatitude() != -1).mapToInt(Centrali::getLatitude).min();

				count = call.stream().filter(p -> p.getLatitude() != -1).mapToInt(Centrali::getLatitude).count();

				validSelection = true;

				break;
				
			case 6:

				sum = call.stream().filter(p -> p.getLongitude() != -1).mapToInt(Centrali::getLongitude).sum();

				avg = call.stream().filter(p -> p.getLongitude() != -1).mapToInt(Centrali::getLongitude).average();

				max = call.stream().filter(p -> p.getLongitude() != -1).mapToInt(Centrali::getLongitude).max();

				min = call.stream().filter(p -> p.getLongitude() != -1).mapToInt(Centrali::getLongitude).min();

				count = call.stream().filter(p -> p.getLongitude() != -1).mapToInt(Centrali::getLongitude).count();

				validSelection = true;

				break;


			}

		} while (validSelection != true);

		// in.close(); //close input stream

		// based on the choice prints the filtered data

		BufferedWriter w = new BufferedWriter(new FileWriter(JSON_FILE_NAME_FILTER_SUMMINMAXAVG+this.scelta(choice)+".json"));// open Buffer

		// to write on a

		w.write("{");

		w.newLine();

		w.write("\"Field\":" + this.scelta(choice));

		w.write("\n\"Sum\":" + sum);

		w.write("\n\"Avg\":" + avg);

		w.write("\n\"Max\":" + max);

		w.write("\n\"Min\":" + min);

		w.write("\n\"Count\":" + count);

		w.write("\n}");

		w.newLine();

		w.close();

		System.out.println("File " + JSON_FILE_NAME_FILTER_SUMMINMAXAVG+this.scelta(choice)+".json" + "creato");

	}

	// generate getter and setter of Serialization 's'

	public Serialization getS() {

		return s;

	}

	public void setS(Serialization s) {

		this.s = s;

	}



	public static String getJsonFileName() {

		return JSON_FILE_NAME;}

	}



	


		/** 
		 * Il metodo seguente modella gli oggetti di tipo MetaDati creandoli sulla base degli attributi della classe ripetitore,
		 * così da permettere una stampa ottimale e rapida utilizzando il formato json, dal controller.
		 * @return m Elenco dei tipi di dati presi in analisi
		 */
		public Collection MetaDati()
		{List<it.univpm.demoSpringBootApp.Metadati.MetaDati> m = new ArrayList<>();
			Field[] fields = Serialization.class.getDeclaredFields();
			for (Field o : fields){
					it.univpm.demoSpringBootApp.Metadati.MetaDati mm = new it.univpm.demoSpringBootApp.Metadati.MetaDati();
					mm.setNome(o.getName());
					mm.setTipo(o.getType().getSimpleName());
					m.add (mm);}
			return m;}}
