package de.fabidark.clansystem.messages;

import de.fabidark.clansystem.ClanSystem;

/**
 * Created by Fabian on 13.01.2016.
 */

public class MessageHandler {

    public static String getMessage(String code) {
        if(ClanSystem.getInstance().lang.equalsIgnoreCase("GERMAN")) {
            return German.getMessage(code);
        }
        return "Please use 'German' as a Language!";
    }

}
