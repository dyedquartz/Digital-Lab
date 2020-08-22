package com.thinkslynk.digital_lab.gui

import com.thinkslynk.digital_lab.entity.ComputerCoreEntity
import io.github.cottonmc.cotton.gui.SyncedGuiDescription
import io.github.cottonmc.cotton.gui.widget.WGridPanel
import io.github.cottonmc.cotton.gui.widget.WItemSlot
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.ScreenHandlerType

class ExampleGuiDescription(
    type: ScreenHandlerType<*>,
    syncId: Int,
    playerInventory: PlayerInventory,
    context: ScreenHandlerContext
) : SyncedGuiDescription(
        type,
        syncId,
        playerInventory,
        getBlockInventory(context, ComputerCoreEntity.INVENTORY_SIZE),
        getBlockPropertyDelegate(context)
    ) {

    init {
        val root = WGridPanel()
        setRootPanel(root)
        root.setSize(300, 200)
        val itemSlot: WItemSlot = WItemSlot.of(blockInventory, 0)
        root.add(itemSlot, 4, 1)
        root.add(this.createPlayerInventoryPanel(), 0, 3)
        root.validate(this)
    }
}