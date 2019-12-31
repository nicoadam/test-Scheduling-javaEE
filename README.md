
1- hacer en el directorio del proyecto: nota(se debe tener maven instalado en el sistema)
  mvn install
  mvn liberty:dev

 nota: para volver a ejecutar el servidor una vez que las dependencias se descarguen , hacer : mvn liberty:clean liberty:dev

2-) el programa empezara a ejecutar la tarea a las 13:35, tal como se indico. (nota: sera necesario cambiar la hora de la pc, o cambiar el cron del Schedule a la hora o tiempo deseado y hacer mvn liberty:clean liberty:dev).

3-) acceder a la consola web h2 : http://localhost:8082
    jdbc-url:  jdbc:h2:mem:test
    user: sa
  
  Hay estara la tabla mock_data, cuando corra el job este cargara la data del csv.
 
 