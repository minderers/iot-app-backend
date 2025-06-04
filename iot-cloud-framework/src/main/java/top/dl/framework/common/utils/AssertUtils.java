package top.dl.framework.common.utils;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import top.dl.framework.common.exception.ServerException;
/**
 * @author: minder
 * @createTime: 2025/04/25 8:52
 * @description:
 **/
public class AssertUtils {

    public static void isBlank(String str, String variable) {
        if (StrUtil.isBlank(str)) {
            throw new ServerException(variable + "不能为空");
        }
    }

    public static void isNull(Object object, String variable) {
        if (object == null) {
            throw new ServerException(variable + "不能为空");
        }
    }

    public static void isArrayEmpty(Object[] array, String variable) {
        if (ArrayUtil.isEmpty(array)) {
            throw new ServerException(variable + "不能为空");
        }
    }

}
