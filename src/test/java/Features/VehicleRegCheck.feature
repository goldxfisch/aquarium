@SmokeTests
Feature: DVLA Checks

@VerifyVehicleReg
  Scenario Outline: Validating Vehicle Registration Numbers
    Given the user navigates to DVLA landing page
    When input registration number "<registration>"
    Then the user is displayed vehicle detail "<registration>" , "<make>", "<colour>"
    Examples:
    | registration | make | colour |
    | KV60 XDV| XXXX| BLACK |
| APB10 PB| ZZZZ| RED |
| DA11 PZM| AUDI| BLUE |
| CE11 XUA| HONDA| RED |
| KV65 XDV| XXXX| BLACK |
| APB13 PB| ZZZZ| RED |
| DA10 PZM| AUDI| BLUE |
| CE51 XUA| HONDA| RED |
| DA00 PZM| AUDI| BLUE |
| CE00 XUA| HONDA| RED |
| APB13 PB| XXXX| YELLOW |
| CE51 XUA| HONDA| RED |
| KV65 XDV| BMW| RED |
| DA10 PZM| AUDI| BLUE |
