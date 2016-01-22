package de.fabidark.clansystem;

import de.fabidark.clansystem.commands.ClanCommand;
import de.fabidark.clansystem.files.BlacklistFile;
import de.fabidark.clansystem.files.ConfigFile;
import de.fabidark.clansystem.files.MessageFile;
import de.fabidark.clansystem.files.SQLFile;
import de.fabidark.clansystem.listener.DamageListener;
import de.fabidark.clansystem.listener.DeathListener;
import de.fabidark.clansystem.listener.JoinListener;
import de.fabidark.clansystem.listener.MoveListener;
import de.fabidark.clansystem.sql.SQL;
import de.fabidark.clansystem.utils.RegisterManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

/**
 * Created by Fabian on 11.01.2016.
 */

public class ClanSystem extends JavaPlugin {

    static ClanSystem instance;

    public BukkitTask task;

    public HashMap<String, Location> saveteleportPos = new HashMap<String, Location>();

    public String lang = "German";

    private RegisterManager<ClanSystem> registerManager;

    @Override
    public void onLoad() {
        instance = this;
        registerManager = new RegisterManager<ClanSystem>(this);
    }

    @Override
    public void onEnable() {
        setupServerStart();
    }

    @Override
    public void onDisable() {
        SQL.disconnect();
    }

    private void setupServerStart() {

        SQLFile.setupConfigs();
        SQL.connect();
        ConfigFile.setupConfigs();
        MessageFile.setupConfigs();
        BlacklistFile.setupConfigs();

        getServer().getConsoleSender().sendMessage("[ClanSystem] ----------------------------");
        getServer().getConsoleSender().sendMessage("[ClanSystem] MySQL-Verbindung: " + (SQL.isConnected() ? "Verbunden" : "Getrennt!"));
        getServer().getConsoleSender().sendMessage("[ClanSystem] ConfigFile: " + (ConfigFile.getConfigFile().exists() ? "Geladen" : "Nicht Geladen!"));
        getServer().getConsoleSender().sendMessage("[ClanSystem] MessageFile: " + (MessageFile.getMessageFile().exists() ? "Geladen" : "Nicht Geladen!"));
        getServer().getConsoleSender().sendMessage("[ClanSystem] Plugin by: FabiDark");
        getServer().getConsoleSender().sendMessage("[ClanSystem] ----------------------------");

        registerManager.registerCommand("clan", "", new ClanCommand());
        registerManager.registerCommand("c", "", new ClanCommand());
        registerManager.registerEvents(MoveListener.class);
        registerManager.registerEvents(DamageListener.class);
        registerManager.registerEvents(DeathListener.class);
        registerManager.registerEvents(JoinListener.class);

    }

    public static ClanSystem getInstance() {
        return instance;
    }
}
