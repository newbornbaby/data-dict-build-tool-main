package org.maozuowei.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author maozuowei
 * @version 1.0.0
 * @description: 目录栏
 * @className: CatalogueColumn
 * @date 2021/12/13 16:04
 **/
@ColumnWidth(20)
public class CatalogueColumn implements Serializable {
    /**
     * 表名称
     */
    @ExcelProperty("表名称")
    private String tableName;

    /**
     * 表备注
     */
    @ExcelProperty("表备注")
    private String tableComment;

    /**
     * sheet 超链接
     */
    @ExcelProperty("超链接")
    private String hyperlink;

    public CatalogueColumn(String tableName, String tableComment, String hyperlink) {
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.hyperlink = hyperlink;
    }

    public CatalogueColumn() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogueColumn that = (CatalogueColumn) o;
        return Objects.equals(tableName, that.tableName) && Objects.equals(tableComment, that.tableComment) && Objects.equals(hyperlink, that.hyperlink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, tableComment, hyperlink);
    }

    @Override
    public String toString() {
        return "CatalogueColumn{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", hyperlink='" + hyperlink + '\'' +
                '}';
    }
}