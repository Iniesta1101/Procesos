package Clases;

public class ProcesadorPagos {
	private static final int PRECIO_ENTRADA = 100;
	private Cine cine;

	
	public ProcesadorPagos(Cine cine) {
		this.cine = cine;
	}
	
	public boolean procesarPago(String cuentaCliente, int fondosCliente) {
		System.out.println("Verificando saldo del cliente con la cuenta "+ cuentaCliente + ": " + fondosCliente);
		//Verificamos que el cliente tenga fondos suficientes para pagar el coste de la entrada
		if(fondosCliente >= PRECIO_ENTRADA) {
			System.out.println("Pago realizado con exito: " + cuentaCliente);
			cine.agregarRecaudación(PRECIO_ENTRADA);
			return true;
		}else {
			System.out.println("Pago rechazado por fondos insuficientes: " + cuentaCliente);
			return false;
		}
	}

	public int getPrecioEntrada() {
		return PRECIO_ENTRADA;
	}
	
	
}
