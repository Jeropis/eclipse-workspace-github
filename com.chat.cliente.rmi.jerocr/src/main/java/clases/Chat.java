package clases;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Chat implements Serializable, Comparable<Chat>{

	private String nombre;
	private String mensaje;
	private LocalDateTime fecha;
	
	public Chat(String nombre, String mensaje, LocalDateTime fecha) {
		super();
		this.nombre = nombre;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Mensaje en el chat: (Nombre: " + nombre + ": " + mensaje + " [" + fecha + "]";
	}

	@Override
	public int compareTo(Chat o) {
		return this.fecha.compareTo(o.fecha);
	}
	
	
	
}
