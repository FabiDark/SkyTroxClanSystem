package de.fabidark.clansystem.messages;

import de.fabidark.clansystem.files.MessageFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 13.01.2016.
 */

public enum MessageType {

    //Clan Messages
    MESSAGE_CLAN_ALREADY_EXISTS(MessageFile.getMessageClanAlreadyExists(), "MESSAGE_CLAN_ALREADY_EXISTS"),
    MESSAGE_CLAN_ON_BLACKLIST(MessageFile.getMessageClanOnBlacklist(), "MESSAGE_CLAN_ON_BLACKLIST"),
    MESSAGE_CLAN_INVALID_CHARS(MessageFile.getMessageClanInvalidChars(), "MESSAGE_CLAN_INVALID_CHARS"),
    MESSAGE_CLAN_NAME_TOO_LONG(MessageFile.getMessageClanNameTooLong(), "MESSAGE_CLAN_NAME_TOO_LONG"),

    //Error Messages
    MESSAGE_ERROR_NOT_IN_CLAN(MessageFile.getMessageErrorNotInClan(), "MESSAGE_ERROR_NOT_IN_CLAN"),
    MESSAGE_ERROR_CLAN_NOT_EXIST(MessageFile.getMessageErrorClanNotExist(), "MESSAGE_ERROR_CLAN_NOT_EXIST"),
    MESSAGE_ERROR_ALREADY_IN_CLAN(MessageFile.getMessageErrorAlreadyInClan(), "MESSAGE_ERROR_ALREADY_IN_CLAN"),
    MESSAGE_ERROR_NO_PERMISSION(MessageFile.getMessageErrorNoPermission(), "MESSAGE_ERROR_NO_PERMISSION"),
    MESSAGE_ERROR_LEAVE_CLAN_AS_LEADER(MessageFile.getMessageErrorLeaveClanAsLeader(), "MESSAGE_ERROR_LEAVE_CLAN_AS_LEADER"),
    MESSAGE_ERROR_NO_CLAN_INVITE(MessageFile.getMessageErrorNoClanInvite(), "MESSAGE_ERROR_NO_CLAN_INVITE"),
    MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN(MessageFile.getMessageErrorTargetNotInTheClan(), "MESSAGE_ERROR_TARGET_NOT_IN_THE_CLAN"),
    MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN(MessageFile.getMessageErrorTargetNotInAClan(), "MESSAGE_ERROR_TARGET_NOT_IN_A_CLAN"),
    MESSAGE_ERROR_TARGET_ALREADY_IN_THE_CLAN(MessageFile.getMessageErrorTargetAlreadyInTheClan(), "MESSAGE_ERROR_TARGET_ALREADY_IN_THE_CLAN"),
    MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL(MessageFile.getMessageErrorTargetNotInTheSql(), "MESSAGE_ERROR_TARGET_NOT_IN_THE_SQL"),
    MESSAGE_ERROR_TARGET_IS_PLAYER(MessageFile.getMessageErrorTargetIsPlayer(), "MESSAGE_ERROR_TARGET_IS_PLAYER"),
    MESSAGE_ERROR_TARGET_IS_LEADER(MessageFile.getMessageErrorTargetIsLeader(), "MESSAGE_ERROR_TARGET_IS_LEADER"),
    MESSAGE_ERROR_TARGET_ALREADY_IN_A_CLAN(MessageFile.getMessageErrorTargetAlreadyInAClan(), "MESSAGE_ERROR_TARGET_ALREADY_IN_A_CLAN"),
    MESSAGE_ERROR_TARGET_ALREADY_INVITED(MessageFile.getMessageErrorInviteAlreadyInvited(), "MESSAGE_ERROR_TARGET_ALREADY_INVITED"),
    MESSAGE_ERROR_TARGET_NOT_ONLINE(MessageFile.getMessageErrorTargetNotOnline(), "MESSAGE_ERROR_TARGET_NOT_ONLINE"),
    MESSAGE_ERROR_CLANBASE_NOT_IN_SQL(MessageFile.getMessageErrorClanbaseNotInSql(), "MESSAGE_ERROR_CLANBASE_NOT_IN_SQL"),
    MESSAGE_ERROR_CLANBASE_TP_CANCELED(MessageFile.getMessageErrorClanbaseTpCanceled(), "MESSAGE_ERROR_CLANBASE_TP_CANCELED"),
    MESSAGE_ERROR_NOT_PROMOTE_YOURSELF(MessageFile.getMessageErrorNotPromoteYourself(), "MESSAGE_ERROR_NOT_PROMOTE_YOURSELF"),
    MESSAGE_ERROR_NOT_DEMOTE_YOURSELF(MessageFile.getMessageErrorNotDemoteYourself(), "MESSAGE_ERROR_NOT_DEMOTE_YOURSELF"),
    MESSAGE_ERROR_NOT_INVITE_YOURSELF(MessageFile.getMessageErrorNotInviteYourself(), "MESSAGE_ERROR_NOT_INVITE_YOURSELF"),
    MESSAGE_ERROR_PROMOTE_TARGET_HAS_HIGHEST_RANG(MessageFile.getMessageErrorPromoteTargetHasHighestRang(), "MESSAGE_ERROR_PROMOTE_TARGET_HAS_HIGHEST_RANG"),
    MESSAGE_ERROR_DEMOTE_TARGET_HAS_LOWEST_RANG(MessageFile.getMessageErrorDemoteTargetHasLowestRang(), "MESSAGE_ERROR_DEMOTE_TARGET_HAS_LOWEST_RANG"),
    MESSAGE_ERROR_BLACKLIST_ADD_NAME_ALREADY_CONTAINS(MessageFile.getMessageErrorBlacklistAddNameAlreadyContains(), "MESSAGE_ERROR_BLACKLIST_ADD_NAME_ALREADY_CONTAINS"),
    MESSAGE_ERROR_BLACKLIST_REMOVE_NAMR_NOT_CONTAINS(MessageFile.getMessageErrorBlacklistRemoveNamrNotContains(), "MESSAGE_ERROR_BLACKLIST_REMOVE_NAMR_NOT_CONTAINS"),
    MESSAGE_ERROR_TOGGLE_ON(MessageFile.getMessageErrorToggleOn(), "MESSAGE_ERROR_TOGGLE_ON"),
    MESSAGE_ERROR_INVITE_ALREADY_INVITED(MessageFile.getMessageErrorInviteAlreadyInvited(), "MESSAGE_ERROR_INVITE_ALREADY_INVITED"),
    MESSAGE_ERROR_INVITE_NO_INVITES(MessageFile.getMessageErrorInviteNoInvites(), "MESSAGE_ERROR_INVITE_NO_INVITES"),
    MESSAGE_ERROR_INVITE_NOT_INVITED(MessageFile.getMessageErrorInviteNotInvited(), "MESSAGE_ERROR_INVITE_NOT_INVITED"),

    //Messages
    MESSAGE_CLAN_SUCCESSFUL_CREATE(MessageFile.getMessageClanSuccessful(), "MESSAGE_CLAN_SUCCESSFUL_CREATE"),
    MESSAGE_CLAN_LEAVE(MessageFile.getMessageClanLeave(), "MESSAGE_CLAN_LEAVE"),
    MESSAGE_CLAN_LEAVE_SEND_TO_ALL(MessageFile.getMessageClanLeaveSendToAll(), "MESSAGE_CLAN_LEAVE_SEND_TO_ALL"),
    MESSAGE_CLAN_JOIN(MessageFile.getMessageClanJoin(), "MESSAGE_CLAN_JOIN"),
    MESSAGE_CLAN_JOIN_SEND_TO_ALL(MessageFile.getMessageClanJoinSendToAll(), "MESSAGE_CLAN_JOIN_SEND_TO_ALL"),
    MESSAGE_CLAN_KICK_SEND_TO_TARGET(MessageFile.getMessageClanKickSendToTarget(), "MESSAGE_CLAN_KICK_SEND_TO_TARGET"),
    MESSAGE_CLAN_KICK_SEND_TO_KICKER(MessageFile.getMessageClanKickSendToKicker(), "MESSAGE_CLAN_KICK_SEND_TO_KICKER"),
    MESSAGE_CLAN_KICK_SEND_TO_ALL(MessageFile.getMessageClanKickSendToAll(), "MESSAGE_CLAN_KICK_SEND_TO_ALL"),
    MESSAGE_CLAN_BASE_SET(MessageFile.getMessageClanBaseSet(), "MESSAGE_CLAN_BASE_SET"),
    MESSAGE_CLAN_BASE_TP_TIMER_START(MessageFile.getMessageClanBaseTpTimerStart(), "MESSAGE_CLAN_BASE_TP_TIMER_START"),
    MESSAGE_CLAN_BASE_TP(MessageFile.getMessageClanBaseTp(), "MESSAGE_CLAN_BASE_TP"),
    MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_PROMOTER(MessageFile.getMessageClanPromoteToModSendToPromoter(), "MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_PROMOTER"),
    MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_TARGET(MessageFile.getMessageClanPromoteToModSendToTarget(), "MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_TARGET"),
    MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_ALL(MessageFile.getMessageClanPromoteToModSendToAll(), "MESSAGE_CLAN_PROMOTE_TO_MOD_SEND_TO_ALL"),
    MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_DEMOTER(MessageFile.getMessageClanDemoteToMemberSendToDemoter(), "MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_DEMOTER"),
    MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_TARGET(MessageFile.getMessageClanDemoteToMemberSendToTarget(), "MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_TARGET"),
    MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_ALL(MessageFile.getMessageClanDemoteToMemberSendToAll(), "MESSAGE_CLAN_DEMOTE_TO_MEMBER_SEND_TO_ALL"),
    MESSAGE_CLAN_DELETE_SEND_TO_ALL(MessageFile.getMessageClanDeleteSendToAll(), "MESSAGE_CLAN_DELETE_SEND_TO_ALL"),
    MESSAGE_CLAN_DELETE_SEND_TO_PLAYER(MessageFile.getMessageClanDeleteSendToPlayer(), "MESSAGE_CLAN_DELETE_SEND_TO_PLAYER"),
    MESSAGE_BLACKLIST_ADD(MessageFile.getMessageBlacklistAdd(), "MESSAGE_BLACKLIST_ADD"),
    MESSAGE_BLACKLIST_REMOVE(MessageFile.getMessageBlacklistRemove(), "MESSAGE_BLACKLIST_REMOVE"),
    MESSAGE_INVITE_SEND_TO_TARGET(MessageFile.getMessageInviteSendToTarget(), "MESSAGE_INVITE_SEND_TO_TARGET"),
    MESSAGE_INVITE_SEND_TO_INVITER(MessageFile.getMessageInviteSendToInviter(), "MESSAGE_INVITE_SEND_TO_INVITER"),
    MESSAGE_TOGGLE_SET_TO_TRUE(MessageFile.getMessageToggleSetToTrue(), "MESSAGE_TOGGLE_SET_TO_TRUE"),
    MESSAGE_TOGGLE_SET_TO_FALSE(MessageFile.getMessageToggleSetToFalse(), "MESSAGE_TOGGLE_SET_TO_FALSE"),
    MESSAGE_CLAN_CHAT(MessageFile.getMessageClanChat(), "MESSAGE_CLAN_CHAT"),

    //Invites
    INVITE_HEADER(MessageFile.getInviteHeader(), "INVITE_HEADER"),
    INVITE_INVITES(MessageFile.getInviteInvites(), "INVITE_INVITES"),
    INVITE_FOOTER(MessageFile.getInviteFooter(), "INVITE_FOOTER"),

    //Mitglieder
    MEMBERS_HEADER(MessageFile.getMembersHeader(), "MEMBERS_HEADER"),
    MEMBERS_CLANNAME(MessageFile.getMembersClanname(), "MEMBERS_CLANNAME"),
    MEMBERS_MEMBERS(MessageFile.getMembersMembers(), "MEMBERS_MEMBERS"),
    MEMBERS_FOOTER(MessageFile.getMembersFooter(), "MEMBERS_FOOTER"),

    //Help
    HELP_HEADER(MessageFile.getHelpHeader(), "HELP_HEADER"),
    HELP_HEADER2(MessageFile.getHelpHeader2(), "HELP_HEADER"),
    HELP_CLAN_TOGGLE(MessageFile.getHelpClanToggle(), "HELP_CLAN_TOGGLE"),
    HELP_CLAN_CREATE(MessageFile.getHelpClanCreate(), "HELP_CLAN_CREATE"),
    HELP_CLAN_JOIN(MessageFile.getHelpClanJoin(), "HELP_CLAN_JOIN"),
    HELP_CLAN_LEAVE(MessageFile.getHelpClanLeave(), "HELP_CLAN_LEAVE"),
    HELP_CLAN_DELETE(MessageFile.getHelpClanDelete(), "HELP_CLAN_DELETE"),
    HELP_CLAN_INVITE(MessageFile.getHelpClanInvite(), "HELP_CLAN_INVITE"),
    HELP_CLAN_INVITES(MessageFile.getHelpClanInvites(), "HELP_CLAN_INVITES"),
    HELP_CLAN_KICK(MessageFile.getHelpClanKick(), "HELP_CLAN_KICK"),
    HELP_CLAN_PROMOTE(MessageFile.getHelpClanPromote(), "HELP_CLAN_PROMOTE"),
    HELP_CLAN_DEMOTE(MessageFile.getHelpClanDemote(), "HELP_CLAN_DEMOTE"),
    HELP_CLAN_STATS(MessageFile.getHelpClanStats(), "HELP_CLAN_STATS"),
    HELP_CLAN_STATS_ANOTHER_CLAN(MessageFile.getHelpClanStatsAnotherClan(), "HELP_CLAN_STATS_ANOTHER_CLAN"),
    HELP_CLAN_MEMBERS(MessageFile.getHelpClanMembers(), "HELP_CLAN_MEMBERS"),
    HELP_CLAN_MEMBERS_ANTHOER_CLAN(MessageFile.getHelpClanMembersAnthoerClan(), "HELP_CLAN_MEMBERS_ANTHOER_CLAN"),
    HELP_CLAN_SETBASE(MessageFile.getHelpClanSetbase(), "HELP_CLAN_SETBASE"),
    HELP_CLAN_BASE(MessageFile.getHelpClanBase(), "HELP_CLAN_BASE"),
    HELP_CLAN_CHAT(MessageFile.getHelpClanChat(), "HELP_CLAN_CHAT"),
    HELP_CLAN_BLACKLIST(MessageFile.getHelpClanBlacklist(), "HELP_CLAN_BLACKLIST"),
    HELP_CLAN_USTATS(MessageFile.getHelpClanUstats(), "HELP_CLAN_USTATS"),
    HELP_CLAN_UMEMBERS(MessageFile.getHelpClanUmembers(), "HELP_CLAN_UMEMBERS"),
    HELP_FOOTER(MessageFile.getHelpFooter(), "HELP_FOOTER"),

    //Stats
    STATS_HEADER(MessageFile.getStatsHeader(), "STATS_HEADER"),
    STATS_CLANNAME(MessageFile.getStatsClanname(), "STATS_CLANNAME"),
    STATS_KILLS(MessageFile.getStatsKills(), "STATS_KILLS"),
    STATS_DEATHS(MessageFile.getStatsDeaths(), "STATS_DEATHS"),
    STATS_KD(MessageFile.getStatsKd(), "STATS_KD"),
    STATS_FOOTER(MessageFile.getStatsFooter(), "STATS_FOOTER");

    String code;
    String text;

    private MessageType(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }

    public static List<String> getUnitsAsString() {
        List<String> units = new ArrayList<String>();
        for(MessageType type : MessageType.values()) {
            units.add(type.getCode());
        }
        return units;
    }

    public static MessageType getUnit(String unit) {
        for(MessageType types : MessageType.values()) {
            if(types.getCode().equals(unit)) {
                return types;
            }
        }
        return null;
    }
}
