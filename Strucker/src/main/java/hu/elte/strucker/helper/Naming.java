package hu.elte.strucker.helper;

import hu.elte.strucker.model.project.Project;

public class Naming {
    public static String qualifiedPath(Project project) {
        return project.getLocation() + "/" + project.getName() + ".strucker";
    }
}
