package main;

import db.DBConn;
import dto.TelDto;
import view.UserView;

import java.sql.Connection;
import java.util.Scanner;

public class TellBookMain {


    public static void main(String[] args) {
        UserView userView = new UserView();


        Scanner sc = new Scanner(System.in);
        /*Connection conn = DBConn.getConnection();
        TelDto lee = new TelDto();
        lee.setId(1);
        lee.setName("홍길동");
        lee.setAge(20);
        lee.setAddress("서울");
        lee.setPhone("010-2222-2222");
        System.out.println(lee);*/
        while (true) {
            System.out.println("1. 입력  2. 수정  3. 삭제  4. 목룍  5. 검색  6. 종료");
            int num = sc.nextInt();
            switch (num) {
                case 1 :
                    userView.insertView();
                    break;
                case 2 :
                    userView.updateView();
                    break;
                case 3:
                    userView.deleteView();
                    break;
                case 4:
                    userView.findAllView();
                    break;
                case 5:
                    userView.searchView();
                    break;
                case 6:
                    return;
            }

        }
    }
}
