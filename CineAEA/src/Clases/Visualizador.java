package Clases;

public class Visualizador implements Runnable{
	private Cine cine;
	
	public Visualizador(Cine cine) {
		this.cine = cine;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (cine) {
				try {
					cine.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mostrarEstadoAsientos();
			}
		}
	}
	
	
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
