package service;

import db.DBConn;
import dto.TelDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelBookService implements CrudInterface{
    // DB 연결하기
    Connection conn = DBConn.getConnection();
    PreparedStatement psmt = null;
    // select * from telbook; 으로 들어가고
    String sql;
    // sql -> select * from telbook

    @Override
    public int insertData(TelDto dto) {
        System.out.println("[TelBookService.InsertData]");
        // 이렇게 달아놓으면 어떤 클래스에 어떤 메서드에 들어와 있구나를 알 수 있음

        try {
            sql = "INSERT INTO telbook(name, age, address, phone, insertedDate) ";
            // sql 구문은 공백을 하나 꼭 넣어주자 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ^  -> 이부분
            sql = sql + "VALUES(?, ?, ?, ?, ?)";

            psmt = conn.prepareStatement(sql);
            // ? 각 자리를 Mapping 해 준다.
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());
            psmt.setTimestamp(5, Timestamp.valueOf(dto.getInsertedDate()));


            // 쿼리 실행하기

            int result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }


        return 0;
    }

    @Override
    public int UpdateData(TelDto dto) {
        System.out.println("[TelBookService.UpdateData]");
        int result = 0;
        try {
            sql = "UPDATE telbook SET ";
            sql = sql + " name = ?,";
            sql = sql + " age = ?,";
            sql = sql + " address = ?,";
            sql = sql + " phone = ?,";
            sql = sql + "updatedDate = ?";
            sql = sql + " WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());
            psmt.setTimestamp(5, Timestamp.valueOf(dto.getUpdatedDate()));
            psmt.setInt(6, dto.getId());

            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteData(int id) {
        System.out.println("[TelBookService.deleteData]");
        int result = 0;
        try {
            sql = "DELETE FROM telbook WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            result = psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<TelDto> getListAll() {
        System.out.println("[TelBookService.getListAll]");
        // DB 에서 select한 결과를 담을 리스트
        List<TelDto> dtoList = new ArrayList<>();
        ResultSet rs = null;
        // select만 리스트를 받는데 그걸 ResullSet 이 받음
        // 그래서 select만 ResultSet 으로 받음

        try {
            sql = "SELECT * FROM telbook";
            psmt = conn.prepareStatement(sql);
            // exception 설정을 하세요
            // 오류가 날 수 있으니까 오류 메세지 받을 곳을 정해
            //-------------------------------------------------
            // SQL 구문 실행
            rs = psmt.executeQuery();

            // ResultSet에 들어온 레코드들을 하나씩 뽑아서
            // DtoList에 담는다.
            while (rs.next()) {
                // rs.next()는 다음게 있니? 하며 불리안으로 물어보는 것임
                TelDto dto = new TelDto();
                // 빈깡통을 생성해서
                dto.setId(rs.getInt("id"));
                // columnLabel = id, name, age . . .
                // table 이름 class 이름 다 동일하게 적용해야한다
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));

                // 날짜가 null 인지 확인 후 처리
                if (rs.getTimestamp("insertedDate") != null) {
                    dto.setInsertedDate(rs.getTimestamp("insertedDate").toLocalDateTime());
                } else {
                    dto.setInsertedDate(null);
                }

                if (rs.getTimestamp("updatedDate") != null) {
                    dto.setUpdatedDate(rs.getTimestamp("updatedDate").toLocalDateTime());
                } else {
                    dto.setUpdatedDate(null);
                }
                // 리스트에 담기
                dtoList.add(dto);
            }
            /*잘 들어왔는지 확인
            dtoList.stream().forEach(x -> System.out.println(x));
           rs.close();*/
            psmt.close();
            // 열었으면 다 닫아주는 겁니다.
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dtoList;
    }

    @Override
    public TelDto findById(int id) {
        System.out.println("[TelBookService.findById]");
        // id를 받아서 해당 레코드를 읽어오는 작업
        ResultSet rs = null;
        try {
            sql = "SELECT id, name, age, address, phone" +
                    " FROM telbook WHERE id = ?";
            psmt =  conn.prepareStatement(sql);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            // 레코드 셋의 자료를 while로 순회하면서 읽는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                return dto;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<TelDto> searchList(String keyword) {
        System.out.println("[TelBookService.searchList]");
        ResultSet rs = null;
        List<TelDto> dtoList = new ArrayList<>();
        try {
            sql = "SELECT id, name, age, address, phone FROM telbook WHERE name LIKE ? ";
            sql = sql + " ORDER BY name DESC";
            psmt =  conn.prepareStatement(sql);
            psmt.setString(1, "%" + keyword + "%");
            // 찾았다구요...
            rs = psmt.executeQuery();
            // 돌면서 List<TelDto> 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dtoList;
    }
}
