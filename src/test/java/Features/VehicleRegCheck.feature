@SmokeTests
Feature: DVLA Checks

@VerifyVehicleReg
  Scenario Outline: Validating Vehicle Registration Numbers
    Given the user navigates to DVLA landing page
    When input registration number "<registration>"
    Then the user is displayed vehicle detail "<registration>" , "<make>", "<colour>"
    Examples:
    | registration | make | colour |
| DA10 PZM| AUDI| BLUE |
