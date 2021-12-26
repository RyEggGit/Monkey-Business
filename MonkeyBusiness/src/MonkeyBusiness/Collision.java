/*
    Ryan Eggens
    Nov 10, 2020
    Collision class 
 */
package MonkeyBusiness;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ryan Eggens
 */
public class Collision {

    int x;
    int y;
    private int H = 32;
    private int W = 64;
    final private double estimater = 15;
    BufferedImage i = null;

    String side = "x";
    String yCol = "";

    //enum for type of collision
    enum Type {
        platformSmall,
        banana,
        ground,
        platformLong,
        platformCloud,
        platformRockH,
        platformRockV
    }

    Type type;

    /**
     * Constructor (no default constructor)
     *
     * @param x - collisions x
     * @param y - collisions y
     * @param type - type of collision
     */
    public Collision(int x, int y, String type) {
        this.x = x;
        this.y = y;

        //sets the type based off string input(netbeans formatted this)
        if (null == type) {
            System.out.println("ERROR INVALID TYPE");
        } else {
            switch (type) {
                case "platformSmall":
                    this.type = Type.platformSmall;
                    break;
                case "banana":
                    this.type = Type.banana;
                    break;
                case "ground":
                    this.type = Type.ground;
                    break;
                case "platformLong":
                    this.type = Type.platformLong;
                    break;
                case "platformCloud":
                    this.type = Type.platformCloud;
                    break;
                case "platformRockH":
                    this.type = Type.platformRockH;
                    break;
                case "platformRockV":
                    this.type = Type.platformRockV;
                    break;
                default:
                    System.out.println("ERROR INVALID TYPE");
                    break;
            }
        }
        //Links type to image
        try {
            if (this.type == Type.platformSmall) {
                i = ImageIO.read(GameLoop.class.getResourceAsStream("platform.png"));
                H = i.getTileHeight() - 20;
                W = i.getTileWidth() + 7;
            } else if (this.type == Type.banana) {
                i = ImageIO.read(GameLoop.class.getResourceAsStream("banana.png"));
                H = i.getTileHeight();
                W = i.getTileWidth();
            } else if (this.type == Type.platformLong) {
                i = ImageIO.read(GameLoop.class.getResourceAsStream("platformLong.png"));
                H = i.getTileHeight() - 20;
                W = i.getTileWidth();
            } else if (this.type == Type.platformCloud) {
                i = ImageIO.read(GameLoop.class.getResourceAsStream("cloud.png"));
                H = i.getTileHeight();
                W = i.getTileWidth() - 60;
            } else if (this.type == Type.platformRockH) {
                i = ImageIO.read(GameLoop.class.getResourceAsStream("rockH.png"));
                H = i.getTileHeight();
                W = i.getTileWidth();
            } else if (this.type == Type.platformRockV) {
                i = ImageIO.read(GameLoop.class.getResourceAsStream("rockV.png"));
                H = i.getTileHeight();
                W = i.getTileWidth() - 50;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Accessor - gets the value of x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    public int getDrawingX() {
        if (type == Type.platformCloud) {
            return x - 25;
        }
        return x;
    }

    /**
     * Accessor - gets the value of y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the drawing Y
     *
     * @return the drawing Y
     */
    public int getDrawingY() {
        if (type == Type.platformCloud) {
            return y - 20;
        }
        return y;
    }

    /**
     * Accessor - gets the width of the collision
     *
     * @return width
     */
    public int getWidth() {
        return W;
    }

    /**
     * Accessor - gets the height of the collision
     *
     * @return height
     */
    public int getHeight() {
        return H;
    }

    /**
     * get the image of the collision
     *
     * @return image
     */
    public BufferedImage getimage() {
        return i;
    }

    /**
     * Mutator - sets the x of the platform
     *
     * @param x x of collision
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Mutator - sets the x of the platform
     *
     * @param y of collsion
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Checks if there is a top collsion with the platform
     *
     * @param playerX
     * @param playerY
     * @param playerW
     * @param playerH
     * @return true or false whether a collision occured
     */
    protected boolean collisionTop(int playerX, int playerY, int playerW, int playerH) {
        int pX = playerX;
        int pY = playerY;
        int pW = playerW;
        int pH = playerH;

        //System.out.println("player Y:  " + (pY + pH));
        //System.out.println("platform Y:  " + y);
        if (pY + pH > y - estimater && pY + pH < y + estimater) {
            /*
            System.out.println("Player X: " + pX);
            System.out.println("Platform X: " + x);
            System.out.println("Platform X + Platform W: " + (pX + pW));
            System.out.println("Player X + Player W: " + (x + W));
             */
            if (pX + pW > x && pX < x + W) {
                //System.out.println("Top Collision");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a bottom collsion with the platform
     *
     * @param playerX
     * @param playerY
     * @param playerW
     * @param playerH
     * @return true or false whether a collision occured
     */
    protected boolean collisionBot(int playerX, int playerY, int playerW, int playerH) {
        int pX = playerX;
        int pY = playerY;
        int pW = playerW;

        //System.out.println("player Y:  " + (pY + pH));
        //System.out.println("platform Y:  " + y);
        if (pY > y + H - estimater && pY < y + H + estimater) {
            /*
            System.out.println("Player X: " + pX);
            System.out.println("Platform X: " + x);
            System.out.println("Platform X + Platform W: " + (pX + pW));
            System.out.println("Player X + Player W: " + (x + W));
             */
            if (pX + pW > x && pX < x + W) {

                return true;
            }
        }
        return false;
    }

    public String getSide() {
        return side;
    }
    
    public String getYCol() {
        return yCol;
    }

    /**
     * Checks if there is a side collsion with the platform
     *
     * @param playerX
     * @param playerY
     * @param playerW
     * @param playerH
     * @return true or false whether a collision occured
     */
    protected boolean collisionSides(int playerX, int playerY, int playerW, int playerH) {
        int pX = playerX;
        int pY = playerY;
        int pW = playerW;
        int pH = playerH;
        //left                                                 //right
        if ((pX + pW > x - estimater && pX + pW < x + estimater) || (pX > x + W - estimater && pX < x + W + estimater)) {
            
            //System.out.println("-----X Check Passed------");
            /*
            System.out.println("Player X: " + pX);
            System.out.println("Platform X: " + x);
            System.out.println("Platform X + Platform W: " + (pX + pW));
            System.out.println("Player X + Player W: " + (x + W));
            System.out.println("");
            /*
            System.out.println("Player Y: " + pY);
            System.out.println("Platform Y: " + y);
            System.out.println("Platform Y + Platform H: " + (pY + pH));
            System.out.println("Player Y + Player H: " + (y + H));
            System.out.println("");
            */
            if ((pY > y && pY < y +H) || (pY + pH < y +H && pY + pH > y)) {
                yCol = "yes";
                System.out.println("-----Y Check Passed------");
                System.out.println("Player Y: " + pY);
                System.out.println("Platform Y: " + y);
                System.out.println("Platform Y + Platform H: " + (pY + pH));
                System.out.println("Player Y + Player H: " + (y + H));
                System.out.println("");
                if ((pX + pW > x - estimater && pX + pW < x + estimater)) {
                    System.out.println("left collision");
                    side = "left";
                }
                if (pX > x + W - estimater && pX < x + W + estimater) {
                    System.out.println("right collision");
                    side = "right";
                }
                return true;
            }
        }
        return false;
    }

}
