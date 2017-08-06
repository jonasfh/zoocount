/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dogp.zoocount;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

/**
 * The Settings object is more or less just a wrapper around the
 * java.util.prefs.Preferences - object.
 *
 * @author jonass
 */
public class Settings {
    private static Settings settings;
    private Preferences options;

    /**
     * Get an instance of the settings, implemented using
     * java.util.prefs.Preferences. The implementation makes sure there is only
     * one instance of the settings object, using the singleton pattern.
     *
     * @return the Settings object
     */
    public static synchronized Settings getInstance() {
        if (settings == null)
            settings=new Settings();
        return settings;
    }

    /**
     * Set an option.
     *
     * @param key option key
     * @param value option value
     */
    public void setOption(String key, String value){
        options.put(key, value);
    }

    /**
     * Get an option
     *
     * @param key
     * @return
     */
    public String getOption(String key){
        return getOption(key, "");
    }

    /**
     * Get an option, providing a default value if not set.
     *
     * @param key
     * @param defaultval
     * @return
     */
    public String getOption(String key, String defaultval){
        return options.get(key, defaultval);
    }

    private Settings() {
        try {
            options = Preferences.userRoot().node(getClass().getName());

            // Set some defaults
            if ("".equals(options.get("csv.separator", ""))) {
                this.setOption("csv.separator", ",");
                this.setOption("csv.quote", "\"");
                options.putBoolean("general.saveAllValues", false);
            }
            options.flush();
        } catch (BackingStoreException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Overriding the toString - method for this object, returning the string
     * created by java.util.prefs.Preferences exportSubtree - method. This is
     * used to allow the user to edit the preferences by hand.
     *
     * @return an xml with the current preferences.
     */
    @Override
    public String toString() {
        try {
            OutputStream string = new OutputStream(){
                StringBuilder s = new StringBuilder();
                @Override
                public void write(int b) throws IOException {
                    s.append((char)b);
                }

                @Override
                public String toString() {
                    return s.toString();
                }
            };
            options.exportSubtree(string);
            return string.toString();
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BackingStoreException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * Import an xml-string of preferences in the format that
     * java.util.prefs.Preferences requires.
     *
     * @param s
     */
    public void importPreferences(String s) {
        try {
            InputStream stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
            Preferences.importPreferences(stream);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidPreferencesFormatException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
