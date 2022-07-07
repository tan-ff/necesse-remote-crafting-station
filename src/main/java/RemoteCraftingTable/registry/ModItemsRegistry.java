package RemoteCraftingTable.registry;

import RemoteCraftingTable.items.tools.ExampleSwordItem;
import necesse.engine.registries.ItemRegistry;
import RemoteCraftingTable.items.tools.CraftingRemote;

public class ModItemsRegistry {
    public static void RegisterAll() {

        ItemRegistry.registerItem("craftingremote", new CraftingRemote(), 20, true);
        ItemRegistry.registerItem("ExampleSwordItem", new ExampleSwordItem(), 20, true);
    }
}
