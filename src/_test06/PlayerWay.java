package _test06;

/**
 * [enum]플레이어의 방향 상태 관리
 * enum : 상수의 범주화를 만들 때 사용.
 * boolean 2개로 (left , right)로 사용할 수 있지만 둘다 true가 되면 잘못된 상태가 생길 수 있음.
 * enum은 정해진 값중 하나만 가질 수 있어 더 안전함.
 *
 * 왜 사용?
 * 나의 프로젝트나 논리안에서 데이터의 범위를 지정하고 싶을때 안전하게 사용가능.
 * 사용법 : PlayerWay p = PlayerWay.LEFT;
 * 사용법 : PlayerWay p = PlayerWay.RIGHT;
 */

public enum PlayerWay {
    LEFT , RIGHT
}
