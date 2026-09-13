// Compile-only API declaration. Not included in the runtime host.
package com.orangepixel.spacegrunts;

public class DesktopPreferences implements com.orangepixel.utils.OrangePreferences {
    public String getDefaultPreferencesDirectory() { throw new UnsupportedOperationException(); }
    public com.badlogic.gdx.Files.FileType getDefaultPreferencesFileType() { throw new UnsupportedOperationException(); }
    public com.badlogic.gdx.Preferences getPreferences(String name, boolean forceCreation) { throw new UnsupportedOperationException(); }
}
