package zzzank.libs.config.natived.sync;

import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.entry.ConfigListener;

/**
 * @author ZZZank
 */
public class IntSyncListener implements ConfigListener<Integer> {
    @Override
    public void postSet(ConfigEntry<Integer> entry, Integer oldValue) {

    }
}
