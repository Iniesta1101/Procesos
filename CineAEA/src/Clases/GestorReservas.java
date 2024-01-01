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
		// Verificamos si el asiento deseado está disponible
		if(cine.verificarDisponibilidad(filaDeseada, columnaDeseada)) {
			procesarReserva(cliente, filaDeseada, columnaDeseada);
		}else {
			//Sino lo esta, buscamos uno alternativo
			System.out.println("El asiento de la fila " + filaDeseada + " y columna " + columnaDeseada + " no está disponible");
			System.out.println("Buscando asiento alternativo...");
			int[] asientoAlternativo = buscarAsientoAlternativo(filaDeseada, columnaDeseada, cine.getnFilas(), cine.getnColumnas());
			//Iniciamos la reserva si el asiento es distinto de null, si es null significa que el cine está lleno 
			if (asientoAlternativo != null) {
				System.out.println("Busqueda de asiento alternativo realizado con éxito");
				System.out.println("El nuevo asiento es fila " + asientoAlternativo[0] + " y columna " + asientoAlternativo[1]);
				procesarReserva(cliente, asientoAlternativo[0], asientoAlternativo[1]);
			}
		}
	}
	
	/**
	 * Método para buscar un asiento alternativo basado en la proximidad a un asiento deseado.
	 * Devuelve las coordenadas de fila y columna del asiento alternativo más cercano.
	 * Si no hay asientos disponibles, imprime un mensaje y devuelve null.
	 */
	public int[] buscarAsientoAlternativo(int filaDeseada, int columnaDeseada, int filas, int columnas) {
	    int[] filaColumna = new int[2];

		// Inicializar la distancia mínima con un valor infinito.
	    double distanciaMinima = Double.POSITIVE_INFINITY;

	    for (int i = 0; i < filas; i++) {
	        for (int j = 0; j < columnas; j++) {
				// Verificar si el asiento en la posición (i, j) está disponible y no es el asiento deseado.
	            if (cine.verificarDisponibilidad(i, j) && !(i == filaDeseada && j == columnaDeseada)) {
					// Calcular las distancias en filas y columnas.
	                double distanciaFilas = Math.abs(filaDeseada - i);
	                double distanciaColumnas = Math.abs(columnaDeseada - j);
					// Calcular la distancia total utilizando el teorema de Pitágoras.
	                double distanciaTotal = Math.sqrt(distanciaFilas * distanciaFilas + distanciaColumnas * distanciaColumnas);

					// Verificar si la distancia total es menor que la distancia mínima registrada hasta ahora.
	                if (distanciaTotal < distanciaMinima) {
						// Actualizar la distancia mínima.
	                    distanciaMinima = distanciaTotal;
						// Actualizar las coordenadas del asiento alternativo más cercano.
	                    filaColumna[0] = i;
	                    filaColumna[1] = j;
	                }
	            }
	        }
	    }
		// Verificar si se encontró un asiento alternativo.
	    if (distanciaMinima != Double.POSITIVE_INFINITY) {
	        return filaColumna;
	    } else {
	        System.out.println("No hay asientos disponibles");
			// Devolver null ya que no hay asientos alternativos.
	        return null;
	    }
	}


	
	public void procesarReserva(Cliente cliente, int fila, int columna){
		//Hacemos la reserva si el cliente tiene fondos suficientes
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
