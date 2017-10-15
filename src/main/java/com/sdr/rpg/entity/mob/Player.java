package com.sdr.rpg.entity.mob;

import com.sdr.rpg.graphics.Renderer;
import com.sdr.rpg.graphics.Sprite;
import com.sdr.rpg.input.KeyboardInput;

/**
 * Player =)
 * <p>
 * Created by Konstantin on 15.10.2017.
 */
public class Player extends Mob {

    private Sprite[][] directions = new Sprite[4][3];

    {
        directions[0][0] = Sprite.player_oldman_up;
        directions[0][1] = Sprite.player_oldman_up_1;
        directions[0][2] = Sprite.player_oldman_up_2;

        directions[1][0] = Sprite.player_oldman_right;
        directions[1][1] = Sprite.player_oldman_right_1;
        directions[1][2] = Sprite.player_oldman_right_2;

        directions[2][0] = Sprite.player_oldman_down;
        directions[2][1] = Sprite.player_oldman_down_1;
        directions[2][2] = Sprite.player_oldman_down_2;

        directions[3][0] = Sprite.player_oldman_left;
        directions[3][1] = Sprite.player_oldman_left_1;
        directions[3][2] = Sprite.player_oldman_left_2;
    }

    private int animation = 0;

    private KeyboardInput keyboardInput;

    public Player(KeyboardInput keyboardInput) {
        this.keyboardInput = keyboardInput;
        this.sprite = Sprite.player_oldman_down;
        this.movementSpeed = 2;
    }

    public Player(int x, int y, KeyboardInput keyboardInput) {
        this.x = x;
        this.y = y;
        this.keyboardInput = keyboardInput;
    }

    @Override
    public void update() {
        int xDir = 0;
        int yDir = 0;

        if (animation <= 7500)
            animation++;
        else
            animation = 0;

        if (keyboardInput.up)
            yDir-= movementSpeed;
        if (keyboardInput.down)
            yDir+=movementSpeed;
        if (keyboardInput.right)
            xDir+= movementSpeed;
        if (keyboardInput.left)
            xDir-=movementSpeed;

        if (xDir != 0 || yDir != 0) {
            move(xDir, yDir);
            moving = true;
        } else {
            moving = false;
        }

    }

    @Override
    public void render(Renderer renderer) {

        int animationIndex = 0;
        if (moving) {
            int val = animation % 40;
            if (val <= 20) {
                animationIndex = 1;
            } else
             animationIndex = 2;
        }
        this.sprite = directions[getDirection()][animationIndex];

        int xCenter = x - getSprite().getXSize() / 2;
        int yCenter = y - getSprite().getYSize() / 2;
        renderer.renderPlayer(xCenter, yCenter, getSprite());
    }
}
