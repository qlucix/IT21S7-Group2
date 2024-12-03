package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class NPC_Merchant extends Entity {

    public NPC_Merchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
        up1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        up3 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up4 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down3 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down4 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left3 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left4 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right3 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right4 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0][0] = "You found me! He he. \nI have the good stuff. \nDo you want to trade?";
        dialogues[1][0] = "Come again, hehe!";
        dialogues[2][0] = "You need more coin to buy that!";
        dialogues[3][0] = "Your inventory is full!";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    public void setItems() {
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Lantern(gp));
    }

    public void speak() {

    	facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
