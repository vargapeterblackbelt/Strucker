package hu.elte.strucker.controller;

import hu.elte.strucker.model.diagram.Diagram;

public interface DiagramOperations {
    void open(Diagram diagram);
    void export(Diagram diagram);
    void capture(Diagram diagram);
    void delete(Diagram diagram);
    void showProperties(Diagram diagram);
    void check(Diagram diagram);
    void execute(Diagram diagram);
}
