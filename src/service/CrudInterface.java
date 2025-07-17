package service;

import dto.TelDto;

import java.util.List;

public interface CrudInterface {
    int insertData(TelDto dto);

    int UpdateData(TelDto dto);

    int deleteData(int id);

    List<TelDto> getListAll(); // 전체 찾기
    // 검색해서 가지고 오면 List TelDto를 넣어서 뿌림

    TelDto findById(int id); // 한 개 데이터 찾기

    List<TelDto> searchList(String keyword);
}
