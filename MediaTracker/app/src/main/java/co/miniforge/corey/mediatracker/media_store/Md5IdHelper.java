package co.miniforge.corey.mediatracker.media_store;

import android.util.Log;

import java.security.MessageDigest;

/**
 * Created by corey on 10/21/17.
 */

public class Md5IdHelper {
    public static String idForObject(Object o){
        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(o.toString().getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            result = sb.toString();

        } catch (Exception e){
            Log.e("Md5Err", "Could not generate an ID from the object");
        }

        return result;
    }
}
