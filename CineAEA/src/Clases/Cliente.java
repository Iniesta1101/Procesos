package Clases;

public class Cliente implements Runnable{
	private String nombre, apellidos, correo, cuentaBancaria;
	private int fondos, filaDeseada, columnaDeseada;
	GestorReservas gr;
	
	public Cliente(String nombre, String apellidos, String correo, String cuentaBancaria, int fondos, int filaDeseada,
			int columnaDeseada, GestorReservas gr) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.cuentaBancaria = cuentaBancaria;
		this.fondos = fondos;
		this.filaDeseada = filaDeseada;
		this.columnaDeseada = columnaDeseada;
		this.gr = gr;
	}

	@Override
	public void run() {
		synchronized (gr.getCine()) {
			gr.intentarReservar(this, filaDeseada, columnaDeseada);
			gr.getCine().notifyAll();
		}
		
		
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public int getFondos() {
		return fondos;
	}

	public void setFondos(int fondos) {
		this.fondos = fondos;
	}

	public int getFilaDeseada() {
		return filaDeseada;
	}

	public void setFilaDeseada(int filaDeseada) {
		this.filaDeseada = filaDeseada;
	}

	public int getColumnaDeseada() {
		return columnaDeseada;
	}

	public void setColumnaDeseada(int columnaDeseada) {
		this.columnaDeseada = columnaDeseada;
	}

	public GestorReservas getGr() {
		return gr;
	}

	public void setGr(GestorReservas gr) {
		this.gr = gr;
	}
	
	

	
	
	
	
}
