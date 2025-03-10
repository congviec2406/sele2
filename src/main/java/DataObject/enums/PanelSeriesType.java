package DataObject.enums;

public enum PanelSeriesType {
    NAME("name"),
    LOCATION("location");

    private String value;

    PanelSeriesType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
