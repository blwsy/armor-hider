package net.blwsy.armorhider.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public class Config {
    private static File configFile;
    private static Configuration config;

    public static Property enable;
    public static Property helmet;
    public static Property chestplate;
    public static Property leggings;
    public static Property boots;

    public static void init(File configFile) {
        Config.configFile = configFile;
    }

    public static void reloadConfig() {
        config = new Configuration(configFile);
        config.load();

        final String category = "configuration";
        enable = config.get(category, "enable", false, "Enable armor hiding");
        helmet = config.get(category, "helmet", true, "Hide helmet");
        chestplate = config.get(category, "chestplate", true, "Hide chestplate");
        leggings = config.get(category, "leggings", true, "Hide leggings");
        boots = config.get(category, "boots", true, "Hide boots");

        saveConfig();
    }

    public static void saveConfig() {
        config.save();
    }
}
