package de.thelooter.iosteinpolls.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public enum Heads {

    //https://minesk.in/9670416471a24114844c158116e8721c
    CHECKMARK("ewogICJ0aW1lc3RhbXAiIDogMTU5Mzg3Mjg4MzQyNCwKICAicHJvZmlsZUlkIiA6ICI1YjFlNGY3NDI0Yjg0OWIwODIwZTAwNThiNzJlYTBkZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTaXR1cHMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDI1MjNkYWQ1MDdkZGFkOGI5OGU4MWJhNTU0ZjI1MDI3OTAxYWVhOWY3MTc5YTg4MjI0ODc4YjRlMmMwZTc1YSIKICAgIH0KICB9Cn0=",
            "iUYO5+kFgfsER+xRiUSQbmMtUZKC+Blt5173Nkk3nb+DPfMoQ0S4vgf17GF1FApwRZzQhB/kicfwwDiItYiOPsyrz40nmixtjqNBmTPRIAALN7J1HdMMzU8QSvnxqzKuxDw5N0w0u5jVSALzrokwAWIb7Jqjx5WnnQNfzVT3N3ALvN88ubrfT6E1n0NyNOZVvZvcearKhT1wrbLbvbykx0Zml9vj9Osk5QpnCiIPNfdIKfzMYvbwjUXbQxwzwZsQqhqAC+fofPydwoUWcNlsc8croQq3lUfXeUSFEc0tg9vhU0hyaePY1XyOwAQdB2eujCl6um7+ncKlXMr7MhE+ky0rwzaEHFYjcItf6w4UcoDak53vGoJcnm64grLWubKHjpBnx5MNuWb4rWAR/B6XrzMojYVsQtoGbareoqABd2iyH9G36FgdgGdnAbQYkPASQrSLAPNTuSxSXr0NBgLAh9DYPWKDQ13V9gsWmvxR/Y4STkGFWWlaxn7oDSyq8JlWrAfqXXrJcoNI/5KGdYsvGSR8o5Jq9Cq94nC2uSlsH+j2LT7EHAXzyPSQGOQiK+Cf3ddst+oBXvdC/UtKy/BM9xwTTxGHK2OvyXErp5CzoslHpUqpZTWR1dHkOOvZ+Az7o3R7VknB2C5o38XlpJroN8a878lptFqAdnT/lsXLYkM=");



    public final String texture;
    public final String signature;

    Heads(String texture, String signature) {
        this.texture = texture;
        this.signature = signature;
    }

    public ItemStack createHead(Heads head) {
        PlayerProfile profile = Bukkit.createProfile(UUID.fromString("b13e2665-af25-4044-a039-bdff4b222f00"), "checkmark");
        profile.setProperty(new ProfileProperty("textures", head.texture, head.signature));

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setPlayerProfile(profile);
        item.setItemMeta(meta);

        return item;
    }


}
