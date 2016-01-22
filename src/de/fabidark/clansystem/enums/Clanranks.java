package de.fabidark.clansystem.enums;

import de.fabidark.clansystem.ClanSystem;

/**
 * Created by Fabian on 11.01.2016.
 */
public enum Clanranks {

    LEADER("Leader", "", 2),
    MOD("Mod", "", 1),
    MEMBER("Member", "", 0);

    String name;
    String tag;
    int priority;

    private Clanranks(String name, String tag, int priority) {
        this.name = name;
        this.tag = tag;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public String getTag() {
        return tag;
    }
}
