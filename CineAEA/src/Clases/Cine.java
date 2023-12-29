package Clases;

public class Cine {
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
	
	public void inicializarAsientos() {
		for(int fila = 0; fila < FILAS_CINE; fila++) {
			for(int columna = 0; columna < COLUMNAS_CINE; columna++) {
				this.asientos[fila][columna] = new Asiento(fila,columna);
			}
		}
	}
	
	public boolean verificarDisponibilidad(int fila, int columna) {
		return !this.asientos[fila][columna].isOcupado();
	}
	
	public void reservarAsiento(int fila, int columna) {
		this.asientos[fila][columna].setOcupado(true);
	}
	
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
	
	public void agregarRecaudación(int pago){
		this.recaudacionTotal += pago;
	}
	
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
