package de.fabidark.clansystem.sqlmanager;

import de.fabidark.clansystem.enums.Clanranks;
import de.fabidark.clansystem.sql.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Fabian on 11.01.2016.
 */

public class PlayerManager {

    private static ExecutorService executor;

    static {
        executor = Executors.newCachedThreadPool();
    }

    public static boolean IsInSQL(String UUID) {
        try {
            String qry = "SELECT * FROM " + SQL.Players + " WHERE PlayerUUID = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, UUID);
            ResultSet rs = pstmt.executeQuery();
            if(rs.first()) return true;
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean IsInClan(String UUID) {
        try {
            String qry = "SELECT * FROM " + SQL.Players + " WHERE PlayerUUID = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, UUID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                if(!rs.getString("Clanname").equals("null")) {
                    return true;
                }
                return false;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addNewPlayer(String UUID, String Playername) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "INSERT INTO " + SQL.Players + " (PlayerUUID, Playername, ClanInvites, Clanname, Clanrank, Clantoggle) VALUES (?, ?, ?, ?, ? ,?)";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, UUID);
                    pstmt.setString(2, Playername);
                    pstmt.setString(3, null);
                    pstmt.setString(4, "null");
                    pstmt.setInt(5, 0);
                    pstmt.setInt(6, 0);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getClan(String UUID) {
        try {
            String qry = "SELECT * FROM " + SQL.Players + " WHERE PlayerUUID = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, UUID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getString("Clanname");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void setClan(String UUID, String clanname) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Players + " SET Clanname = ? WHERE PlayerUUID = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, clanname);
                    pstmt.setString(2, UUID);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getClanrank(String UUID) {
        try {
            String qry = "SELECT * FROM " + SQL.Players + " WHERE PlayerUUID = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, UUID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int priority = rs.getInt("Clanrank");
                if(priority == 0) return Clanranks.MEMBER.getName();
                if(priority == 1) return Clanranks.MOD.getName();
                if(priority == 2) return Clanranks.LEADER.getName();
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getClanrankINT(String UUID) {
        try {
            String qry = "SELECT * FROM " + SQL.Players + " WHERE PlayerUUID = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, UUID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getInt("Clanrank");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void setClanrank(String UUID, Clanranks rank) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Players + " SET Clanrank = ? WHERE PlayerUUID = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setInt(1, rank.getPriority());
                    pstmt.setString(2, UUID);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setToggle(String UUID, boolean status) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Players + " SET Clantoggle = ? WHERE PlayerUUID = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setBoolean(1, status);
                    pstmt.setString(2, UUID);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static boolean isToggleOn(String UUID) {
        try {
            String qry = "SELECT * FROM " + SQL.Players + " WHERE PlayerUUID = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, UUID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                boolean table = rs.getBoolean("Clantoggle");
                if(table == true) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setInvites(String UUID, String clanname) {
        executor.execute(new Runnable() {

            @Override
            public void run() {
                String qry = "UPDATE " + SQL.Players + " SET ClanInvites = ? WHERE PlayerUUID = ?";
                try {
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    if(clanname == null) {
                        pstmt.setString(1, null);
                    }
                    if(getInvites(UUID) == null) {
                        pstmt.setString(1, clanname);
                    } else {
                        pstmt.setString(1, getInvites(UUID) + "," + clanname);
                    }
                    pstmt.setString(2, UUID);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void clearInvites(String UUID) {
        executor.execute(new Runnable() {

            @Override
            public void run() {
                String qry = "UPDATE " + SQL.Players + " SET ClanInvites = ? WHERE PlayerUUID = ?";
                try {
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, null);
                    pstmt.setString(2, UUID);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getInvites(String uuid) {
        String qry = "SELECT * FROM " + SQL.Players + " WHERE PlayerUUID = ?";
            try {
                PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                pstmt.setString(1, uuid);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()) return rs.getString("ClanInvites");
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    public static List<String> getAllInvite(String uuid) {
        return Arrays.asList(getInvites(uuid).split(","));
    }

}
