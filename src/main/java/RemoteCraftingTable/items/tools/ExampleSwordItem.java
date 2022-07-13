package RemoteCraftingTable.items.tools;

import necesse.inventory.item.toolItem.swordToolItem.CustomSwordToolItem;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.entity.mobs.PlayerMob;
import necesse.level.maps.Level;
import necesse.inventory.PlayerInventorySlot;
import necesse.engine.network.PacketReader;
import necesse.engine.screenHudManager.ScreenHudElement;
import necesse.engine.localization.Localization;

// Extends CustomSwordToolItem
public class ExampleSwordItem extends CustomSwordToolItem {
    // Weapon attack textures are loaded from resources/player/weapons/<itemStringID>
    public ExampleSwordItem() {
        // Attack Speed is animSpeed/1000 = Attack Speed.
        super(Rarity.UNCOMMON, 100, 1, 1, 1, 1);

    }

    @Override
    public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = new ListGameTooltips();
        tooltips.add("Example - Custom Sword");
        tooltips.add(getAttackDamageTip(item, perspective)); // Add attack damage to tooltip
        tooltips.add(getAttackSpeedTip(item, perspective)); // Adds attack speed to tooltip
        addCritChanceTip(tooltips, item, perspective); // Adds crit chance if above 0%
        return tooltips;

    }

    @Override
    public InventoryItem onAttack(Level level, int x, int y, PlayerMob player, int attackHeight, InventoryItem item, PlayerInventorySlot slot, int animAttack, int seed, PacketReader contentReader) {
        System.out.println("Test");
        return item;
    }
}