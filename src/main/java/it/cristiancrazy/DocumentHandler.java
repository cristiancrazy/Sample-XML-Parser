/* ========================================================== *
 * Author:		Cristian Capraro
 * Date:		12-2022
 * License:		MIT License
 * P. Desc:		TP Progetto valutazione del 21-12-2022
 * 				Realizzazione validator + parser XML per
 * 				dati ISTAT
 * C. Desc:		Lo scopo della classe è quella di mettere a
 * 				disposizione metodi per la verifica e il
 * 				parsing di un Document XML e XSD specifico.
 * ========================================================== */
package it.cristiancrazy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class DocumentHandler {
	private final Document document;
	private final URL sourceXML;
	private final URL sourceXSD;

	/**
	 * Costruttore della classe DocumentHandler
	 * @param sourceXML URL del file sorgente XML sul quale operare
	 * @param sourceXSD URL del file schema XSD utilizzato per validare
	 * @throws SAXException errore di parsing sul file XML
	 * @throws IOException errore sul file XML
	 * @throws ParserConfigurationException errore del DocumentBuilder
	 **/
	public DocumentHandler(URL sourceXML, URL sourceXSD) throws SAXException, IOException, ParserConfigurationException {
		// Assegnamento attributi classe
		this.sourceXML = sourceXML;
		this.sourceXSD = sourceXSD;

		// Creazione dell'oggetto Document
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		document = builder.parse(sourceXML.openStream());
	}

	/**
	 * Validazione del file XML con l'ausilio di un file schema XSD
	 * @return true se la validazione va a buon fine, false altrimenti.
	 **/
	public boolean validateXML(){
		try{
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(sourceXSD);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(sourceXML.openStream()));
			return true; // Validazione effettuata -> corretta
		}catch (IOException | SAXException e){
			return false; // Validazione non effettuata -> non corretta
		}
	}

	/**
	 * Effettua il parsing "specifico" del file XML
	 * @return una HashMap strutturata: K1: Anno; K2: Fascia di etò; V: valori statistici;
	 */
	public HashMap<String, HashMap<String, Statistica>> parseXML(){
		HashMap<String, HashMap<String, Statistica>> dati = new HashMap<>(); // K1: anno -> K2: fascia -> V: statistiche

		NodeList tagAnno = document.getDocumentElement().getElementsByTagName("year");
		for(int x = 0; x < tagAnno.getLength(); ++x){
			// Creazione della seconda HashMap
			HashMap<String, Statistica> fascia = new HashMap<>();

			// ottiene l'anno dall'attributo "value" del tag <year>
			String annoAttuale = ((Element)tagAnno.item(x)).getAttribute("value");
			System.out.println("Anno: " + annoAttuale);

			NodeList listaFasceEta = (tagAnno.item(x)).getChildNodes();
			for(int y = 0; y < listaFasceEta.getLength(); ++y){
				// Filtra per tipo di elemento -> tag
				if(listaFasceEta.item(y).getNodeType() == Node.ELEMENT_NODE){
					// Ottiene la fascia d'età dall'attributo "value" del tag <age>
					String fasciaAttuale = ((Element)listaFasceEta.item(y)).getAttribute("value");
					System.out.println("Fascia: " + fasciaAttuale);

					// Ottiene il valore contenuto nel tag <walk>
					String piedi = ((Element)listaFasceEta.item(y)).getElementsByTagName("walk").item(0).getTextContent();
					System.out.println("A piedi: " + piedi);

					// Ottiene il valore contenuto nel tag <total>
					String mezzi = ((Element)listaFasceEta.item(y)).getElementsByTagName("total").item(0).getTextContent();
					System.out.println("Mezzi: " + mezzi);

					// Ottiene il valore contenuto nel tag <train>
					String treno = ((Element)listaFasceEta.item(y)).getElementsByTagName("train").item(0).getTextContent();
					System.out.println("Mezzi: " + treno);

					// Ottiene il valore contenuto nel tag <tram>
					String tram = ((Element)listaFasceEta.item(y)).getElementsByTagName("tram").item(0).getTextContent();
					System.out.println("Tram: " + tram);

					// Ottiene il valore contenuto nel tag <metro>
					String metro = ((Element)listaFasceEta.item(y)).getElementsByTagName("metro").item(0).getTextContent();
					System.out.println("Metro: " + metro);

					// Ottiene il valore contenuto nel tag <pullman-n>
					String pullmanN = ((Element)listaFasceEta.item(y)).getElementsByTagName("pullman-n").item(0).getTextContent();
					System.out.println("Pullman(N): " + pullmanN);

					// Ottiene il valore contenuto nel tag <pullman-c>
					String pullmanC = ((Element)listaFasceEta.item(y)).getElementsByTagName("pullman-c").item(0).getTextContent();
					System.out.println("Pullman(C): " + pullmanC);

					// Ottiene il valore contenuto nel tag <car-d>
					String autoD = ((Element)listaFasceEta.item(y)).getElementsByTagName("car-d").item(0).getTextContent();
					System.out.println("Auto(D): " + autoD);

					// Ottiene il valore contenuto nel tag <car-p>
					String autoP = ((Element)listaFasceEta.item(y)).getElementsByTagName("car-p").item(0).getTextContent();
					System.out.println("Auto(P): " + autoP);

					// Ottiene il valore contenuto nel tag <motorcycle>
					String moto = ((Element)listaFasceEta.item(y)).getElementsByTagName("motorcycle").item(0).getTextContent();
					System.out.println("Moto: " + moto);

					// Ottiene il valore contenuto nel tag <bike>
					String bici = ((Element)listaFasceEta.item(y)).getElementsByTagName("bike").item(0).getTextContent();
					System.out.println("Bici: " + bici);

					// Ottiene una lista dei tag <time>
					NodeList listaTempi = ((Element)listaFasceEta.item(y)).getElementsByTagName("time");
					// Variabili utilizzate per il salvataggio dei tempi <time val="15min"> e <time val="31min">
					String tempoBreve = null, tempoLungo = null;
					// Ciclo sugli elementi <time> presenti
					for(int z = 0; z < listaTempi.getLength(); z++){
						// Verifica degli attributi del tag "15min" e "31min"
						if(((Element)listaTempi.item(z)).getAttribute("val").equals("15min")){
							tempoBreve = listaTempi.item(z).getTextContent();
							System.out.println("Tempo impiegato (<= 15 min): " + tempoBreve);

						}else if(((Element)listaTempi.item(z)).getAttribute("val").equals("31min")){
							tempoLungo = listaTempi.item(z).getTextContent();
							System.out.println("Tempo impiegato (> 30 min): " + tempoLungo);

						}
					}

					// Creazione dell'oggetto statistica con i dati letti dal file XML:
					Statistica stat = new Statistica(annoAttuale, fasciaAttuale, piedi, mezzi, treno, tram, metro, pullmanN, pullmanC, autoD, autoP, moto, bici, tempoBreve, tempoLungo);
					fascia.put(fasciaAttuale, stat);
				}
				// salvataggio dati
				dati.put(annoAttuale, fascia);
			}
		}
		return dati;
	}

	/**
	 * Restituisce la risposta di una query xPath specificata
	 * @param expression query xPath da valutare
	 * @return restituisce la risposta alla query richiesta
	 */
	public String evaluateXPATH(String expression){
		String output;
		XPath xPath = XPathFactory.newDefaultInstance().newXPath();
		try{
			// Compilazione della query xPath
			output = (String) xPath.compile(expression).evaluate(document, XPathConstants.STRING);
		}catch (XPathExpressionException e){
			output = "Query non valida";
		}
		return output;
	}
}
