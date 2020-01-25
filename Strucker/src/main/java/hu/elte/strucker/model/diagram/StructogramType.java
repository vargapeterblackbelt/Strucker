package hu.elte.strucker.model.diagram;

public enum StructogramType {
    STATEMENT("Kifejezés"), LOOP("Ciklus"), SELECTION("Elágazás"), SEQUENCE("Szekvencia"), DIAGRAM("Diagram");

    private String name;

    StructogramType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
