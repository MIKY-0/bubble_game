package _test03;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// 클래스 역할 : 플레이어의 벽 충돌 감시 서비스(백그라운드에서 계속 돌아감)
// 메인 쓰레드는 너무 바쁨.
public class BackGroundPlayerService implements Runnable{

    // Image / ImageIcon : 좌표 값으로 현재 픽셀 값 추출할 수 없다.
    // 메모리에 픽셀 배열로 저장된 이미지.
    // getRGB(x , y)로 특정 좌표에 색상값을 직접 읽을 수 있음.

    private BufferedImage image;
    private Player player;

    // 의존성 주입(DI)
    public BackGroundPlayerService(Player player) {
        this.player = player;
        try {
            image = ImageIO.read(new File("images/backgroundMapService.png"));
        } catch (IOException e) {
            System.out.println("이미지 경로 및 파일명을 확인하세요.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        // 게임이 종료될때까지 계속 실행.
        while (true){
            Color leftColor = new Color(image.getRGB(player.getX() + 5, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 60 , player.getY() + 25));

            System.out.println("leftColor : " + leftColor);
            System.out.println("rightColor : " + rightColor);

            // 왼쪽 벽 감지 판단. - 빨간색이라면 플레이어가 왼쪽에 충돌.
            if(isRed(leftColor)) {
                // 현재 플레이어가 왼쪽벽에 충돌된 상태.
                player.setLeftWallCrash(true);
                player.setLeft(false);
            } else {
                player.setLeftWallCrash(false);
            }

            // 오른쪽 벽 감지 판단. - 빨간색이라면 플레이어가 오른쪽에 충돌.
            if(isRed(rightColor)) {
                // 현재 플레이어가 왼쪽벽에 충돌된 상태.
                player.setRightWallCrash(true);
                player.setRight(false);
            } else {
                player.setRightWallCrash(false);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private boolean isRed(Color color) {
        return (color.getRed() == 255) && (color.getGreen() == 0) && (color.getBlue() == 0);
    }
}
