package RemoteCraftingTable.items.tools;

import necesse.engine.Screen;
import necesse.engine.localization.Localization;
import necesse.engine.network.PacketReader;
import necesse.engine.network.packet.PacketSpawnProjectile;
import necesse.engine.sound.SoundEffect;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.GameResources;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.PlayerInventorySlot;
import necesse.inventory.item.Item;
import necesse.level.maps.Level;
import necesse.inventory.item.toolItem.miscToolItem.TestToolItem;
import necesse.inventory.item.trinketItem.SimpleTrinketItem;

public class CraftingRemote extends Item {

    public CraftingRemote() {
        super(1);
    }

    @Override
    public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = new ListGameTooltips();
        tooltips.add("Test Name");
        tooltips.add("");
        tooltips.add("Test Description blah blah blah");
        return tooltips;
    }

}
