package org.example.serverCV;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 * Classe <code>ServerRegistry</code> che costituisce il servizio registry per la connessione RMI
 */
public class ServerRegistry {

    /**
     * Porta su cui si inizializza il registry, dove poi il server caricherà la sua istanza remota
     */
    public static final int PORT = 1200; //todo sistemare e mettera la porta come campo statico nell'interfaccia del server

    /**
     * Registry con cui il server si rende disponibile remotamente
     */
    public static Registry registry;

    protected ServerRegistry() throws RemoteException {
        super();
    }

    /**
     * Questo metodo permette di inizializzare il Registry
     *
     * @throws RemoteException RemoteException
     */
    public static void startRegistry() throws RemoteException {
        //settiamo l'ip hostname con l'ip della macchina che esegue questo codice, ServerCV main()
        System.setProperty("java.rmi.server.hostname", IpAddressServer.getServerAddress());

        // Crea una instanza dell'oggetto server
        ServerCV serverCV = new ServerCV();

        //registriamo l'oggetto server sul registry
        registry = LocateRegistry.createRegistry(PORT);
        //bind
        registry.rebind("ServerCV", serverCV); //se è gia bound fa un bind con il nuovo valore
    }
}
