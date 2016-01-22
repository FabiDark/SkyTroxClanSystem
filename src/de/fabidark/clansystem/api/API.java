package de.fabidark.clansystem.api;

import de.fabidark.clansystem.ClanSystem;
import de.fabidark.clansystem.enums.Clanranks;
import de.fabidark.clansystem.files.BlacklistFile;
import de.fabidark.clansystem.files.MessageFile;
import de.fabidark.clansystem.messages.MessageHandler;
import de.fabidark.clansystem.messages.MessageType;
import de.fabidark.clansystem.sqlmanager.BaseManager;
import de.fabidark.clansystem.sqlmanager.ClanManager;
import de.fabidark.clansystem.sqlmanager.PlayerManager;
import de.fabidark.clansystem.sqlmanager.StatsManager;
import de.fabidark.clansystem.utils.UUIDFetcher;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Fabian on 12.01.2016.
 */

public class API {

    public static Player player;

    public API(Player player) {
        this.player = player;
    }

    public static void addtPlayerToSQL() {
        try {
            if (!PlayerManager.IsInSQL(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                PlayerManager.addNewPlayer(UUIDFetcher.getUUIDOf(player.getName()).toString(), player.getName());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createClan(String clanname, String LeaderUUID) {
        try {
            if (ClanManager.IsInSQL(clanname)) {
                String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_ALREADY_EXISTS.getCode());
                msg = msg.replaceAll("%CLAN%", clanname);
                msg = msg.replaceAll("%PREFIX%", MessageFile.getPrefix());
                player.sendMessage(msg);
                return;
            }
            if(PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_ALREADY_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if (clanname.contains("!") || clanname.contains("$") || clanname.contains("&") || clanname.contains("%") || clanname.contains("/") || clanname.contains("(") || clanname.contains(")") || clanname.contains("=") || clanname.contains("?") || clanname.contains("{") || clanname.contains("[") || clanname.contains("]") || clanname.contains("}")) {
                String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_INVALID_CHARS.getCode());
                msg = msg.replaceAll("%CHARS", "!$%&/()={[]}");
                msg = msg.replaceAll("%PREFIX%", MessageFile.getPrefix());
                player.sendMessage(msg);
                return;
            }
            if (clanname.length() > 16) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_CLAN_NAME_TOO_LONG.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            for(String words : BlacklistFile.getBlacklistFileConfiguration().getStringList("deniedClannames")) {
                if(words.equalsIgnoreCase(clanname)) {
                    String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_ON_BLACKLIST.getCode());
                    msg = msg.replaceAll("%CLAN%", clanname);
                    msg = msg.replaceAll("%PREFIX%", MessageFile.getPrefix());
                    player.sendMessage(msg);
                    return;
                }
            }
            PlayerManager.setToggle(UUIDFetcher.getUUIDOf(player.getName()).toString(), true);
            PlayerManager.clearInvites(UUIDFetcher.getUUIDOf(player.getName()).toString());
            PlayerManager.setClan(UUIDFetcher.getUUIDOf(player.getName()).toString(), clanname);
            PlayerManager.setClanrank(UUIDFetcher.getUUIDOf(player.getName()).toString(), Clanranks.LEADER);
            ClanManager.addNewClan(clanname, UUIDFetcher.getUUIDOf(player.getName()).toString(), UUIDFetcher.getUUIDOf(player.getName()).toString());
            StatsManager.addNewClanStats(clanname);
            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_SUCCESSFUL_CREATE.getCode());
            msg = msg.replaceAll("%CLAN%", clanname);
            msg = msg.replaceAll("%PREFIX%", MessageFile.getPrefix());
            player.sendMessage(msg);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leaveClan() {
        try {
            if(!PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NOT_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(player.getName()).toString()) >= Clanranks.LEADER.getPriority()) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_LEAVE_CLAN_AS_LEADER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(PlayerManager.getClan(UUIDFetcher.getUUIDOf(players.getName()).toString()).equals(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                    if(players != player) {
                        String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_LEAVE_SEND_TO_ALL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                        msg = msg.replaceAll("%TARGET%", player.getName());
                        msg = msg.replaceAll("%PREFIX%", MessageFile.getPrefix());
                        players.sendMessage(msg);
                    }
                }
            }
            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_LEAVE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg = msg.replaceAll("%CLAN%", PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
            msg = msg.replaceAll("%PREFIX%", MessageFile.getPrefix());
            player.sendMessage(msg);
            PlayerManager.setToggle(UUIDFetcher.getUUIDOf(player.getName()).toString(), false);
            ClanManager.removeMitglied(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), player.getName());
            PlayerManager.setClan(UUIDFetcher.getUUIDOf(player.getName()).toString(), "null");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void joinClan(String clanname) {
        try {
            if (PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_ALREADY_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.getInvites(UUIDFetcher.getUUIDOf(player.getName()).toString()) == null) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_INVITE_NOT_INVITED.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.getAllInvite(UUIDFetcher.getUUIDOf(player.getName()).toString()).contains(clanname)) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_INVITE_NOT_INVITED.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            PlayerManager.setToggle(UUIDFetcher.getUUIDOf(player.getName()).toString(), true);
            PlayerManager.setClan(UUIDFetcher.getUUIDOf(player.getName()).toString(), clanname);
            PlayerManager.setClanrank(UUIDFetcher.getUUIDOf(player.getName()).toString(), Clanranks.MEMBER);
            PlayerManager.clearInvites(UUIDFetcher.getUUIDOf(player.getName()).toString());
            ClanManager.addMitglied(clanname, UUIDFetcher.getUUIDOf(player.getName()).toString());
            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_JOIN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg = msg.replaceAll("%CLAN%", clanname);
            msg = msg.replaceAll("%PREFIX%", MessageFile.getPrefix());
            player.sendMessage(msg);
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(PlayerManager.getClan(UUIDFetcher.getUUIDOf(players.getName()).toString()).equals(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                    if(players != player) {
                        String msg2 = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_JOIN_SEND_TO_ALL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                        msg2 = msg2.replaceAll("%TARGET%", player.getName());
                        msg2 = msg2.replaceAll("%PREFIX%", MessageFile.getPrefix());
                        players.sendMessage(msg2);
                    }
                }
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void kickPlayer(OfflinePlayer Target) {
        try {
            if(Target == player) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_IS_PLAYER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.IsInSQL(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.getClan(UUIDFetcher.getUUIDOf(Target.getName()).toString()).equals(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(player.getName()).toString()) >= Clanranks.LEADER.getPriority())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NO_PERMISSION.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            PlayerManager.setClan(UUIDFetcher.getUUIDOf(Target.getName()).toString(), "null");
            PlayerManager.setToggle(UUIDFetcher.getUUIDOf(Target.getName()).toString(), false);
            PlayerManager.setClanrank(UUIDFetcher.getUUIDOf(Target.getName()).toString(), Clanranks.MEMBER);
            ClanManager.removeMitglied(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), UUIDFetcher.getUUIDOf(Target.getName()).toString());
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(PlayerManager.getClan(UUIDFetcher.getUUIDOf(players.getName()).toString()).equals(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                    if(players != player) {
                        if(players != Target)  {
                            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_KICK_SEND_TO_ALL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                            msg = msg.replaceAll("%KICKER%", player.getName());
                            msg = msg.replaceAll("%TARGET%", Target.getName());
                            msg = msg.replaceAll("%CLAN%", PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
                            players.sendMessage(msg);
                        }
                    }
                }
            }
            if(Target != null) {
                Player pp = Bukkit.getPlayer(Target.getName());
                String msg2 = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_KICK_SEND_TO_TARGET.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                msg2 = msg2.replaceAll("%KICKER%", player.getName());
                msg2 = msg2.replaceAll("%CLAN%", PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
                pp.sendMessage(msg2);
            }
            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_KICK_SEND_TO_KICKER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg = msg.replaceAll("%TARGET%", Target.getName());
            msg = msg.replaceAll("%CLAN%", PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
            player.sendMessage(msg);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBase() {
        try {
            if(PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                if (ClanManager.isLeader(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                    if(!BaseManager.IsInSQL(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                        BaseManager.addNewBases(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                        String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_BASE_SET.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                        player.sendMessage(msg);
                        return;
                    } else {
                        BaseManager.setBaseX(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), player.getLocation().getX());
                        BaseManager.setBaseY(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), player.getLocation().getY());
                        BaseManager.setBaseZ(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), player.getLocation().getZ());
                        BaseManager.setBaseYaw(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), player.getLocation().getYaw());
                        BaseManager.setBasePitch(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()), player.getLocation().getPitch());
                        String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_BASE_SET.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                        player.sendMessage(msg);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gotoBase() {
        try {
            Location loc = new Location(player.getWorld(), BaseManager.getBaseX(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString())), BaseManager.getBaseY(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString())), BaseManager.getBaseZ(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString())), BaseManager.getBaseYaw(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString())), BaseManager.getBasePitch(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString())));
            if(!BaseManager.IsInSQL(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_CLANBASE_NOT_IN_SQL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            ClanSystem.getInstance().saveteleportPos.put(player.getName(), player.getLocation());
            player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_CLAN_BASE_TP_TIMER_START.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));

            ClanSystem.getInstance().task = Bukkit.getScheduler().runTaskLater(ClanSystem.getInstance(), new Runnable() {
                @Override
                public void run() {
                    player.teleport(loc);
                    player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_CLAN_BASE_TP.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                    ClanSystem.getInstance().saveteleportPos.remove(player.getName());
                }
            }, 20*3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onPromote(Player Target) {
        try {
            if(!PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NOT_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.getClan(UUIDFetcher.getUUIDOf(Target.getName()).toString()).equals(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(Target == player) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NOT_PROMOTE_YOURSELF.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(player.getName()).toString()) >= Clanranks.LEADER.getPriority())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NO_PERMISSION.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(Target.getName()).toString()) == Clanranks.MOD.getPriority()) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_PROMOTE_TARGET_HAS_HIGHEST_RANG.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(Target == null) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_ONLINE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(ClanManager.getAllMitglieder(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString())).contains(UUIDFetcher.getUUIDOf(players.getName()).toString())) {
                    if(players != Target) {
                        if(players != player) {
                            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_ALL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                            msg = msg.replaceAll("%TARGET%", Target.getName());
                            msg = msg.replaceAll("%PROMOTER%", player.getName());
                            players.sendMessage(msg);
                        }
                    }
                }
            }
            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_PROMOTER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg = msg.replaceAll("%TARGET%", Target.getName());
            String msg2 = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_TARGET.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg2 = msg2.replaceAll("%PROMOTER%", player.getName());
            player.sendMessage(msg);
            Target.sendMessage(msg2);
            PlayerManager.setClanrank(UUIDFetcher.getUUIDOf(Target.getName()).toString(), Clanranks.MOD);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onDemote(Player Target) {
        try {
            if(!PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.getClan(UUIDFetcher.getUUIDOf(Target.getName()).toString()).equals(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(Target == player) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NOT_DEMOTE_YOURSELF.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(player.getName()).toString()) >= Clanranks.LEADER.getPriority())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NO_PERMISSION.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(Target.getName()).toString()) == Clanranks.LEADER.getPriority()) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_IS_LEADER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(Target.getName()).toString()) == Clanranks.MEMBER.getPriority()) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_DEMOTE_TARGET_HAS_LOWEST_RANG.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(Target == null) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_ONLINE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(ClanManager.getAllMitglieder(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString())).contains(UUIDFetcher.getUUIDOf(players.getName()).toString())) {
                    if(players != Target) {
                        if(players != player) {
                            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_ALL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                            msg = msg.replaceAll("%TARGET%", Target.getName());
                            msg = msg.replaceAll("%DEMOTER%", player.getName());
                            players.sendMessage(msg);
                        }
                    }
                }
            }
            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_DEMOTER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg = msg.replaceAll("%TARGET%", Target.getName());
            String msg2 = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_TARGET.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg2 = msg2.replaceAll("%DEMOTER%", player.getName());
            player.sendMessage(msg);
            Target.sendMessage(msg2);
            PlayerManager.setClanrank(UUIDFetcher.getUUIDOf(Target.getName()).toString(), Clanranks.MEMBER);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBlockedClanname(String name) {

        try {
            if(!player.hasPermission("clansystem.blacklist.add")) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NO_PERMISSION.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            name = name.toLowerCase();
            if(!BlacklistFile.words.contains(name)) {
                FileConfiguration cfg = BlacklistFile.getBlacklistFileConfiguration();
                BlacklistFile.words.add(name);
                cfg.set("deniedClannames", BlacklistFile.words);
                cfg.save(BlacklistFile.getBlacklistFile());
                String msg = MessageHandler.getMessage(MessageType.MESSAGE_BLACKLIST_ADD.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                msg = msg.replaceAll("%NAME%", name);
                player.sendMessage(msg);
                return;
            } else {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_BLACKLIST_ADD_NAME_ALREADY_CONTAINS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeBlockedClanname(String name) {
        try {
            if(!player.hasPermission("clansystem.blacklist.remove")) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NO_PERMISSION.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            name = name.toLowerCase();
            if(BlacklistFile.words.contains(name)) {
                FileConfiguration cfg = BlacklistFile.getBlacklistFileConfiguration();
                BlacklistFile.words.remove(name);
                cfg.set("deniedClannames", BlacklistFile.words);
                cfg.save(BlacklistFile.getBlacklistFile());
                String msg = MessageHandler.getMessage(MessageType.MESSAGE_BLACKLIST_REMOVE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                msg = msg.replaceAll("%NAME%", name);
                player.sendMessage(msg);
                return;
            } else {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_BLACKLIST_REMOVE_NAMR_NOT_CONTAINS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void invitePlayer(Player Target) {
        try {
            if(Target == null) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_ONLINE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.IsInSQL(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_ALREADY_IN_A_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.isToggleOn(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TOGGLE_ON.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(player.getName()).toString()) >= Clanranks.LEADER.getPriority())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NO_PERMISSION.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(Target == player) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NOT_INVITE_YOURSELF.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.getInvites(UUIDFetcher.getUUIDOf(Target.getName()).toString()) == null) {
                PlayerManager.setInvites(UUIDFetcher.getUUIDOf(Target.getName()).toString(), PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
                String msg = MessageHandler.getMessage(MessageType.MESSAGE_INVITE_SEND_TO_TARGET.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                msg = msg.replaceAll("%CLAN%", PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
                msg = msg.replaceAll("%INVITER%", player.getName());
                String msg2 = MessageHandler.getMessage(MessageType.MESSAGE_INVITE_SEND_TO_INVITER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                msg2 = msg2.replaceAll("%TARGET%", Target.getName());
                Target.sendMessage(msg);
                player.sendMessage(msg2);
                return;
            }
            if(PlayerManager.getAllInvite(UUIDFetcher.getUUIDOf(Target.getName()).toString()).contains(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_ALREADY_INVITED.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            String msg = MessageHandler.getMessage(MessageType.MESSAGE_INVITE_SEND_TO_TARGET.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg = msg.replaceAll("%CLAN%", PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
            msg = msg.replaceAll("%INVITER%", player.getName());
            String msg2 = MessageHandler.getMessage(MessageType.MESSAGE_INVITE_SEND_TO_INVITER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg2 = msg2.replaceAll("%TARGET%", Target.getName());
            PlayerManager.setInvites(UUIDFetcher.getUUIDOf(Target.getName()).toString(), PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
            Target.sendMessage(msg);
            player.sendMessage(msg2);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getInvites() {
        try {
            if(PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_ALREADY_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.getInvites(UUIDFetcher.getUUIDOf(player.getName()).toString()) == null) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_INVITE_NO_INVITES.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            player.sendMessage(MessageHandler.getMessage(MessageType.INVITE_HEADER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
            for(String all : PlayerManager.getAllInvite(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(ClanManager.getLeader(all)));
                String msg = MessageHandler.getMessage(MessageType.INVITE_INVITES.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
                msg = msg.replaceAll("%CLAN%", all);
                msg = msg.replaceAll("%CLANLEADER%", op.getName());
                player.sendMessage(msg);
            }
            player.sendMessage(MessageHandler.getMessage(MessageType.INVITE_FOOTER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ClanDelete() {
        try {
            HashMap<String, String> saveclan = new HashMap<String, String>();
            if(!PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NOT_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!(PlayerManager.getClanrankINT(UUIDFetcher.getUUIDOf(player.getName()).toString()) >= Clanranks.LEADER.getPriority())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_NO_PERMISSION.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(saveclan.containsKey(player.getName())) {
                player.sendMessage("§cError! Please Contact a Admin or a Developer!");
                return;
            }
            saveclan.put(player.getName(), PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()));
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(ClanManager.getAllMitglieder(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString())).contains(UUIDFetcher.getUUIDOf(players.getName()).toString())) {
                    if(players != player) {
                        String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_DELETE_SEND_TO_ALL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
                        msg = msg.replaceAll("%DELETER%", player.getName());
                        players.sendMessage(msg);
                    }
                }
            }
            for(String clanPlayers : ClanManager.getAllMitglieder(PlayerManager.getClan(UUIDFetcher.getUUIDOf(player.getName()).toString()))) {
                PlayerManager.setClan(clanPlayers, "null");
                PlayerManager.setToggle(clanPlayers, false);
                PlayerManager.setClanrank(clanPlayers, Clanranks.MEMBER);
            }
            ClanManager.removeClan(saveclan.get(player.getName()));
            BaseManager.removeBase(saveclan.get(player.getName()));
            StatsManager.deleteClan(saveclan.get(player.getName()));
            String msg = MessageHandler.getMessage(MessageType.MESSAGE_CLAN_DELETE_SEND_TO_PLAYER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix());
            msg = msg.replaceAll("%CLAN%", saveclan.get(player.getName()));
            player.sendMessage(msg);
            saveclan.remove(player.getName());
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setToggle() {
        try {
            if(PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_ALREADY_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(PlayerManager.isToggleOn(UUIDFetcher.getUUIDOf(player.getName()).toString())) {
                PlayerManager.setToggle(UUIDFetcher.getUUIDOf(player.getName()).toString(), false);
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_TOGGLE_SET_TO_FALSE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            } else {
                PlayerManager.setToggle(UUIDFetcher.getUUIDOf(player.getName()).toString(), true);
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_TOGGLE_SET_TO_TRUE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getClanStats(String clanname) {
        if(!ClanManager.IsInSQL(clanname)) {
            player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_CLAN_NOT_EXIST.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
            return;
        }

        String Clanname = MessageHandler.getMessage(MessageType.STATS_CLANNAME.getCode());
        Clanname = Clanname.replaceAll("%CLANNAME%", clanname);

        String kills = MessageHandler.getMessage(MessageType.STATS_KILLS.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
        kills = kills.replaceAll("%KILLS%", StatsManager.getKills(clanname) + "");

        String deaths = MessageHandler.getMessage(MessageType.STATS_DEATHS.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
        deaths = deaths.replaceAll("%DEATHS%", StatsManager.getDeaths(clanname) + "");

        String kd = MessageHandler.getMessage(MessageType.STATS_KD.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
        kd = kd.replaceAll("%KD%", myRound(StatsManager.kd(StatsManager.getKills(clanname), StatsManager.getDeaths(clanname)), 2) + "");

        player.sendMessage(MessageHandler.getMessage(MessageType.STATS_HEADER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(Clanname);
        player.sendMessage(kills);
        player.sendMessage(deaths);
        player.sendMessage(kd);
        player.sendMessage(MessageHandler.getMessage(MessageType.STATS_FOOTER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        return;
    }

    public static void getClanMitglieder(String clanname) {
        if(!ClanManager.IsInSQL(clanname)) {
            player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_CLAN_NOT_EXIST.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
            return;
        }

        try {
            player.sendMessage(MessageHandler.getMessage(MessageType.MEMBERS_HEADER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
            String Clanname = MessageHandler.getMessage(MessageType.MEMBERS_CLANNAME.getCode());
            Clanname = Clanname.replaceAll("%CLANNAME%", clanname);
            player.sendMessage(Clanname);
            for (String all : ClanManager.getAllMitglieder(clanname)) {
                OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(all));
                String msg = MessageHandler.getMessage(MessageType.MEMBERS_MEMBERS.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
                msg = msg.replaceAll("%MEMBER%", op.getName());
                msg = msg.replaceAll("%RANK%", (PlayerManager.getClanrankINT(all) == 1 ? "§7Mod" : "") + (PlayerManager.getClanrankINT(all) == 2 ? "§7Leader" : ""));
                player.sendMessage(msg);
            }
            player.sendMessage(MessageHandler.getMessage(MessageType.MEMBERS_FOOTER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getUserStats(Player Target) {
        try {
            if(!PlayerManager.IsInSQL(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_ALREADY_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            String clan = PlayerManager.getClan(UUIDFetcher.getUUIDOf(Target.getName()).toString());

            String Clanname = MessageHandler.getMessage(MessageType.STATS_CLANNAME.getCode());
            Clanname = Clanname.replaceAll("%CLANNAME%", clan);

            String kills = MessageHandler.getMessage(MessageType.STATS_KILLS.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
            kills = kills.replaceAll("%KILLS%", StatsManager.getKills(clan) + "");

            String deaths = MessageHandler.getMessage(MessageType.STATS_DEATHS.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
            deaths = deaths.replaceAll("%DEATHS%", StatsManager.getDeaths(clan) + "");

            String kd = MessageHandler.getMessage(MessageType.STATS_KD.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
            kd = kd.replaceAll("%KD%", myRound(StatsManager.kd(StatsManager.getKills(clan), StatsManager.getDeaths(clan)), 2) + "");

            player.sendMessage(MessageHandler.getMessage(MessageType.STATS_HEADER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
            player.sendMessage(Clanname);
            player.sendMessage(kills);
            player.sendMessage(deaths);
            player.sendMessage(kd);
            player.sendMessage(MessageHandler.getMessage(MessageType.STATS_FOOTER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));

            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getUserMembers(Player Target) {
        try {
            if(!PlayerManager.IsInSQL(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }
            if(!PlayerManager.IsInClan(UUIDFetcher.getUUIDOf(Target.getName()).toString())) {
                player.sendMessage(MessageHandler.getMessage(MessageType.MESSAGE_ERROR_ALREADY_IN_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
                return;
            }

            String clan = PlayerManager.getClan(UUIDFetcher.getUUIDOf(Target.getName()).toString());

            player.sendMessage(MessageHandler.getMessage(MessageType.MEMBERS_HEADER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
            String Clanname = MessageHandler.getMessage(MessageType.MEMBERS_CLANNAME.getCode());
            Clanname = Clanname.replaceAll("%CLANNAME%", clan);
            player.sendMessage(Clanname);
            for (String all : ClanManager.getAllMitglieder(clan)) {
                OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(all));
                String msg = MessageHandler.getMessage(MessageType.MEMBERS_MEMBERS.getCode().replaceAll("%PREFIX%", MessageFile.getPrefix()));
                msg = msg.replaceAll("%MEMBER%", op.getName());
                msg = msg.replaceAll("%RANK%", (PlayerManager.getClanrankINT(all) == 1 ? "Mod" : "") + (PlayerManager.getClanrankINT(all) == 2 ? "Leader" : ""));
                player.sendMessage(msg);
            }
            player.sendMessage(MessageHandler.getMessage(MessageType.MEMBERS_FOOTER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));

            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Help1() {
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_HEADER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_TOGGLE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_CREATE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_JOIN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_LEAVE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_DELETE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_INVITE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_INVITES.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_STATS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_STATS_ANOTHER_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_MEMBERS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_MEMBERS_ANTHOER_CLAN.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_CHAT.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_FOOTER.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
    }


    public static void Help2() {
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_HEADER2.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_USTATS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_UMEMBERS.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_PROMOTE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_DEMOTE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_SETBASE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_BASE.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
        player.sendMessage(MessageHandler.getMessage(MessageType.HELP_CLAN_KICK.getCode()).replaceAll("%PREFIX%", MessageFile.getPrefix()));
    }

    static double myRound(double wert, int stellen) {
        return  Math.round(wert * Math.pow(10, stellen)) / Math.pow(10, stellen);
    }

}