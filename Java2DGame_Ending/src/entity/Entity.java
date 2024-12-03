package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
    GamePanel gp;

    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, 
    left2, left3, left4, right1, right2, right3, right4;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2,
            guardUp, guardDown, guardLeft, guardRight;
    
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public String dialogues[][] = new String[20][20];
    public Entity attacker;

    // STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    public int standCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;

    // CHARACTER ATTRIBUTES
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;

    // ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    // TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public int getLeftX () {
    	return worldX + solidArea.x;
    }
    public int getRightX () {
    	return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY() {
    	return worldX + solidArea.y;
    }
    public int getBottomY () {
    	return worldY + solidArea.y + solidArea.height;
    }
    public int getCol () {
    	return(worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow () {
    	return (worldY + solidArea.y)/gp.tileSize;
    }
    public int getXdistance(Entity target)
    {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYdistance(Entity target)
    {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }
    public int getTileDistance(Entity target)
    {
        int tileDistance = Math.abs(getXdistance(target) - getYdistance(target)/gp.tileSize);
        return tileDistance;
    }
    public int getGoalCol(Entity target)
    {
        int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target)
    {
        int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
        return goalRow;
    }
    public void resetCounter() {
    	
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        standCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }
    public void setLoot(Entity loot) {
    	
    }
    public void setAction() {
    }

    public void damageReaction() {
    }

    public void speak() {
    }
    public void facePlayer() {
    	
    	  switch (gp.player.direction) {
          case "up":
              direction = "down";
              break;
          case "down":
              direction = "up";
              break;
          case "left":
              direction = "right";
              break;
          case "right":
              direction = "left";
              break;
      }
  
    }
    public void startDialogue(Entity entity, int setNum) {
    	
    	gp.gameState = gp.dialogueState;
    	gp.ui.npc = entity;
    	dialogueSet = setNum;
    }
    public void interact() {
    	
    }

    public boolean use(Entity entity) {
    	return false;
    }

    public void checkDrop() {
    }

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor() {
        Color color = null;

        return color;
    }

    public int getParticleSize() {
        int size = 0;

        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;

        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 0;

        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true) {
            damagePlayer(attack);
        }
    }

    public void update() {

        if (knockBack == true) {
            checkCollision();

            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else if (collisionOn == false) {
                switch (knockBackDirection) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            knockBackCounter++;

            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            	}
            }
        	else if(attacking == true) {
        	attacking();
        	}
        	else {
            setAction();
            checkCollision();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            
            spriteCounter++;
            if (spriteCounter > 13) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                	spriteNum = 4;
                } else if (spriteNum == 4) {
    	        	spriteNum = 1;
    	        }
                spriteCounter = 0;
            }

        }

       
        
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if(offBalance == true) {
        	offBalanceCounter++;
        	if(offBalanceCounter > 60) {
        		offBalance = false;
        		offBalanceCounter = 0;
        	}
        }
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal) {
    	
    	boolean targetInRange = false;
    	int xDis = getXdistance(gp.player);
    	int yDis = getYdistance(gp.player);
    	
    	switch(direction) {
    	case "up":
    		if(gp.player.worldY < worldY && yDis < straight && xDis < horizontal) {
    			targetInRange = true;
    		}
    		break;
    	case "down":
    		if(gp.player.worldY > worldY && yDis < straight && xDis < horizontal) {
    			targetInRange = true;
    		}
    		break;
    	case "left":
    		if(gp.player.worldX < worldX && xDis < straight && yDis < horizontal) {
    			targetInRange = true;
    		}
    		break;
    	case "right":
    		if(gp.player.worldX > worldX && xDis < straight && yDis < horizontal) {
    			targetInRange = true;
    		}
    		break;
    	}
    	
    	if(targetInRange == true) {
    		//Check if it initiates an attack
    		int i = new Random().nextInt(rate);
    		if(i == 0) {
    			attacking = true;
    			spriteNum = 1;
    			spriteCounter = 0;
    		}
    	}
    }
    public void checkShootOrNot(int rate, int shotInterval) {
    	int i = new Random().nextInt(rate);

        if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);
            //gp.projectileList.add(projectile);

            // CHECK VACANCY
            for (int ii = 0; ii < gp.projectile[1].length; ii++) {
                if (gp.projectile[gp.currentMap][ii] == null) {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
    	if(getTileDistance(target) < distance) {
    		int i = new Random().nextInt(rate);
    		if(i == 0) {
    			onPath = true;
    		}
    	}
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
    	if(getTileDistance(target) > distance) {
    		int i = new Random().nextInt(rate);
    		if(i == 0) {
    			onPath = false;
    		}
    	}
    }
    public void getRandomDirection() {
    	
    	actionLockCounter++;
        
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
    	}
    }
    public String getOppositeDirection(String direction) {
    	
    	String oppositeDirection = "";
    	
    	switch (direction) {
    	case "up": oppositeDirection = "down"; break;
    	case "down": oppositeDirection = "up"; break;
    	case "left": oppositeDirection = "right"; break;
    	case "right": oppositeDirection = "left"; break;
    	}
    	return oppositeDirection;
    }
    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // SAVE CURRENT worldX/Y & solidArea
            int currentworldX = worldX;
            int currentworldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // ADJUST PLAYER worldX/Y FOR attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            // CHANGE attackArea INTO solidArea
            solidAreaWidth = attackArea.width;
            solidAreaHeight = attackArea.height;

            if(type == type_monster) {
            	if(gp.cChecker.checkPlayer(this)==true) {
            		damagePlayer(attack);
            	}
            } 
            else { //Player
            	int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }
            
            // CHECK MONSTER COLLISION WITH THE UPDATED worldX/Y & solidArea
            

            // RESTORE THE ORIGINAL DATA
            worldX = currentworldX;
            worldY = currentworldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void damagePlayer(int attack) {
    		
        if (gp.player.invincible == false) {
        	
        	int damage = attack - gp.player.defense;
        	
        	// get an opposite direction of this attacker 
        	String canGuardDirection = getOppositeDirection(direction);
        	
        	if(gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)) {
        		
        		// Parry
        		if(gp.player.guardCounter < 10 ) {
        			damage = 0;
        			gp.playSE(16);
        			setKnockBack(this, gp.player, knockBackPower);
        			offBalance = true;
        			spriteCounter =- 60;
        		}
        		else {
        			//normal guard
            		damage /= 3;
            		gp.playSE(15);
            	}	
        		}
        		
        	else {
        		// Not guarding
        		gp.playSE(6);
        		if (damage < 1) {
                    damage = 1;
                }
        	}
           
        	
        	if(damage != 0) {
        		gp.player.transparent = true;
        		setKnockBack(gp.player, this, knockBackPower);
        	}
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
    	
    	this.attacker = attacker;
    	target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

        	int tempScreenX = screenX;
        	int tempScreenY = screenY;
        	
        	switch(direction){
            case "up":
            	if(attacking == false) {
            		   if(spriteNum == 1){image = up1; }
                       if(spriteNum == 2){image = up2;}
                       if(spriteNum == 3){ image = up3; }
                       if(spriteNum == 4){ image = up4; }
            	}
            	if(attacking == true) {
            		tempScreenY = screenY - gp.tileSize;
            		if(spriteNum == 1){ image = attackUp1; }
                    if(spriteNum == 2){ image = attackUp2; }
                    if(spriteNum == 3){ image = attackUp1; }
                    if(spriteNum == 4){ image = attackUp2; }
            		
            	}
             
                break;
            case "down":
            	if(attacking == false) {
            		 if(spriteNum == 1){ image = down1; }
                     if(spriteNum == 2){image = down2;}
                     if(spriteNum == 3){ image = down3; }
                     if(spriteNum == 4){image = down4;}
            	}
            	if(attacking == true) {
            		if(spriteNum == 1){ image = attackDown1; }
                    if(spriteNum == 2){ image = attackDown2; }
                    if(spriteNum == 3){ image = attackUp1; }
                    if(spriteNum == 4){ image = attackUp2; }
            		
            	}
               
                break;
            case "left":
            	if(attacking == false) {
            		if(spriteNum == 1){image = left1; }
                    if(spriteNum == 2){ image = left2; }
                    if(spriteNum == 3){ image = left3;}
                    if(spriteNum == 4){ image = left4; }
            	}
            	if(attacking == true) {
            		tempScreenX = screenX - gp.tileSize;
            		if(spriteNum == 1){ image = attackLeft1; }
                    if(spriteNum == 2){ image = attackLeft2; }
                    if(spriteNum == 3){ image = attackUp1; }
                    if(spriteNum == 4){ image = attackUp2; }
            		
            	}
                
                break;
            case "right":
            	if(attacking == false) {
            		if(spriteNum == 1){ image = right1; }
                    if(spriteNum == 2){ image = right2; }
                    if(spriteNum == 3){ image = right3; }
                    if(spriteNum == 4){image = right4;  }
            	}
            	if(attacking == true) {
            		if(spriteNum == 1){ image = attackRight1; }
                    if(spriteNum == 2){ image = attackRight2; }
                    if(spriteNum == 3){ image = attackUp1; }
                    if(spriteNum == 4){ image = attackUp2; }
            		
            	}
                
                break;
        }

            // MOSNTER HEALTH BAR
            if (type == 2 && hpBarOn == true) {
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }

            if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);

            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }

        if (dyingCounter > i * 8) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search() == true) {
            // NEXT WORLD X / Y
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // ENTITY SOLID AREA POSITION
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // LEFT OR RIGHT
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // UP OR LEFT
                direction = "up";
                checkCollision();

                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // UP OR RIGHT
                direction = "up";
                checkCollision();

                if (collisionOn == true) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                // DOWN OR LEFT
                direction = "down";
                checkCollision();

                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                // DOWN OR RIGHT
                direction = "down";
                checkCollision();

                if (collisionOn == true) {
                    direction = "right";
                }
            }

            // IF REACH ThE GOAL, STOP THE SEARCH
            // int nextCol = gp.pFinder.pathList.get(0).col;
            // int nextRow = gp.pFinder.pathList.get(0).row;

            // if (nextCol == goalCol && nextRow == goalRow) {
            // onPath = false;
            // }
        }
    }
    public int getDetected (Entity user, Entity target [] [], String targetName){
    	
    	int index = 999;
    	//chech the surrounding object
    	int nextWorldX = user.getLeftX();
    	int nextWorldY = user.getTopY();
    	
    	switch(user.direction) {
    	case "up": nextWorldY = user.getTopY()-user.speed; break;    // change 1 to user.speed
    	case "down": nextWorldY = user.getBottomY()+user.speed; break;    // change 1 to user.speed
    	case "left": nextWorldX = user.getLeftX()-user.speed; break;    // change 1 to user.speed
    	case "right": nextWorldX = user.getRightX()+user.speed; break;    // change 1 to user.speed
    	}
    	int col = nextWorldX/gp.tileSize;
    	int row = nextWorldY/gp.tileSize;
    	
    	for(int i = 0; i < target[1].length; i++) {
    		if(target[gp.currentMap] [i] != null) {
    			if(target[gp.currentMap] [i].getCol() == col && 
    					target[gp.currentMap] [i].getRow() == row &&
    					target[gp.currentMap] [i].name.equals(targetName)) {
    				
    				index = i;
    				break;
    			}
    		}
    	}
    	return index;
		
	}
}

	