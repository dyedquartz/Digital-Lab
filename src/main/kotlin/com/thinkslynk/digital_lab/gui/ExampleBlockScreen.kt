package com.thinkslynk.digital_lab.gui

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import org.apache.logging.log4j.LogManager

class ExampleBlockScreen(gui: ExampleGuiDescription, player: PlayerEntity, title: Text) :
    CottonInventoryScreen<ExampleGuiDescription>(gui, player, title){
    companion object{
        val log = LogManager.getLogger(ExampleBlockScreen::class.java)
    }

    init {
        log.info("Creating ExampleBlockScreen...")
    }
}