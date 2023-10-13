package net.phoi.rot.level.feature.tree;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.phoi.rot.registry.ConfiguredFeatureRegistry;
import org.jetbrains.annotations.Nullable;

public class ArchaeopterisTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        return ConfiguredFeatureRegistry.ARCHAEOPTERIS.getHolder().get();
    }
}
