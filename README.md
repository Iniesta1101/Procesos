# Proyecto simulación cine en java
---
Este proyecto crea una simulación de un cine en el que varios clientes reservan asientos para una sala de cine.

##Descripción
Los clientes son hilos que se estan creando constantemente, los caules estás intentando reservar asientos. Podrán reservar si tienen fondos suficientes, y el sitio que eligan está libre. En caso de que el asiento elgido está ocupado, se le buscará un asiento alternativo. Una vez hecho esto se realiza el pago y se hace la reserva, y en caso de que no se cumplan las condiciones se recha la reserva.
Cuando la sala de cine se llene, está se vaciará, y comenzara de nuevo el bucle.
Cada vez que se haga una reserva, se mostrará un represantación visual en la terminal de los asientos ocupados ("[X]") y los no ocupados ("[O]").

Este proyecto está compuesto:

- Cliente: simula a un cliente individual que intenta reservar un asiento en el cine. Cada cliente se ejecuta como un hilo separado para simular la concurrencia en el sistema.
- Asiento: representa individualmente cada asiento en el cine. Cada objeto. Asiento mantiene información sobre su ubicación específica en la sala (fila y columna) y si está ocupado o no.
- Cine: es el corazón de la simulación, representando el cine con su conjunto de asientos y gestionando la información relacionada con las reservas y la recaudación total.
- ProcesadorPagos: gestiona las transacciones financieras asociadas a la reserva de asientos en el cine.
- GestorReservas: coordina el proceso de reserva de asientos en el cine, actuando como intermediario entre los clientes y el sistema de reservas.
- Visualizador: es el responsable de mostrar el estado actual de los asientos en el cine, proporcionando una representación visual en la terminal.
- Main: La clase Main sirve como el punto de inicio y control para la simulación de reserva de entradas de cine. Inicializa todos los componentes necesarios y gestiona la ejecución de la simulación.


