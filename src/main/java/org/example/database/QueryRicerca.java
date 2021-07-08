/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database;

//todo la classe QueryRicerca potrebbe essere eliminata in quanto mai usata
/**
 *
 * Classe che contiene le query per la ricerca
 *
 */
public class QueryRicerca {


    public static String cercaCentroVaccinalePerNome =  "SELECT * " +
                                                        "FROM centrivaccinali " +
                                                        "WHERE nome = 'Divine Multispecialty Hosp. & Cancer Cent.'" +      //todo Metodo da implementare che deve restituire il nome del centro vaccinale che l'utente ha cercato
                                                        "ORDER BY idCentroVaccinale";

    public static String cercaCentroVaccinalePerComuneTipologia =   "SELECT * " +
                                                                    "FROM centrivaccinali " +
                                                                    "WHERE comune = 'Vertemate con minoprio' "  +          //todo Metodo da implementare che deve restituire il comune del centro vaccinale che l'utente ha cercato
                                                                    "AND tipologia = 'Aziendale' " +                       //todo Metodo da implementare che deve restituire la tipologia che ha selezionato l'utente in fase di ricerca
                                                                    "ORDER BY idCentroVaccinale";

    //public static List<Indirizzo> ricercaIndirizzo


    /* //todo eliminare commento
    public static List<String> ricercaCV(String tipoRicerca) throws SQLException{
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(tipoRicerca);
        List<String> cvCercato = new LinkedList<String>();

        while (rs.next()) {
            cvCercato.add(new String(rs.getString("idCentroVaccinale") +
                                            rs.getString("nome") +
                                            rs.getString("qualificatore") +
                                            rs.getString("nomeVia") +
                                            rs.getString("numeroCivico") +
                                            rs.getString("comune") +
                                            //rs.getString("provincia") +
                                            rs.getString("cap ") +
                                            rs.getString("tipologia")));
        }
        return cvCercato;
    }


     */
}
