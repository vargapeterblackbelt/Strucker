package hu.elte.editor;

public interface Application {
    void newDiagram();
    void newProject();
    void openDiagram();
    void save();
    void openProject();
    void saveAsProject();
    void showSettings();
    void exit();
    void close();
    void undo();
    void redo();
    void copy();
    void cut();
    void paste();
    void delete();
    void zoomIn();
    void zoomOut();
    void zoomDefault();
    void check();
    void run();
    void runDebug();
    void stop();
    void about();
    void closeWindow();
    void showProperties();
    void addElement();
    void printMessage(String msg);
}
