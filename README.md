## Table of Contents
1. [Información general](#general-info)
2. [Documentación](#documentacion)
### Información general
***
Proyecto que contiene el desarrollo de una prueba técnica cuya cuál consiste:

Eres un bar ténder del bar más famoso de tu ciudad. Hay un arreglo de vasos numerados que está
representado por el arreglo A. Adicional se tiene un arreglo con números primos representado
por P.

Valor del array P:

$ P=2,3,5,7,11,13,17…

En cada iteración Qi , retira cada vaso de la parte superior del arreglo A. Determina si el número
del plato es divisible uniformemente por el Pi número primo. Si lo es, agréguelo en un arreglo B.
En caso contrario, añádalo a un arreglo Ai . Guarda los valores de B en el arreglo Respuesta. En
la siguiente iteración, haz lo mismo con los valores del arreglo Ai . Una vez completado el
número necesario de iteraciones Q, almacene los valores restantes de Ai , al final de Respuesta.
Se debe exponer un API que reciba un número de iteraciones Q y un número del 1 al 5 que
representara el i.

Se debe exponer un API que reciba un número de iteraciones Q y un número del 1 al 5 que
representara el ID de la pila de datos a trabajar (script de DB entregados) y entregue el array de
respuesta.

**script de DB entregados**

Este script lo puedes encontrar en los **recursos** del proyecto dentro de la carpeta sql (**bd.sql**).

## Documentación
***
* swagger: http://localhost:8080/swagger-ui.html 
* Javadoc: http://localhost:5500/doc/index.html