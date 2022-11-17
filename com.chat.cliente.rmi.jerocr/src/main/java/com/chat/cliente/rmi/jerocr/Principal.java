package com.chat.cliente.rmi.jerocr;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.Scanner;

import clases.Chat;
import interfaces.ChatCodificado;

public class Principal {

	public static void main(String[] args) {

		try {
			Registry registro = LocateRegistry.getRegistry("192.168.18.68", 1099);
			ChatCodificado cd = (ChatCodificado) registro.lookup("bdchats");
			Scanner sc = new Scanner(System.in);

			System.out.println("Dime tu nombre: ");
			String nombre = sc.nextLine();
			System.out.println("Mensaje que quieres enviar: ");
			String mensaje = sc.nextLine();

			Chat c = new Chat(nombre, mensaje, LocalDateTime.now());
			System.out.println(c);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
