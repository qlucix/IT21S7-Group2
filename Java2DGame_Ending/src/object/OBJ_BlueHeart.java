package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity {

    GamePanel gp;
    public static final String objName = "Blue Heart";
    public OBJ_BlueHeart(GamePanel gp)
    {
        super(gp);

        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        down1 = setup("/res/objects/blueheart", gp.tileSize, gp.tileSize);
        setDialogues();
    }
    public void setDialogues()
    {
        dialogues[0][0] = "You pick up a beautiful blue gem.";
        dialogues[0][1] = "You found the Blue Heart, the legendary treasure!";
        dialogues[0][2] = "Congratulations!";
    }
    public boolean use(Entity entity) //when pickup this method will be called
    {
        gp.playSE(4);
        startDialogue(this, 0);
        gp.gameState = gp.endingState;
    	return true;
    }

}
