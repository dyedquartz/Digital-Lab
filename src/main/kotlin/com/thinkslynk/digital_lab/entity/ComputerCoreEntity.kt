package com.thinkslynk.digital_lab.entity

import com.thinkslynk.digital_lab.DigitalLabMod
import com.thinkslynk.digital_lab.block.ComputerCore
import com.thinkslynk.digital_lab.gui.ExampleGuiDescription
import com.thinkslynk.fabric.generated.BlockEntityRegistryGenerated
import com.thinkslynk.fabric.annotations.registry.RegisterBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.collection.DefaultedList

@RegisterBlockEntity(DigitalLabMod.identifier, ComputerCore.NAME, ["COMPUTER_CORE"])
class ComputerCoreEntity:
    BlockEntity(BlockEntityRegistryGenerated.COMPUTER_CORE_ENTITY),
    BaseInventory,
    NamedScreenHandlerFactory
{
    companion object{
        const val INVENTORY_SIZE = 1
    }
    private val items = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY)

    override fun getItems(): DefaultedList<ItemStack> = items

    override fun canPlayerUse(player: PlayerEntity): Boolean =
        pos.isWithinDistance(player.blockPos, 4.5)

    override fun fromTag(state: BlockState, tag: CompoundTag) {
        super.fromTag(state, tag)
        Inventories.fromTag(tag,items)
    }

    override fun toTag(tag: CompoundTag): CompoundTag {
        Inventories.toTag(tag,items)
        return super.toTag(tag)
    }

    override fun markDirty() {}
    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler =
        ExampleGuiDescription(
            DigitalLabMod.SCREEN_HANDLER_TYPE,
            syncId, inv,
            ScreenHandlerContext.create(world, pos)
        )

    override fun getDisplayName(): Text = LiteralText("")
}