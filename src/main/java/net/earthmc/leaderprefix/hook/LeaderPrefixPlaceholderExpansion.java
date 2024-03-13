package net.earthmc.leaderprefix.hook;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.earthmc.leaderprefix.LeaderPrefix;
import net.earthmc.leaderprefix.manager.ResidentMetadataManager;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class LeaderPrefixPlaceholderExpansion extends PlaceholderExpansion {
    private final LeaderPrefix plugin;

    public LeaderPrefixPlaceholderExpansion(LeaderPrefix plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "leaderprefix";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        Resident resident = TownyAPI.getInstance().getResident(player.getUniqueId());

        if (identifier.equalsIgnoreCase("leader_prefix")) {
            ResidentMetadataManager rmm = new ResidentMetadataManager();
            return rmm.getResidentLeaderPrefix(resident);
        }

        return null;
    }
}
