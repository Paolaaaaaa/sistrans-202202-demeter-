# SuperAndes

Este proyecto académico, desarrollado para la asignatura de Sistemas Transaccionales 2022-02, tiene como objetivo demostrar la competencia en el diseño de bases de datos relacionales. El desarrollo se enfoca en la correcta aplicación de la normalización y el establecimiento de relaciones sólidas, garantizando una arquitectura escalable capaz de adaptarse a futuras reglas de negocio y cambios en los requerimientos del sistema.

Este repositorio está resubido de este: https://bitbucket.org/sistrans-202202/demeter/src/master/ 
## Descripción del Caso

Uno de los grandes actores que mueven la economía y la vida diaria en el momento actual es el concepto de 
las grandes superficies o supermercados. Los hay de todo tipo y, como muestra, se pueden nombrar: Éxito, 
Carrefour, Jumbo, Carulla, IKEA, HomeCenter, entre otros. El objetivo de estas grandes superficies es agrupar 
en un solo espacio una gran oferta de productos, de manera que el consumidor está en la capacidad de adquirir 
en un solo sitio y momento todo aquello que necesita. ..

[ST-Pry-DocMarco-It1.pdf](https://github.com/user-attachments/files/24314965/ST-Pry-DocMarco-It1.pdf)

[ST-Pry-DocMarco-It3.pdf](https://github.com/user-attachments/files/24314980/ST-Pry-DocMarco-It3.pdf)
[Iteración4_D-04_p.campino_i.bermudezl.docx](https://github.com/user-attachments/files/24315538/Iteracion4_D-04_p.campino_i.bermudezl.docx)


## Diseño de Bases de datos

### Diseño de Base de datos V4:

A continuación, se presenta el nuevo modelo UML, para este nuevo modelo se ha considerado las correcciones anteriores con respecto al manejo de los carritos por lo que la relación con respecto a los clientes ha cambiado. Así mismo un producto que está dentro de un carrito guarda información acerca del estante de donde este ha salido: 
Diseño de Base de datos V3:
![diseno_dbV1_it4](https://github.com/user-attachments/assets/36282f76-765f-4c51-a129-6cb16a78d88e)


### Diseño de Base de datos V3:

![diseno_dbV2](https://github.com/user-attachments/assets/479e06f1-fa56-4fa1-b00e-1d0d80495b43)

[Iteración3_D-04_p.campino_i.bermudezl.docx](https://github.com/user-attachments/files/24315537/Iteracion3_D-04_p.campino_i.bermudezl.docx)


En base a los nuevos requerimientos funcionales de la aplicación, así como las correcciones propuestas en iteración 1 y 2 se ha podido notar que hay una necesidad de generar un concepto de carrito el cual solo puede ser usado por personas naturales. Este carrito es reservado por un cliente en un momento especifico y solo hasta que el cliente lo devuelva este carrito no podrá ser usado por otro cliente. El objetivo de este nuevo elemento es guardar todo producto que un cliente desee 
Otro punto de actualización es el funcionamiento de las ordenes de pedidos a proveedores pues se llegó a notar que no era necesario realizar una lista do productos por orden, de hecho, se ha cambiado las cosas ya que ahora la orden de pedido solo puede pedir un solo producto.
Para finalizar hay que mencionar los cambios en las promociones donde se ha decidido que no es necesario tener 2 tablas diferentes para las promociones, pues solo con especificar todos los atributos en promoción se puede llegar a tener un registro completo de lo que puede significar hacer una promoción.


### Diseño de Base de datos V2:

![diseno_dbV1_it3](https://github.com/user-attachments/assets/67ffcc24-c011-4b99-89a1-445b10cf39e6)

[Iteración2_D-04_p.campino.docx](https://github.com/user-attachments/files/24315549/Iteracion2_D-04_p.campino.docx)



### Diseño de Base de datos V1:


Planeaicón modelo Relacional:


[ModeloRelacional.xlsx](https://github.com/user-attachments/files/24314957/ModeloRelacional.xlsx)
[Iteración1_D-04_ca.medinac1_p.campino.docx](https://github.com/user-attachments/files/24315552/Iteracion1_D-04_ca.medinac1_p.campino.docx)

