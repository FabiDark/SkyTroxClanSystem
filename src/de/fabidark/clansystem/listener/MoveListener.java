package de.fabidark.clansystem.listener;

import de.fabidark.clansystem.ClanSystem;
import de.fabidark.clansystem.files.MessageFile;
import de.fabidark.clansystem.messages.MessageHandler;
import de.fabidark.clansystem.messages.MessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Fabian on 12.01.2016.
 */

public class MoveListener implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent e) {
        try {
            if(ClanSystem.getInstance().saveteleportPos.containsKey(e.getPlayer().getName())) {
                if(ClanSystem.getInstance().saveteleportPos.get(e.getPlayer().getName()).getBlockX() != e.getPlayer().getLocation().getBlockX()) {
                    ClanSystem.getInstance().saveteleportPos.remove(e.getPlayer().getName());
                    cancelTpTimer(e.getPlayer());
                }
                if(ClanSystem.getInstance().saveteleportPos.get(e.getPlayer().getName()).getBlockZ() != e.getPlayer().getLocation().getBlockZ()) {
                    ClanSystem.getInstance().saveteleportPos.remove(e.getPlayer().getName());
                    cancelTpTimer(e.getPlayer());
                }
            }
        } catch (Exception ex) {
        }
    }

    public void cancelTpTimer(Player p) {
        ClanSystem.getInstance().task.cancel();
        p.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_CLANBASE_TP_CANCELED.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
    }

}
