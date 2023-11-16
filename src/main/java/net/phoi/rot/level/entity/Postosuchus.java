package net.phoi.rot.level.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.phoi.rot.level.entity.ai.DinosaurLookAroundGoal;
import net.phoi.rot.level.entity.ai.DinosaurLookAtPlayerGoal;
import net.phoi.rot.level.entity.ai.DinosaurSleepGoal;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Postosuchus extends Dinosaur implements IAnimatable {
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    protected static final AnimationBuilder IDLE = new AnimationBuilder().loop("idle");
    protected static final AnimationBuilder WALK = new AnimationBuilder().loop("walk");
    protected static final AnimationBuilder RUN = new AnimationBuilder().loop("run");
    protected static final AnimationBuilder SLEEP = new AnimationBuilder().loop("sleep");
    protected static final AnimationBuilder BITE = new AnimationBuilder().loop("bite");

    public Postosuchus(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.3D, true));
        this.goalSelector.addGoal(2, new DinosaurSleepGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new DinosaurLookAtPlayerGoal(this));
        this.goalSelector.addGoal(4, new DinosaurLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.15F)
                .add(Attributes.ATTACK_DAMAGE, 3.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2F);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "Controller", 5, (event -> {
            if (this.isSleeping()) {
                event.getController().setAnimation(SLEEP);
            } else if (event.isMoving()) {
                event.getController().setAnimation(this.isSprinting() ? RUN : WALK);
            } else {
                event.getController().setAnimation(IDLE);
            }
            return PlayState.CONTINUE;
        })));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }
}
