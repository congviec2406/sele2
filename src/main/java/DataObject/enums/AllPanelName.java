package DataObject.enums;

public enum AllPanelName {
    Chart("Charts"),
    SubChart1("Action Implementation By Status"),
    SubChart2("Test Case Execution Failure Trend"),
    SubChart3("Test Case Execution Results"),
    SubChart4("Test Case Execution Trend"),
    SubChart5("Test Module Execution Failure Trend"),
    SubChart6("Test Module Execution Results"),
    SubChart7("Test Module Execution Trend"),
    SubChart8("Test Module Implementation By Priority"),
    SubChart9("Test Module Implementation By Status"),
    SubChart10("Test Module Status per Assigned Users"),
    Indi("Indicators"),
    SubIndi1("Test Case Execution"),
    SubIndi2("Test Module Execution"),
    SubIndi3("Test Objective Execution"),
    Report("Reports"),
    SubReport1("Test Module Execution Results Report"),
    HeatM("Heat Maps"),
    SubHeatM1("Test Case Execution History"),
    SubHeatM2("Test Module Execution History"),
    Err(" is not populated and sorted correctly");
    private String value;

    AllPanelName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}