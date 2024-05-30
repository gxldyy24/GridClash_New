package edu.skidmore.cs326.game.sudoku.logic;
import java.util.HashMap;
import java.util.Map;

import edu.skidmore.cs326.game.sudoku.frontend.WindowData;

/**
 * Manages different themes for the application UI.
 */
public class ThemeShop {
	
/**
 * Manages themes.
 */
    private Map<String, Theme> themes;
    /**
     * Manages themes.
     */
    private String currentTheme;

    /**
     * Constructs a ThemeShop and initializes it with a default theme.
     */
    public ThemeShop() {
        themes = new HashMap<>();
        currentTheme = "default";
        // Initialize with a default theme
        themes.put(currentTheme, new Theme(WindowData.getBackgroundColor(),
        		WindowData.getTextColor(), WindowData.getFont()));
    }

    /**
     * Adds a new theme to the collection.
     * @param name the name of the theme
     * @param theme the Theme object
     */
    public void addTheme(String name, Theme theme) {
        themes.put(name, theme);
    }

    /**
     * Retrieves a theme by its name.
     * @param name the name of the theme to retrieve
     * @return the Theme object
     */
    public Theme getTheme(String name) {
        return themes.get(name);
    }

    /**
     * Switches to a different theme if it exists in the collection.
     * @param name the name of the theme to switch to
     */
    public void switchTheme(String name) {
        if (themes.containsKey(name)) {
            currentTheme = name;
            // Apply theme settings to all UI elements
            applyThemeSettings();
        }
    }

    /**
     * Applies the current theme's settings to UI elements.
     */
    private void applyThemeSettings() {
        @SuppressWarnings("unused")
		Theme theme = themes.get(currentTheme);
        // Assuming methods to apply these settings to the UI components
        //WindowData.setBackgroundColor(theme.getBackgroundColor());
       // WindowData.setTextColor(theme.getTextColor());
        //WindowData.setFont(theme.getFont());
    }

    /**
     * Returns the name of the current theme.
     * @return the current theme name
     */
    public String getCurrentThemeName() {
        return currentTheme;
    }
}
