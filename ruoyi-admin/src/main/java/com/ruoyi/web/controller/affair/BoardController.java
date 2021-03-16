package com.ruoyi.web.controller.affair;

import com.ruoyi.affair.domain.Board;
import com.ruoyi.affair.service.IBoardService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 外宿申请
 *
 * @author akino
 */
@Api("外宿申请")
@RestController
@RequestMapping("/no/board")
public class BoardController extends BaseController {

    @Autowired
    private IBoardService boardService;

    @ApiOperation("根据学号查询外宿申请")
    @GetMapping("/queryById")
    public AjaxResult getBoard(String studentId){
        List<Board> boards = boardService.selectBoardByStuId(studentId);
        return AjaxResult.success(boards);
    }

    @ApiOperation("根据辅导员工号查询外宿申请")
    @GetMapping("/instructor/queryById")
    public AjaxResult getInsBoard(String instructorId){
        List<Board> boards = boardService.selectBoardByInsId(instructorId);
        return AjaxResult.success(boards);
    }

    @ApiOperation("修改外宿申请信息")
    @PostMapping("/edit")
    public AjaxResult editBoard(@Validated @RequestBody Board board){
        return toAjax(boardService.updateBoard(board));
    }

    @ApiOperation("修改外宿申请状态")
    @PreAuthorize("@ss.hasPermi('affair:noboard:approve')")
    @PostMapping("/setStatus")
    public AjaxResult editBoardStatus(@Validated @RequestBody Board board){
        return toAjax(boardService.updateBoardStatus(board));
    }

    @ApiOperation("新增外宿申请")
    @PostMapping("/add")
    public AjaxResult addBoard(@Validated @RequestBody Board board){
        return toAjax(boardService.addBoard(board));
    }

    @ApiOperation("撤销外宿申请")
    @DeleteMapping("/delete/{boardId}")
    public AjaxResult deleteBoard(@PathVariable Long boardId){
        return toAjax(boardService.deleteBoard(boardId));
    }

}
