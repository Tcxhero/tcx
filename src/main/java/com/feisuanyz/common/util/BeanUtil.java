package com.feisuanyz.common.util;

import org.springframework.beans.BeanUtils;

/**
 * <p>
 *   Bean工具类
 * </p>
 * @author tianchunxu
 */
public class BeanUtil {

    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Bean复制失败", e);
        }
    }
}