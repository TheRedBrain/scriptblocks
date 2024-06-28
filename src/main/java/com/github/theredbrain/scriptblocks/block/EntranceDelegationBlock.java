package com.github.theredbrain.scriptblocks.block;

import com.github.theredbrain.scriptblocks.block.entity.EntranceDelegationBlockEntity;
import com.github.theredbrain.scriptblocks.entity.player.DuckPlayerEntityMixin;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EntranceDelegationBlock extends RotatedBlockWithEntity {
    public EntranceDelegationBlock(Settings settings) {
        super(settings);
    }

    // TODO Block Codecs
    public MapCodec<EntranceDelegationBlock> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EntranceDelegationBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof EntranceDelegationBlockEntity entranceDelegationBlockEntity && player.isCreativeLevelTwoOp()) {
            ((DuckPlayerEntityMixin) player).scriptblocks$openEntranceDelegationBlockScreen(entranceDelegationBlockEntity);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}
