package hu.elte.strucker.controller.operations;

public interface StructogramOperations {
    void delete();
    void insert();
    void insertBefore();
    void insertAfter();
    void moveUp();
    void moveDown();
    void showProperties();
}
