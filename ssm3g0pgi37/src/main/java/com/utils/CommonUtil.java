package com.utils;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.service.ConfigService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.util.ResourceUtils;

@Component
public class CommonUtil {
    private static JavaMailSenderImpl mailSender;
    
	@Autowired
	public void setMailSender(JavaMailSenderImpl mailSender) {
    	CommonUtil.mailSender = mailSender;
	}
    private static AipFace client = null;

    private static ConfigService configService;

    @Autowired
    public void setConfigService(ConfigService configService) {
        CommonUtil.configService = configService;
    }

	/**
     * 获取随机字符串
     *
     * @param num
     * @return
     */
    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

	/**
     * 获取随机验证码
     *
     * @param num
     * @return
     */
    public static String getRandomNumber(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getCellValue(Cell cell) {
        String resultValue = "";
        // 判空
        if (Objects.isNull(cell)) {
            return resultValue;
        }

        DataFormatter formatter = new DataFormatter();
        // 创建公式求值器
        FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();

        // 拿到单元格类型
        int cellType = cell.getCellType();
        switch (cellType) {
            // 字符串类型
            case Cell.CELL_TYPE_STRING:
                resultValue = StringUtils.isEmpty(cell.getStringCellValue()) ? "" : cell.getStringCellValue().trim();
                break;
            // 计算公式
            case Cell.CELL_TYPE_FORMULA:
                resultValue = formatter.formatCellValue(cell, evaluator);
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                resultValue = String.valueOf(cell.getBooleanCellValue());
                break;
            // 数值类型
            case Cell.CELL_TYPE_NUMERIC:
                /**
                 * format 的值可能为以下这些 yyyyMMddHHmmss
                 * yyyy-MM-dd----- 14
                 * yyyy年m月d日----- 31
                 * yyyy年m月--------57
                 * m月d日  -----------58
                 * HH:mm-----------20
                 * h时mm分  --------- 32
                 */
                Object val = cell.getNumericCellValue();
                // POI Excel 日期格式转换
                String formatDate = "";
                if (DateUtil.isCellDateFormatted(cell)) {
                    switch (cell.getCellStyle().getDataFormat()) {
                        case 14:
                            formatDate = "yyyy-MM-dd";
                            break;
                        case 20:
                            formatDate = "HH:mm";
                            break;
                        case 21:
                            formatDate = "HH:mm:ss";
                            break;
                        case 31:
                            formatDate = "yyyy年MM月dd日";
                            break;
                        case 32:
                            formatDate = "HH时mm分";
                            break;
                        case 33:
                            formatDate = "HH时mm分mm秒";
                            break;
                        case 57:
                            formatDate = "yyyy年MM月";
                            break;
                        case 58:
                            formatDate = "MM月dd日";
                            break;
                        case 176:
                            formatDate = "yyyy-MM-dd HH:mm:ss";
                            break;
                    }
                    if(!"".equals(formatDate)){
                        resultValue = new SimpleDateFormat(formatDate).format(DateUtil.getJavaDate((Double) val));
                    }
                }else{
                    resultValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            // 取空串
            default:
                break;
        }
        return resultValue;
    }

    /**
     * 检查实体中是否存在数组中的属性字段，并返回第一个匹配的属性的get方法的结果
     * @param entity 实体对象
     * @param fieldNames 属性字段名称数组
     * @return 第一个匹配的属性的get方法的结果，如果没有匹配则返回null
     */
    public static Object getPropertyByFieldNames(Object entity, String... fieldNames) {
        if (entity == null || fieldNames == null) {
            return null;
        }
        Class<?> clazz = entity.getClass();
        for (String fieldName : fieldNames) {
            try {
                // 构造get方法名
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = clazz.getMethod(methodName);
                // 调用get方法并返回结果
                return method.invoke(entity);
            } catch (NoSuchMethodException e) {
                // get方法不存在，继续下一个字段
                continue;
            } catch (Exception e) {
                // 其他异常，打印堆栈信息
                e.printStackTrace();
                return null;
            }
        }
        // 没有找到匹配的字段
        return null;
    }

    /**
     * 邮件发送
     * @param recipient 收件人
     * @param subject 主题
     * @param content 内容
     */
    public static void sendEmail(String recipient,String subject,String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom("yclw9@qq.com");//发件人
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(content);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
                e.printStackTrace();
        }
    }




    /**
     * 人脸比对
     *
     * @param face1 人脸1
     * @param face2 人脸2
     * @return
     */
    public static R matchFace(String face1, String face2,HttpServletRequest request) {
        if(client==null) {
            /*String AppID = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "AppID")).getValue();*/
            String APIKey = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "APIKey")).getValue();
            String SecretKey = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "SecretKey")).getValue();
            String token = BaiduUtil.getAuth(APIKey, SecretKey);
            if(token==null) {
                return R.error("请在配置管理中正确配置APIKey和SecretKey");
            }
            client = new AipFace(null, APIKey, SecretKey);
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
        }
        org.json.JSONObject res = null;
        try {
            File file1 = new File(request.getSession().getServletContext().getRealPath("/upload")+"/"+face1);
            File file2 = new File(request.getSession().getServletContext().getRealPath("/upload")+"/"+face2);
            String img1 = Base64Util.encode(FileUtil.FileToByte(file1));
            String img2 = Base64Util.encode(FileUtil.FileToByte(file2));
            MatchRequest req1 = new MatchRequest(img1, "BASE64");
            MatchRequest req2 = new MatchRequest(img2, "BASE64");
            ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
            requests.add(req1);
            requests.add(req2);
            res = client.match(requests);
            System.out.println(res.get("result"));
            if((int)res.get("error_code")!=0) {
                return R.ok().put("score",0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return R.error("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("score", com.alibaba.fastjson.JSONObject.parse(res.getJSONObject("result").get("score").toString()));
    }
}
