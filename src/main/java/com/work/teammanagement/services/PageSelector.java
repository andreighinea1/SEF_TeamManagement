package com.work.teammanagement.services;

import com.work.teammanagement.Main;

import java.net.URL;

public final class PageSelector {
    public static URL selectPage(Class<?> _class, String pageName) {
        URL resource;
        if ((resource = _class.getResource(pageName + ".fxml")) != null)
            return resource;

        if (LoggedInUser.isLoggedInAsManager())
            resource = _class.getResource(pageName + "-manager.fxml");
        else if (LoggedInUser.isLoggedInAsEmployee())
            resource = _class.getResource(pageName + "-employee.fxml");

        if (resource == null)
            throw new RuntimeException(String.format("Page '%s.fxml' not found", pageName));
        return resource;
    }

    public static URL selectPage(String pageName) {
        return selectPage(Main.class, pageName);
    }
}
