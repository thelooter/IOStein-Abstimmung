import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import de.thelooter.iosteinpolls.IOSteinPolls;
import de.thelooter.iosteinpolls.util.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ItemBuilderTest {

    static List<ItemStack> itemStacks = new ArrayList<>();

    public static ServerMock serverMock;
    public static IOSteinPolls plugin;

    @BeforeAll
    public static void setup() {

        serverMock = MockBukkit.mock();
        plugin =  (IOSteinPolls) MockBukkit.load(IOSteinPolls.class);

        itemStacks.add(new ItemBuilder(Material.STONE).setName("Stone123").toItemStack());
        itemStacks.add(new ItemBuilder(new ItemStack(Material.STONE)).toItemStack());
        itemStacks.add(new ItemBuilder(Material.STONE, 1, (byte) 2).toItemStack());
        itemStacks.add(new ItemBuilder(Material.STONE).clone().toItemStack());
        itemStacks.add(new ItemBuilder(Material.STONE_AXE).setDurability((short) 2).toItemStack());
        itemStacks.add(new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2).toItemStack());
        itemStacks.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack());
        itemStacks.add(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).removeEnchantment(Enchantment.DAMAGE_ALL).toItemStack());
        itemStacks.add(new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner("thelooter").toItemStack());
        itemStacks.add(new ItemBuilder(Material.DIAMOND_SWORD).setInfinityDurability().toItemStack());
        itemStacks.add(new ItemBuilder(Material.DIAMOND_SWORD).setLore("1", "2", "3").toItemStack());
        itemStacks.add(new ItemBuilder(Material.DIAMOND_SWORD).setLore(List.of("1", "2", "3")).toItemStack());

    }
    @Test
    public void testItemBuilder() {

        assertThat(itemStacks.get(0).getItemMeta().getDisplayName(), equalTo("Stone123"));
        assertThat(itemStacks.get(1).getType(), equalTo(Material.STONE));
        assertThat(itemStacks.get(2).getType(), equalTo(Material.STONE));
        assertThat(itemStacks.get(3).getType(), equalTo(Material.STONE));
        assertThat(itemStacks.get(4).getDurability(), equalTo((short) 2));
        assertThat(itemStacks.get(5).getItemMeta().hasEnchant(Enchantment.DAMAGE_ALL), equalTo(true));
        assertThat(itemStacks.get(6).getItemMeta().hasEnchant(Enchantment.DAMAGE_ALL), equalTo(true));
        assertThat(itemStacks.get(7).getItemMeta().hasEnchant(Enchantment.DAMAGE_ALL), equalTo(false));
        assertThat(((SkullMeta)itemStacks.get(8).getItemMeta()).getOwningPlayer().getName(),equalTo("thelooter"));
        assertThat(itemStacks.get(9).getDurability(), equalTo(Short.MAX_VALUE));
        assertThat(itemStacks.get(10).getLore().containsAll(List.of("1", "2", "3")), equalTo(true));



    }

    @Test
    public void testWrongMaterialForSetSkullOwner() {
        boolean thrown = false;
        try {
            new ItemBuilder(Material.STONE).setSkullOwner("thelooter").toItemStack();
        } catch (ClassCastException e) {
            thrown = true;
            e.printStackTrace();
        }

        assertThat(thrown, equalTo(true));
    }

}
