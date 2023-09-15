package net.phoi.rot.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.phoi.rot.RelicsOfTime;

@Mod.EventBusSubscriber(modid = RelicsOfTime.MODID, bus =  Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new ModBlockStateProvider(generator, fileHelper));
        generator.addProvider(true, new ModItemModelProvider(generator, fileHelper));
    }
}