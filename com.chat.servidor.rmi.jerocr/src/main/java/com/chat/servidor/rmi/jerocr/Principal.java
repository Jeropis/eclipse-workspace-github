package com.chat.servidor.rmi.jerocr;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import clases.BDChats;

public class Principal {

	public static void main(String[] args) {
		try {
			BDChats bdp = new BDChats();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.bind("bdchats", bdp);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
