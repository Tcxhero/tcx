package com.feisuanyz.common.util;

import org.springframework.stereotype.Component;

/**
 * <p>
 *   用户工具类
 * </p>
 * @author tianchunxu
 */
@Component
public class UserUtil {

    public static boolean exists(Long userId) {
        // 实现逻辑，例如查询数据库中是否存在该用户ID
        // 这里仅作示例，实际需要调用用户服务或数据库访问层
        return userId != null && userId > 0;
    }
}