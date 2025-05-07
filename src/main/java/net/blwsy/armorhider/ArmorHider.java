package net.blwsy.armorhider;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.blwsy.armorhider.command.CommandArmorHider;
import net.blwsy.armorhider.config.Config;
import net.blwsy.armorhider.listener.RenderListener;

@Mod(
        modid = ArmorHider.MODID,
        version = ArmorHider.VERSION,
        clientSideOnly = true
)
@SideOnly(Side.CLIENT)
public class ArmorHider {
    public static final String MODID = "armorhider";
    public static final String VERSION = "@VERSION@";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.init(event.getSuggestedConfigurationFile());
        Config.reloadConfig();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new RenderListener());

        ClientCommandHandler.instance.registerCommand(new CommandArmorHider());
    }
}
