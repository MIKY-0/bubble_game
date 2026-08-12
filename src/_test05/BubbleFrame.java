package _test05;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame {
    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initData();
        setInitLayout();
        addEventListener();

        // 플레이어의 위치에 따라 픽셀 감지하는 백그라운드 서비스 객체 생성.
        new Thread(new BackGroundPlayerService(player)).start();

    }

    private void initData() {
        setTitle("버블버블");
        setSize(1000 , 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
        setContentPane(backgroundMap); // 루트패널에 JLabel 설정.
        player = new Player();
    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null); // JFrame 화면 가운데 배치.

        add(player);
        setVisible(true);
    }

    private void addEventListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT :
                        if(!player.isLeft() && !player.isLeftWallCrash()) player.left();
                        break;

                    case KeyEvent.VK_RIGHT :
                        if(!player.isRight() && !player.isRightWallCrash()) player.right();
                        break;

                    case KeyEvent.VK_UP :
                        if(!player.isUp() && !player.isDown()) player.up();
                        break;

                    case KeyEvent.VK_SPACE :
                        System.out.println("스페이스");
//                        add(new Bubble(player));
                        player.fireBubble(BubbleFrame.this); // this만 사용하면 new KeyAdaptor의 참조를 가져옴.
                        // 현재 나의 참조를 가져오려면 BubbleFrame.this.
                        break;

                    default :
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT :
                        // 왼쪽으로 가고있다가 방향키를 떼면 while멈추는 동작.
                        player.setLeft(false); // 키를 떼면 false로 변환. while문 멈춤.
                        break;

                    case KeyEvent.VK_RIGHT :
                        player.setRight(false);
                        break;

                    case KeyEvent.VK_UP :

                        break;


                }

            }
        });
    }


    public static void main(String[] args) {
        new BubbleFrame();
    }
}
