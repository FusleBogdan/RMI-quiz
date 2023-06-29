package com.example.licenta;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer implements RemoteService {
    public RMIServer() throws RemoteException {
    }

    public static void main(String[] args) {
        try {

            RemoteService server = new RMIServer();
            RemoteService stub = (RemoteService) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RemoteService", stub);

            System.out.println("RMI Server is running...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getWelcomeMessage() throws RemoteException {
        return "Welcome to the RMI server!";
    }
}
