package RemoteCraftingTable.items.tools;

import necesse.level.maps.levelData.settlementData.*;
import necesse.engine.network.PacketReader;
import necesse.engine.registries.ContainerRegistry;
import necesse.engine.registries.RecipeTechRegistry;
import necesse.entity.mobs.PlayerMob;
import necesse.inventory.container.object.CraftingStationContainer;
import necesse.inventory.recipe.*;
import necesse.level.maps.Level;
import necesse.inventory.InventoryItem;
import necesse.inventory.PlayerInventorySlot;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.item.Item;

import necesse.inventory.container.object.CraftingStationContainer;
import necesse.level.gameObject.WorkstationObject;


import necesse.engine.Screen;
import necesse.engine.localization.Localization;
import necesse.gfx.GameResources;
import necesse.inventory.Inventory;
import necesse.inventory.container.Container;
import necesse.inventory.container.ContainerActionResult;
import necesse.inventory.container.slots.ContainerSlot;
import necesse.inventory.item.miscItem.CoinPouch;
import necesse.inventory.container.item.ItemInventoryContainer;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CraftingRemote extends Item implements SettlementWorkstationObject {
    // public HashSet<String> combinePurposes = new HashSet();

    public CraftingRemote() {
        super(1);
        //this.setItemCategory(new String[] {"items","craftingstation"});

       /* this.setItemCategory(new String[]{"misc", "pouches"});
        this.rarity = Rarity.RARE;
        this.combinePurposes.add("leftclick");
        this.combinePurposes.add("leftclickinv");
        this.combinePurposes.add("rightclick");
        this.combinePurposes.add("lootall");
        this.combinePurposes.add("pouchtransfer");
        this.worldDrawSize = 32;*/

    }

    @Override
    public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = new ListGameTooltips();
        tooltips.add("Test Name");
        tooltips.add("Test Description blah blah blah");
        return tooltips;
    }

    @Override
    public InventoryItem onAttack(Level level, int x, int y, PlayerMob player, int attackHeight, InventoryItem item, PlayerInventorySlot slot, int animAttack, int seed, PacketReader contentReader) {
        System.out.println("Changed");
       /* if (level.isClientLevel() && level.getClient().getPlayer() == player) {
            level.getClient().tutorial.usedWorkstation();
        }*/
        // I think we need to make our own container? -- ContainerRegistry.CRAFTING_STATION_CONTAINER
        // Or link the remote to a specific work station when you click on it....
        System.out.println(getID());
        
//        if (level.isServerLevel()) {
//            CraftingStationContainer.openAndSendContainer(ContainerRegistry.CRAFTING_STATION_CONTAINER, player.getServerClient(), level, x, y);
//        }
        return item;
    }

    public Tech[] getCraftingTechs() {
        return new Tech[]{RecipeTechRegistry.WORKSTATION, RecipeTechRegistry.NONE};
    }

    public Stream<Recipe> streamSettlementRecipes(Level level, int tileX, int tileY) {
        Tech[] techs = this.getCraftingTechs();
        return Recipes.streamRecipes().filter((r) -> {
            return r.matchesTechs(techs);
        });
    }

    /* Example of how CoinPouch overrides functions to do stuff.

    @Override
    public InventoryItem onAttack(Level level, int x, int y, PlayerMob player, int attackHeight, InventoryItem item, PlayerInventorySlot slot, int animAttack, int seed, PacketReader contentReader) {
        System.out.println("Changed");
        int currentCoins = this.getCurrentCoins(item);
        int thrown = Math.min(1000, currentCoins);
        if (thrown > 0) {
            this.saveCurrentCoins(item, currentCoins - thrown);
            if (level.isServerLevel()) {
                Point2D.Float dir = GameMath.normalize((float)x - player.x, (float)y - player.y);
                level.entityManager.pickups.add((new InventoryItem("coin", thrown)).getPickupEntity(level, player.x, player.y, dir.x * 175.0F, dir.y * 175.0F));
            } else if (level.isClientLevel()) {
                Screen.playSound(GameResources.coins, SoundEffect.effect(player));
            }
        }
        System.out.println(currentCoins);
        return item;
    }

    public Supplier<ContainerActionResult> getInventoryRightClickAction(Container container, InventoryItem item, int slotIndex, ContainerSlot slot) {
        return container.client.playerMob.isInventoryExtended() ? () -> {
            int currentCoins = this.getCurrentCoins(item);
            if (currentCoins > 0) {
                ContainerSlot clientDraggingSlot = container.getClientDraggingSlot();
                Item coinItem = ItemRegistry.getItem("coin");
                int startItems = Math.min(currentCoins, coinItem.getStackSize());
                InventoryItem coins = new InventoryItem(coinItem, startItems);
                if (clientDraggingSlot.isClear()) {
                    this.saveCurrentCoins(item, currentCoins - coins.getAmount());
                    clientDraggingSlot.setItem(coins);
                    return new ContainerActionResult(2657165);
                } else {
                    if (clientDraggingSlot.getItem().canCombine(container.client.playerMob.getLevel(), container.client.playerMob, coins, "pouchtransfer") && clientDraggingSlot.getItem().combine(container.client.playerMob.getLevel(), container.client.playerMob, coins, coins.getAmount(), "pouchtransfer").success) {
                        int itemsCombined = startItems - coins.getAmount();
                        this.saveCurrentCoins(item, currentCoins - itemsCombined);
                    }

                    return new ContainerActionResult(10619587);
                }
            } else {
                return new ContainerActionResult(3401846);
            }
        } : null;
    }

    public boolean canCombineItem(Level level, PlayerMob player, InventoryItem me, InventoryItem him, String purpose) {
        if (him == null) {
            return false;
        } else {
            return this.getID() == him.item.getID() || this.combinePurposes.contains(purpose) && him.item.getStringID().equals("coin");
        }
    }

    public boolean onCombine(Level level, PlayerMob player, InventoryItem me, InventoryItem other, int maxStackSize, int amount, String purpose) {
        if (this.combinePurposes.contains(purpose) && other.item.getStringID().equals("coin")) {
            this.saveCurrentCoins(me, this.getCurrentCoins(me) + amount);
            other.setAmount(other.getAmount() - amount);
            return true;
        } else {
            return super.onCombine(level, player, me, other, maxStackSize, amount, purpose);
        }
    }

    public ComparableSequence<Integer> getInventoryAddPriority(Level level, PlayerMob player, Inventory inventory, int inventorySlot, InventoryItem item, InventoryItem input, String purpose) {
        ComparableSequence<Integer> last = super.getInventoryAddPriority(level, player, inventory, inventorySlot, item, input, purpose);
        return input.item.getStringID().equals("coin") && purpose.equals("itempickup") ? last.beforeBy(-10000) : last;
    }

    public int getInventoryAmount(Level level, PlayerMob player, InventoryItem item, Item requestItem, String purpose) {
        return purpose.equals("buy") && requestItem.getStringID().equals("coin") ? this.getCurrentCoins(item) : super.getInventoryAmount(level, player, item, requestItem, purpose);
    }

    public void countIngredientAmount(Level level, PlayerMob player, Inventory inventory, int inventorySlot, InventoryItem item, IngredientCounter handler) {
        handler.handle((Inventory)null, inventorySlot, new InventoryItem("coin", this.getCurrentCoins(item)));
        super.countIngredientAmount(level, player, inventory, inventorySlot, item, handler);
    }

    public boolean inventoryAddItem(Level level, PlayerMob player, InventoryItem item, InventoryItem input, String purpose, boolean isValid, int stackLimit) {
        if (!input.item.getStringID().equals("coin") || !purpose.equals("itempickup") && !purpose.equals("sell")) {
            return super.inventoryAddItem(level, player, item, input, purpose, isValid, stackLimit);
        } else {
            this.saveCurrentCoins(item, this.getCurrentCoins(item) + input.getAmount());
            input.setAmount(0);
            return true;
        }
    }

    public int inventoryCanAddItem(Level level, PlayerMob player, InventoryItem item, InventoryItem input, String purpose, boolean isValid, int stackLimit) {
        return input.item.getStringID().equals("coin") ? input.getAmount() : super.inventoryCanAddItem(level, player, item, input, purpose, isValid, stackLimit);
    }

    public int removeInventoryAmount(Level level, PlayerMob player, InventoryItem item, Item requestItem, int amount, String purpose) {
        return requestItem.getStringID().equals("coin") ? this.removeCoins(item, amount) : super.removeInventoryAmount(level, player, item, requestItem, amount, purpose);
    }

    public int removeInventoryAmount(Level level, PlayerMob player, InventoryItem item, Ingredient ingredient, int amount) {
        Item coin = ItemRegistry.getItem("coin");
        return ingredient.matchesItem(coin) ? this.removeCoins(item, amount) : super.removeInventoryAmount(level, player, item, ingredient, amount);
    }

    private int removeCoins(InventoryItem item, int amount) {
        int currentCoins = this.getCurrentCoins(item);
        int removedAmount = Math.min(currentCoins, amount);
        currentCoins -= removedAmount;
        this.saveCurrentCoins(item, currentCoins);
        return removedAmount;
    }

    protected int getCurrentCoins(InventoryItem item) {
        return item.getGndData().getInt("coins");
    }

    protected void saveCurrentCoins(InventoryItem item, int coins) {
        item.getGndData().setInt("coins", coins);
    }*/
}
