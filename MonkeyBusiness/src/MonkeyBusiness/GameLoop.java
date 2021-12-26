/*
    Ryan Eggens
    Nov 10, 2020
    Main Game Loop for the Class Monkey Business
 */
package MonkeyBusiness;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Ryan Eggens
 */
public class GameLoop extends JFrame {

    MainMenu firstWindow;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    // width will store the width of the screen
    int screenWidth = (int) size.getWidth();
    // height will store the height of the screen
    int screenHeight = (int) size.getHeight();
    //size

    //Jframe of the game loop
    public GameLoop(MainMenu m) {
        firstWindow = m;
        setSize(screenWidth, screenHeight);
        //starting position
        setLocationRelativeTo(null);
        //exits when closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //draws the JLabel of the game
        setContentPane(new draw(this, firstWindow));
        //Sets JFrame to Full screen
        setUndecorated(true);
        setResizable(false);
        //sets JFrame to visible
        setVisible(true);
    }
}

class draw extends JPanel implements ActionListener, KeyListener {

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    // width will store the width of the screen
    int screenWidth = (int) size.getWidth();
    // height will store the height of the screen
    int screenHeight = (int) size.getHeight();
    //size
    EndScreen endScreen;
    MainMenu firstWindow;
    GameLoop gameLoop;
    //Starts a timer for how long the game took
    long startTime = System.nanoTime();

    BufferedImage playerIdleImg = null;
    BufferedImage playerFallingImg = null;
    BufferedImage playerHoldingJumpImg = null;
    BufferedImage playerImg;
    //game timer (runs Action event ever 5 milliseseconds)
    Timer t = new Timer(1, this);
    //players inital x and y
    double pX = screenWidth - 150;
    double pY = 1000;
    //players Width and Height
    int pW = 40;
    int pH = 100;
    //players x and y velocities
    double pVelX = 0;
    double pVelY = 0;
    //gravity
    double grav = 0.01;

    //Max and min jump velocites and the fastests y velocity
    double maxJumpVel = 8.0;
    double minJumpVel = 4.0;
    double terminalVelY = 5;

    //the x speed of the player
    double xSpeed = 3;

    //boolean values that tell what the user is doing
    boolean collisionTop = false;
    boolean isGrounded = false;
    boolean isJumping = false;
    boolean isFalling = true;
    boolean holdingJump = false;

    boolean goingLeft = false;
    boolean goingRight = false;

    //used for calculating speed
    double right = 0;
    double left = 0;

    //the current rooom index
    int roomIndex = 0;

    //Test Drawing Variables
    boolean isSideColis = false;
    int sideColisCount = 0;

    boolean isSideColisY = false;
    int sideColisCountY = 0;

    //Array lists of the platforms for each level
    ArrayList<Collision> platformsR1 = new ArrayList();
    ArrayList<Collision> platformsR2 = new ArrayList();
    ArrayList<Collision> platformsR3 = new ArrayList();
    ArrayList<Collision> platformsR4 = new ArrayList();

    //Array list of all the platforms for every level
    ArrayList<ArrayList<Collision>> platforms = new ArrayList();
    //End banana
    Collision banana = new Collision(700, 0, "banana");

    //Player and background array list imagers
    ArrayList<BufferedImage> playerRunning = new ArrayList();
    ArrayList<BufferedImage> playerJumping = new ArrayList();
    ArrayList<BufferedImage> backgrounds = new ArrayList();

    public draw(GameLoop g, MainMenu m) {
        firstWindow = m;
        gameLoop = g;
        //creates all the platforms
        //platforms for room 1
        Collision p0 = new Collision(-100, -100, "platformSmall");
        Collision p1 = new Collision(1550, 1000, "platformLong");
        Collision p2 = new Collision(990, 800, "platformLong");
        Collision p3 = new Collision(350, 600, "platformLong");
        Collision p4 = new Collision(950, 350, "platformSmall");
        Collision p5 = new Collision(1150, 150, "platformSmall");

        //platforms for room 2
        Collision p6 = new Collision(1300, 1050, "platformLong");
        Collision p7 = new Collision(900, 900, "platformSmall");
        Collision p8 = new Collision(600, 600, "platformSmall");
        Collision p9 = new Collision(900, 500, "platformSmall");
        Collision p10 = new Collision(1200, 500, "platformLong");
        Collision p11 = new Collision(1000, 200, "platformLong");

        //platforms for room 3
        Collision p12 = new Collision(1000, 1000, "platformLong");
        Collision c1 = new Collision(700, 750, "platformCloud");
        Collision c2 = new Collision(300, 600, "platformCloud");
        Collision c3 = new Collision(100, 650, "platformCloud");
        Collision c4 = new Collision(300, 300, "platformCloud");
        Collision c5 = new Collision(900, 300, "platformCloud");
        Collision c6 = new Collision(1200, 100, "platformCloud");

        //platforms for room 4
        Collision r1 = new Collision(1500, 975, "platformRockH");
        Collision r2 = new Collision(1200, 800, "platformRockH");
        Collision r3 = new Collision(700, 750, "platformRockH");
        Collision r4 = new Collision(350, 750, "platformRockH");
        Collision r5 = new Collision(50, 650, "platformRockH");
        Collision r6 = new Collision(350, 450, "platformRockH");
        Collision r7 = new Collision(700, 750, "platformRockH");
        Collision r8 = new Collision(700, 450, "platformRockH");
        Collision r9 = new Collision(1050, 100, "platformRockV");
        Collision r10 = new Collision(650, 200, "platformRockH");

        //adds platforms to array lists (room 1)
        platformsR1.add(p0);
        platformsR1.add(p1);
        platformsR1.add(p2);
        platformsR1.add(p3);
        platformsR1.add(p4);
        platformsR1.add(p5);

        //adds platforms to array lists (room 2)
        platformsR2.add(p6);
        platformsR2.add(p7);
        platformsR2.add(p8);
        platformsR2.add(p9);
        platformsR2.add(p10);
        platformsR2.add(p11);

        //adds platforms to array lists(room 3)
        platformsR3.add(p12);
        platformsR3.add(c1);
        platformsR3.add(c2);
        platformsR3.add(c3);
        platformsR3.add(c4);
        platformsR3.add(c5);
        platformsR3.add(c6);

        //adds platforms to array lists(room 4)
        platformsR4.add(r1);
        platformsR4.add(r2);
        platformsR4.add(r3);
        platformsR4.add(r4);
        platformsR4.add(r5);
        platformsR4.add(r6);
        platformsR4.add(r7);
        platformsR4.add(r8);
        platformsR4.add(r9);
        platformsR4.add(r10);
        //adds the platforms from each rooms to the main platforms array list
        platforms.add(platformsR1);
        platforms.add(platformsR2);
        platforms.add(platformsR3);
        platforms.add(platformsR4);

        //Gets all the player and background images (platform images are found on definition
        try {

            //URL url = GameLoop.class.getResource("playerIdle.png");
            //BufferedImage img = ImageIO.read(url.openStream());
            //playerIdleImg = img;
            //gets idel images
            playerIdleImg = ImageIO.read(GameLoop.class.getResource("playerIdle.png"));
            //gets falling image
            playerFallingImg = ImageIO.read(GameLoop.class.getResource("playerFalling.png"));
            //gets the hlding jump images
            playerHoldingJumpImg = ImageIO.read(GameLoop.class.getResource("playerJumpingGround.png"));

            //gets the running image
            for (int i = 0; i < 8; i++) {
                BufferedImage image = ImageIO.read(GameLoop.class.getResource("playerRunning" + i + ".png"));
                playerRunning.add(image);
            }

            //gets the background images
            for (int i = 0; i < 4; i++) {
                BufferedImage image = ImageIO.read(GameLoop.class.getResource("backgroundRoom" + i + ".png"));
                backgrounds.add(image);

            }
            //gets the jumping images
            for (int i = 0; i < 4; i++) {
                BufferedImage image = ImageIO.read(GameLoop.class.getResource("playerJump" + i + ".png"));
                playerJumping.add(image);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to read file!", "File Error", JOptionPane.ERROR_MESSAGE);
        }
        //sets the inital player image to idle
        playerImg = playerIdleImg;
        //starts the start timer and starts listen for button inputs
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }

    //draw the images on the screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);

        //draws the background and player
        g.drawImage(backgrounds.get(roomIndex), 0, 0, null);
        //g.drawString("Going left: " + goingLeft + " Going Right: " + goingRight, (int) pX + 10, (int) pY - 20);
        //g.drawRect((int) pX, (int) pY, pW, pH);
        g.drawImage(playerImg, (int) pX - 27, (int) pY, null);

        if (isSideColis) {
            if (sideColisCount <= 5) {
                g.drawString("Side Collision", (int) pX + 10, (int) pY - 30);
                sideColisCount++;
            } else {
                sideColisCount = 0;
                isSideColis = false;
            }
        }

        if (isSideColisY) {
            if (sideColisCountY <= 5) {
                g.drawString("Y Collision", (int) pX + 10, (int) pY - 40);
                sideColisCountY++;
            } else {
                sideColisCountY = 0;
                isSideColisY = false;
            }
        }

        //draws the banana if on the right room
        if (roomIndex == 3) {
            g.drawImage(banana.getimage(), (int) banana.getX(), (int) banana.getY(), banana.getWidth(), banana.getimage().getHeight(), null);
        }
        //draws the platform for the specific room
        for (int i = 0; i < platforms.get(roomIndex).size(); i++) {

            //g.drawRect(platforms.get(roomIndex).get(i).getX(), platforms.get(roomIndex).get(i).getY(), platforms.get(roomIndex).get(i).getWidth(), platforms.get(roomIndex).get(i).getHeight());
            g.drawImage(platforms.get(roomIndex).get(i).getimage(), (int) platforms.get(roomIndex).get(i).getX(), (int) platforms.get(roomIndex).get(i).getDrawingY(), null);
        }

    }
    //used a counter to top collisions
    int x = 0;
    //used a counter for side collisions
    int y = 0;
    //checks running and jumping images
    int runningIndex = 0;
    int jumpingIndex = 0;
    //slows down the running speed
    int animateRun = 0;

    /**
     * Main Game loop
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        //checks what room they are
        checkRooms();

        //if grounded
        if (isGrounded) {

            double runSpeed = right - left;
            //animation
            //holding jump
            if (holdingJump) {
                playerImg = playerHoldingJumpImg;
            } //running to the right animations
            else if (runSpeed == xSpeed) {
                if (animateRun == 5) {
                    checkRunningIndex();
                    playerImg = playerRunning.get(runningIndex);
                    runningIndex++;
                    animateRun = 0;
                }
                animateRun++;
            } //running animation to the left
            else if (runSpeed == -xSpeed) {
                if (animateRun == 5) {
                    checkRunningIndex();
                    playerImg = horizontalflip(playerRunning.get(runningIndex));
                    runningIndex++;
                    animateRun = 0;
                }
                animateRun++;
            } //idle animation
            else {
                playerImg = playerIdleImg;
                animateRun = 0;
            }
            //makes sure y velocity is zero
            pVelY = 0;
            //makes sure player is on the platform
            pY = platforms.get(roomIndex).get(x).getY() - pH;

            //checks if the player is still on the platfornm
            if (!platforms.get(roomIndex).get(x).collisionTop((int) pX, (int) pY, pW, pH)) {
                isFalling = true;
                collisionTop = false;
                goingRight = false;
                goingLeft = false;
                left = 0;
                right = 0;
                runningIndex = 0;
            }

        }
        //if the player is jumping
        if (isJumping) {
            //calculates x velocity
            pVelX = right - left;
            //animation
            // jumping to the right
            if (pVelX == xSpeed) {
                checkJumpingIndex();
                playerImg = playerJumping.get(jumpingIndex);
                jumpingIndex++;

            } //jumping to the left
            else if (pVelX == -xSpeed) {
                checkJumpingIndex();
                playerImg = horizontalflip(playerJumping.get(jumpingIndex));
                jumpingIndex++;
            } //straightdown
            else {
                playerImg = playerIdleImg;
            }
            //increases the player y by gravity
            pVelY += grav;
            //
            if (pVelY > 0.1) {
                pVelY = 0;
                isJumping = false;
                isFalling = true;
            }
            //checks for bottom collision
            if (collisionBotCheck()) {
                pVelY = 0;
                isJumping = false;
                isFalling = true;
            }

        }

        //if falling
        if (isFalling) {
            //sets the image
            playerImg = playerFallingImg;
            //sets the velocity y to gravity
            pVelY = grav;
            //if falling at less then terminal velocity increments gravity
            if (pVelY < terminalVelY) {
                grav += 0.5;
            }

            //decrease x velocities
            if (goingLeft) {
                left = 0.5;
            }
            if (goingRight) {
                right = 0.5;
            }
            //if there is a top collision
            if (collisionTopCheck()) {
                resetGrav();
                isGrounded = true;
                isFalling = false;
                goingLeft = false;
                goingRight = false;
                left = 0;
                right = 0;
                pY = platforms.get(roomIndex).get(x).getY() - pH;
            } //not on ground
            else {
                isGrounded = false;
            }
        }

        //if the player hits the right bounday stops them 
        if (pX + pW * 1.5 > screenWidth) {
            pX = screenWidth - pW * 1.5;
            right = 0;
        }
        //if the player hits the left bounday stops them 
        if (pX < 3) {
            pX = 0;
            left = 0;
        }
        //if the player is on the ground calcualte their left and right
        if (isGrounded) {
            pVelX = right - left;
        }
        //if there is a side collision switches the direction
        if (collisionSidesCheck()) {

            System.out.println("******STOPING SPEED*******");
            /*
            pVelX = 0;
            right = 0;
            left = 0;
            goingLeft = false;
            goingRight = false;
            isSideColis = true;
             */
            System.out.println(platforms.get(roomIndex).get(y).getSide());

            if ("left".equals(platforms.get(roomIndex).get(y).getSide())) {

                left = 0;
                if (!isGrounded) {
                    right = 2.5;
                    pVelX = -2.5;
                    goingRight = true;
                    goingLeft = false;
                }

            } 
            if ("right".equals(platforms.get(roomIndex).get(y).getSide())) {
                right = 0;
                if (!isGrounded) {
                    left = 2.5;
                    pVelX = 2.5;
                    goingLeft = true;
                    goingRight = false;
                }
            }

        }

        // move the player based off x and y velocities
        System.out.println("VelX: " + pVelX);
        pX += pVelX;
        pY += pVelY;
        //draws the game
        repaint();
    }

    //needs this since is abstract
    public void keyTyped(KeyEvent e) {
    }

    double jumpCounter = 0;

    /**
     * if a key is pressed
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //if the player is holding space
        if (key == KeyEvent.VK_SPACE && isGrounded) {
            holdingJump = true;
            jumpCounter += 0.5;
            left = 0;
            right = 0;
        }

        //if the player if going left and is not holding jump
        if (key == KeyEvent.VK_LEFT && isGrounded && !holdingJump) {
            left = xSpeed;
            goingLeft = true;
            goingRight = false;
        }
        //if the player if going left and is holding jump
        if (key == KeyEvent.VK_LEFT && isGrounded && holdingJump) {
            holdingJump = true;
            jumpCounter += 0.5;
            left = 0;
            right = 0;
            System.out.println("left jump");
            goingLeft = true;
            goingRight = false;
        }
        //if the player if going right and is not holding jump
        if (key == KeyEvent.VK_RIGHT && isGrounded && !holdingJump) {
            right = xSpeed;
            goingLeft = false;
            goingRight = true;
        }
        //if the player if going right and is holding jump
        if (key == KeyEvent.VK_RIGHT && isGrounded && holdingJump) {
            holdingJump = true;
            jumpCounter += 0.5;
            left = 0;
            right = 0;
            System.out.println("right jump");
            goingLeft = false;
            goingRight = true;
        }
 
        if (key == KeyEvent.VK_ESCAPE) {
            
            switchToCredit();
        }

    }

    //resets gravity
    private void resetGrav() {
        grav = 0.1;
    }

    /**
     * checks if the running index is too high and resets it
     */
    private void checkRunningIndex() {
        if (runningIndex == 8) {
            runningIndex = 0;
        }
    }

    /**
     * checks if the jump index is too high and resets it
     */
    private void checkJumpingIndex() {
        if (jumpingIndex == 3) {
            jumpingIndex = 2;
        }
    }

    /**
     * Searches every platform to see if there is a bottom collision
     *
     * @return true if there is a collision
     */
    private boolean collisionTopCheck() {
        x = 0;
        int i = 0;

        while (i < platforms.get(roomIndex).size()) {
            if (platforms.get(roomIndex).get(i).collisionTop((int) pX, (int) pY, pW, pH)) {
                return true;
            }
            x++;
            i++;
        }
        x = 0;
        return false;
    }

    /**
     * Searches every platform to see if there is a bottom collision
     *
     * @return true if there is a collision
     */
    private boolean collisionBotCheck() {
        x = 0;
        int i = 0;
        while (i < platforms.get(roomIndex).size()) {
            if (platforms.get(roomIndex).get(i).collisionBot((int) pX, (int) pY, pW, pH)) {
                return true;
            }
            x++;
            i++;
        }
        x = 0;
        return false;
    }

    /**
     * checks the current room index and if its needs to be changed
     */
    public void checkRooms() {
        if (pY < 0) {
            changeRooms(roomIndex + 1);
            pY = 1120;
        }

        if (pY > 1120) {
            if (roomIndex == 0) {
                pX = 1600;
                pY = 900;
            } else {
                changeRooms(roomIndex - 1);
                pY = 0;
            }

        }
        //if banana checks if there is a collision
        if (roomIndex == 3) {
            checkBanana();
        }
    }

    /**
     * switches the rooms and redraws
     *
     * @param room
     */
    public void changeRooms(int room) {
        roomIndex = room;
        x = 0;
        repaint();
    }

    /**
     * Searches the platforms if there is a side collision
     *
     * @return true if a side collision
     */
    private boolean collisionSidesCheck() {

        y = 0;
        int i = 0;
        while (i < platforms.get(roomIndex).size()) {
            if (platforms.get(roomIndex).get(i).collisionSides((int) pX, (int) pY, pW, pH)) {
                System.out.println("*****SIDE CHECK ACTIVATED*******");
                return true;

            }
            y++;
            i++;
        }
        y = 0;
        return false;
    }

    /**
     * Checks if the banana has collision and ends game
     */
    private void checkBanana() {
        if (banana.collisionTop((int) pX, (int) pY, pW, pH)) {
            switchToCredit();
        }
        if (banana.collisionBot((int) pX, (int) pY, pW, pH)) {
            switchToCredit();
        }

        if (banana.collisionSides((int) pX, (int) pY, pW, pH)) {
            switchToCredit();
        }
    }

    /**
     * If key is released
     *
     * @param e keyEnvent
     */
    public void keyReleased(KeyEvent e) {
        //checks what key is released
        int key = e.getKeyCode();
        //if space is released and on ground
        if (key == KeyEvent.VK_SPACE && isGrounded) {
            //turns holding jump to false
            holdingJump = false;

            System.out.println("Jump time: " + jumpCounter);
            //sets Jumpcounter to logical values
            if (jumpCounter > maxJumpVel) {
                jumpCounter = maxJumpVel;
            }
            if (jumpCounter < minJumpVel) {
                jumpCounter = minJumpVel;
            }

            //sets velY to the jump counter
            pVelY = -jumpCounter;
            //turns ground on and falling off
            isJumping = true;
            isGrounded = false;
            //resets jump counter
            jumpCounter = 0;

            //moves the jump depending on whether going left or right
            if (goingRight) {
                right = xSpeed;
            }
            if (goingLeft) {
                left = xSpeed;
            }
            //resets direction
            //goingRight = false;
            //goingLeft = false;
            pY++;

        } //stops left moving
        if (key == KeyEvent.VK_LEFT && isGrounded) {
            left = 0;
            //goingRight = false;
            goingLeft = false;
        } //stops right moving
        if (key == KeyEvent.VK_RIGHT && isGrounded) {
            right = 0;
            goingRight = false;
            //goingLeft = false;
        }

    }

    public void switchToCredit() {
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        int time = Integer.parseInt(totalTime / 10000000 / 100 + "");
        System.out.println(time);

        if (endScreen == null) {
            endScreen = new EndScreen(firstWindow, gameLoop, time);
        }

        endScreen.setVisible(true);
        this.setVisible(false);
        t.stop();
    }

    //Copied this method off the internet ( flips images so I don't have to a run left and run right)
    /**
     * This method flips the image horizontally
     *
     * @param img --> BufferedImage Object to be flipped horizontally
     * @return
     */
    public BufferedImage horizontalflip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();
        /**
         * img - the specified image to be drawn. This method does nothing if
         * img is null. dx1 - the x coordinate of the first corner of the
         * destination rectangle. dy1 - the y coordinate of the first corner of
         * the destination rectangle. dx2 - the x coordinate of the second
         * corner of the destination rectangle. dy2 - the y coordinate of the
         * second corner of the destination rectangle. sx1 - the x coordinate of
         * the first corner of the source rectangle. sy1 - the y coordinate of
         * the first corner of the source rectangle. sx2 - the x coordinate of
         * the second corner of the source rectangle. sy2 - the y coordinate of
         * the second corner of the source rectangle. observer - object to be
         * notified as more of the image is scaled and converted.
         *
         */
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return dimg;
    }

}
