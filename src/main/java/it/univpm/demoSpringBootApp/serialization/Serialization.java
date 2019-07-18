package it.univpm.demoSpringBootApp.serialization;


	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;
    import it.univpm.demoSpringBootApp.Centrali.Centrali;
    import java.io.*;
	


	public class Serialization implements Serializable {

		public List<Centrali> lista = new ArrayList<>(); 

		/**

		 * Method for a correct creation Centrali with data of csv

		 * @return List<Centrali> that is the data of csv

		 */
		public List<Centrali> serialize() {

			String csvFile = "http://cidportal.jrc.ec.europa.eu/ftp/jrc-opendata/JRCOD/RES-DATA/10001/LATEST/JRC-GEOPP-DB.csv"; //create and initialize  String "csvFile"

			String line = "";//create and initialize  String "line"

			String cvsSplitBy = ";";//create and initialize  String "cvsPlitBy"


			try {

				BufferedReader br = new BufferedReader(new FileReader(csvFile)); // open Buffer to read "csvFile"

				br.readLine();// skip the first line because there is no informations

				while ((line = br.readLine()) != null) { // While end of file, read line and put it in string 

					                                     //"line"

															
					List<String> powerplant = Arrays.asList(line.split(cvsSplitBy, 13)); // split of line in an array

																					// of string


					for (int i = 0; i < powerplant.size(); i++) {

						if (powerplant.get(i).equals("") == true) { // if find in the cells of each position an empty cell

																// then puts -1

							powerplant.set(i, "0");

						}
					}

					/*

					 * System.out.println("

					  // Stampa di tutti i data in the array of string presi dal csv file*/

																							
					lista.add(new Centrali(powerplant)); //create an obj "Centrali" to use it in the list

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

		 * Method that create a file appartamento.ser with serialization of the data on List<Appartamento>

		 * @param lista that arrive with serialize

		 */

		public void outputfile(final List<Centrali> lista) {

			try {

				FileOutputStream fileOut = new FileOutputStream("centrali.ser"); // Creation of a file

																						// appartamento.ser to put

																						// the serialization of the file

				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(lista); //Writing of the list object where all the data of the csv are saved

				out.close();

				fileOut.close();

				System.out.printf("La serializzazione dei dati è stata inserita in centrali.ser\n");

			} catch (IOException i) {

				i.printStackTrace();

			}

			System.out.println("\nI dati serializzati sono: " + lista.size()); // Print the list size to see if

																				// everything has been saved

		}
	}

