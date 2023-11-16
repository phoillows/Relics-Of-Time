package net.phoi.rot.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.block.entity.DnaCentrifugeBlockEntity;
import org.jetbrains.annotations.Nullable;

public class DnaCentrifugeBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public DnaCentrifugeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof DnaCentrifugeBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer)player, (DnaCentrifugeBlockEntity)blockEntity, pos);
            } else {
                RelicsOfTime.LOGGER.error("Incorrect block entity");
            }
        }
        return super.use(state, level, pos, player, hand, blockHitResult);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DnaCentrifugeBlockEntity(pos, state);
    }
}
