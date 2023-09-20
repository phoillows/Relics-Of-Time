package net.phoi.rot.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.Concavenator;
import net.phoi.rot.level.entity.Platyhystrix;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class PlatyhystrixModel extends AnimatedGeoModel<Platyhystrix> {

    @Override
    public ResourceLocation getModelResource(Platyhystrix entity) {
        return new ResourceLocation(RelicsOfTime.MODID, "geo/platyhystrix.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Platyhystrix entity) {
        if (entity.isSleeping()) {
            return new ResourceLocation(RelicsOfTime.MODID, "textures/entity/platyhystrix/platyhystrix_sleeping.png");
        }
        return new ResourceLocation(RelicsOfTime.MODID, "textures/entity/platyhystrix/platyhystrix.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Platyhystrix entity) {
        return new ResourceLocation(RelicsOfTime.MODID, "animations/platyhystrix.animation.json");
    }

    @Override
    public void setCustomAnimations(Platyhystrix animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("Head");
        EntityModelData modelData = (EntityModelData)animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null && !animatable.isSleeping()) {
            head.setRotationX(modelData.headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(modelData.netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}
