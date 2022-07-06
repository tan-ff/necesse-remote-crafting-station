package Mod.registry;

import necesse.engine.registries.ItemRegistry;
import Mod.items.tools.CraftingRemote;

public class ModItemsRegistry {
    public static void RegisterAll() {
        ItemRegistry.registerItem("craftingremote", new CraftingRemote(), 20, true);
    }
}
