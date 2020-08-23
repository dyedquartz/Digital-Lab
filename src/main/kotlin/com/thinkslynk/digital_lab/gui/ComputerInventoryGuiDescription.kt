package com.thinkslynk.digital_lab.gui

import com.thinkslynk.digital_lab.entity.ComputerCoreEntity
import io.github.cottonmc.cotton.gui.SyncedGuiDescription
import io.github.cottonmc.cotton.gui.widget.WGridPanel
import io.github.cottonmc.cotton.gui.widget.WItemSlot
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.ScreenHandlerType
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ComputerInventoryGuiDescription(
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

    companion object{
        val log: Logger = LogManager.getLogger(ComputerInventoryGuiDescription::class.java)
    }

    init {
        log.info("Initializing...")

        // Setup our root panel
        val root = WGridPanel()
        setRootPanel(root)

        // Player Inventory
        val playerInventoryPanel = this.createPlayerInventoryPanel()

        // Storage Inventory
        val inventorySlots = blockInventory.size()
        val rows = (((inventorySlots - 1) / 18) + 1)
            .coerceAtMost(16)
        val displaySlotCount = inventorySlots
            .coerceAtMost(rows * 18)

        (0 until displaySlotCount)
        .forEach {
            val itemSlot: WItemSlot = WItemSlot.of(blockInventory, it)
            root.add(itemSlot, it%18, (it/18) + 1)
        }
        root.add(playerInventoryPanel, 0, rows + 2)

        root.setSize(playerInventoryPanel.width, playerInventoryPanel.height + (rows * 18))

        root.validate(this)
        log.info("Initialized!")
    }
}