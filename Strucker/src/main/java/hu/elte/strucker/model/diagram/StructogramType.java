package hu.elte.strucker.model.diagram;

public enum StructogramType {
    STATEMENT("Akció"), LOOP("Ciklus"), SELECTION("Elágazás"), SEQUENCE("Szekvencia");

    private String name;

    StructogramType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
