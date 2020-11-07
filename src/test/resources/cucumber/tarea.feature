Feature: Tarea

  Scenario: agregar una tarea
    Given Un usuario logeado con contraseña "asdbqwerty", userName "Admin"
    And una tarea vacia
    When Seteo el titulo de la tarea con "Estudiar matemática"
    And Seteo la descripcion de la tarea con "Estudiar enteros, matrices y ecuaciones lineales"
    And Guardo la tarea
    Then La tarea se guardó con el titulo "Estudiar matemática" y la descripcion "Estudiar enteros, matrices y ecuaciones lineales"

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


