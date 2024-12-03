package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Orc extends Entity {
    GamePanel gp; // cuz of different package
    public MON_Orc(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 2;
        defense = 1;
        exp = 10;
        knockBackPower = 3;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
//        motion1_duration = 40;
//        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage()
    {
        up1 = setup("/res/monster/orc_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/monster/orc_up_2",gp.tileSize,gp.tileSize);
        up3 = setup("/res/monster/orc_up_1",gp.tileSize,gp.tileSize);
        up4 = setup("/res/monster/orc_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/res/monster/orc_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/res/monster/orc_down_2",gp.tileSize,gp.tileSize);
        down3 = setup("/res/monster/orc_down_1",gp.tileSize,gp.tileSize);
        down4 = setup("/res/monster/orc_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/monster/orc_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/monster/orc_left_2",gp.tileSize,gp.tileSize);
        left3 = setup("/res/monster/orc_left_1",gp.tileSize,gp.tileSize);
        left4 = setup("/res/monster/orc_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/monster/orc_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/monster/orc_right_2",gp.tileSize,gp.tileSize);
        right3 = setup("/res/monster/orc_right_1",gp.tileSize,gp.tileSize);
        right4 = setup("/res/monster/orc_right_2",gp.tileSize,gp.tileSize);
    }
    public void getAttackImage()
    {
        attackUp1 = setup("/res/monster/orc_attack_up_1",gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/res/monster/orc_attack_up_2",gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/res/monster/orc_attack_down_1",gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/res/monster/orc_attack_down_2",gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/res/monster/orc_attack_left_1",gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/res/monster/orc_attack_left_2",gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/res/monster/orc_attack_right_1",gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/res/monster/orc_attack_right_2",gp.tileSize * 2, gp.tileSize);
    }
    public void setAction()
    {
        if(onPath == true)
        {
            //Check if it stops chasing
            checkStopChasingOrNot(gp.player,15,100);

            //Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }
        else
        {
            //Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);

            //Get a random direction
            getRandomDirection();
        }

        //Check if it is attacks
        if(attacking == false)
        {
        	checkAttackOrNot(10, gp.tileSize*4, gp.tileSize); //Small rate = More agressive
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true; // gets aggro
    }
    public void checkDrop()
    {

        //SET THE MONSTER DROP
       
            dropItem(new OBJ_Key(gp));
       
    }
}
