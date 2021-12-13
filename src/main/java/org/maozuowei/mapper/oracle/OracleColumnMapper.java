package org.maozuowei.mapper.oracle;

import org.apache.ibatis.annotations.Param;
import org.maozuowei.entity.OracleColumn;

import java.util.List;

/**
 * @author maozuowei
 * @date 2020/10/12 15:36
 * @description
 */
public interface OracleColumnMapper {

    /**
     * 根据表名查询某张表的列元数据
     * @author maozuowei
     * @date 2020/10/12 14:16
     * @param tableName 表名
     * @return java.util.List<org.maozuowei.entity.OracleColumn>
     */
    List<OracleColumn> findAllColumnsMetadataByTableName(@Param("tableName") String tableName);

}
