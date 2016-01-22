package de.fabidark.clansystem.sqlmanager;

import de.fabidark.clansystem.sql.SQL;
import io.netty.handler.codec.http.HttpContentEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Fabian on 11.01.2016.
 */

public class ClanManager {

    private static ExecutorService executor;

    static {
        executor = Executors.newCachedThreadPool();
    }

    public static boolean IsInSQL(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Clans + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.first()) return true;
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addNewClan(String clanname, String LeaderUUID, String MemberUUID) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "INSERT INTO " + SQL.Clans + " (Clanname, LeaderUUID, MembersUUID) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, clanname);
                    pstmt.setString(2, LeaderUUID);
                    pstmt.setString(3, MemberUUID);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getLeader(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Clans + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getString("LeaderUUID");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setLeader(String clanname, String UUID) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Clans + " SET LeaderUUID = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, UUID);
                    pstmt.setString(2, clanname);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getMitglieder(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Clans + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getString("MembersUUID");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addMitglied(String clanname, String UUID) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Clans + " SET MembersUUID = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, getMitglieder(clanname) + "," + UUID);
                    pstmt.setString(2, clanname);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void removeMitglied(String clanname, String UUID) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Clans + " SET MembersUUID = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    String replaced = getMitglieder(clanname);
                    String mitglied = UUID;
                    replaced = replaced.replaceAll("," + mitglied, "");
                    pstmt.setString(1, replaced);
                    pstmt.setString(2, clanname);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static List<String> getAllMitglieder(String clanname) {
        return Arrays.asList(getMitglieder(clanname).split(","));
    }

    public static void removeClan(String clanname) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "DELETE FROM " + SQL.Clans + " WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, clanname);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static boolean isLeader(String clanname, String UUID) {
        if(getLeader(clanname).equals(UUID)) {
            return true;
        }
        return false;
    }

    public static List<String> getAllClans() {
        List<String> list = new ArrayList<String>();
        try {
            String qry = "SELECT * FROM " + SQL.Clans;
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(rs.getString("Clanname"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
