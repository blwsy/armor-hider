package net.blwsy.armorhider.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.blwsy.armorhider.config.Config;

@SideOnly(Side.CLIENT)
public class RenderListener {
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    @SubscribeEvent
    public void preRenderEntity(RenderLivingEvent.Pre<?> e) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!Config.enable.getBoolean() || !e.entity.equals(Minecraft.getMinecraft().thePlayer)) {
            return;
        }
        EntityPlayerSP player = mc.thePlayer;
        InventoryPlayer inventory = player.inventory;
        ItemStack[] armorInventory = inventory.armorInventory;
        if (Config.helmet.getBoolean()) {
            helmet = armorInventory[3];
            armorInventory[3] = null;
        }
        if (Config.chestplate.getBoolean()) {
            chestplate = armorInventory[2];
            armorInventory[2] = null;
        }
        if (Config.leggings.getBoolean()) {
            leggings = armorInventory[1];
            armorInventory[1] = null;
        }
        if (Config.boots.getBoolean()) {
            boots = armorInventory[0];
            armorInventory[0] = null;
        }
    }

    @SubscribeEvent
    public void postRenderEntity(RenderLivingEvent.Post<?> e) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!e.entity.equals(Minecraft.getMinecraft().thePlayer)) {
            return;
        }
        if (helmet == null && chestplate == null && leggings == null && boots == null) {
            return;
        }
        EntityPlayerSP player = mc.thePlayer;
        InventoryPlayer inventory = player.inventory;
        ItemStack[] armourInventory = inventory.armorInventory;
        if (helmet != null) {
            armourInventory[3] = helmet;
        }
        if (chestplate != null) {
            armourInventory[2] = chestplate;
        }
        if (leggings != null) {
            armourInventory[1] = leggings;
        }
        if (boots != null) {
            armourInventory[0] = boots;
        }
        helmet = chestplate = leggings = boots = null;
    }
}
