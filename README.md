# pedidos-comportamiento

Proyecto de la materia Patrones de Diseño de Software - Unidad 4.
Se implementaron los patrones Chain of Responsibility y Command en un proyecto
Spring Boot que simula el backend de un sistema de comercio electronico con
validacion de pedidos y operaciones reversibles.

## Que hace el proyecto

Cuando un usuario realiza un pedido, este pasa por una cadena de validaciones
(stock, monto minimo y credito del cliente) antes de confirmarse. Si pasa todas
las validaciones, se pueden ejecutar acciones sobre el pedido como confirmarlo
o aplicar un descuento, y cada accion puede deshacerse con undo.

## Patrones implementados

### Chain of Responsibility
El problema era que un pedido necesita pasar por varias validaciones antes de
confirmarse. Si se pusieran todas las validaciones en un solo metodo, el codigo
quedaria muy acoplado y dificil de extender con nuevas reglas.

La solucion fue crear una cadena de validadores donde cada uno revisa su condicion
y si pasa, delega al siguiente. La cadena se construye con setNext() de forma fluida:
Stock -> Monto -> Credito. Si cualquier validador falla, la cadena se detiene ahi.

### Command con Undo
El problema era que las acciones sobre el pedido (confirmar, aplicar descuento)
necesitaban poder revertirse. Sin Command, no habria forma de guardar el estado
anterior para hacer undo.

La solucion fue encapsular cada accion como un objeto Comando que guarda el estado
anterior antes de ejecutarse. El HistorialComandos actua como Invoker y mantiene
una pila de comandos ejecutados. Al llamar deshacer(), toma el ultimo comando
y llama su metodo undo() que restaura el estado anterior.

## Como ejecutar

Requisitos: Java 17 y Maven 3.8 o superior.

Compilar y empaquetar:
mvn clean package

Ejecutar la aplicacion:
mvn spring-boot:run

Ejecutar los tests:
mvn test

## Capturas de pantalla
![image alt](https://github.com/DiegiTriana/Triana-post1-u4/blob/68f908d2e24442abdd4b0de14e89c80d609a22ac/captura%201.png)
![image alt](https://github.com/DiegiTriana/Triana-post1-u4/blob/68f908d2e24442abdd4b0de14e89c80d609a22ac/captura%202.png)
