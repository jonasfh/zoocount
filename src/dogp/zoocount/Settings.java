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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

/**
 *
 * @author jonas
 */
public class Settings {
    private static Settings settings;
    private Preferences options;

    public static synchronized Settings getInstance() {
        if (settings == null)
            settings=new Settings();
        return settings;
    }
    public void setOption(String key, String value){
        options.put(key, value);
    }

    public String getOption(String key){
        return getOption(key, "");
    }
    public String getOption(String key, String defaultval){
        return options.get(key, defaultval);
    }
    private Settings() {
        options = Preferences.userRoot().node(this.getClass().getName());
    }
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
