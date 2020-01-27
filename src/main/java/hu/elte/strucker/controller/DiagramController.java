package hu.elte.strucker.controller;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.HealthCheck;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.recognizer.ExecuteException;
import hu.elte.strucker.service.MessageType;
import hu.elte.strucker.view.dialogs.DiagramPropertiesDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static hu.elte.strucker.model.HealthCheck.ERROR;
import static hu.elte.strucker.model.ObjectLoader.serialize;
import static hu.elte.strucker.service.MessageService.message;

@SuppressWarnings("ALL")
public class DiagramController implements DiagramOperations {

    private Application app;

    public DiagramController(Application app) {
        this.app = app;
    }

    @Override
    public void open(Diagram diagram) {
        app.fireDiagramOpening(diagram);
    }

    @Override
    public void export(Diagram diagram) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Diagram exportálása");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Diagram fájl", "diagram"));
        fileChooser.setApproveButtonText("Exportálás");
        fileChooser.showSaveDialog(app.getFrame());
        File file = fileChooser.getSelectedFile();
        if (file == null) {
            message("Exportálás megszakítva", MessageType.INFO);
        } else {
            Object serialized = serialize(file.getPath(), diagram);
            if (serialized == null) {
                message("Nem sikerült az exportálás.", MessageType.ERROR);
            } else {
                message("Sikeres exportálás", MessageType.SUCCESS);
            }
        }
    }

    @Override
    public void capture(Diagram diagram) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Diagram exportálása képként");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setApproveButtonText("Kép mentése");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG képfájlok", "png"));
        fileChooser.showSaveDialog(app.getFrame());
        File file = fileChooser.getSelectedFile();
        if (file == null) {
            message("Exportálás megszakítva", MessageType.INFO);
        } else {
            Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 32);
            Dimension imageSize = diagram.getImageSize(font);
            BufferedImage image = new BufferedImage(imageSize.width + 20, imageSize.height + 20, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, imageSize.width + 20, imageSize.height + 20);
            diagram.draw(graphics, 10, 10, diagram.widthNeeded(font), 0, font, true);
            try {
                ImageIO.write(image, "PNG", file);
            } catch (IOException e) {
                message("Nem sikerült az exportálás.", MessageType.ERROR);
            }
            message("Sikeres exportálás", MessageType.SUCCESS);
        }
    }

    @Override
    public void delete(Diagram diagram) {
        app.fireDiagramRemoved(diagram);
    }

    @Override
    public void showProperties(Diagram diagram) {
        DiagramPropertiesDialog dialog = new DiagramPropertiesDialog(diagram);
        if (!dialog.isCancelled()) {
            app.fireDiagramChanged(diagram);
        }
    }

    @Override
    public void check(Diagram diagram) {
        HealthCheck check = diagram.check();
        if (check.equals(ERROR)) {
            message("Az ellenőrzés sikertelen.", MessageType.ERROR);
        } else {
            message("Az ellenőrzés sikeres.", MessageType.SUCCESS);
        }
        app.fireDiagramChecked(diagram);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute(Diagram diagram) {
        boolean ok = true;
        Object result = null;
        try {
            result = diagram.eval(diagram.getType());
        } catch (ExecuteException e) {
            ok = false;
            e.printStackTrace();
            JOptionPane.showMessageDialog(app.getFrame(), "Hiba a lefutás közben: \n" + e.getMessage(), "Futási hiba", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            ok = false;
            e.printStackTrace();
            JOptionPane.showMessageDialog(app.getFrame(), "Nullérték keletkezett lefutás közben", "Futási hiba", JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            ok = false;
            e.printStackTrace();
            JOptionPane.showMessageDialog(app.getFrame(), "Indexelési hiba lefutás közben: \n" + e.getMessage(), "Futási hiba", JOptionPane.ERROR_MESSAGE);
        } catch (StackOverflowError e) {
            ok = false;
            e.printStackTrace();
            JOptionPane.showMessageDialog(app.getFrame(), "Stackoverflow hiba keletkezett", "Futási hiba", JOptionPane.ERROR_MESSAGE);
        } catch (OutOfMemoryError e) {
            ok = false;
            e.printStackTrace();
            JOptionPane.showMessageDialog(app.getFrame(), "Nincs elég memória", "Futási hiba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
            JOptionPane.showMessageDialog(app.getFrame(), "Hiba keletkezett a lefutás közben: \n" + e.getMessage(), "Futási hiba", JOptionPane.ERROR_MESSAGE);
        }
        if (ok) {
            if (result != null)
                JOptionPane.showMessageDialog(app.getFrame(), "return = " + formatResult(result), "Eredmény", JOptionPane.INFORMATION_MESSAGE, null);
            else
                JOptionPane.showMessageDialog(app.getFrame(), "Nincs visszatérési érték", "Eredmény", JOptionPane.INFORMATION_MESSAGE, null);
        }
    }

    @SuppressWarnings("rawtypes")
    private String formatResult(Object result) {
        if (result instanceof Number) {
            return formatNumber((Number) result);
        }
        if (result instanceof Boolean) {
            return formatBoolean((Boolean) result);
        }
        if (result instanceof List) {
           return formatList((List) result);
        }
        return result.toString();
    }

    private String formatNumber(Number number) {
        return number.doubleValue() == number.intValue() ? "" + number.intValue() : number.toString();
    }

    private String formatBoolean(Boolean b) {
        return b ? "igaz" : "hamis";
    }

    private String formatList(List list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o : list) {
            if (o instanceof Boolean) {
                sb.append(formatBoolean((Boolean) o)).append(", ");
            } else if (o instanceof Number) {
                sb.append(formatNumber((Number) o)).append(", ");
            } else if (o instanceof List) {
                sb.append(formatList((List) o)).append(", ");
            } else {
                sb.append(o.toString());
            }
        }
        sb.append("]");
        String result = sb.toString();
        return result.replace(", ]", "]");
    }
}
