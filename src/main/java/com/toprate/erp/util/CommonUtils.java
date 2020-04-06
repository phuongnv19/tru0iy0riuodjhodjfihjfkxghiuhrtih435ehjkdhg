package com.toprate.erp.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.List;

public class CommonUtils {
    public static String DOUBLE_PATTERN_DEFAULT = "#0.##";

    public static String nvl(Object o) {
        return o == null ? "" : o.toString();
    }

    public static boolean isEmpty(List<?> l) {
        return l == null || l.isEmpty();
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String formatNumberDefault(Double d) {
        return d == null ? "" : new DecimalFormat(DOUBLE_PATTERN_DEFAULT).format(d);
    }

    public static boolean validateHeaderTemplateImport(String headerContent, String title) {
        if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(headerContent) && headerContent.equalsIgnoreCase(title)) {
            return true;
        }
        return false;
    }

//    public static void main(String[] args) {
//        Double d=123D;
//        System.out.println(formatNumberDefault(d));
//        EvoucherDTO evoucherDTO = new EvoucherDTO();
//        LocalDateTime localDateTime = LocalDateTime.now();
//        evoucherDTO.setAvailableFrom(localDateTime);
//        System.out.println(localDateTime == null? "":DateUtils.formatLocalDateTime(evoucherDTO.getAvailableFrom()));
//        //System.out.println(evoucherDTO.getAvailableFrom().toString());
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("123456"));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, String> map = new HashMap<>();
//        map.put("campaignId", "123");
//        map.put("merchantId", "456");
//        try {
//            String json = objectMapper.writeValueAsString(map);
//            Map<String, String> map2 = objectMapper.readValue(json, Map.class);
//            System.out.println(map2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
