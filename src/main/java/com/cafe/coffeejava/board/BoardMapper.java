package com.cafe.coffeejava.board;

import com.cafe.coffeejava.board.model.*;
import com.cafe.coffeejava.common.model.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardPostReq p);
    Long roleByUserId(Long userId);
    List<BoardGetRes> getBoard(Paging p);
    List<BoardDetailGetRes> getBoardDetail(Long boardId);
    int patchBoard(BoardPutReq p);
    Long findUserIdByBoard(Long boardId);
    int delBoard(Long boardId);
}