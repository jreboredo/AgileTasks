
Feature: Nota

  Scenario: Agregar una nueva nota

    Given Un usuario logeado con contraseña "asdbqwerty" y userName "Admin1"
    And Nota vacia
    When Seteo la descripcion con "contraseña-wifi"
    And Seteo el titulo con "wifi"
    And Guardo la nota
    Then La nota se guardó con el titulo "wifi" y la descripcion "contraseña-wifi"

  Scenario: Actualizar una Nota

    Given Un usuario logeado con contraseña "asdbqwerty" y userName "Admin2"
    And una nota con titulo "pizzeria", descripcion "11-9999-8888"
    When Actualizo la descripcion con "11-7777-6666"
    And Actualizo el titulo con "panaderia"
    Then La nota se guardó con el titulo "panaderia" y la descripcion "11-7777-6666"

  Scenario: Eliminar una Nota

    Given Un usuario logeado con contraseña "asdbqwerty" y userName "Admin3"
    And una nota con titulo "estudiar", descripcion "matematica"
    When elimino la nota
    Then la nota se eliminó