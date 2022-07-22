package com.jazzkuh.gunshell.utils;

import com.cryptomorin.xseries.XMaterial;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class PluginUtils {
    private static @Getter @Setter(AccessLevel.PRIVATE) PluginUtils instance;

    public PluginUtils() {
        setInstance(this);
    }

    public Optional<ItemStack> getItemWithNBTTag(Player player, String tag, String value) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && NBTEditor.contains(item, tag)) {
                if (NBTEditor.getString(item, tag).equals(value)) {
                    return Optional.of(item);
                }
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    public void applyNBTTag(ItemStack itemStack, String key, Object value) {
        ItemStack newItemStack = NBTEditor.set(itemStack, value, key);
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemStack.setItemMeta(itemMeta);
    }

    public Material getMaterial(String materialName) {
        if (XMaterial.matchXMaterial(materialName).isPresent()) {
            return XMaterial.matchXMaterial(materialName).get().parseMaterial();
        }

        return null;
    }
}
