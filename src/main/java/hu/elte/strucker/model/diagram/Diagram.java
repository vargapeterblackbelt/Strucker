package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.HealthCheck;
import hu.elte.strucker.model.interpretation.Parameter;
import hu.elte.strucker.model.interpretation.parsed.ParsedStructogram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.recognizer.ExecuteException;
import hu.elte.strucker.recognizer.Expression;
import hu.elte.strucker.recognizer.Identifier;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hu.elte.strucker.model.HealthCheck.OK;
import static hu.elte.strucker.model.HealthCheck.UNKNOWN;
import static hu.elte.strucker.view.editor.ViewProperties.*;

@Getter
@Setter
public class Diagram extends Sequence {

    @JsonIgnore
    private Library library;

    private String name;

    private Class type;

    private List<Parameter> parameters;

    @JsonIgnore
    private HealthCheck healthCheck = UNKNOWN;

    @JsonIgnore
    private List<Map<String, Identifier>> scopes = new ArrayList<>();

    @JsonCreator
    public Diagram(@JsonProperty("name") String name,
                   @JsonProperty("type") Class type,
                   @JsonProperty("parameters") List<Parameter> parameters,
                   @JsonProperty("sequence") List<Structogram> sequence) {
        super(sequence);
        this.name = name;
        this.parameters = parameters;
        this.type = type;
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int balanceHeight, Font font, boolean capture) {
        String signature = getSignature();
        int headWidth = textWidth(signature, font);
        int textHeight = textHeight(font);
        int headX = x, stgX = x;
        if (headWidth < width) {
            headX = x + width / 2 - headWidth / 2;
        } else {
            stgX = x + headWidth / 2 - width / 2;
        }
        g.setColor(isHovered() && !capture ? DEFAULT_SIGNATURE_HOVER_COLOR : DEFAULT_SIGNATURE_COLOR);
        if (!capture)
            detectionArea = new Rectangle(headX, y, headWidth, textHeight);
        g.fillRoundRect(headX, y, headWidth, textHeight, 12, 12);
        g.setColor(DEFAULT_FONT_COLOR);
        g.drawRoundRect(headX, y, headWidth, textHeight, 10, 10);
        drawTextCentered(signature, headX, y, headWidth, textHeight, g, font);
        g.drawLine(headX + headWidth / 2, y + textHeight, headX + headWidth / 2, y + textHeight + font.getSize());
        super.draw(g, stgX, y + textHeight + font.getSize(), width, balanceHeight, font, capture);
    }

    @Override
    public void hover(boolean isHovered) {
        setHovered(isHovered);
        super.hover(isHovered);
    }

    @JsonIgnore
    public String getSignature() {
        StringBuilder params = new StringBuilder();

        for (Parameter parameter : parameters) {
            if (parameters.indexOf(parameter) != 0) {
                params.append(", ");
            }
            params.append(parameter.getName()).append(":").append(parameter.getType().getSimpleName());
        }

        return name + "(" + params.toString() + ") : " + type.getSimpleName();
    }

    @Override
    public Structogram in(int x, int y) {
        if (detectionArea != null && detectionArea.contains(x, y)) {
            return this;
        }
        return super.in(x, y);
    }

    @JsonIgnore
    public Dimension getImageSize(Font font) {
        int width = Math.max(textWidth(getSignature(), font), widthNeeded(font)) + 1;
        int height = heightNeeded(font) + 2 * textHeight(font);
        return new Dimension(width, height);
    }

    @JsonIgnore
    public String getFullName() {
        return library.getName() + "::" + name;
    }

    private Map<String, Identifier> setupScope() {
        Map<String, Identifier> scope = new HashMap<>();
        for (Parameter parameter : parameters) {
            scope.put(parameter.getName(), new Identifier(parameter.getName(), parameter.getType(), null));
        }
        for (Diagram diagram : library.getProject().getDiagrams()) {
            scope.put(diagram.getFullName(), new Identifier(diagram.getFullName(), Diagram.class, diagram));
        }
        scope.put("return", new Identifier("return", getType(), null));
        return scope;
    }

    @SuppressWarnings("unchecked")
    public <T> T eval(Class<T> type) throws Exception {
        scopes.add(setupScope());
        ParsedStructogram parsedStructogram = super.parse(this);
        parsedStructogram.execute();
        Identifier aReturn = getCurrentScope().get("return");
        scopes.remove(scopes.size() - 1);
        return (T) aReturn.getValue();
    }

    @JsonIgnore
    public Map<String, Identifier> getCurrentScope() {
        return scopes.get(scopes.size() - 1);
    }

    public <T> T eval(Class<T> type, List<Expression> params) throws Exception {
        scopes.add(setupScope());
        for (int i = 0; i < params.size(); i++) {
            try {
                getCurrentScope().replace(parameters.get(i).getName(), new Identifier(parameters.get(i).getName(), params.get(i).getType(), params.get(i).eval()));
            } catch (ExecuteException e) {
                e.printStackTrace();
            }
        }
        ParsedStructogram parsedStructogram = super.parse(this);
        try {
            parsedStructogram.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Identifier aReturn = getCurrentScope().get("return");
        //noinspection unchecked
        scopes.remove(scopes.size() - 1);
        return (T) aReturn.getValue();
    }

    public HealthCheck check() {
        healthCheck = OK;
        scopes.add(setupScope());
        super.parse(this);
        return healthCheck;
    }

//    public Map<String, Identifier> getScope() {
//        Map<String, Identifier> map = new HashMap<>();
//        for (String s : this.scope.keySet()) {
//            Identifier identifier = scope.get(s);
//            map.put(s, new Identifier(identifier.getName(), identifier.getType(), identifier.getValue()));
//        }
//        return map;
//    }

}
