
Feature: Nota

  Scenario: Setear una Nota con un titulo y descripcion

    Given Nota vacia
    When Seteo el titulo con "Telefono-Abuela"
    Then El titulo tiene "Telefono-Abuela"

    Given Nota vacia
    When Seteo la descripcion con "11-8811-3233"
    Then La descripcion tiene "11-8811-3233"
