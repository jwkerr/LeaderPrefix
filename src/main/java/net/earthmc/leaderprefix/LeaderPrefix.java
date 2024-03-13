package net.earthmc.leaderprefix;

import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import net.earthmc.leaderprefix.command.SetLeaderPrefixCommand;
import net.earthmc.leaderprefix.hook.LeaderPrefixPlaceholderExpansion;
import org.bukkit.plugin.java.JavaPlugin;

public final class LeaderPrefix extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();

        new LeaderPrefixPlaceholderExpansion(this).register();
    }

    private void registerCommands() {
        TownyCommandAddonAPI.addSubCommand(TownyCommandAddonAPI.CommandType.RESIDENT_SET, "leaderprefix", new SetLeaderPrefixCommand());
    }
}
