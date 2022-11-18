package clases;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.TreeMap;

import interfaces.ChatCodificado;

public class BDChats extends UnicastRemoteObject implements ChatCodificado {

	private TreeMap<LocalDateTime, Chat> mensajes;

	public BDChats() throws RemoteException {
		super();
		mensajes = new TreeMap<LocalDateTime, Chat>();
	}

	@Override
	public void añadirMensaje(String nombre, String mensaje) throws RemoteException {
		Chat c = new Chat(nombre, mensaje, LocalDateTime.now().withNano(0));

		mensajes.put(c.getFecha(), c);

	}

	@Override
	public String recuperarMensajes() throws RemoteException {

		String m = "";
		Iterator it = mensajes.values().iterator();
		while (it.hasNext()) {
			Chat c = (Chat) it.next();
			m += c + "\n";
		}
		return m;
	}

	@Override
	public String recuperarMensajes(LocalDateTime inicio) throws RemoteException {

		String m = "";
		Iterator it = mensajes.values().iterator();
		while (it.hasNext()) {

			Chat c = (Chat) it.next();
			LocalDateTime fechaMensaje = c.getFecha();
			int comparacion = inicio.compareTo(fechaMensaje);

			if (comparacion > 0) {
				m += c + "\n";
			}

		}
		return m;

	}

}
