package Clases;

public class GestorReservas {
	private Cine cine;
	private ProcesadorPagos pg;
	
	public GestorReservas(Cine cine, ProcesadorPagos pg) {
		this.cine = cine;
		this.pg = pg;
	}
	
	public synchronized void intentarReservar(Cliente cliente, int filaDeseada, int columnaDeseada) {
		System.out.println("El cliente " + cliente.getNombre() + " "+cliente.getApellidos()+" quiere reservar el asiento de la fila " + filaDeseada + " y columna " + columnaDeseada);
		System.out.println("Se registra con la cuenta de correo " + cliente.getCorreo() + " y la cuenta del banco " + cliente.getCuentaBancaria());
		if(cine.verificarDisponibilidad(filaDeseada, columnaDeseada)) {
			procesarReserva(cliente, filaDeseada, columnaDeseada);
		}else {
			System.out.println("El asiento de la fila " + filaDeseada + " y columna " + columnaDeseada + " no está disponible");
			System.out.println("Buscando asiento alternativo...");
			int[] asientoAlternativo = buscarAsientoAlternativo(filaDeseada, columnaDeseada, cine.getnFilas(), cine.getnColumnas());
			if (asientoAlternativo != null) {
				System.out.println("Busqueda de asiento alternativo realizado con éxito");
				System.out.println("El nuevo asiento es fila " + asientoAlternativo[0] + " y columna " + asientoAlternativo[1]);
				procesarReserva(cliente, asientoAlternativo[0], asientoAlternativo[1]);
			}
		}
	}
	
	public int[] buscarAsientoAlternativo(int filaDeseada, int columnaDeseada, int filas, int columnas) {
	    int[] filaColumna = new int[2];
	    double distanciaMinima = Double.POSITIVE_INFINITY;

	    for (int i = 0; i < filas; i++) {
	        for (int j = 0; j < columnas; j++) {
	            if (cine.verificarDisponibilidad(i, j) && !(i == filaDeseada && j == columnaDeseada)) {
	                double distanciaFilas = Math.abs(filaDeseada - i);
	                double distanciaColumnas = Math.abs(columnaDeseada - j);
	                double distanciaTotal = Math.sqrt(distanciaFilas * distanciaFilas + distanciaColumnas * distanciaColumnas);

	                if (distanciaTotal < distanciaMinima) {
	                    distanciaMinima = distanciaTotal;
	                    filaColumna[0] = i;
	                    filaColumna[1] = j;
	                }
	            }
	        }
	    }

	    if (distanciaMinima != Double.POSITIVE_INFINITY) {
	        return filaColumna;
	    } else {
	        System.out.println("No hay asientos disponibles");
	        return null;
	    }
	}


	
	public void procesarReserva(Cliente cliente, int fila, int columna){
		if(pg.procesarPago(cliente.getCuentaBancaria(), cliente.getFondos())) {
			cliente.setFondos(cliente.getFondos() - pg.getPrecioEntrada());
			cine.reservarAsiento(fila, columna);
			System.out.println("La reserva del cliente " + cliente.getNombre() + " "+cliente.getApellidos() + " ha sido realizada con éxito");
		}else {
			System.out.println("La reserva del cliente "+cliente.getNombre()+" "+cliente.getApellidos()+" ha sido rechazada por fondos insuficientes");
		}
	}

	public Cine getCine() {
		return cine;
	}
	
	
	
}
