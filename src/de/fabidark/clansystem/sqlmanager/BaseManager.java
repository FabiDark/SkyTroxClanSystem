package de.fabidark.clansystem.sqlmanager;

import de.fabidark.clansystem.sql.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Fabian on 11.01.2016.
 */

public class BaseManager {

    private static ExecutorService executor;

    static {
        executor = Executors.newCachedThreadPool();
    }

    public static boolean IsInSQL(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Bases + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.first()) {
                return true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addNewBases(String clanname, double x, double y, double z, float yaw, float pitch) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "INSERT INTO " + SQL.Bases + " (Clanname, PosX, PosY, PosZ, PosYaw, PosPitch) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setString(1, clanname);
                    pstmt.setDouble(2, x);
                    pstmt.setDouble(3, y);
                    pstmt.setDouble(4, z);
                    pstmt.setDouble(5, yaw);
                    pstmt.setDouble(6, pitch);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setBaseX(String clanname, double x) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Bases + " SET PosX = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setDouble(1, x);
                    pstmt.setString(2, clanname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setBaseY(String clanname, double y) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Bases + " SET PosY = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setDouble(1, y);
                    pstmt.setString(2, clanname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setBaseZ(String clanname, double z) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Bases + " SET PosZ = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setDouble(1, z);
                    pstmt.setString(2, clanname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setBaseYaw(String clanname, float yaw) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Bases + " SET PosYaw = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setFloat(1, yaw);
                    pstmt.setString(2, clanname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setBasePitch(String clanname, float pitch) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "UPDATE " + SQL.Bases + " SET PosPitch = ? WHERE Clanname = ?";
                    PreparedStatement pstmt = SQL.con.prepareStatement(qry);
                    pstmt.setFloat(1, pitch);
                    pstmt.setString(2, clanname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static double getBaseX(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Bases + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getDouble("PosX");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getBaseY(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Bases + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getDouble("PosY");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getBaseZ(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Bases + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getDouble("PosZ");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static float getBaseYaw(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Bases + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getFloat("PosYaw");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static float getBasePitch(String clanname) {
        try {
            String qry = "SELECT * FROM " + SQL.Bases + " WHERE Clanname = ?";
            PreparedStatement pstmt = SQL.con.prepareStatement(qry);
            pstmt.setString(1, clanname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return rs.getFloat("PosPitch");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void removeBase(String clanname) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String qry = "DELETE FROM " + SQL.Bases + " WHERE Clanname = ?";
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

}
