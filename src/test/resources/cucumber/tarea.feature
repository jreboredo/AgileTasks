Feature: Tarea

  Scenario: agregar una tarea
    Given Un usuario logeado con contraseña "asdbqwerty", userName "Admin"
    And una tarea vacia
    When Seteo el titulo de la tarea con "Estudiar matemática"
    And Seteo la descripcion de la tarea con "Estudiar enteros, matrices y ecuaciones lineales"
    And Seteo la prioridad de la tarea en "Alta"
    And Seteo el comienzo de la tarea en "2020-11-13T21:15:00"
    And Seteo el vencimiento de la tarea en "2020-11-14T01:00:00"
    And Guardo la tarea
    Then La tarea se guardó con el titulo "Estudiar matemática" y la descripcion "Estudiar enteros, matrices y ecuaciones lineales"
    And La tarea se guardó con la prioridad Alta
    And La tarea se guardó con el comienzo "2020-11-13T21:15:00"
    And La tarea se guardó con el vencimiento "2020-11-14T01:00:00"

  Scenario: Actualizar una tarea

    Given Un usuario logeado con contraseña "asdbqwerty", userName "Admin5"
    And Una tarea con titulo "Tp de videojuegos", descripcion "Hacer luces y arreglar sprites del minion"
    When Seteo el titulo de la tarea con "Boss de Videojuegos"
    And Seteo la descripcion de la tarea con "Arreglar la muerte del personaje"
    And Actualizo la tarea
    Then La tarea se guardó con el titulo "Boss de Videojuegos" y la descripcion "Arreglar la muerte del personaje"

  Scenario: Eliminar una tarea

    Given Un usuario logeado con contraseña "asdbqwerty", userName "Admin4"
    And Una tarea con titulo "Limpiar", descripcion "limpiar la cocina y el comedor"
    When elimino la tarea
    Then la tarea se eliminó

  Scenario: Priorizar Tarea

    Given Un usuario logeado con contraseña "asdbqwerty", userName "Admin8"
    And Una tarea con titulo "Barrer", descripcion "barrer la cocina"
    When Seteo la prioridad de la tarea en "Alta"
    And Actualizo la tarea
    Then La tarea se guardó con la prioridad Alta
    When Seteo la prioridad de la tarea en "Media"
    And Actualizo la tarea
    Then La tarea se guardó con la prioridad Media
    When Seteo la prioridad de la tarea en "Baja"
    And Actualizo la tarea
    Then La tarea se guardó con la prioridad Baja

  Scenario: Estado de la Tarea

    Given Un usuario logeado con contraseña "asdbqwerty", userName "Admin9"
    And Una tarea con titulo "Ir a comprar", descripcion "leche, huevos y azucar"
    When Seteo el estado de la tarea a "Hecho"
    And Actualizo la tarea
    Then La tarea está completada
    When Seteo el estado de la tarea a "incompleta"
    And Actualizo la tarea
    Then La tarea no está completada

  Scenario: Comienzo y fin de una Tarea

    Given Un usuario logeado con contraseña "asdbqwerty", userName "Admin10"
    And Una tarea con titulo "barrer", descripcion "barrer la pieza"
    When Seteo el comienzo de la tarea en "2020-11-13T21:15:00"
    And Seteo el vencimiento de la tarea en "2020-11-13T22:00:00"
    And Actualizo la tarea
    Then La tarea se guardó con el comienzo "2020-11-13T21:15:00"
    And La tarea se guardó con el vencimiento "2020-11-13T22:00:00"

  Scenario: envio de mail
    Given Un usuario logeado con contraseña "asdbqwerty", userName "Admin20" y email "joge@gmail.com"
    And una tarea vacia
    When Seteo el titulo de la tarea con "Estudiar matemática"
    And Seteo la descripcion de la tarea con "Estudiar enteros, matrices y ecuaciones lineales"
    And Seteo la prioridad de la tarea en "Alta"
    And Seteo el comienzo de la tarea en "2020-11-13T21:15:00"
    And Seteo el vencimiento de la tarea en "2020-11-14T01:00:00"
    And Guardo la tarea
    And Se enviá el mail
    Then El mail se envió correctamente
    When Seteo el titulo de la tarea con "Estudiar Matemática"
    And Se enviá el mail
    And Actualizo la tarea
    Then El mail se envió correctamente




