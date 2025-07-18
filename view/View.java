package view;

import girlGroup.Entertainment;
import girlGroup.GirlGroup;
import service.GirlGroupService;
import service.GirlInterface;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner sc = new Scanner(System.in);
    private GirlGroupService girlGroupService = new GirlGroupService();

    public void menuEnter() {
        List<Entertainment> eList = new ArrayList<>();
        System.out.println("소속사 정보입니다.");
        eList = girlGroupService.getListE();
        eList.stream().forEach(x -> System.out.println(x));
    }

    public void menuGirl() {
        List<GirlGroup> gList = new ArrayList<>();
        System.out.println("걸그룹 정보입니다.");
        gList = girlGroupService.getListG();
        gList.stream().forEach(x -> System.out.println(x));

    }

    public void entertainGirl() {
        System.out.println("소속사의 그룹정보를 보려면 id를 입력하세요.");
        int num = sc.nextInt();
        List<GirlGroup> gList = girlGroupService.selectEnter(num);
        if (gList.size() == 0) {
            System.out.println("찾는 데이터가 없습니다.");
        } else {
            gList.stream().forEach(x -> System.out.println(x));
        }
    }
}


