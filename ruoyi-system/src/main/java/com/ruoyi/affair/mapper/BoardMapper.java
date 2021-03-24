package com.ruoyi.affair.mapper;

import com.ruoyi.affair.domain.Board;

import java.util.List;

public interface BoardMapper {

    /**
     * 根据学号查询外宿申请
     *
     * @param board 学号
     * @return 结果
     */
    public List<Board> selectBoardByStuId(Board board);

    /**
     * 根据辅导员查询外宿申请
     *
     * @param board 工号
     * @return 结果
     */
    public List<Board> selectBoardByInsId(Board board);

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
