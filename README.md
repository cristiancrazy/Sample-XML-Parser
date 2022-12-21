## Descrizione progetto
Il progetto, realizzato per fini didattici, permette di eseguire alcune operazioni su file `.xml` e `.xsd` specifici:
1. Verifiche sui file sorgente e **validazione XML**
2. **Parsing** del file sorgente tramite l'utilizzo di DOM (*Document Object Model*)
3. Esecuzione di **query xPath** sul file sorgente

Il progetto non dispone di un'interfaccia grafica per la visualizzazione dei dati, ma
offre semplici interazioni tramite l'utilizzo di un terminale.

## Prerequisiti
Per poter eseguire il programma è necessario possedere:
- **JRE 17** o superiore

Per poter compilare il programma è necessario possedere:
- **JDK 17** o superiore

> Nota: il progetto utilizza il *build tool* **Gradle**

## Scopo del progetto
Lo scopo finale del progetto, e il motivo per cui è stato pubblicato, è quella di fornire codice d'esempio per la realizzazione di programmi
che necessitano di leggere e validare file XML.

La realizzazione del file XML è basata sulle informazioni fornite dall'**ISTAT**, sugli *aspetti della vita quotidiana*. 
La tabella presa in considerazione considera *i mezzi di trasporto utilizzati dai lavoratori, con età superiore a 15 anni, che escono di casa
per recarsi al lavoro*. 

Fonte: [dati.istat.it](http://dati.istat.it)

Dati sorgente:  [Trasporti.xls](https://github.com/cristiancrazy/Sample-XML-Parser/files/10280770/Trasporti.xls)

## Struttura XML
Il file XML è strutturato come di seguito (estratto dal file `source.xml`):
```
<?xml version="1.0" encoding="UTF-8" ?>
<data>
    <year value="2021">
       <age value="15-19">
            <walk>25.1</walk>
            <means>
                <total>74.9</total>
                <train>3.2</train>
                <tram>1.7</tram>
                <metro>3.2</metro>
                <pullman-n>3.3</pullman-n>
                <pullman-c>0</pullman-c>
                <car-d>35.8</car-d>
                <car-p>23.5</car-p>
                <motorcycle>7.8</motorcycle>
                <bike>1.8</bike>
            </means>
            <times>
                <time val="15min">41.8</time>
                <time val="31min">15.7</time>
            </times>
        </age>
    </year>
</data>
```

## Output programma

Di seguito sono rappresentate alcune delle interazioni possibili:
- > Interfaccia base del programma, con visualizzazione organizzata dei dati letti: <br> <br>
  > ![Output1](https://user-images.githubusercontent.com/49765306/208993623-63670709-fd18-4979-a5cb-c360140c9d32.PNG)

- > Interfaccia base del programma, visualizzazione del risultato di una query xPath <br> <br>
  >![Output2](https://user-images.githubusercontent.com/49765306/208993627-c6eb93fd-a1af-4e85-a5f5-5bc0cac420b5.PNG)
