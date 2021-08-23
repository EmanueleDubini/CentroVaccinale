package org.example.common;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Display names bound to RMI registry on provided host and port.
 */
public class RegistryViewer
{
    /**
     * Main executable function for printing out RMI registry names on provided
     * host and port.
     *
     * @param args Command-line arguments; Two expected: first is a String
     *    representing a host name ('localhost' works) and the second is an
     *    integer representing the port.
     */
    public static void main(String[] args)
    {
        final String host = "localhost"; //arguments[0];
        int port = 1200;

        try
        {
            final Registry registry = LocateRegistry.getRegistry(host, port);
            final String[] boundNames = registry.list();

            for (final String name : boundNames)
            {
                System.out.println("RMI registry su " + host + " port " + port + ": " + name);
            }
        }
        catch (ConnectException connectEx)
        {
            System.err.println("ERRORE: Nessun registro RMI disponibile sulla porta " + port);
        }
        catch (RemoteException remoteEx)
        {
            System.err.println("ERRORE: RemoteException");
        }
    }
}
