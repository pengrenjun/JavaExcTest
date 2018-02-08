package com.ronglian.fssc.webapp.core.utils;

import java.io.File;
import java.util.Map;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtilTest {
    @Test
    public void test() {
        try {
            String content = "AP1012016012600001";
            String path = "C:\\Users\\tanliu\\Desktop\\QRCode";

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType, Object> hints = Maps.newHashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 0);
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

            BitMatrix matrix = multiFormatWriter.encode(new String(content.getBytes("UTF-8")), BarcodeFormat.QR_CODE, 120, 120, hints);

            // 生成二维码
            File file = new File(path, "101.png");
            //QRCodeUtil.getQRCode(matrix, "png", file);

            //QRCodeUtil.getQRCode(100, 100, "http://www.baidu.com", "png", file);
            QRCodeUtil.getQRCode(120, 120, "101201601260001", "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
