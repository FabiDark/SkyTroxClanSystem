package de.fabidark.clansystem.listener;

import de.fabidark.clansystem.sqlmanager.ClanManager;
import de.fabidark.clansystem.sqlmanager.PlayerManager;
import de.fabidark.clansystem.sqlmanager.StatsManager;
import de.fabidark.clansystem.utils.UUIDFetcher;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

/**
 * Created by Fabian on 16.01.2016.
 */

public class DeathListener implements Listener {

    @EventHandler
    public void on(PlayerDeathEvent e) {

        if(e.getEntity().getKiller() instanceof Player) {

                try {
                    Player killer = e.getEntity().getKiller();

                    if(PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(killer.getName()).toString())) {
                        StatsManager.addKills(PlayerManager.getClan(UUIDFetcher.getUUIDOf(killer.getName()).toString()));
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        if(e.getEntity().getPlayer() instanceof Player) {
            try {
                Player death = e.getEntity().getPlayer();
                if (PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(death.getName()).toString())) {
                    StatsManager.addDeaths(PlayerManager.getClan(UUIDFetcher.getUUIDOf(death.getName()).toString()));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

}
