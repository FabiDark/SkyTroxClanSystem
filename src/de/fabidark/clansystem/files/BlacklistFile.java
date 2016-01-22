package de.fabidark.clansystem.files;

import de.fabidark.clansystem.ClanSystem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 16.01.2016.
 */

public class BlacklistFile {

    public static List<String> words = new ArrayList<String>();

    public static File getBlacklistFile() {
        return new File(ClanSystem.getInstance().getDataFolder(), "blacklist.yml");
    }

    public static FileConfiguration getBlacklistFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getBlacklistFile());
    }

    public static void setDefault() {
        if(!getBlacklistFile().exists()) {
            ClanSystem.getInstance().saveResource("blacklist.yml", false);
        }
    }

    public static void readFile() {
        FileConfiguration cfg = getBlacklistFileConfiguration();
        if (cfg == null) return;

        words = cfg.getStringList("deniedClannames");
    }

    public static void setupConfigs() {
        BlacklistFile.readFile();
        BlacklistFile.setDefault();
    }

}
