package com.ruoyi.info.mapper;

import com.ruoyi.info.domain.HeadmasterInfo;

import java.util.List;

public interface HeadmasterInfoMapper {

    /**
     * 查询班主任信息
     *
     * @param headmasterId 班主任工号
     * @return 结果
     */
    public HeadmasterInfo selectHeadmaster(String headmasterId);

    /**
     * 批量查询班主任信息
     *
     * @param headmasterInfo 查询信息
     * @return 结果
     */
    public List<HeadmasterInfo> selectAllHeadmaster(HeadmasterInfo headmasterInfo);

    /**
     * 添加班主任基本信息
     *
     * @param headmasterInfo 班主任基本信息
     * @return 结果
     */
    public int insertHeadmaster(HeadmasterInfo headmasterInfo);

    /**
     * 添加班主任角色信息
     *
     * @param userId 班主任用户ID
     * @return 结果
     */
    public int insertHeadmasterRole(Long userId);

    /**
     * 删除班主任基本信息
     *
     * @param headmasterId 班主任工号
     * @return 结果
     */
    public int deleteHeadmaster(String headmasterId);
}
