package com.thinkslynk.digital_lab

import com.thinkslynk.digital_lab.block.ComputerCore
import com.thinkslynk.digital_lab.gui.ExampleGuiDescription
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
    lateinit var SCREEN_HANDLER_TYPE: ScreenHandlerType<ExampleGuiDescription>

    override fun onInitialize() {
        log.info("Initializing digital lab...")
        BlockRegistryGenerated.register()
        ItemRegistryGenerated.register()
        BlockEntityRegistryGenerated.register()
        SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(
            Identifier(identifier, ComputerCore.NAME)
        ) { syncId: Int, inventory: PlayerInventory ->
            ExampleGuiDescription(
                SCREEN_HANDLER_TYPE,
                syncId,
                inventory,
                ScreenHandlerContext.EMPTY
            )
        }

    }
}