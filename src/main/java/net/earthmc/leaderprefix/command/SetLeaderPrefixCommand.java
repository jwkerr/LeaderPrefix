package net.earthmc.leaderprefix.command;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.object.Resident;
import net.earthmc.leaderprefix.manager.PrefixManager;
import net.earthmc.leaderprefix.manager.ResidentMetadataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLeaderPrefixCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            TownyMessaging.sendErrorMsg(sender, "Only players can use this command");
            return true;
        }

        if (args.length < 1) {
            TownyMessaging.sendErrorMsg(player, "No prefix argument provided");
            return true;
        }

        String prefixLetter = args[0];
        if (prefixLetter.length() > 1) {
            TownyMessaging.sendErrorMsg(player, "Provided prefix argument must be one character");
            return true;
        }

        PrefixManager pm = new PrefixManager();
        String letterInCircle = pm.getValidLeaderPrefixOrNull(prefixLetter);
        if (letterInCircle == null) {
            TownyMessaging.sendErrorMsg(player, "Provided prefix argument is not a valid option");
            return true;
        }

        Resident resident = TownyAPI.getInstance().getResident(player);
        if (resident == null) {
            TownyMessaging.sendErrorMsg(player, "Failed to set your leader prefix as you have no associated Towny resident data");
            return true;
        }

        ResidentMetadataManager rmm = new ResidentMetadataManager();
        rmm.setResidentLeaderPrefix(resident, letterInCircle);

        TownyMessaging.sendMsg(player, "Successfully set your leader prefix to " + letterInCircle);

        return true;
    }
}
