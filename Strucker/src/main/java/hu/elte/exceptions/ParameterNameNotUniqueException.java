package hu.elte.exceptions;

public class ParameterNameNotUniqueException extends Exception {
    public ParameterNameNotUniqueException(String name) {
        super("Variable with name " + name + "already exists.");
    }
}
