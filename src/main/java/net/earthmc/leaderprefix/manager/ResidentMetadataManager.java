package net.earthmc.leaderprefix.manager;

import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.StringDataField;

public class ResidentMetadataManager {
    private final String leaderPrefixKey = "leaderprefix_leader_prefix";

    public void setResidentLeaderPrefix(Resident resident, String value) {
        if (!resident.hasMeta(leaderPrefixKey))
            resident.addMetaData(new StringDataField(leaderPrefixKey, null));

        StringDataField sdf = (StringDataField) resident.getMetadata(leaderPrefixKey);
        if (sdf == null) return;

        sdf.setValue(value);
        resident.addMetaData(sdf);
    }

    public String getResidentLeaderPrefix(Resident resident) {
        if (resident == null) return null;

        StringDataField sdf = (StringDataField) resident.getMetadata(leaderPrefixKey);
        if (sdf == null) {
            PrefixManager pm = new PrefixManager();
            return pm.getValidLeaderPrefixOrNull("k");
        }

        return sdf.getValue();
    }
}
