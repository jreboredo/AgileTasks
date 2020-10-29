
Feature: Nota

  Scenario: Setear una Nota con un titulo y descripcion

    Given Nota vacia
    When Seteo el titulo con "Telefono-Abuela"
    Then El titulo tiene "Telefono-Abuela"

    Given Nota vacia
    When Seteo la descripcion con "11-8811-3233"
    Then La descripcion tiene "11-8811-3233"

  Scenario: Guardar una Nota

    Given una nota con titulo "wifi", descripcion "contraseña-wifi"
    When Guardo la nota
    Then La nota se guardó con su titulo y descripcion

  Scenario: Actualizar una Nota

    Given una nota con titulo "pizzeria", descripcion "11-9999-8888"
    When Actualizo la descripcion con "11-7777-6666"
    Then Se actualizo la descripcion con "11-7777-6666"
    When Actualizo el titulo con "panaderia"
    Then Se actualizo el titulo con "panaderia"

  Scenario: Eliminar una Nota

    Given una nota con titulo "estudiar", descripcion "matematica"
    When elimino la nota
    Then la nota se eliminó