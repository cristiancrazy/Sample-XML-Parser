/* ========================================================== *
 * Author:		Cristian Capraro
 * Date:		12-2022
 * License:		MIT License
 * P. Desc:		TP Progetto valutazione del 21-12-2022
 * 				Realizzazione validator + parser XML per
 * 				dati ISTAT
 * C. Desc:		Questo è l' entry-point del progetto
 * ========================================================== */
package it.cristiancrazy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Specifica dei file XML e XSD sorgente
		final URL XMLSource = Main.class.getResource("source.xml");
		final URL XSDSource = Main.class.getResource("source.xsd");

		// Creazione dell'oggetto DocumentHandler per la validazione e il parsing
		DocumentHandler handler = null;
		try{
			handler = new DocumentHandler(Objects.requireNonNull(XMLSource), Objects.requireNonNull(XSDSource));
		}catch (SAXException | IOException | ParserConfigurationException | NullPointerException e){
			System.err.println("Errore file XML e XSD");
			System.exit(255); // Termina esecuzione
		}

		// Effettua la validazione
		boolean XMLValidated = handler.validateXML();
		System.out.println("Il file " + (XMLValidated? "è stato validato correttamente" : "non risulta corretto"));
		if(!XMLValidated){
			System.exit(255); // Termina esecuzione
		}

		// Effettua il parsing + stampa totale (TASK 0)
		HashMap<String, HashMap<String, Statistica>> dati = handler.parseXML();

		// Creazione di un'interfaccia testuale
		Scanner in = new Scanner(System.in);
		String command = "";
		while(!command.equals("exit")){
			System.out.println("-----------[SELEZIONE]-----------");
			System.out.println("1 = Selezione dati"); // Task PLUS 1
			System.out.println("2 = Effettua query xPath"); // Task PLUS 2
			System.out.println("exit = Uscita dal programma"); // Uscita programma
			// Prompt
			System.out.print(": ");
			command = in.nextLine();

			// Azioni
			switch (command){
				case "exit" -> System.out.println("Bye");

				// Seleziona dati
				case "1" -> {
					System.out.println("Selezionare anno: " + (String.join(", ", dati.keySet())));
					System.out.print(": ");
					command = in.nextLine();
					HashMap<String, Statistica> dato = dati.get(command);
					if(dato != null){
						System.out.println("Selezionare fascia: " + (String.join(", ", dato.keySet())));
						System.out.print(": ");
						command = in.nextLine();
						Statistica element = dato.get(command);
						if(element != null){
							// Stampa dei dati richiesti
							System.out.println(element);
						}else{
							System.out.println("Fascia inserita non valida");
						}
					}else{
						System.out.println("Anno inserito non valido");
					}

					System.out.println("PREMERE INVIO PER CONTINUARE");
					in.nextLine();
				}

				// Effettua query xPath
				case "2" -> {
					System.out.print("Espressione: ");
					command = in.nextLine();
					System.out.println(handler.evaluateXPATH(command));

					System.out.println("PREMERE INVIO PER CONTINUARE");
					in.nextLine();
				}

				// gestione comandi non definiti (errati)
				default ->{
					System.out.println("Il comando inserito non risulta valido");

					System.out.println("PREMERE INVIO PER CONTINUARE");
					in.nextLine();
				}
			}

		}
		System.exit(0); // Termina
	}
}