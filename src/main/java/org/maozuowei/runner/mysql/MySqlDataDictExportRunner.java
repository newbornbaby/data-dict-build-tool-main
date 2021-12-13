package org.maozuowei.runner.mysql;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.maozuowei.config.DictExportConfig;
import org.maozuowei.entity.CatalogueColumn;
import org.maozuowei.entity.Column;
import org.maozuowei.entity.Table;
import org.maozuowei.mapper.mysql.ColumnMapper;
import org.maozuowei.mapper.mysql.TableMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maozuowei
 * @date 2020/10/12 11:33
 */
@Component
@Order(1)
@ConditionalOnProperty(value = "app.db-type", havingValue = "mysql")
@RequiredArgsConstructor
@Slf4j
public class MySqlDataDictExportRunner implements CommandLineRunner {
    private final TableMapper tableMapper;

    private final ColumnMapper columnMapper;

    private final DictExportConfig config;

    @Override
    public void run(String... args) {
        ExcelWriter excelWriter = null;
        List<Table> tables = null;
        WriteSheet writeSheet = null;
        try {
            // Excel 初始化
            excelWriter = EasyExcel.write(config.getExportPath()).build();
            tables = tableMapper.findAllTablesMetadataByTableSchema(config.getTableSchema());
            // 创建目录sheet
            List<CatalogueColumn> catalogueColumns = new ArrayList<>();
            for (int i = 0; i < tables.size(); i++) {
                // 超链接格式
                String jump = String.format("=HYPERLINK(\"#\"&A%s&\"!a1\",\"点我跳转到工作表\")", (i + 2));
                CatalogueColumn catalogueColumn = StringUtils.isEmpty(tables.get(i).getTableComment()) ?
                        new CatalogueColumn(tables.get(i).getTableName(), "无", jump) :
                        new CatalogueColumn(tables.get(i).getTableName(), tables.get(i).getTableComment(), jump);
                catalogueColumns.add(catalogueColumn);
            }
            writeSheet = EasyExcel.writerSheet("目录").head(CatalogueColumn.class)
                    .build();
            excelWriter.write(catalogueColumns, writeSheet);
            // 创建tables sheet
            List<Column> columns;
            // 设置返回目录链接
            Column backColumn = new Column();
            backColumn.setTableScheme("=HYPERLINK(\"#目录!a1\", \"返回目录\")");
            for (int i = 0; i < tables.size(); i++) {
                columns = columnMapper.findAllColumnsMetadataByTableSchemaAndTableName(config.getTableSchema(),
                        tables.get(i).getTableName());
                // 最后一行，强制添加返回目录链接
                columns.add(backColumn);
                /*writeSheet = EasyExcel.writerSheet(StringUtils.isEmpty(table.getTableComment()) ? table.getTableName() :
                        String.format("%s(%s)", table.getTableName(), table.getTableComment()))
                        .build();*/
                writeSheet = EasyExcel.writerSheet(tables.get(i).getTableName()).head(Column.class)
                        .build();
                excelWriter.write(columns, writeSheet);
            }
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        log.info("MySQL数据字典导出完毕，存放位置为：[{}]", config.getExportPath());
    }
}
