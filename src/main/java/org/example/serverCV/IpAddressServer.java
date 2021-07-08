/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Classe per trovare l'indirizzo ip locale del server quando viene avviato da inserire nel client per permettere di
 * collegarsi sia da locale che da remoto
 */
public class IpAddressServer {

    /**
     * metodo che restituisce l'indirizzo ip corrente della macchina su cui viene eseguita il programma
     *
     * @return stringa che contiene l'indirizzo ip
     */
    public static String getServerAddress()  {

        String ip = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filtra gli indirizzi locali 127.0.0.1 e interfaccie inattive
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    // *EDIT*
                    if (addr instanceof Inet6Address) continue;

                    ip = addr.getHostAddress();
                    //System.out.println(iface.getDisplayName() + " " + ip); // stampa a prompt l'indirizzo ip
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        assert ip != null;
        return ip.strip();

    }//END_Method
}//END_Class
