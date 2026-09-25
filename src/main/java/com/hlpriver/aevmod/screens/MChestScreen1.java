package com.hlpriver.aevmod.screens;

import com.hlpriver.aevmod.PlatinumCore;
import com.hlpriver.aevmod.container.CChestContainer1;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MChestScreen1 extends ContainerScreen<CChestContainer1> {
    private final ResourceLocation GUI_1 = new ResourceLocation(PlatinumCore.MOD_ID,"textures/gui/container/platinum_chest_1.png");
    public MChestScreen1(CChestContainer1 screenC1, PlayerInventory playerInventory1, ITextComponent textComponent1){
        super(screenC1,playerInventory1,textComponent1);

        this.xSize=194;
        // Set correct ySize for 10 rows chest
        this.ySize=294;

        this.guiLeft=(this.width-this.xSize)/2;
        this.guiTop=(this.height-this.ySize)/2;
    }
    //先行渲染
    @Override
    public void render(MatrixStack m,int mouseX,int mouseY,float particleTicks){
        this.renderBackground(m);
        super.render(m,mouseX,mouseY,particleTicks);
        this.renderHoveredTooltip(m,mouseX,mouseY);
    }
    //加载绘制大小及渲染背景
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(GUI_1);
        }
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack,i,j,0,0,this.xSize,this.ySize,256,512);
    }
    //添加标题
    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        //显示“箱子”标题
        String title = this.title.getString();
        this.font.drawString(matrixStack,title,8.0F,6.0F,0x404040);
        //显示“物品栏”文本
        this.font.drawText(matrixStack,this.playerInventory.getDisplayName(),17.0F,199.0F,0x404040);
    }
}