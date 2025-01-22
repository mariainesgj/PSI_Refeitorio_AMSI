package pt.ipleiria.estg.dei.refeitorio.helpers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class QRCodeUtils {

    public static Bitmap generateQRCode(String content) throws WriterException {

        byte[] data = content.getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);


        int size = 512; //pixels
        HashMap<EncodeHintType, Integer> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bits = new QRCodeWriter().encode(base64, BarcodeFormat.QR_CODE, size, size, hints);
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                bitmap.setPixel(x, y, bits.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }

        return bitmap;
    }


}
