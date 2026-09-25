package com.hlpriver.aevmod.screens;

import com.hlpriver.aevmod.PlatinumCore;
import com.hlpriver.aevmod.container.ChestItemContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ItemChestScreen extends ContainerScreen<ChestItemContainer> {
    private final ResourceLocation GUI = new ResourceLocation(PlatinumCore.MOD_ID,"textures/gui/container/platinum_chest.png");
    public ItemChestScreen(ChestItemContainer screenC, PlayerInventory playerInventory, ITextComponent textComponent){
        super(screenC,playerInventory,textComponent);

        this.xSize=176;
        // Set correct ySize for 9 rows chest
        this.ySize=276;

        this.guiLeft=(this.width-this.xSize)/2;
        this.guiTop=(this.height-this.ySize)/2;
    }

    @Override
    public void render(MatrixStack m,int mouseX,int mouseY,float particleTicks){
        this.renderBackground(m);
        super.render(m,mouseX,mouseY,particleTicks);
        this.renderHoveredTooltip(m,mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(GUI);
        }
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack,i,j,0,0,this.xSize,this.ySize,256,512);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        String title = this.title.getString();
        this.font.drawString(matrixStack,title,8.0F,6.0F,0x404040);
        this.font.drawText(matrixStack,this.playerInventory.getDisplayName(),8.0F,182.0F,0x404040);
    }
}