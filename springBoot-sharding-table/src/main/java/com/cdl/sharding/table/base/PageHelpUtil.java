package com.cdl.sharding.table.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author wuwenjun(Flying)
 * @date 2019/8/29 0029 21:17
 * @desc pageHelp分页[函数式]
 */
public class PageHelpUtil {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUM = 1;


    public static <T> PageInfo<T> pageHelp(int pageSize, int pageNum, PageHelpService<T> pageHelpService) {

        if (pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if (pageNum <= 0) {
            pageSize = DEFAULT_PAGE_NUM;
        }

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(pageHelpService.apply());
    }

    /**
     * 分页接口
     *
     * @param <T>
     */
    public interface PageHelpService<T> {
        List<T> apply();
    }

}
