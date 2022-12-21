/* ========================================================== *
 * Author:		Cristian Capraro
 * Date:		12-2022
 * License:		MIT License
 * P. Desc:		TP Progetto valutazione del 21-12-2022
 * 				Realizzazione validator + parser XML per
 * 				dati ISTAT
 * C. Desc:		Questa classe è stata progettata per contenere
 * 				i dati di una singola statistica, per anno e
 * 				per fascia di età.
 * ========================================================== */
package it.cristiancrazy;

public class Statistica {
	// Variabili d'istanza
	private final String anno; // Anno in considerazione
	private final String fascia; // Fascia in considerazione

	// Parametri sui mezzi di trasporto
	private final String piedi, mezzi, treno, tram, metro, pullmanN, pullmanC, autoD, autoP, moto, bici;

	// Parametri sui tempi di arrivo
	private final String tempoBreve, tempoLungo;

	// Costruttore
	public Statistica(String anno, String fascia, String piedi, String mezzi, String treno, String tram, String metro, String pullmanN, String pullmanC, String autoD, String autoP, String moto, String bici, String tempoBreve, String tempoLungo){
		this.anno = anno;
		this.fascia = fascia;
		this.piedi = piedi;
		this.mezzi = mezzi;
		this.treno = treno;
		this.tram = tram;
		this.metro = metro;
		this.pullmanN = pullmanN;
		this.pullmanC = pullmanC;
		this.autoD = autoD;
		this.autoP = autoP;
		this.moto = moto;
		this.bici = bici;
		this.tempoBreve = tempoBreve;
		this.tempoLungo = tempoLungo;
	}

	// Metodi
	@Override
	public String toString(){
		return "Anno: " + anno + System.lineSeparator() + "Fascia di età: " + fascia +
				System.lineSeparator() + System.lineSeparator() + // Separazione
				"A Piedi: " + piedi + "%" + System.lineSeparator() +
				"Mezzi T: " + mezzi + "%" + System.lineSeparator() +
				"Treno: " + treno + System.lineSeparator() +
				"Tram: " + tram + System.lineSeparator() +
				"Metro: " + metro + System.lineSeparator() +
				"Pullman(N): " + pullmanN + System.lineSeparator() +
				"Pullman(C): " + pullmanC + System.lineSeparator() +
				"Auto(D): " + autoD + System.lineSeparator() +
				"Auto(P): " + autoP + System.lineSeparator() +
				"Moto: " + moto + System.lineSeparator() +
				"Bici: " + bici + System.lineSeparator() +
				System.lineSeparator() + System.lineSeparator() + // Separazione
				"Tempo - max 15 min: " + tempoBreve + System.lineSeparator() +
				"Tempo - oltre 30 min: " + tempoLungo + System.lineSeparator() +
				"---";
	}

}
