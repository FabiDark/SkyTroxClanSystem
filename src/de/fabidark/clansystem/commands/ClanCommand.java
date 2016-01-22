package de.fabidark.clansystem.commands;

import de.fabidark.clansystem.api.API;
import de.fabidark.clansystem.files.MessageFile;
import de.fabidark.clansystem.messages.MessageHandler;
import de.fabidark.clansystem.messages.MessageType;
import de.fabidark.clansystem.sqlmanager.ClanManager;
import de.fabidark.clansystem.sqlmanager.PlayerManager;
import de.fabidark.clansystem.utils.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Fabian on 16.01.2016.
 */

public class ClanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage("Du darfst diesen Command nur Ingame nutzen!");
            return true;
        }

        Player p = (Player) commandSender;

        API api = new API(p);

        if(command.getName().equalsIgnoreCase("clan")) {

            if (strings.length == 0) {
                api.Help1();
                return true;
            }

            if (strings[0].equalsIgnoreCase("toggle")) {
                if (strings.length == 1) {
                    api.setToggle();
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_TOGGLE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("2")) {
                api.Help2();
            }

            if (strings[0].equalsIgnoreCase("verlassen")) {
                if (strings.length == 1) {
                    api.leaveClan();
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_LEAVE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("löschen")) {
                if (strings.length == 1) {
                    api.ClanDelete();
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_DELETE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("einladungen")) {
                if (strings.length == 1) {
                    api.getInvites();
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_INVITES.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("stats")) {
                if (strings.length == 1) {
                    try {
                        api.getClanStats(PlayerManager.getClan(UUIDFetcher.getUUIDOf(p.getName()).toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (strings.length == 2) {
                    String clan = strings[1];
                    api.getClanStats(clan);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_STATS_ANOTHER_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("mitglieder")) {
                if (strings.length == 1) {
                    try {
                        api.getClanMitglieder(PlayerManager.getClan(UUIDFetcher.getUUIDOf(p.getName()).toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (strings.length == 2) {
                    String clan = strings[1];
                    api.getClanMitglieder(clan);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_MEMBERS_ANTHOER_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("einladen")) {
                if (strings.length == 1) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_INVITE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                } else if (strings.length == 2) {
                    Player Target = Bukkit.getPlayer(strings[1]);
                    api.invitePlayer(Target);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_INVITE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("beitreten")) {
                if (strings.length == 1) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_JOIN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                } else if (strings.length == 2) {
                    String clan = strings[1];
                    api.joinClan(clan);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_JOIN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("kick")) {
                if (strings.length == 1) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_KICK.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                } else if (strings.length == 2) {
                    String playername = strings[1];
                    try {
                        api.kickPlayer(Bukkit.getOfflinePlayer(UUIDFetcher.getUUIDOf(playername)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_KICK.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("erstellen")) {
                if (strings.length == 1) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_CREATE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                } else if (strings.length == 2) {
                    String name = strings[1];
                    try {
                        api.createClan(name, UUIDFetcher.getUUIDOf(p.getName()).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_CREATE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("setbase")) {
                if (strings.length == 1) {
                    api.setBase();
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_SETBASE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("base")) {
                if (strings.length == 1) {
                    api.gotoBase();
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_BASE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("promote")) {
                if (strings.length == 1) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_PROMOTE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                } else if (strings.length == 2) {
                    Player Target = Bukkit.getPlayer(strings[1]);
                    api.onPromote(Target);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_PROMOTE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("demote")) {
                if (strings.length == 1) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_DEMOTE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                } else if (strings.length == 2) {
                    Player Target = Bukkit.getPlayer(strings[1]);
                    api.onDemote(Target);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_DEMOTE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if(strings[0].equalsIgnoreCase("ustats")) {
                if(strings.length == 1) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_USTATS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                } else if(strings.length == 2) {
                    Player Target = Bukkit.getPlayer(strings[1]);
                    api.getUserStats(Target);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_USTATS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if(strings[0].equalsIgnoreCase("umitglieder")) {
                if(strings.length == 1) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_UMEMBERS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                } else if(strings.length == 2) {
                    Player Target = Bukkit.getPlayer(strings[1]);
                    api.getUserMembers(Target);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_UMEMBERS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }

            if (strings[0].equalsIgnoreCase("blacklist")) {
                if (strings[1].equalsIgnoreCase("add")) {
                    api.addBlockedClanname(strings[2]);
                } else if (strings[1].equalsIgnoreCase("remove")) {
                    api.removeBlockedClanname(strings[2]);
                } else {
                    p.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_BLACKLIST.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
            }
        }
        if(command.getName().equalsIgnoreCase("c")) {
            try {
                if(!PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(p.getName()).toString())) {
                    p.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    return true;
                }
                for(Player players : Bukkit.getOnlinePlayers()) {
                    if(ClanManager.getAllMitglieder(PlayerManager.getClan(UUIDFetcher.getUUIDOf(p.getName()).toString())).contains(UUIDFetcher.getUUIDOf(players.getName()).toString())) {
                        StringBuilder text = new StringBuilder();
                        for(int i = 0; i < strings.length; i++) {
                            text.append(strings[i]).append(" ");
                        }
                        String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_CHAT.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                        msg = msg.replaceAll("%CLAN%", PlayerManager.getClan(UUIDFetcher.getUUIDOf(p.getName()).toString()));
                        msg = msg.replaceAll("%PLAYER%", p.getName());
                        msg = msg.replaceAll("%RANK%", PlayerManager.getClanrank(UUIDFetcher.getUUIDOf(p.getName()).toString()));
                        msg = msg.replaceAll("%MSG%", text.toString());
                        players.sendMessage(msg);
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
