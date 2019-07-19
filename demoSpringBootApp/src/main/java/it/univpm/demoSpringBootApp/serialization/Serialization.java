package it.univpm.demoSpringBootApp.serialization;


	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;
    import it.univpm.demoSpringBootApp.Centrali.Centrali;
    import java.io.*;
	


	public class Serialization implements Serializable {

		public List<Centrali> lista = new ArrayList<>(); 

		/**author Federico Tartabini,Nicola Montesi

		 * Procediamo con la serializzazione delle Centrali

		 * @return List<Centrali> 

		 */
		public List<Centrali> serialize() {

			String csvFile = "http://cidportal.jrc.ec.europa.eu/ftp/jrc-opendata/JRCOD/RES-DATA/10001/LATEST/JRC-GEOPP-DB.csv";

			String line = "";

			String cvsSplitBy = ";";


			try {

				BufferedReader br = new BufferedReader(new FileReader(csvFile)); 

				br.readLine();

				while ((line = br.readLine()) != null) {  					                                     
															
					List<String> powerplant = Arrays.asList(line.split(cvsSplitBy, 13));
																
					for (int i = 0; i < powerplant.size(); i++) {

						if (powerplant.get(i).equals("") == true) { 

							powerplant.set(i, "0");

						}
					}
																							
					lista.add(new Centrali(powerplant)); 

				}

				br.close();

			} catch (FileNotFoundException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} catch (NumberFormatException e) {

				e.printStackTrace();

			}

			return lista;

		}
		/**

		 * Con il prossimo metodo generiamo centrali.ser con i dati serailizzati

		 */

		public void outputfile(final List<Centrali> lista) {

			try {

				FileOutputStream fileOut = new FileOutputStream("centrali.ser"); // file creato
																				
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(lista); 

				out.close();

				fileOut.close();

				System.out.printf("La serializzazione dei dati è stata inserita in centrali.ser\n");

			} catch (IOException i) {

				i.printStackTrace();
			}

			System.out.println("\nI dati serializzati sono: " + lista.size()); 																		
		}
	}

