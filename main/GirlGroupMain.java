package main;

import view.View;

import java.util.Scanner;

public class GirlGroupMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        View view = new View();
        while (true) {
            view.menuEnter(); // 소속사
            view.entertainGirl(); // 어떤 소속사의 걸그룹을 볼 것인지
            System.out.println("종료하시려면 0을, 되돌아가시려면 1을 입력해주세요.");
            int exit = sc.nextInt();
            if (exit == 0) {
                break;
            } else if (exit == 1) {
                System.out.println("처음으로 돌아갑니다.");
            }
        }
    }
}

