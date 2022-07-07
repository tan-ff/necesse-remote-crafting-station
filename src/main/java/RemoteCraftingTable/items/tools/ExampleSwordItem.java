package RemoteCraftingTable.items.tools;

import necesse.inventory.item.toolItem.swordToolItem.CustomSwordToolItem;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.entity.mobs.PlayerMob;
import necesse.engine.localization.Localization;

// Extends CustomSwordToolItem
public class ExampleSwordItem extends CustomSwordToolItem {
    // Weapon attack textures are loaded from resources/player/weapons/<itemStringID>
    public ExampleSwordItem() {
        super(Rarity.UNCOMMON, 100, 1, 1, 1, 1);
    }

    @Override
    public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = new ListGameTooltips();
        tooltips.add("Test");
        tooltips.add(getAttackDamageTip(item, perspective)); // Add attack damage to tooltip
        tooltips.add(getAttackSpeedTip(item, perspective)); // Adds attack speed to tooltip
        addCritChanceTip(tooltips, item, perspective); // Adds crit chance if above 0%
        return tooltips;
    }
}