package service;

import db.DBConn;
import girlGroup.Entertainment;
import girlGroup.GirlGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GirlGroupService implements GirlInterface {
    Connection conn = DBConn.getConnection();
    PreparedStatement psmt = null;
    String sql;

    public List<Entertainment> getListE() {
        System.out.println("[girlGroupService.getListAll]");
        List<Entertainment> eList = new ArrayList<>();
        ResultSet rs = null;

        try {
            sql = "SELECT * FROM entertainment";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                Entertainment entertainment = new Entertainment();
                entertainment.setE_id(rs.getInt("e_id"));
                entertainment.setName(rs.getString("name"));
                eList.add(entertainment);
            }
            //잘 들어왔는지 확인
            /*eList.stream().forEach(x -> System.out.println(x));
           rs.close();*/
            psmt.close();
            // 열었으면 다 닫아주는 겁니다.
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return eList;
    }

    @Override
    public List<GirlGroup> getListG() {
        System.out.println("[girlGroupService.getListAll]");
        List<GirlGroup> gList = new ArrayList<>();
        ResultSet rs = null;

        try {
            sql = "SELECT * FROM girl_group";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                GirlGroup girlGroup = new GirlGroup();
                girlGroup.setG_id(rs.getInt("g_id"));
                girlGroup.setName(rs.getString("name"));
                girlGroup.setDebut(rs.getDate("debut").toLocalDate());
                gList.add(girlGroup);
            }
            //잘 들어왔는지 확인
            /*gList.stream().forEach(x -> System.out.println(x));
            rs.close();*/
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return gList;
    }

    public List<GirlGroup> selectEnter(int num) {
        System.out.println("입력하신 소속사의 걸그룹입니다.");
        List<GirlGroup> gList = new ArrayList<>();
        ResultSet rs = null;

        try {
            sql = "SELECT g.name, g.debut FROM girl_group g WHERE g.enter_id = " + num + ";";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                GirlGroup girlGroup = new GirlGroup();
                girlGroup.setName(rs.getString("name"));
                girlGroup.setDebut(rs.getDate("debut").toLocalDate());
                gList.add(girlGroup);
            }
            //잘 들어왔는지 확인
            /*gList.stream().forEach(x -> System.out.println(x));
            rs.close();*/
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return gList;
    }
}




