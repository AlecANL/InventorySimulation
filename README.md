Se desea simular el inventario de una tienda.

* Hay varios hilos productores que representan entregas de productos (incrementan el inventario).
* Hay varios hilos consumidores que representan ventas (decrementan el inventario).
* El inventario se controla con una variable atómica AtomicInteger.

Reglas:

* El inventario nunca debe bajar de 0. Si un consumidor intenta vender cuando no hay stock, debe esperar o ignorar la venta.
* Cada hilo realiza un número fijo de operaciones. Por ejemplo: 100 ventas o 100 entregas.
* Al final, se debe imprimir:
  * El inventario inicial.
  * Total de entregas realizadas.
  * Total de ventas realizadas.
  * Inventario final (inicial + entregas – ventas válidas).

Entregable: Repositorio de Github.
