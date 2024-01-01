package Clases;

public class Main {

	public static void main(String[] args) {
		//Inicializamos las clases
		 Cine cine = new Cine(generarCuentas("cine"));
		 ProcesadorPagos pg = new ProcesadorPagos(cine);
		 GestorReservas gr = new GestorReservas(cine, pg);
		 Visualizador v = new Visualizador(cine);
		 Thread th = new Thread(v);
		 th.start();
		//Este bucle hace que la simulación no finalice
		 while(true) {
			 String nombre = nombreAleatorio();
			 String apellido = apellidoAleatorio();
			 Cliente c = new Cliente(nombre,apellido,generarCorreo(nombre,apellido),generarCuentas(nombre),generarFondos(), generarFila(cine.getnFilas()), generarColumna(cine.getnColumnas()),gr);
			 th = new Thread(c);
			 th.start();
			 try {
				//Hacemos que espere para que al visualizador le de tiempo a mostrarse
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Cuando el cine este lleno, lo vaciamos, y hacemos que el bulce vuelva a empezar con nuevos clientes
			if(cine.estaLleno()) {
				System.out.println("El cine esta lleno, el cine ha recaudado " + cine.getRecaudacionTotal() + "€");
				System.out.println("Vaciando la sala para que comience la siguiente película");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cine.reiniciarAsientos();
			}
		 }
	}
	

	/**
	 * Generan variables aleatorias para las diferentes datos que necesitan las clases
	 */
	public static String generarCuentas(String nombre) {
		String cuenta = "";
		for(int i = 0; i < nombre.length(); i++) {
			int caracter = nombre.charAt(i);
			String caracteres = String.valueOf(caracter);
			cuenta += caracteres;
		}
		return cuenta+nombre.charAt(0);
	}
	
	public static String nombreAleatorio() {
		 String[] nombres = {
		            "Juan", "María", "Carlos", "Ana", "Pedro",
		            "Laura", "Diego", "Isabel", "Luis", "Marta",
		            "Ricardo", "Carmen", "Sergio", "Elena", "Alejandro",
		            "Patricia", "Javier", "Natalia", "Gabriel"
		        };
		 int numero = (int) Math.floor(Math.random()*19);
		 return nombres[numero];
	}
	
	public static String apellidoAleatorio() {
		 String[] apellidos = {
		            "Gómez", "Fernández", "López", "Martínez", "Rodríguez",
		            "Pérez", "Sánchez", "García", "Díaz", "Romero",
		            "Ruiz", "Torres", "Jiménez", "Gutiérrez", "Núñez",
		            "Hernández", "Serrano", "Castro", "Vargas", "Molina"
		        };
		 int numero = (int) Math.floor(Math.random()*19);
		 return apellidos[numero];
	}
	
	public static String generarCorreo(String nombre, String apellido) {
		return nombre + "." + apellido + "@gmail.com";
	}
	
	public static int generarFondos() {
		int numero = (int) Math.floor(Math.random() * 1000);
		return numero;
	}
	
	public static int generarFila(int nFila) {
		int numero = (int) Math.floor(Math.random() * nFila);
		return numero;
	}
	
	public static int generarColumna(int nColumna) {
		int numero = (int) Math.floor(Math.random() * nColumna);
		return numero;
	}
}
