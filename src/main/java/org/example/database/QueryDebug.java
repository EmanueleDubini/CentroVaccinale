/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database;

import java.sql.SQLException;

/**
 *
 * Classe che definisce le stringhe (statiche) per la formulazione delle query di interazione con il database.
 *
 */
public class QueryDebug {

    public QueryDebug() throws SQLException {
    }

    // DATABASE
    public static String createDB = "CREATE DATABASE IF NOT EXISTS centrivaccinalidb";
    public static String dropDB = "DROP DATABASE IF EXISTS centrivaccinalidb";

    // TABELLE
    public static String createCentriVaccinaliTable = "CREATE TABLE IF NOT EXISTS centrivaccinali (" + //todo
                                                    "idCentroVaccinale VARCHAR(36) PRIMARY KEY," +
                                                    "nome VARCHAR(50) NOT NULL," +
                                                    "indirizzo VARCHAR(255) NOT NULL," +
                                                    "comune VARCHAR(50) NOT NULL," +
                                                    "provincia VARCHAR(4) NOT NULL," +
                                                    "cap NUMERIC(5) NOT NULL," +
                                                    "tipologia VARCHAR(50) NOT NULL" +
                                                    ")";

    public static String dropCentriVaccinaliTable = "DROP TABLE IF EXISTS centrivaccinali";

    public static String createCittadiniRegistratiTable = "CREATE TABLE IF NOT EXISTS cittadini_registrati (" +
                                                        "codiceFiscale VARCHAR(16)," +      //todo Rimettere PRIMARY KEY
                                                        "cognomeCittadino VARCHAR(50) NOT NULL," +
                                                        "nomeCittadino VARCHAR(50) NOT NULL," +
                                                        "email VARCHAR(50) NOT NULL," +
                                                        "userid VARCHAR(50) NOT NULL," +
                                                        "password VARCHAR(50) NOT NULL," +
                                                        "idVaccinazione VARCHAR(16) NOT NULL" +
                                                        ")";

    public static String dropCittadiniRegistratiTable = "DROP TABLE IF EXISTS cittadini_registrati";

    public static String createEventiAvversiTable = "CREATE TABLE IF NOT EXISTS eventi_avversi (" +
                                                    "idCentroVaccinale NUMERIC(10) REFERENCES centrivaccinali(idCentroVaccinale) NOT NULL," +
                                                    "codiceFiscale VARCHAR(16) REFERENCES cittadini_registrati(codiceFiscale) NOT NULL," +
                                                    "malDiTesta VARCHAR(50) NOT NULL," +
                                                    "malDiTesta_note VARCHAR(50) NOT NULL," +
                                                    "PRIMARY KEY(idCentroVaccinale, codiceFiscale)" +
                                                    ")";

    public static String dropEventiAvversiTable = "DROP TABLE IF EXISTS eventi_avversi";

    public static String createVaccinatiNomeCentroVaccinale = "";


}//END_Query
