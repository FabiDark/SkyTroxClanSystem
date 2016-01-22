package de.fabidark.clansystem.files;

import de.fabidark.clansystem.ClanSystem;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by Fabian on 11.01.2016.
 */
public class MessageFile {

    private static String Prefix;
    private static String MESSAGE_CLAN_ALREADY_EXISTS;
    private static String MESSAGE_CLAN_ON_BLACKLIST;
    private static String MESSAGE_CLAN_CREATE;
    private static String MESSAGE_CLAN_INVALID_CHARS;
    private static String MESSAGE_CLAN_NAME_TOO_LONG;
    private static String MESSAGE_ERROR_NOT_IN_CLAN;
    private static String MESSAGE_ERROR_CLAN_NOT_EXIST;
    private static String MESSAGE_ERROR_ALREADY_IN_CLAN;
    private static String MESSAGE_ERROR_NO_PERMISSION;
    private static String MESSAGE_ERROR_LEAVE_CLAN_AS_LEADER;
    private static String MESSAGE_ERROR_NO_CLAN_INVITE;
    private static String MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN;
    private static String MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN;
    private static String MESSAGE_ERROR_TARGET_ALREADY_IN_THE_CLAN;
    private static String MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL;
    private static String MESSAGE_ERROR_TARGET_IS_PLAYER;
    private static String MESSAGE_ERROR_TARGET_IS_LEADER;
    private static String MESSAGE_ERROR_TARGET_ALREADY_IN_A_CLAN;
    private static String MESSAGE_ERROR_TARGET_ALREADY_INVITED;
    private static String MESSAGE_ERROR_CLANBASE_NOT_IN_SQL;
    private static String MESSAGE_ERROR_CLANBASE_TP_CANCELED;
    private static String MESSAGE_ERROR_NOT_PROMOTE_YOURSELF;
    private static String MESSAGE_ERROR_NOT_DEMOTE_YOURSELF;
    private static String MESSAGE_ERROR_NOT_INVITE_YOURSELF;
    private static String MESSAGE_ERROR_PROMOTE_TARGET_HAS_HIGHEST_RANG;
    private static String MESSAGE_ERROR_DEMOTE_TARGET_HAS_LOWEST_RANG;
    private static String MESSAGE_ERROR_BLACKLIST_ADD_NAME_ALREADY_CONTAINS;
    private static String MESSAGE_ERROR_BLACKLIST_REMOVE_NAMR_NOT_CONTAINS;
    private static String MESSAGE_ERROR_TOGGLE_ON;
    private static String MESSAGE_ERROR_INVITE_ALREADY_INVITED;
    private static String MESSAGE_ERROR_INVITE_NO_INVITES;
    private static String MESSAGE_ERROR_INVITE_NOT_INVITED;
    private static String MESSAGE_CLAN_SUCCESSFUL;
    private static String MESSAGE_CLAN_LEAVE;
    private static String MESSAGE_CLAN_LEAVE_SEND_TO_ALL;
    private static String MESSAGE_CLAN_JOIN;
    private static String MESSAGE_CLAN_JOIN_SEND_TO_ALL;
    private static String MESSAGE_CLAN_KICK_SEND_TO_TARGET;
    private static String MESSAGE_CLAN_KICK_SEND_TO_KICKER;
    private static String MESSAGE_CLAN_KICK_SEND_TO_ALL;
    private static String MESSAGE_CLAN_BASE_SET;
    private static String MESSAGE_CLAN_BASE_TP_TIMER_START;
    private static String MESSAGE_CLAN_BASE_TP;
    private static String MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_PROMOTER;
    private static String MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_TARGET;
    private static String MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_ALL;
    private static String MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_DEMOTER;
    private static String MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_TARGET;
    private static String MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_ALL;
    private static String MESSAGE_CLAN_DELETE_SEND_TO_ALL;
    private static String MESSAGE_CLAN_DELETE_SEND_TO_PLAYER;
    private static String MESSAGE_BLACKLIST_ADD;
    private static String MESSAGE_BLACKLIST_REMOVE;
    private static String MESSAGE_INVITE_SEND_TO_TARGET;
    private static String MESSAGE_INVITE_SEND_TO_INVITER;
    private static String MESSAGE_TOGGLE_SET_TO_TRUE;
    private static String MESSAGE_TOGGLE_SET_TO_FALSE;
    private static String MESSAGE_CLAN_CHAT;
    private static String INVITE_HEADER;
    private static String INVITE_INVITES;
    private static String INVITE_FOOTER;
    private static String MEMBERS_HEADER;
    private static String MEMBERS_CLANNAME;
    private static String MEMBERS_MEMBERS;
    private static String MEMBERS_FOOTER;
    private static String HELP_HEADER;
    private static String HELP_HEADER2;
    private static String HELP_CLAN_TOGGLE;
    private static String HELP_CLAN_CREATE;
    private static String HELP_CLAN_JOIN;
    private static String HELP_CLAN_LEAVE;
    private static String HELP_CLAN_DELETE;
    private static String HELP_CLAN_INVITE;
    private static String HELP_CLAN_INVITES;
    private static String HELP_CLAN_KICK;
    private static String HELP_CLAN_PROMOTE;
    private static String HELP_CLAN_DEMOTE;
    private static String HELP_CLAN_STATS;
    private static String HELP_CLAN_STATS_ANOTHER_CLAN;
    private static String HELP_CLAN_MEMBERS;
    private static String HELP_CLAN_MEMBERS_ANTHOER_CLAN;
    private static String HELP_CLAN_SETBASE;
    private static String HELP_CLAN_BASE;
    private static String HELP_CLAN_CHAT;
    private static String HELP_CLAN_BLACKLIST;
    private static String HELP_CLAN_USTATS;
    private static String HELP_CLAN_UMEMBERS;
    private static String HELP_FOOTER;
    private static String STATS_HEADER;
    private static String STATS_CLANNAME;
    private static String STATS_KILLS;
    private static String STATS_DEATHS;
    private static String STATS_KD;
    private static String STATS_FOOTER;
    private static String MESSAGE_ERROR_TARGET_NOT_ONLINE;

    // Message File
    public static File getMessageFile() {
        return new File(ClanSystem.getInstance().getDataFolder(), "messages.yml");
    }

    public static FileConfiguration getMessageFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getMessageFile());
    }

    //Message File setup
    public static void setDefaultMessage() {
        if(!getMessageFile().exists()) {
            ClanSystem.getInstance().saveResource("messages.yml", false);
        }
    }

    public static void readMessage(){
        FileConfiguration cfg = getMessageFileConfiguration();
        if(cfg == null) return;
        Prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix"));
         MESSAGE_CLAN_ALREADY_EXISTS = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_ALREADY_EXISTS"));
         MESSAGE_CLAN_ON_BLACKLIST = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_ON_BLACKLIST"));
         MESSAGE_CLAN_INVALID_CHARS = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_INVALID_CHARS"));
         MESSAGE_CLAN_NAME_TOO_LONG = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_NAME_TOO_LONG"));
         MESSAGE_ERROR_NOT_IN_CLAN = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_NOT_IN_CLAN"));
         MESSAGE_ERROR_CLAN_NOT_EXIST = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_CLAN_NOT_EXIST"));
         MESSAGE_ERROR_ALREADY_IN_CLAN = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_ALREADY_IN_CLAN"));
         MESSAGE_ERROR_NO_PERMISSION = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_NO_PERMISSION"));
         MESSAGE_ERROR_LEAVE_CLAN_AS_LEADER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_LEAVE_CLAN_AS_LEADER"));
         MESSAGE_ERROR_NO_CLAN_INVITE = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_NO_CLAN_INVITE"));
         MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN"));
         MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN"));
         MESSAGE_ERROR_TARGET_ALREADY_IN_THE_CLAN = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_ALREADY_IN_THE_CLAN"));
         MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL"));
         MESSAGE_ERROR_TARGET_IS_PLAYER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_IS_PLAYER"));
         MESSAGE_ERROR_TARGET_IS_LEADER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_IS_LEADER"));
         MESSAGE_ERROR_TARGET_ALREADY_IN_A_CLAN = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_ALREADY_IN_A_CLAN"));
         MESSAGE_ERROR_TARGET_ALREADY_INVITED = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_ALREADY_INVITED"));
         MESSAGE_ERROR_CLANBASE_NOT_IN_SQL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_CLANBASE_NOT_IN_SQL"));
         MESSAGE_ERROR_CLANBASE_TP_CANCELED = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_CLANBASE_TP_CANCELED"));
         MESSAGE_ERROR_NOT_PROMOTE_YOURSELF = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_NOT_PROMOTE_YOURSELF"));
         MESSAGE_ERROR_NOT_DEMOTE_YOURSELF = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_NOT_DEMOTE_YOURSELF"));
         MESSAGE_ERROR_NOT_INVITE_YOURSELF = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_NOT_INVITE_YOURSELF"));
         MESSAGE_ERROR_PROMOTE_TARGET_HAS_HIGHEST_RANG = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_PROMOTE_TARGET_HAS_HIGHEST_RANG"));
         MESSAGE_ERROR_DEMOTE_TARGET_HAS_LOWEST_RANG = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_DEMOTE_TARGET_HAS_LOWEST_RANG"));
         MESSAGE_ERROR_BLACKLIST_ADD_NAME_ALREADY_CONTAINS = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_BLACKLIST_ADD_NAME_ALREADY_CONTAINS"));
         MESSAGE_ERROR_BLACKLIST_REMOVE_NAMR_NOT_CONTAINS = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_BLACKLIST_REMOVE_NAMR_NOT_CONTAINS"));
         MESSAGE_ERROR_TOGGLE_ON = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TOGGLE_ON"));
         MESSAGE_ERROR_INVITE_ALREADY_INVITED = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_INVITE_ALREADY_INVITED"));
         MESSAGE_ERROR_INVITE_NO_INVITES = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_INVITE_NO_INVITES"));
         MESSAGE_ERROR_INVITE_NOT_INVITED = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_INVITE_NOT_INVITED"));
         MESSAGE_CLAN_SUCCESSFUL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_SUCCESSFUL_CREATE"));
         MESSAGE_CLAN_LEAVE = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_LEAVE"));
         MESSAGE_CLAN_LEAVE_SEND_TO_ALL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_LEAVE_SEND_TO_ALL"));
         MESSAGE_CLAN_JOIN = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_JOIN"));
         MESSAGE_CLAN_JOIN_SEND_TO_ALL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_JOIN_SEND_TO_ALL"));
         MESSAGE_CLAN_KICK_SEND_TO_TARGET = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_KICK_SEND_TO_TARGET"));
         MESSAGE_CLAN_KICK_SEND_TO_KICKER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_KICK_SEND_TO_KICKER"));
         MESSAGE_CLAN_KICK_SEND_TO_ALL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_KICK_SEND_TO_ALL"));
         MESSAGE_CLAN_BASE_SET = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_BASE_SET"));
         MESSAGE_CLAN_BASE_TP_TIMER_START = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_BASE_TP_TIMER_START"));
         MESSAGE_CLAN_BASE_TP = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_BASE_TP"));
         MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_PROMOTER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_PROMOTER"));
         MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_TARGET = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_TARGET"));
         MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_ALL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_ALL"));
         MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_DEMOTER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_DEMOTER"));
         MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_TARGET = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_TARGET"));
         MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_ALL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_ALL"));
         MESSAGE_CLAN_DELETE_SEND_TO_ALL = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_DELETE_SEND_TO_ALL"));
         MESSAGE_CLAN_DELETE_SEND_TO_PLAYER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_DELETE_SEND_TO_PLAYER"));
         MESSAGE_BLACKLIST_ADD = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_BLACKLIST_ADD"));
         MESSAGE_BLACKLIST_REMOVE = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_BLACKLIST_REMOVE"));
         MESSAGE_INVITE_SEND_TO_TARGET = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_INVITE_SEND_TO_TARGET"));
         MESSAGE_INVITE_SEND_TO_INVITER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_INVITE_SEND_TO_INVITER"));
         MESSAGE_TOGGLE_SET_TO_TRUE = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_TOGGLE_SET_TO_TRUE"));
         MESSAGE_TOGGLE_SET_TO_FALSE = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_TOGGLE_SET_TO_FALSE"));
         MESSAGE_CLAN_CHAT = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_CLAN_CHAT"));
         INVITE_HEADER = ChatColor.translateAlternateColorCodes('&', cfg.getString("INVITE_HEADER"));
         INVITE_INVITES = ChatColor.translateAlternateColorCodes('&', cfg.getString("INVITE_INVITES"));
         INVITE_FOOTER = ChatColor.translateAlternateColorCodes('&', cfg.getString("INVITE_FOOTER"));
         MEMBERS_HEADER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MEMBERS_HEADER"));
         MEMBERS_CLANNAME = ChatColor.translateAlternateColorCodes('&', cfg.getString("MEMBERS_CLANNAME"));
         MEMBERS_MEMBERS = ChatColor.translateAlternateColorCodes('&', cfg.getString("MEMBERS_MEMBERS"));
         MEMBERS_FOOTER = ChatColor.translateAlternateColorCodes('&', cfg.getString("MEMBERS_FOOTER"));
         HELP_HEADER = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_HEADER"));
        HELP_HEADER2 = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_HEADER_ANOTHER_PAGE"));
         HELP_CLAN_TOGGLE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_TOGGLE"));
         HELP_CLAN_CREATE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_CREATE"));
         HELP_CLAN_JOIN = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_JOIN"));
         HELP_CLAN_LEAVE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_LEAVE"));
         HELP_CLAN_DELETE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_DELETE"));
         HELP_CLAN_INVITE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_INVITE"));
         HELP_CLAN_INVITES = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_INVITES"));
         HELP_CLAN_KICK = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_KICK"));
         HELP_CLAN_PROMOTE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_PROMOTE"));
         HELP_CLAN_DEMOTE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_DEMOTE"));
         HELP_CLAN_STATS = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_STATS"));
         HELP_CLAN_STATS_ANOTHER_CLAN = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_STATS_ANOTHER_CLAN"));
         HELP_CLAN_MEMBERS = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_MEMBERS"));
         HELP_CLAN_MEMBERS_ANTHOER_CLAN = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_MEMBERS_ANTHOER_CLAN"));
         HELP_CLAN_SETBASE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_SETBASE"));
         HELP_CLAN_BASE = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_BASE"));
         HELP_CLAN_CHAT = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_CHAT"));
         HELP_CLAN_BLACKLIST = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_BLACKLIST"));
        HELP_CLAN_USTATS = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_USTATS"));
        HELP_CLAN_UMEMBERS = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_CLAN_UMEMBERS"));
         HELP_FOOTER = ChatColor.translateAlternateColorCodes('&', cfg.getString("HELP_FOOTER"));
         STATS_HEADER = ChatColor.translateAlternateColorCodes('&', cfg.getString("STATS_HEADER"));
         STATS_CLANNAME = ChatColor.translateAlternateColorCodes('&', cfg.getString("STATS_CLANNAME"));
         STATS_KILLS = ChatColor.translateAlternateColorCodes('&', cfg.getString("STATS_KILLS"));
         STATS_DEATHS = ChatColor.translateAlternateColorCodes('&', cfg.getString("STATS_DEATHS"));
         STATS_KD = ChatColor.translateAlternateColorCodes('&', cfg.getString("STATS_KD"));
         STATS_FOOTER = ChatColor.translateAlternateColorCodes('&', cfg.getString("STATS_FOOTER"));
        MESSAGE_ERROR_TARGET_NOT_ONLINE = ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGE_ERROR_TARGET_NOT_ONLINE"));
    }

    public static void setupConfigs() {
        setDefaultMessage();
        readMessage();
    }

    public static String getPrefix() {
        return Prefix;
    }

    public static String getMessageClanAlreadyExists() {
        return MESSAGE_CLAN_ALREADY_EXISTS;
    }

    public static String getMessageClanOnBlacklist() {
        return MESSAGE_CLAN_ON_BLACKLIST;
    }

    public static String getMessageClanCreate() {
        return MESSAGE_CLAN_CREATE;
    }

    public static String getMessageClanInvalidChars() {
        return MESSAGE_CLAN_INVALID_CHARS;
    }

    public static String getMessageClanNameTooLong() {
        return MESSAGE_CLAN_NAME_TOO_LONG;
    }

    public static String getMessageErrorNotInClan() {
        return MESSAGE_ERROR_NOT_IN_CLAN;
    }

    public static String getMessageErrorClanNotExist() {
        return MESSAGE_ERROR_CLAN_NOT_EXIST;
    }

    public static String getMessageErrorAlreadyInClan() {
        return MESSAGE_ERROR_ALREADY_IN_CLAN;
    }

    public static String getMessageErrorNoPermission() {
        return MESSAGE_ERROR_NO_PERMISSION;
    }

    public static String getMessageErrorLeaveClanAsLeader() {
        return MESSAGE_ERROR_LEAVE_CLAN_AS_LEADER;
    }

    public static String getMessageErrorNoClanInvite() {
        return MESSAGE_ERROR_NO_CLAN_INVITE;
    }

    public static String getMessageErrorTargetNotInTheClan() {
        return MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN;
    }

    public static String getMessageErrorTargetNotInAClan() {
        return MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN;
    }

    public static String getMessageErrorTargetAlreadyInTheClan() {
        return MESSAGE_ERROR_TARGET_ALREADY_IN_THE_CLAN;
    }

    public static String getMessageErrorTargetNotInTheSql() {
        return MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL;
    }

    public static String getMessageErrorTargetIsPlayer() {
        return MESSAGE_ERROR_TARGET_IS_PLAYER;
    }

    public static String getMessageErrorTargetIsLeader() {
        return MESSAGE_ERROR_TARGET_IS_LEADER;
    }

    public static String getMessageErrorTargetAlreadyInAClan() {
        return MESSAGE_ERROR_TARGET_ALREADY_IN_A_CLAN;
    }

    public static String getMessageErrorTargetAlreadyInvited() {
        return MESSAGE_ERROR_TARGET_ALREADY_INVITED;
    }

    public static String getMessageErrorClanbaseNotInSql() {
        return MESSAGE_ERROR_CLANBASE_NOT_IN_SQL;
    }

    public static String getMessageErrorClanbaseTpCanceled() {
        return MESSAGE_ERROR_CLANBASE_TP_CANCELED;
    }

    public static String getMessageErrorNotPromoteYourself() {
        return MESSAGE_ERROR_NOT_PROMOTE_YOURSELF;
    }

    public static String getMessageErrorNotDemoteYourself() {
        return MESSAGE_ERROR_NOT_DEMOTE_YOURSELF;
    }

    public static String getMessageErrorNotInviteYourself() {
        return MESSAGE_ERROR_NOT_INVITE_YOURSELF;
    }

    public static String getMessageErrorPromoteTargetHasHighestRang() {
        return MESSAGE_ERROR_PROMOTE_TARGET_HAS_HIGHEST_RANG;
    }

    public static String getMessageErrorDemoteTargetHasLowestRang() {
        return MESSAGE_ERROR_DEMOTE_TARGET_HAS_LOWEST_RANG;
    }

    public static String getMessageErrorBlacklistAddNameAlreadyContains() {
        return MESSAGE_ERROR_BLACKLIST_ADD_NAME_ALREADY_CONTAINS;
    }

    public static String getMessageErrorBlacklistRemoveNamrNotContains() {
        return MESSAGE_ERROR_BLACKLIST_REMOVE_NAMR_NOT_CONTAINS;
    }

    public static String getMessageErrorToggleOn() {
        return MESSAGE_ERROR_TOGGLE_ON;
    }

    public static String getMessageErrorInviteAlreadyInvited() {
        return MESSAGE_ERROR_INVITE_ALREADY_INVITED;
    }

    public static String getMessageErrorInviteNoInvites() {
        return MESSAGE_ERROR_INVITE_NO_INVITES;
    }

    public static String getMessageErrorInviteNotInvited() {
        return MESSAGE_ERROR_INVITE_NOT_INVITED;
    }

    public static String getMessageClanSuccessful() {
        return MESSAGE_CLAN_SUCCESSFUL;
    }

    public static String getMessageClanLeave() {
        return MESSAGE_CLAN_LEAVE;
    }

    public static String getMessageClanLeaveSendToAll() {
        return MESSAGE_CLAN_LEAVE_SEND_TO_ALL;
    }

    public static String getMessageClanJoin() {
        return MESSAGE_CLAN_JOIN;
    }

    public static String getMessageClanJoinSendToAll() {
        return MESSAGE_CLAN_JOIN_SEND_TO_ALL;
    }

    public static String getMessageClanKickSendToTarget() {
        return MESSAGE_CLAN_KICK_SEND_TO_TARGET;
    }

    public static String getMessageClanKickSendToKicker() {
        return MESSAGE_CLAN_KICK_SEND_TO_KICKER;
    }

    public static String getMessageClanKickSendToAll() {
        return MESSAGE_CLAN_KICK_SEND_TO_ALL;
    }

    public static String getMessageClanBaseSet() {
        return MESSAGE_CLAN_BASE_SET;
    }

    public static String getMessageClanBaseTpTimerStart() {
        return MESSAGE_CLAN_BASE_TP_TIMER_START;
    }

    public static String getMessageClanBaseTp() {
        return MESSAGE_CLAN_BASE_TP;
    }

    public static String getMessageClanPromoteToModSendToPromoter() {
        return MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_PROMOTER;
    }

    public static String getMessageClanPromoteToModSendToTarget() {
        return MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_TARGET;
    }

    public static String getMessageClanPromoteToModSendToAll() {
        return MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_ALL;
    }

    public static String getMessageClanDemoteToMemberSendToDemoter() {
        return MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_DEMOTER;
    }

    public static String getMessageClanDemoteToMemberSendToTarget() {
        return MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_TARGET;
    }

    public static String getMessageClanDemoteToMemberSendToAll() {
        return MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_ALL;
    }

    public static String getMessageClanDeleteSendToAll() {
        return MESSAGE_CLAN_DELETE_SEND_TO_ALL;
    }

    public static String getMessageClanDeleteSendToPlayer() {
        return MESSAGE_CLAN_DELETE_SEND_TO_PLAYER;
    }

    public static String getMessageBlacklistAdd() {
        return MESSAGE_BLACKLIST_ADD;
    }

    public static String getMessageBlacklistRemove() {
        return MESSAGE_BLACKLIST_REMOVE;
    }

    public static String getMessageInviteSendToTarget() {
        return MESSAGE_INVITE_SEND_TO_TARGET;
    }

    public static String getMessageInviteSendToInviter() {
        return MESSAGE_INVITE_SEND_TO_INVITER;
    }

    public static String getMessageToggleSetToTrue() {
        return MESSAGE_TOGGLE_SET_TO_TRUE;
    }

    public static String getMessageToggleSetToFalse() {
        return MESSAGE_TOGGLE_SET_TO_FALSE;
    }

    public static String getMessageClanChat() {
        return MESSAGE_CLAN_CHAT;
    }

    public static String getInviteHeader() {
        return INVITE_HEADER;
    }

    public static String getInviteInvites() {
        return INVITE_INVITES;
    }

    public static String getInviteFooter() {
        return INVITE_FOOTER;
    }

    public static String getMembersHeader() {
        return MEMBERS_HEADER;
    }

    public static String getMembersClanname() {
        return MEMBERS_CLANNAME;
    }

    public static String getMembersMembers() {
        return MEMBERS_MEMBERS;
    }

    public static String getMembersFooter() {
        return MEMBERS_FOOTER;
    }

    public static String getHelpHeader() {
        return HELP_HEADER;
    }

    public static String getHelpClanToggle() {
        return HELP_CLAN_TOGGLE;
    }

    public static String getHelpClanCreate() {
        return HELP_CLAN_CREATE;
    }

    public static String getHelpClanJoin() {
        return HELP_CLAN_JOIN;
    }

    public static String getHelpClanLeave() {
        return HELP_CLAN_LEAVE;
    }

    public static String getHelpClanDelete() {
        return HELP_CLAN_DELETE;
    }

    public static String getHelpClanInvite() {
        return HELP_CLAN_INVITE;
    }

    public static String getHelpClanInvites() {
        return HELP_CLAN_INVITES;
    }

    public static String getHelpClanKick() {
        return HELP_CLAN_KICK;
    }

    public static String getHelpClanPromote() {
        return HELP_CLAN_PROMOTE;
    }

    public static String getHelpClanDemote() {
        return HELP_CLAN_DEMOTE;
    }

    public static String getHelpClanStats() {
        return HELP_CLAN_STATS;
    }

    public static String getHelpClanStatsAnotherClan() {
        return HELP_CLAN_STATS_ANOTHER_CLAN;
    }

    public static String getHelpClanMembers() {
        return HELP_CLAN_MEMBERS;
    }

    public static String getHelpClanMembersAnthoerClan() {
        return HELP_CLAN_MEMBERS_ANTHOER_CLAN;
    }

    public static String getHelpClanSetbase() {
        return HELP_CLAN_SETBASE;
    }

    public static String getHelpClanBase() {
        return HELP_CLAN_BASE;
    }

    public static String getHelpClanChat() {
        return HELP_CLAN_CHAT;
    }

    public static String getHelpClanBlacklist() {
        return HELP_CLAN_BLACKLIST;
    }

    public static String getHelpFooter() {
        return HELP_FOOTER;
    }

    public static String getStatsHeader() {
        return STATS_HEADER;
    }

    public static String getStatsClanname() {
        return STATS_CLANNAME;
    }

    public static String getStatsKills() {
        return STATS_KILLS;
    }

    public static String getStatsDeaths() {
        return STATS_DEATHS;
    }

    public static String getStatsKd() {
        return STATS_KD;
    }

    public static String getStatsFooter() {
        return STATS_FOOTER;
    }

    public static String getHelpHeader2() {
        return HELP_HEADER2;
    }

    public static String getHelpClanUstats() {
        return HELP_CLAN_USTATS;
    }

    public static String getHelpClanUmembers() {
        return HELP_CLAN_UMEMBERS;
    }

    public static String getMessageErrorTargetNotOnline() {
        return MESSAGE_ERROR_TARGET_NOT_ONLINE;
    }
}
