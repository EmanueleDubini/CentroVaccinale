/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali.gui;

import org.example.common.CentroVaccinale;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 *
 * Un implementazione di questa interfaccia sarà utilizzato per la finestra di ricerca
 * dei centri vaccinali <code>03CT_CercaCV.fxml</code>.
 *
 */
public interface CercaCVListener {
        public void onClickListener(CentroVaccinale centroVaccinale) throws SQLException, RemoteException;
}
