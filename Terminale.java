package it.univpm.demoSpringBootApp.Terminale;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Collection;
	import java.util.List;
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.net.URI;
	import java.net.URL;
	import java.net.URLConnection;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.Vector;
	import org.json.simple.JSONArray;
	import org.json.simple.JSONObject;
	import org.json.simple.JSONValue;
	import org.json.simple.parser.ParseException;
	import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import it.univpm.demoSpringBootApp.Serialize.Serialize;

import java.lang.reflect.Field;
	/** Classe utilizzare per la dichiarazione del vettore su cui poi vado ad interagire con le varie richieste. Tramite l'url poi
	 * effettua la lettura e il parsing tramite i metodi readdata e insertdata
	 * @author Federico Tartabini
	 * @author Nicola Montesi
	 */
	@Component
	public class Terminale
	{
		private static ArrayList<Serialize> v = new ArrayList<Serialize>();
		final static String COMMA_DELIMITER = ";";
		static {
				String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=jrc-10128-10001";
				try {URLConnection openConnection = new URL(url).openConnection();
				openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				InputStream in = openConnection.getInputStream();
				String data = "";
				String line = "";
				try {InputStreamReader inR = new InputStreamReader(in);
					BufferedReader buf = new BufferedReader(inR);
						while ((line = buf.readLine()) != null) { 
							data+= line;}
					} finally {
						in.close();}
				JSONObject obj = (JSONObject) JsonValue.parseWithException(data);
				JSONObject objI = (JSONObject) (obj.get("result"));
				JSONArray objA = (JSONArray) (objI.get("resources"));
				for(Object o: objA) {                 
				    if (o instanceof JSONObject) {
				        JSONObject o1 = (JSONObject)o; 
				        String format = (String)o1.get("format");
				        String urlD = (String)o1.get("url");
				        if(format.equals("csv"))
				        {download(urlD, "t1.csv"); }}}
				System.out.println("Il csv è stato letto");
						;
					} catch (IOException | ParseException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				try {
						inserimento("t1.csv");
					} catch (Exception e) {
					e.printStackTrace();}}
		/** Metodo che effettua il download dei dati dall'URL passatogli, effettua un primo controllo di esistenza del file 
		 * e tramite il try scarica i dati.
		 * @param url
		 * @param fileName
		 * @throws Exception
		 */
		public static void download(String url, String fileName) throws Exception
		{
		    File f = new File(fileName);
		    /**
		     * Invece di effettuare un sistema di caching, in considerazione del fatto che la base dei dati che andiamo a scricare
		     * non è particolarmente impegnativa a livello di carico per la banda internet utilizzata.
		     * Abbiamo optato per un download ad ogni avvio di applicazione avendo cura di verificare la presenza del csv precedentemente scaricatoe se fosse presente, proseguire con la sua eliminazione 
		     * per evitare accumolo di csv e scricamento della versione aggiornata al momento dell'avvio.
		     */
		    if (f.exists()) {
		    	f.delete(); }
			try (InputStream in = URI.create(url).toURL().openStream()) {
				Files.copy(in, Paths.get(fileName));
			}
			System.out.println("Download effettuato");}
		/** Metodo che effettua la lettura dei dati dall'URL passatogli, crea due stringhe data e line le quali si occupano di leggere riga
		 *  per riga il file dell'url. Line, tramite il while controlla l'andata a capo del file di testo(quindi la fine della riga). Successivamente, tramite il for
		 *  vado a cercare nel file le parole "format","url","csv" da cui poi vado a scaricare i dati 
		 * @param url url dal quale aprire il file da leggere ed analizzare
		 * @throws Exception
		 */
		
/**
 * Questa funzione effettua il parsing dei dati appena scaricati.
 * Interpreta le stringhe e crea gli oggetti assegnando i parametri agli attributi.
 * @param file
* @throws Exception
*/
public static void inserimento(String file) throws Exception
		{
			List<List<String>> records = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) { 
					String[] values = line.split(COMMA_DELIMITER); 
					records.add(Arrays.asList(values));
					v.add(new Serialize( values[0], values[1], values[2], values[3],values[4],values[5],values[6],values[7],values[8],values[9],values[10],values[11],values[12],values[13],values[14],values[15],values[16],values[17],values[18],values[19],values[20],values[21],values[22],values[23],values[24],values[25],values[26],values[27]));
				}
			br.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			}
			System.out.println("Parsing effettuato");
		}
		/**
		 * Questa funzione viene utilizzata per permettere l'applicazione di filtri per la ricerca, altrimenti restituisce l'ArrayList completo di tutti i dati.
		 * Alla sua chiamata viene passata la stringa inserita nel URL, la funzione la interpreta e agisce di consegyenza sulla base di comandi specifici "<", ">", "$and" e "$or".
		 * 
		 * @param filter
		 * @return
		 */
		public ArrayList<Serialize> Dati(String filter)
		{
			if(filter.equalsIgnoreCase("Std"))
			{return v;
			}
			ArrayList<Serialize> VFilter = new ArrayList<Serialize>();
			String delim = "[_ ]+";
			String delim2 = "[ ><=e]+";
			String [] tokens = filter.split (delim);
			try
			{
				if (tokens[0].equals("<"))
				{
					for(int i = 1; i < v.size(); i++)
					{
						System.out.println("NON");
						String [] tokens2 = v.get(i).getpotenza().split (delim2);
						System.out.println("NON");
						if(tokens2.length == 3)
					{
						try{
								if (Integer.parseInt(tokens2[1]) <= Integer.parseInt(tokens[1]) && Integer.parseInt(tokens2[2]) <= Integer.parseInt(tokens[1]))
								{
									VFilter.add(v.get(i));
								}
							} catch (Exception e) {
								throw new IllegalArgumentException(" - invalid format!");}}
						else if (tokens2.length == 2)
						{
							try
							{
								if (Integer.parseInt(tokens2[1]) <= Integer.parseInt(tokens[1]))
								{
									VFilter.add(v.get(i));
								}
							} catch (Exception e) {
								throw new IllegalArgumentException(" - invalid format!");}}
						else
						{throw new IllegalArgumentException(" - invalid format!");
						}}}
				else if (tokens[0].equals(">"))
				{
					for(int i = 1; i < v.size(); i++)
					{
						String [] tokens2 = v.get(i).getpotenza().split (delim2);
						if(tokens2.length == 3)
						{
							if (Integer.parseInt(tokens2[1]) >= Integer.parseInt(tokens[1]) && Integer.parseInt(tokens2[2]) >= Integer.parseInt(tokens[1]))
							{
								VFilter.add(v.get(i));
							}
						}
						else if (tokens2.length == 2)
						{
							if (Integer.parseInt(tokens2[1]) >= Integer.parseInt(tokens[1]))
							{
								VFilter.add(v.get(i));}}}}
				else if (tokens[0].equalsIgnoreCase("$and"))
				{
					for(int i = 1; i < v.size(); i++)
					{
						String [] tokens2 = v.get(i).getpotenza().split (delim2);
						if(tokens2.length == 3)
						{
							if(tokens[1].equals(">") && tokens[3].equals("<"))
							{
							if (Integer.parseInt(tokens2[1]) >= Integer.parseInt(tokens[2]) && Integer.parseInt(tokens2[2]) >= Integer.parseInt(tokens[2]) && (Integer.parseInt(tokens2[1]) <= Integer.parseInt(tokens[4]) && Integer.parseInt(tokens2[2]) <= Integer.parseInt(tokens[4])))
								{VFilter.add(v.get(i));}}
						}
						else if (tokens2.length == 2)
						{
							if (Integer.parseInt(tokens2[1]) >= Integer.parseInt(tokens[2]) && Integer.parseInt(tokens2[1]) <= Integer.parseInt(tokens[4]))
							{

								VFilter.add(v.get(i));}}}}
				else if (tokens[0].equals("$or"))
				{
					for(int i = 1; i < v.size(); i++)
					{String [] tokens2 = v.get(i).getpotenza().split (delim2);
						if(tokens2.length == 3)
						{
							if(tokens[1].equals(">") && tokens[3].equals("<"))
							{
								if ((Integer.parseInt(tokens2[1]) >= Integer.parseInt(tokens[2]) && Integer.parseInt(tokens2[2]) >= Integer.parseInt(tokens[2])) || ((Integer.parseInt(tokens2[1]) <= Integer.parseInt(tokens[4]) && Integer.parseInt(tokens2[2]) <= Integer.parseInt(tokens[4]))))
								{
									VFilter.add(v.get(i));}}}
						else if (tokens2.length == 2)
						{
							if (Integer.parseInt(tokens2[1]) >= Integer.parseInt(tokens[2]) || Integer.parseInt(tokens2[1]) <= Integer.parseInt(tokens[4]))
							{
								VFilter.add(v.get(i));}}}}
				else
				{
					throw new IllegalArgumentException(" - invalid format!");
				}
			} catch (Exception e)
			{
				throw new IllegalArgumentException(" - invalid format!");
			}	
				return VFilter;}
		
		/** 
		 * Il metodo seguente modella gli oggetti di tipo MetaDati creandoli sulla base degli attributi della classe ripetitore,
		 * così da permettere una stampa ottimale e rapida utilizzando il formato json, dal controller.
		 * @return m Elenco dei tipi di dati presi in analisi
		 */
		public Collection MetaDati()
		{List<it.univpm.demoSpringBootApp.Metadati.MetaDati> m = new ArrayList<>();
			Field[] fields = Serialize.class.getDeclaredFields();
			for (Field o : fields){
					it.univpm.demoSpringBootApp.Metadati.MetaDati mm = new it.univpm.demoSpringBootApp.Metadati.MetaDati();
					mm.setNome(o.getName());
					mm.setTipo(o.getType().getSimpleName());
					m.add (mm);}
			return m;}}
