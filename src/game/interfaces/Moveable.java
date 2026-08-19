package game.interfaces;

public interface Moveable {
    void left();
    void right();
    void up();

    // Adapter 클래스가 너무 많이 생겨서 default 문법을 인터페이스에서 사용할 수 있도록 만들어줌.
    // default 키워드 사용하면 인터페이스에서도 일반 메서드 구현가능.
    default void down(){};

}
