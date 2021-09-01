/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database;

import org.example.common.CentroVaccinale;
import org.example.common.TipologiaCV;
import org.example.centrivaccinali.Cittadino;
import org.example.common.CFGenerator.CalcolaCodiceFiscale;
import org.example.common.Indirizzo;
import org.example.database.GenerateDataLib.*;
import org.example.database.GenerateDataLib.UserIDGenerator;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Le sue istanze mappano e popolano le tuple del database
 */
public class DataModel {

    private final ArrayList<Cittadino> cittadini;
    private final ArrayList<CentroVaccinale> centriVaccinali;
    private static final Random randomGenerator = new Random();

    /**
     * Costruisce una nuova istanza della classe <code>DataModel</code> .
     */
    public DataModel(){
        cittadini = new ArrayList<>();
        centriVaccinali = new ArrayList<>();
    }

    /**
     * Eseguendolo genera una serie di dati anagrafici di Cittadini e informazioni riguardanti i Centri vaccinali,
     * per favorire l'inserimento di dati nel database
     *
     * Stampa sulla console le informazioni inserite nel database
     */
    public void generateAll(){
        DataModel p = new DataModel();
        p.populateModel(3, 3);


        // Stampa di DEBUG per i Cittadini generati
        System.err.println("\033[4;32m" + "DEBUG: Stampa dei cittadini generati:");
        for (int i = 0; i < p.cittadini.size(); i++){
            System.out.println(p.cittadini.get(i));
        }

        // Stampa di DEBUG per i CentriVaccinali generati
        System.err.println("\033[4;32m" + "DEBUG: Stampa dei Centri Vaccinali generati:");
        for (int i = 0; i < p.centriVaccinali.size(); i++){
            System.out.println(p.centriVaccinali.get(i));
        }
    }

    /**
     * Metodo che genera ed inserisce nell'array i cittadini e i centri vaccinali generati casualmente per popolare le
     * tabelle del database
     *
     * @param numCittadini numero dei cittadini da generare casualmente
     * @param numCentriVaccinali numero dei centri vaccinali da generare casualmente
     */
    private void populateModel(int numCittadini, int numCentriVaccinali){
        for(int i = 0; i < numCittadini; i++){
            cittadini.add(generateCittadino());
        }

        for(int i = 0; i < numCentriVaccinali; i++){
            centriVaccinali.add(generateCentroVaccinale());
        }

    }

    /**
     * Questo metodo genera casualmente i dati dei cittadini da inserire nel databse
     *
     * @return un nuovo oggetto di tipo Cittadino
     */
    public static Cittadino generateCittadino() {
        int eta = randomGenerator.nextInt(30) + 20;

        LastNameGenerator lastNameGenerator = new LastNameGenerator();
        String cognome = (String)lastNameGenerator.generate();

        String nome, sesso;
        FemaleNameGenerator femaleNameGenerator = new FemaleNameGenerator();
        MaleNameGenerator maleNameGenerator = new MaleNameGenerator();
        if (randomGenerator.nextInt(100) > 65) {
            nome = (String)maleNameGenerator.generate();
            sesso = "m";
        } else {
            nome = (String)femaleNameGenerator.generate();
            sesso = "f";
        }

        ComuneGenerator comuneGen = new ComuneGenerator();
        DateGenerator dateGenerator = new DateGenerator();  //Generatore di date
        CalcolaCodiceFiscale calcolaCodiceFiscale = new CalcolaCodiceFiscale();

        String codiceFiscale = CalcolaCodiceFiscale.calcolaCf(cognome, nome, sesso, dateGenerator.generate(), calcolaCodiceFiscale.toCodiceErariale((String)comuneGen.generate()));

        EmailGenerator emailGenerator = new EmailGenerator(nome,cognome); // Crea un email univoco collegato al nome e cognome del cittadino generato
        String email = emailGenerator.generate();

        UserIDGenerator userIDGenerator = new UserIDGenerator(nome,cognome); // Crea un UserID univoco collegato al nome e cognome del cittadino generato
        String userId = userIDGenerator.generate();

        UIDGenerator uidGenerator = new UIDGenerator();

        String password = uidGenerator.randomUUID(10,11,'-',false).toLowerCase(); // Crea una password alfanumerica casuale univoca

        String idVaccinazione = uidGenerator.randomUUID(16, 17, '-', true); // codice numerico univoco a 16 cifre, spaziatura= 17 perchè non vogliamo il carattere spaziatore. Parametro Numeric: se true genera codice numerico, altrimenti alfanumerico.

        return new Cittadino(codiceFiscale, cognome, nome, eta, email, userId, password, idVaccinazione);
    }

    /**
     * Questo metodo genera casualmente i dati dei centri vaccinali da inserire nel databse
     *
     * @return un nuovo oggetto di tipo centro vaccinale
     */
    public static CentroVaccinale generateCentroVaccinale(){
        String idCentroVaccinale = UUID.randomUUID().toString(); //id centro vaccinale univoco

        //nome centro vaccinale
        VaccinationCentreNameGenerator nomeCentroVaccinaleGenerator = new VaccinationCentreNameGenerator();
        String nomeCentroVaccinale =(String) nomeCentroVaccinaleGenerator.generate();

        IndirizzoGenerator indirizzoGenerator = new IndirizzoGenerator();
        Indirizzo indirizzoCentroVaccinale = (Indirizzo)indirizzoGenerator.generate();
        //Indirizzo index = new Indirizzo(Qualificatore.Via, indirizzoCentroVaccinale, "2", "Cermenate", 22072, "co");

        //TIPOLOGIA
        VaccinationCentreTypeGenerator tipologiaGenerator = new VaccinationCentreTypeGenerator();
        String t = (String) tipologiaGenerator.generate();
        TipologiaCV tipologia = null;
        if(t.equals(TipologiaCV.Aziendale.name())) tipologia = TipologiaCV.Aziendale;
        if(t.equals(TipologiaCV.Ospedaliero.name())) tipologia = TipologiaCV.Ospedaliero;
        if(t.equals(TipologiaCV.Hub.name())) tipologia = TipologiaCV.Hub;

        return new CentroVaccinale(idCentroVaccinale, nomeCentroVaccinale, indirizzoCentroVaccinale, tipologia);
    }

}//END_DataModel_Class
