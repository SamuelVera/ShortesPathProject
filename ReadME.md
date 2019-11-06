# Proyecto de modelación de sistemas en redes.

## Enunciado

Bogotá es una hermosa ciudad ubicada sobre un inmenso altiplano que está más de 8000 pies encima del nivel del mar. Con algunas excepciones, sus vías de comunicación constituyen una cuadrícula en la que las carreras van de norte a sur y vice-versa, en tanto que las calles las intersectan a 90º y van sentido oeste-este y vice-versa. Cuando una carrera o una calle es muy importante, se le antepone el prefijo “Avenida”, como por ejemplo la Avenida Carrera 68 o la Avenida Calle 134. En algunas zonas hay vías que no son norte-sur ni oeste-este … se llaman diagonales y transversales pero para los efectos de este proyecto supondremos que no existen. En la zona norte de la ciudad los números de las carreras crecen desde el este hacia el oeste, mientras que las calles crencen a medida que se avanza hacia el norte. A diferencia de Caracas, en la que el Parque Nacional El Ávila delimita el confín norte de la ciudad, la interrupción del altiplano en Bogotá se produce al este de la ciudad, debido a los llamados Cerros Orientales.

Javier y Andreína son novios y viven en Bogotá … lamentablemente la familia de ella no aprueba la relación, por lo que tienen que verse en secreto.  Javier vive en la intersección de la Calle 54 con la Carrera 14, en tanto que Andreína vive en la intersección de la Calle 52 con la Carrera 13, o sea a tres cuadras de su novio. Los establecimientos nocturnos que visitan para encontrarse (sin perjuicio de que el día de la defensa del proyecto se agreguen otros) son:

·         Discoteca The Darkness : Carrera 14 con Calle 50

·         Bar La Pasión: Calle 54 con Carrera 11

·        Cervecería Mi Rolita (“rolo” es un término coloquial de “bogotano” aplicable a quienes, habiendo nacido en la capital, descienden de colombianos de otra parte del país): Calle 50 con Carrera 12.

Estos establecimientos son solo para parejas, por lo que los porteros exigen que los dos miembros de la pareja lleguen simultáneamente. La zona de la ciudad delimitada entre la Calle 50 por el Sur, la Calle 55 por el norte, la Carrera 10 por el este y la Carrera 15 por el oeste constituyen una cuadrícula perfecta de 25 cuadras, cada una de las cuales tarda 5 minutos en caminar, con excepción de las Carreras 11, 12 y 13, cuyas aceras están en mal estado y se tarda 7 minutos recorrer cada cuadra, y la Calle 51 cuya extensa actividad comercial hace que uno se tarde 10 minutos en recorrer cada cuadra.

Como se indicó antes, Javier y Andreína deben llegar al mismo momento a la entrada del establecimiento que desean visitar, pero no pueden ser vistos caminando juntos. Escriba un programa en Java o Python que, a partir del dato del establecimiento destino, indique la trayectoria  que debe seguir cada uno y que minimice el tiempo total de caminata de la pareja. En caso de que el tiempo de caminata de cada uno sea distinto, se debe indicar cuál de los dos debe salir antes de su domicilio y cuánto tiempo antes, a fin de llegar a la puerta del establecimiento simultáneamente.

## Estructuras de Datos

### Gráfo
- Vértice inicial de Javier.
- Vértice inicial de Andreina.
- Vértice final.
- Lista de nodos.
- Lista de arcos.

### Vértice
- Calle.
- Carrera.
- dx1. (Distancia desde la casa de Javier al nodo x)
- dx2. (Distancia desde la casa de Andreina al nodo x)
- isInAndreinaPath. (Coloreo para Andreina)
- isInJavierPath. (Coloreo para Javier)

### Arco
- weight. (Tiempo/Peso)
- nodeEnd1. (Nodo Extremo 1)
- nodeEnd2. (Nodo Extremo 2)
- isInAndreinaPath. (Coloreo para Andreina)
- isInJavierPath. (Coloreo para Javier)

## Posibles Soluciones
- Hacer Djikstra con uno y adaptar al otro a su camino.
  - Consideraciones:
    - Calcular tiempos de salida post algoritmo.
    - Verficar los pesos acumulados de los caminos parciales tomando en cuenta el tiempo que tiene que salir uno antes que el otro para reestructurar el árbol generado por el algoritmo de Dijkstra.
    
