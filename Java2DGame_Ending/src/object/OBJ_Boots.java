package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {

	public static final String objName = "Boots";
	
    public OBJ_Boots(GamePanel gp) {
        super(gp);
        
        name = objName;
        description = "[Boots]\n a fashion statement";
        price = 1999;
        down1 = setup("/res/objects/boots", gp.tileSize, gp.tileSize);
    }
}
