package de.fabidark.clansystem.sqlmanager;

import de.fabidark.clansystem.sql.SQL;
import io.netty.handler.codec.http.HttpContentEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Fabian on 11.01.2016.
 */
public class StatsManager {

    private static ExecutorService executor;

    static {
        executor = Executors.newCachedThreadPool();
    }

    public static boolean IsInMySQL(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Stats + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.first()) {
                return true;
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteClan(String clanname) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "DELETE FROM " + SQL.Stats + " WHERE Clanname = ?";
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

    public static void addNewClanStats(String clanname) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "INSERT INTO " + SQL.Stats + " (Clanname, Kills, Deaths) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, clanname);
                    pstmt.setInt(2, 0);
                    pstmt.setInt(3, 0);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static int getKills(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Stats + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getInt("Kills");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void addKills(String clanname) {
        int kills = getKills(clanname) + 1;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Stats + " SET Kills = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setInt(1, kills);
                    pstmt.setString(2, clanname);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void removeAllKills(String clanname) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Stats + " SET Kills = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setInt(1, 0);
                    pstmt.setString(2, clanname);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static int getDeaths(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Stats + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("Deaths");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void addDeaths(String clanname) {
        int deaths = getDeaths(clanname) + 1;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Stats + " SET Deaths = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setInt(1, deaths);
                    pstmt.setString(2, clanname);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void removeAllDeaths(String clanname) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Stats + " SET Deaths = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setInt(1, 0);
                    pstmt.setString(2, clanname);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static double kd(int kills, int deaths) {
        return (double)kills / (double)deaths;
    }



}
