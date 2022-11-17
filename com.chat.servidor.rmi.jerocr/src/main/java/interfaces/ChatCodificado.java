package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface ChatCodificado extends Remote {
	
	public void añadirMensaje(String nombre, String mensaje) throws RemoteException;

	public String recuperarMensajes() throws RemoteException;

	public String recuperarMensajes(LocalDateTime inicio) throws RemoteException;
}
