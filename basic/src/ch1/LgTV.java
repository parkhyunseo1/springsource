package ch1;

/*
* 
* 변수
* 1) 멤버 변수 : 클래스 안에 선언된 변수
*               객체인 경우 null 로 초기화 됨
*              기본 타입 변수인 경우 0 or '' or 0.0, false 초기화 됨
*2) 지역 변수 : 메소드나 {} 안에 선언된 변수
*3) 매개 변수 : powerOn(int x) => x 를 부를 때
* 
 */

public class LgTV implements TV {

    // 멤버변수 (== 인스턴스 변수, 필드, 특성, 속성)
    private Speaker Speaker;

    // private int age

    public void setSpeaker(Speaker Speaker) {
        this.Speaker = Speaker;
    }

    @Override
    public void powerOn() {
        System.out.println("LgTV - 전원 on");
    }

    @Override
    public void powerOff() {
        System.out.println("LgTV - 전원 off");
    }

    @Override
    public void volumeUp() {

        Speaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        System.out.println("LgTV - 볼륨 Down");
        Speaker.volumeDown();
    }

}