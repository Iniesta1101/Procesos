package Clases;

public class Cine {
	//Estos datos los ponemos como constantes, porque es un dato que nunca va a cambiar durante la ejecución del programa
	private static final int COLUMNAS_CINE = 10;
	private static final int FILAS_CINE = 10;
	private Asiento[][] asientos = new Asiento[FILAS_CINE][COLUMNAS_CINE];
	private int recaudacionTotal;
	private String cuentaCine;
	
	
	public Cine(String cuentaCine) {
		inicializarAsientos();
		this.recaudacionTotal = 0;
		this.cuentaCine = cuentaCine;
	}

	/**
	 * Inicializamos todos los asientos 
	 */	
	public void inicializarAsientos() {
		for(int fila = 0; fila < FILAS_CINE; fila++) {
			for(int columna = 0; columna < COLUMNAS_CINE; columna++) {
				this.asientos[fila][columna] = new Asiento(fila,columna);
			}
		}
	}
	
	/**
	 * Miramos si el asiento está ocupado
	 */ 
	public boolean verificarDisponibilidad(int fila, int columna) {
		return !this.asientos[fila][columna].isOcupado();
	}
	
	/**
	 * Al ocupar el asiento, decimos que está ocupado para que otro cliente no lo pueda volver a ocupar
	 */
	public void reservarAsiento(int fila, int columna) {
		this.asientos[fila][columna].setOcupado(true);
	}
	
	/**
	 * Comprobamos si el cine está lleno
	 */
	public boolean estaLleno() {
		for(int fila = 0; fila < FILAS_CINE; fila++) {
			for(int columna = 0; columna < COLUMNAS_CINE; columna++) {
				if(!this.asientos[fila][columna].isOcupado()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Cuando se haga una reserva, guardamos los pagos realizados por el cliente en la reacudacion
	 */
	public void agregarRecaudación(int pago){
		this.recaudacionTotal += pago;
	}
	
	/**
	 * Vaciamos todos los asientos para que el programa pueda continuar
	 */
	public void reiniciarAsientos() {
		for(int fila = 0; fila < FILAS_CINE; fila++) {
			for(int columna = 0; columna < COLUMNAS_CINE; columna++) {
				this.asientos[fila][columna].setOcupado(false);
			}
		}
	}

	public String getCuentaCine() {
		return cuentaCine;
	}

	public void setCuentaCine(String cuentaCine) {
		this.cuentaCine = cuentaCine;
	}

	public Asiento[][] getAsientos() {
		return asientos;
	}

	public int getRecaudacionTotal() {
		return recaudacionTotal;
	}

	public int getnFilas() {
		return FILAS_CINE;
	}

	public int getnColumnas() {
		return COLUMNAS_CINE;
	}
	
	
	
	
}
