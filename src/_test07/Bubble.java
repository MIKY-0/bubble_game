package _test07;

import lombok.Getter;

import javax.swing.*;

@Getter
public class Bubble extends JLabel implements Moveable {
    private int x;
    private int y;
    private Player player;
    private ImageIcon bubbleIcon;
    private ImageIcon bombIcon;
    private BackGroundBubbleServcie backGroundBubbleServcie;

    // 버블 이동 상태 플래그.
    private static final int HORIZONTAL_DISTANCE = 400; // 버블의 수평 이동거리.
    private static final int BUBBLE_SPEED = 1; //  이동간격.
    private static final int SCREEN_TOP = 0; // 화면 상단 경계(y값)
    private boolean leftMoving;
    private boolean rightMoving;
    private boolean upMoving;

    public Bubble(Player player) {
            this.player = player;
            this.backGroundBubbleServcie = new BackGroundBubbleServcie(this);
            iniData();
            setInitLayout();
            new Thread( () -> {
              if(player.getPlayerWay() == PlayerWay.LEFT) left();
              else if(player.getPlayerWay() == PlayerWay.RIGHT) right();
            }).start();
    }

    private void iniData() {
        bubbleIcon = new ImageIcon("images/bubble.png");
        bombIcon = new ImageIcon("images/bomb.png");
    }

    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setLocation(x , y);
        setIcon(bubbleIcon);
        setSize(50 , 50);
    }

    @Override
    public void left() {
        leftMoving = true;
        for(int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            if(backGroundBubbleServcie.leftWall()) break;
            x --;
            setLocation(x , y);
            try {
                Thread.sleep(BUBBLE_SPEED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        leftMoving = false;
        up();
    }

    @Override
    public void right() {
        rightMoving = true;
        for(int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            if(backGroundBubbleServcie.rightWall()) break;
            x ++;
            setLocation(x , y);
            try {
                Thread.sleep(BUBBLE_SPEED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        rightMoving = false;
        up();
    }

    @Override
    public void up() {
        upMoving = true;

        while(true) {
            if(backGroundBubbleServcie.topWall()) break;
            y--;
            setLocation(x , y);
            try {
                Thread.sleep(BUBBLE_SPEED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        upMoving = false;
        explode();
    }

    private void explode() {
        try {
            Thread.sleep(3000);
            setIcon(bombIcon);

            Thread.sleep(1000);

            if(getParent() != null) {
                this.setVisible(false);
                getParent().remove(this); // 메모리에서 해제.
//                getParent().repaint(); // 새로고침.
        }

//            setIcon(null); //여기선 null이지만 부모가 얘를 계속 들고 있어서 GC대상이 안됨.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
