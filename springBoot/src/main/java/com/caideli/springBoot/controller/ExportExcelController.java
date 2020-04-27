package com.caideli.springBoot.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.caideli.springBoot.service.excel.CollectionPerformance;
import com.caideli.springBoot.service.excel.ExcelService;
import com.caideli.springBoot.service.excel.ExcelUtil;
import com.caideli.springBoot.service.excel.ReportTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/27 10:23
 * 描述：
 */
@Slf4j
@RestController
public class ExportExcelController {
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    private ExcelService excelService;

    //,consumes = {"application/x-www-form-urlencoded;charset=UTF-8"}
    @RequestMapping("/createExcel")
    public void createExcel(HttpServletResponse response) {
        String fileName = ReportTypeEnum.getByCode(11) + DATE_TIME_FORMAT.format(new Date());
        List<CollectionPerformance> collectionPerformanceList = new ArrayList<>();
        for (int i=1;i<11;i++){
            collectionPerformanceList.add(CollectionPerformance.builder()
                    .id(i)
                    .collectionReturnCount(i)
                    .collectionReturnCount(i+1)
                    .collectionUserId(i+2)
                    .collectionUserName("催收员"+i)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .colletciontAssignCount(i-1)
                    .reportDate(LocalDate.now())
                    .InterestReturnAmount(BigDecimal.valueOf(i))
                    .principalEntrustAmount(BigDecimal.valueOf(i))
                    .principalReturnAmount(BigDecimal.valueOf(i))
                    .totalReturnAmount(BigDecimal.valueOf(i))
                    .build());
        }
        List<String> keyList = new ArrayList<>();
        keyList.add("reportDate");
        keyList.add("collectionUserName");
        keyList.add("principalEntrustAmount");
        keyList.add("totalReturnAmount");
        try (BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0);
            sheet.setHead(ExcelUtil.createListStringHead(keyList));

            List<List<Object>> rowsList = ExcelUtil.createListObject(collectionPerformanceList,keyList);
            writer.write1(rowsList, sheet);
            response.reset();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xlsx");
            out.flush();
            writer.finish();
        } catch (Exception e) {
            log.error("导出报表异常，{}", e);
        }

    }
}
