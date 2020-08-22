package com.thinkslynk.digital_lab.client

import com.thinkslynk.digital_lab.DigitalLabMod
import com.thinkslynk.digital_lab.gui.ExampleBlockScreen
import com.thinkslynk.digital_lab.gui.ExampleGuiDescription
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import org.apache.logging.log4j.LogManager


object DigitalLabModClient: ClientModInitializer {
    val log = LogManager.getLogger(DigitalLabModClient::class.java)

    override fun onInitializeClient() {
        log.info("Initializing digital lab mod client side...")
        ScreenRegistry.register<ExampleGuiDescription, ExampleBlockScreen>(
            DigitalLabMod.SCREEN_HANDLER_TYPE
        ) { gui: ExampleGuiDescription, inventory: PlayerInventory, title: Text ->
            ExampleBlockScreen(
                gui, inventory.player, title
            )
        }

    }
}