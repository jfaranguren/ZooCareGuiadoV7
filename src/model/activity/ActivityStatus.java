package model.activity;

public enum ActivityStatus {

    ASSIGNED("Asignada"),
    FINISHED("Finalizada"),
    CANCELED("Cancelada");

    private String text;

    private ActivityStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
