package Mod.items.tools;

import necesse.engine.Screen;
import necesse.engine.localization.Localization;
import necesse.engine.network.PacketReader;
import necesse.engine.network.packet.PacketSpawnProjectile;
import necesse.engine.sound.SoundEffect;
import necesse.engine.util.GameRandom;
import necesse.gfx.GameResources;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.PlayerInventorySlot;
import necesse.level.maps.Level;
import necesse.inventory.item.toolItem.miscToolItem.TestToolItem;

public class CraftingRemote extends TestToolItem {

    public CraftingRemote() {
        rarity = Rarity.RARE;

    }
    public ListGameTooltips getTooltips(TestToolItem item) {
        ListGameTooltips tooltips = new ListGameTooltips();
        tooltips.add(Localization.translate("Test: Adding tooltip for Remote Crafting Tool.", "Test: examplestafftip"));
        return tooltips;
    }

}
