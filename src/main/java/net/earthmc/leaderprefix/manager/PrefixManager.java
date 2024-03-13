package net.earthmc.leaderprefix.manager;

public class PrefixManager {

    public String getValidLeaderPrefixOrNull(String letter) {
        if (letter.equalsIgnoreCase("k") || letter.equals("Ⓚ")) return "Ⓚ";
        if (letter.equalsIgnoreCase("q") || letter.equals("Ⓠ")) return "Ⓠ";

        return null;
    }
}
