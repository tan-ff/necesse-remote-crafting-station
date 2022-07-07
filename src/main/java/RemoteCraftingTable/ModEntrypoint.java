package RemoteCraftingTable;

import RemoteCraftingTable.registry.*;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.gfx.gameTexture.GameTexture;

/**
 *  Entry point for your mod, you should rarely have to do anything in here. All registrations are setup in /registry
 */
@ModEntry
public class ModEntrypoint {

    public void init() { ModItemsRegistry.RegisterAll(); }
    public void initResources() {
        ModTextureRegistry.RegisterAll();
    }
    public void postInit() { ModRecipeRegistry.RegisterAll(); }

}
