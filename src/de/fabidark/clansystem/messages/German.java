package de.fabidark.clansystem.messages;

import com.avaje.ebeaninternal.server.core.Message;

import java.util.List;

/**
 * Created by Fabian on 13.01.2016.
 */
public class German {

    public static String getMessage(String code) {

        List<String> unitList = MessageType.getUnitsAsString();

        String msg = "No Message! Please send this to the Developer: " + code;

        if(unitList.contains(code)) {
            MessageType type = MessageType.getUnit(code);
            if (code.equalsIgnoreCase(type.getCode())) {
                msg = type.getText();
            }
        } else {
            return "Unknown Code!";
        }
        return msg;
    }

}
