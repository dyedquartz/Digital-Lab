package com.thinkslynk.digital_lab.client

import com.thinkslynk.digital_lab.DigitalLabMod
import com.thinkslynk.digital_lab.gui.ExampleBlockScreen
import com.thinkslynk.digital_lab.gui.ExampleGuiDescription
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text


class DigitalLabModClient: ClientModInitializer {

    override fun onInitializeClient() {
        ScreenRegistry.register(
            DigitalLabMod.SCREEN_HANDLER_TYPE
        ) { gui: ExampleGuiDescription, inventory: PlayerInventory, title: Text ->
            ExampleBlockScreen(
                gui, inventory.player, title
            )
        }

    }
}