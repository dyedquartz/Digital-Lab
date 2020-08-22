package com.thinkslynk.digital_lab.block

import com.thinkslynk.digital_lab.DigitalLabMod
import com.thinkslynk.digital_lab.entity.ComputerCoreEntity
import com.thinkslynk.fabric.annotations.registry.RegisterBlock
import com.thinkslynk.fabric.annotations.registry.RegisterItem
import com.thinkslynk.fabric.helpers.AnnotationHelpers
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.spi.LoggerAdapter

@RegisterBlock(DigitalLabMod.identifier, ComputerCore.NAME)
@RegisterItem(DigitalLabMod.identifier, ComputerCore.NAME, AnnotationHelpers.ItemGroup.MISC)
class ComputerCore:
    Block(
        FabricBlockSettings
            .of(Material.METAL)
            .hardness(4.0f)
    ),
    BlockEntityProvider
{
    companion object {
        const val NAME = "computer_core"
        private val log = LogManager.getLogger(ComputerCore::class.java)
    }

    override fun createBlockEntity(world: BlockView): BlockEntity {
        return ComputerCoreEntity()
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        log.info("Player used computer core!")
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos))
        return ActionResult.SUCCESS
    }
}