package de.fabidark.clansystem.files;

import de.fabidark.clansystem.ClanSystem;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by Fabian on 11.01.2016.
 */
public class ConfigFile {

    private static String ClanMemberDamage;

    public static File getConfigFile() {
        return new File(ClanSystem.getInstance().getDataFolder(), "config.yml");
    }

    public static FileConfiguration getConfigFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getConfigFile());
    }

    public static void setDefaultConfig() {
        if(!getConfigFile().exists()) {
            ClanSystem.getInstance().saveResource("config.yml", false);
        }
    }

    public static void readConfig() {
        FileConfiguration cfg = getConfigFileConfiguration();
        if(cfg == null) return;

        ClanMemberDamage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ClanMemberDamage"));
    }

    public static void setupConfigs() {
        ConfigFile.setDefaultConfig();
        ConfigFile.readConfig();
    }

    public static String getClanMemberDamage() {
        return ClanMemberDamage;
    }
}
