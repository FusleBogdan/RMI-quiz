package com.example.licenta;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote {
    String getWelcomeMessage() throws RemoteException;

    // Declare other remote methods to be exposed through RMI
    // ...
}
