package com.opryshok.block;

import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.BlockBoundAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.nucleoid.packettweaker.PacketContext;

import static com.opryshok.BorukvaFood.id;

public class PolySlabBlock extends SlabBlock implements FactoryBlock {
    private final String path;

    public PolySlabBlock(Settings settings, String path) {
        super(settings);
        this.path = path;
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.OAK_SLAB.getDefaultState();
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return Blocks.OAK_SLAB.getDefaultState()
                .with(TYPE, state.get(TYPE))
                .with(WATERLOGGED, state.get(WATERLOGGED));
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        return new Model(initialBlockState, path);
    }

    public static final class Model extends BlockModel {
        public final ItemStack MODEL_BOTTOM;
        public final ItemStack MODEL_TOP;
        public final ItemStack MODEL_DOUBLE;
        public ItemDisplayElement main;

        public Model(BlockState state, String path) {
            MODEL_BOTTOM = ItemDisplayElementUtil.getModel(id("block/" + path));
            MODEL_TOP = ItemDisplayElementUtil.getModel(id("block/" + path + "_top"));
            MODEL_DOUBLE = ItemDisplayElementUtil.getModel(id("block/" + path.replace("_slab", "_planks")));
            main = ItemDisplayElementUtil.createSimple();
            main.setTeleportDuration(0);
            main.setInterpolationDuration(0);
            updateItem(state);
            addElement(main);
        }

        private void updateItem(BlockState state) {
            main.setItem(switch (state.get(TYPE)) {
                case BOTTOM -> MODEL_BOTTOM;
                case TOP -> MODEL_TOP;
                case DOUBLE -> MODEL_DOUBLE;
            });
            float scale = 1.004f;
            main.setScale(new Vector3f(2 * scale));
            float scaleOffset = (scale - 1) / 4;
            main.setTranslation(new Vector3f(scaleOffset, scaleOffset, scaleOffset));
        }

        @Override
        public void notifyUpdate(HolderAttachment.UpdateType updateType) {
            if (updateType == BlockBoundAttachment.BLOCK_STATE_UPDATE) {
                updateItem(this.blockState());
                this.tick();
            }
            super.notifyUpdate(updateType);
        }
    }
}
