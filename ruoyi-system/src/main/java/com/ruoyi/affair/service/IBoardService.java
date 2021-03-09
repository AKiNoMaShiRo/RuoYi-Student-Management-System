package com.ruoyi.affair.service;

import com.ruoyi.affair.domain.Board;

import java.util.List;

/**
 * 节假日去向 业务层
 *
 * @author akino
 */
public interface IBoardService {

    /**
     * 根据学号查询外宿申请
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<Board> selectBoardByStuId(String studentId);

    /**
     * 新增外宿申请
     *
     * @param board 外宿申请信息
     * @return 结果
     */
    public int addBoard(Board board);

    /**
     * 更新外宿申请状态
     *
     * @param board 外宿申请信息
     * @return 结果
     */
    public int updateBoardStatus(Board board);

    /**
     * 修改外宿申请状态
     *
     * @param board 外宿申请信息
     * @return 结果
     */
    public int updateBoard(Board board);

    /**
     * 撤销哦外宿申请
     *
     * @param boardId 外宿申请信息ID
     * @return 结果
     */
    public int deleteBoard(Long boardId);

}
