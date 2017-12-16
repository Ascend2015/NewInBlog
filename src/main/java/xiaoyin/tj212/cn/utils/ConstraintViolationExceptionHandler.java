package xiaoyin.tj212.cn.utils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用以处理异常
 */
public class ConstraintViolationExceptionHandler {
    public static String getMessage(ConstraintViolationException e){
        List<String> msgList=new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation:e.getConstraintViolations()){
            msgList.add(constraintViolation.getMessage());
        }

        String messages= StringUtils.join(msgList.toArray(),";");
        return messages;
    }
}
