package Clases;

//Se encarga de mostrar constantemente de manera gráfica los asiento libre y ocupados.
//Aparece cada vez que un cliente hace una reserva
public class Visualizador implements Runnable{
	private Cine cine;
	
	public Visualizador(Cine cine) {
		this.cine = cine;
	}

	@Override
	public void run() {
		while(true) {
			//Ponemos el cine en synchronized porque es la clase que se comparte entre los hilos
			synchronized (cine) {
				try {
					//Espera a que se haga una reserva
					cine.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mostrarEstadoAsientos();
			}
		}
	}
	/**
	 * X para ocupados, O para libre
	 */
	public void mostrarEstadoAsientos() {
		Asiento[][] asientos = cine.getAsientos();
		for(int fila = 0; fila < cine.getnFilas(); fila++) {
			for(int columna = 0; columna < cine.getnColumnas(); columna++) {
				if(asientos[fila][columna].isOcupado()) {
					System.out.print("[X]");
				}else{
					System.out.print("[O]");
				}
			}
			System.out.print("\n");
		}
	}
}
