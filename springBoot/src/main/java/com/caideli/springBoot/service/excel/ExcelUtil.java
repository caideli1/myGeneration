package com.caideli.springBoot.service.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * easyexcel工具类 -- Excel解析
 */
public class ExcelUtil {

    public static <T> List<List<Object>> createListObject(List<T> data, List<String> keyList) throws Exception {
        List<List<Object>> rows = new ArrayList<>();

        if (CollectionUtils.isEmpty(data) || CollectionUtils.isEmpty(keyList)) {
            return rows;
        }

        for (T bean : data) {
            List<Object> row = new ArrayList<>();
            for (String key : keyList) {
                Field fd = bean.getClass().getDeclaredField(key);
                fd.setAccessible(true);
                Object object = fd.get(bean);
                String value = object == null ? "" : object.toString();
                value = convertValueToName(value, fd.getName());
                row.add(value);
            }

            rows.add(row);
        }

        return rows;
    }

    public static List<List<String>> createListStringHead(Collection<String> headNameCollect) {
        List<List<String>> head = new ArrayList<>();

        if (null == headNameCollect || CollectionUtils.isEmpty(headNameCollect)) {
            return head;
        }

        for (String headName : headNameCollect) {
            List<String> headCoulumn = new ArrayList<>();
            headCoulumn.add(headName);
            head.add(headCoulumn);
        }

        return head;
    }

    private static String convertValueToName(String value, String name) {
        String result = value;

        if (StringUtils.isBlank(value) || StringUtils.isChinese(value)) {
            return result;
        }

        if (Objects.equals(name, ResultConvertNameEnum.PAY_STATUS.getName())) {
            return PayStatus.getMessageByNum(Integer.valueOf(value));
        }
        if (Objects.equals(name, ResultConvertNameEnum.ORDER_STATUS.getName())) {
            return OrderStatus.getMessageByNum(Integer.valueOf(value));
        }
        if (Objects.equals(name, ResultConvertNameEnum.ORDER_NO.getName())||
                Objects.equals(name, ResultConvertNameEnum.ORDER_NUM.getName())
        ) {

            if (StringUtils.isNotBlank(value)&&!value.contains("MN")){
                return "MN"+value+"PD";
            }
            return   value;

        }

        if (Objects.equals(name, ResultConvertNameEnum.HANDLER_TYPE.getName())) {
            return HandlerTypeEnum.getMessageByCode(Integer.valueOf(value));
        }
        if (Objects.equals(name, ResultConvertNameEnum.LOAN_TYPE.getName())) {
            return LoanTypeEnum.getMessageByCode(Integer.valueOf(value));
        }
        if (Objects.equals(name, ResultConvertNameEnum.LOAN_STATUS.getName())) {
            return LoanStatusEnum.getMessageByCode(Integer.valueOf(value));
        }

        return result;
    }

    /**
     * StringList 解析监听器
     */
    private static class StringExcelListener extends AnalysisEventListener {
        /**
         * 自定义用于暂时存储data
         * 可以通过实例获取该值
         */
        private List<List<String>> datas = new ArrayList<>();

        /**
         * 每解析一行都会回调invoke()方法
         *
         * @param object
         * @param context
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            List<String> stringList = (List<String>) object;
            //数据存储到list，供批量处理，或后续自己业务逻辑处理。
            datas.add(stringList);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
            //注意不要调用datas.clear(),否则getDatas为null
        }

        public List<List<String>> getDatas() {
            return datas;
        }

        public void setDatas(List<List<String>> datas) {
            this.datas = datas;
        }
    }

    /**
     * 使用 StringList 来读取Excel
     *
     * @param inputStream   Excel的输入流
     * @param excelTypeEnum Excel的格式(XLS或XLSX)
     * @return 返回 StringList 的列表
     */
    public static List<List<String>> readExcelWithStringList(InputStream inputStream, ExcelTypeEnum excelTypeEnum) {
        StringExcelListener listener = new StringExcelListener();
        ExcelReader excelReader = new ExcelReader(inputStream, excelTypeEnum, null, listener);
        excelReader.read();
        return listener.getDatas();
    }

    /**
     * 使用 StringList 来写入Excel
     *
     * @param outputStream  Excel的输出流
     * @param data          要写入的以StringList为单位的数据
     * @param table         配置Excel的表的属性
     * @param excelTypeEnum Excel的格式(XLS或XLSX)
     */
    public static void writeExcelWithStringList(OutputStream outputStream, List<List<String>> data, Table table, ExcelTypeEnum excelTypeEnum) {
        //这里指定不需要表头，因为String通常表头已被包含在data里
        ExcelWriter writer = new ExcelWriter(outputStream, excelTypeEnum, false);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系,无表头
        Sheet sheet1 = new Sheet(0, 0);
        writer.write0(data, sheet1, table);
        writer.finish();
    }

    /**
     * 模型解析监听器 -- 每解析一行会回调invoke()方法，整个excel解析结束会执行doAfterAllAnalysed()方法
     */
    private static class ModelExcelListener<E> extends AnalysisEventListener<E> {
        private List<E> dataList = new ArrayList<E>();

        @Override
        public void invoke(E object, AnalysisContext context) {
            dataList.add(object);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
        }

        public List<E> getDataList() {
            return dataList;
        }

        @SuppressWarnings("unused")
        public void setDataList(List<E> dataList) {
            this.dataList = dataList;
        }
    }

    /**
     * 使用 模型 来读取Excel
     *
     * @param inputStream   Excel的输入流
     * @param clazz         模型的类
     * @param excelTypeEnum Excel的格式(XLS或XLSX)
     * @return 返回 模型 的列表
     */
    public static <E> List<E> readExcelWithModel(InputStream inputStream, Class<? extends BaseRowModel> clazz, ExcelTypeEnum excelTypeEnum) {
        // 解析每行结果在listener中处理
        ModelExcelListener<E> listener = new ModelExcelListener<E>();
        ExcelReader excelReader = new ExcelReader(inputStream, excelTypeEnum, null, listener);
        //默认只有一列表头
        excelReader.read(new Sheet(1, 1, clazz));

        return listener.getDataList();
    }

    /**
     * 使用 模型 来写入Excel
     *
     * @param outputStream  Excel的输出流
     * @param data          要写入的以 模型 为单位的数据
     * @param clazz         模型的类
     * @param excelTypeEnum Excel的格式(XLS或XLSX)
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends BaseRowModel> data,
                                           Class<? extends BaseRowModel> clazz, ExcelTypeEnum excelTypeEnum) {
        //这里指定需要表头，因为model通常包含表头信息
        ExcelWriter writer = new ExcelWriter(outputStream, excelTypeEnum, true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 0, clazz);
        writer.write(data, sheet1);
        writer.finish();
    }

    public static void main(String[] args) throws Exception {
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
        List<List<Object>> rowsList = ExcelUtil.createListObject(collectionPerformanceList,keyList);
        System.out.println(JSON.toJSONString(rowsList));
    }
}