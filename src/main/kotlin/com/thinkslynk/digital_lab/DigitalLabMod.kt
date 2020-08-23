package com.thinkslynk.digital_lab

import com.thinkslynk.digital_lab.block.ComputerCore
import com.thinkslynk.digital_lab.gui.ComputerInventoryGuiDescription
import com.thinkslynk.fabric.generated.BlockEntityRegistryGenerated
import com.thinkslynk.fabric.generated.BlockRegistryGenerated
import com.thinkslynk.fabric.generated.ItemRegistryGenerated
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager


object DigitalLabMod: ModInitializer {
    private val log = LogManager.getLogger(DigitalLabMod::class.java)
    const val identifier = "digital_lab"
    lateinit var COMPUTER_INVENTORY_GUI: ScreenHandlerType<ComputerInventoryGuiDescription>

    override fun onInitialize() {
        log.info("Initializing digital lab...")
        BlockRegistryGenerated.register()
        ItemRegistryGenerated.register()
        BlockEntityRegistryGenerated.register()
        COMPUTER_INVENTORY_GUI = ScreenHandlerRegistry.registerSimple(
            Identifier(identifier, ComputerCore.NAME)
        ) { syncId: Int, inventory: PlayerInventory ->
            ComputerInventoryGuiDescription(
                COMPUTER_INVENTORY_GUI,
                syncId,
                inventory,
                ScreenHandlerContext.EMPTY
            )
        }

    }
}