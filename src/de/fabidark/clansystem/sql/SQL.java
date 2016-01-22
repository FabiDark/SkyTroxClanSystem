package de.fabidark.clansystem.sql;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Fabian on 11.01.2016.
 */

public class SQL {

    public static String host;
    public static String password;
    public static String username;
    public static String port;
    public static String database;
    public static Connection con;

    public static String Clans = "ClanSystem_Clans";
    public static String Players = "ClanSystem_Players";
    public static String Bases = "ClanSystem_Bases";
    public static String Stats = "ClanSystem_Stats";

    private static ExecutorService executor;

    static {
        executor = Executors.newCachedThreadPool();
    }

    // Connect to MySQL
    public static void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            if(isConnected()) {
                setup();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Disconnect from MySQL
    public static void disconnect() {
        try {
            if(isConnected()) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Test connection
    public static boolean isConnected() {
        try {
            return con !=null && con.isValid(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Setup Tabels
    private static void setup() {
        if(isConnected()) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        {
                            String qry = "CREATE TABLE IF NOT EXISTS " + SQL.Players + " (PlayerUUID VARCHAR(100), Playername VARCHAR(100), ClanInvites VARCHAR(100), Clanname VARCHAR(100), Clanrank VARCHAR(100), Clantoggle VARCHAR(100), id INT auto_increment, PRIMARY KEY(id))";
                            PreparedStatement stmt;
                            stmt = con.prepareStatement(qry);
                            stmt.executeUpdate();
                            stmt.close();
                        }
                        {
                            String qry = "CREATE TABLE IF NOT EXISTS " + SQL.Clans + " (Clanname VARCHAR(100), LeaderUUID VARCHAR(100), MembersUUID VARCHAR(100), id INT auto_increment, PRIMARY KEY(id))";
                            PreparedStatement stmt;
                            stmt = con.prepareStatement(qry);
                            stmt.executeUpdate();
                            stmt.close();
                        }
                        {
                            String qry = "CREATE TABLE IF NOT EXISTS " + SQL.Bases + " (Clanname VARCHAR(100), PosX VARCHAR(100), PosY VARCHAR(100), PosZ VARCHAR(100), PosYaw VARCHAR(100), PosPitch VARCHAR(100), id INT auto_increment, PRIMARY KEY(id))";
                            PreparedStatement stmt;
                            stmt = con.prepareStatement(qry);
                            stmt.executeUpdate();
                            stmt.close();
                        }
                        {
                            String qry = "CREATE TABLE IF NOT EXISTS " + SQL.Stats + " (Clanname VARCHAR(100), Kills VARCHAR(100), Deaths VARCHAR(100), id INT auto_increment, PRIMARY KEY(id))";
                            PreparedStatement stmt;
                            stmt = con.prepareStatement(qry);
                            stmt.executeUpdate();
                            stmt.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void update(String query){
        Statement st;
        try {
            st = (Statement) con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // MySQL result
    public static ResultSet getResult(String query) {
        if(isConnected()) {
            try {
                return con.prepareStatement(query).executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
