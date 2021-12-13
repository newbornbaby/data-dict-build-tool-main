package org.maozuowei.mapper.oracle;

import org.apache.ibatis.annotations.Param;
import org.maozuowei.entity.OracleTable;

import java.util.List;

/**
 * @author maozuowei
 * @date 2020/10/12 15:50
 * @description
 */
public interface OracleTableMapper {

    /**
     * 根据用户查找表元数据
     * @author maozuowei
     * @date 2020/10/12 14:28
     * @param tableType 表类型，默认为：TABLE
     * @param owner 用户
     * @return java.util.List<org.maozuowei.entity.OracleTable>
     */
    List<OracleTable> findAllByOwner(@Param("tableType") String tableType, @Param("owner") String owner);
}
