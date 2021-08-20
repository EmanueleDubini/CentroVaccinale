/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database;


/**
 *
 * Classe che definisce le stringhe (statiche) per la formulazione delle query di interazione con il database.
 *
 */
public class QueryDebug {

    public QueryDebug() { }

    // DATABASE
    public static String createDB = "CREATE DATABASE IF NOT EXISTS centrivaccinalidb";
    public static String dropDB = "DROP DATABASE IF EXISTS centrivaccinalidb";

    // TABELLE
    public static String createCentriVaccinaliTable = "CREATE TABLE IF NOT EXISTS centrivaccinali (" +
                                                    "idCentroVaccinale VARCHAR(36) PRIMARY KEY," +
                                                    "nome VARCHAR(50) NOT NULL," +
                                                    "qualificatore VARCHAR(10) NOT NULL," +
                                                    "indirizzo VARCHAR(255) NOT NULL," +
                                                    "numeroCivico VARCHAR(5) NOT NULL," +
                                                    "comune VARCHAR(50) NOT NULL," +
                                                    "provincia VARCHAR(4) NOT NULL," +
                                                    "cap NUMERIC(5) NOT NULL," +
                                                    "tipologia VARCHAR(50) NOT NULL" +
                                                    ")";

    public static String dropCentriVaccinaliTable = "DROP TABLE IF EXISTS centrivaccinali";



    public static String createCittadiniRegistratiTable = "CREATE TABLE IF NOT EXISTS cittadini_registrati (" +
                                                        "codicefiscale VARCHAR(16) PRIMARY KEY," +
                                                        "cognomecittadino VARCHAR(50) NOT NULL," +
                                                        "nomecittadino VARCHAR(50) NOT NULL," +
                                                        "email VARCHAR(50) NOT NULL," +
                                                        "username VARCHAR(50) NOT NULL," +
                                                        "password VARCHAR(50) NOT NULL," +
                                                        "idvaccinazione VARCHAR(16) NOT NULL," +
                                                        "idcentrovaccinale VARCHAR(36) NOT NULL" +
                                                        ")";

    public static String dropCittadiniRegistratiTable = "DROP TABLE IF EXISTS cittadini_registrati";



    public static String createEventiAvversiTable = "CREATE TABLE IF NOT EXISTS eventi_avversi (" +
                                                    "idcentrovaccinale VARCHAR(36) NOT NULL," +
                                                    "codicefiscale VARCHAR(16) NOT NULL," +
                                                    "mal_di_testa INT NOT NULL," +
                                                    "mal_di_testa_note VARCHAR(50) NOT NULL," +
                                                    "febbre INT NOT NULL," +
                                                    "febbre_note VARCHAR(50) NOT NULL," +
                                                    "dolori_muscolari_e_articolari INT NOT NULL," +
                                                    "dolori_muscolari_e_articolari_note VARCHAR(50) NOT NULL," +
                                                    "tachicardia INT NOT NULL," +
                                                    "tachicardia_note VARCHAR(50) NOT NULL," +
                                                    "linfoadenopatia INT NOT NULL," +
                                                    "linfoadenopatia_note VARCHAR(50) NOT NULL," +
                                                    "crisi_ipertensiva INT NOT NULL," +
                                                    "crisi_ipertensiva_note VARCHAR(50) NOT NULL," +
                                                    "PRIMARY KEY(idCentroVaccinale, codiceFiscale)" +
                                                    ")";

    public static String dropEventiAvversiTable = "DROP TABLE IF EXISTS eventi_avversi";
}//END_Query
