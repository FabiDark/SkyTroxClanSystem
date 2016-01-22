package de.fabidark.clansystem.files;

import de.fabidark.clansystem.ClanSystem;
import de.fabidark.clansystem.sql.SQL;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Fabian on 11.01.2016.
 */

public class SQLFile {

    // MySQL File
    public static File getMySQLFile() {
        return new File(ClanSystem.getInstance().getDataFolder(), "mysql.yml");
    }
    public static FileConfiguration getMySQLFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getMySQLFile());
    }

    //MySQL File setup
    public static void setDefaultMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();
        cfg.options().copyDefaults(true);
        cfg.addDefault("username", "root");
        cfg.addDefault("passwort", "");
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");
        cfg.addDefault("database",  "minecraft_db");
        try {
            cfg.save(getMySQLFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();
        SQL.username = cfg.getString("username");
        SQL.password = cfg.getString("passwort");
        SQL.host = cfg.getString("host");
        SQL.port = cfg.getString("port");
        SQL.database = cfg.getString("database");
    }

    // Setup all Configs
    public static void setupConfigs() {
        SQLFile.setDefaultMySQL();
        SQLFile.readMySQL();
    }

}
