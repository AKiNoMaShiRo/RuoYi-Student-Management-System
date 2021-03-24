package com.ruoyi.affair.service.Impl;

import com.ruoyi.affair.domain.Board;
import com.ruoyi.affair.mapper.BoardMapper;
import com.ruoyi.affair.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements IBoardService {

    @Autowired
    private BoardMapper boardMapper;

    /**
     * 根据学号查询外宿申请
     *
     * @param board 学号
     * @return 结果
     */
    public List<Board> selectBoardByStuId(Board board){
        return boardMapper.selectBoardByStuId(board);
    }

    /**
     * 根据辅导员查询外宿申请
     *
     * @param board 工号
     * @return 结果
     */
    public List<Board> selectBoardByInsId(Board board){
        return  boardMapper.selectBoardByInsId(board);
    }

    /**
     * 新增外宿申请
     *
     * @param board 外宿申请信息
     * @return 结果
     */
    public int addBoard(Board board){
        return boardMapper.addBoard(board);
    }

    /**
     * 更新外宿申请状态
     *
     * @param board 外宿申请信息
     * @return 结果
     */
    public int updateBoardStatus(Board board){
        return boardMapper.updateBoardStatus(board);
    }

    /**
     * 修改外宿申请状态
     *
     * @param board 外宿申请信息
     * @return 结果
     */
    public int updateBoard(Board board){
        return boardMapper.updateBoard(board);
    }

    /**
     * 撤销哦外宿申请
     *
     * @param boardId 外宿申请信息ID
     * @return 结果
     */
    public int deleteBoard(Long boardId){
        return boardMapper.deleteBoard(boardId);
    }

}
