package io.doraemon.md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String digest(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	public static void main(String[] args){
		System.out.println(digest("eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBMV81In0.xbkAJqwfYLozvPHNc8asacurfgNMSYrVK1pzLL810TZwKJRuXxU4dnzhs7IUm1n87p6qfPFL6bekdoMbulBGicSzrozR3sOW7l4wx_vM0Mm9kGbBftrMoswkMTANeRy1sSlkIAr0Ru9pJbc2D6V1QgViPALcRJ3_gI5f_PInhQE.rkNdsK_YEFSjBgf4.7yd5Jr6UrDFxs-iZxE61CfdZzA32-QtD7GyapMUVv1c-z1rbir3SvGJuBRxOPVf6fSbnyOTSlJRjQ4BDioN-MB7H9XqlRMOBw0_6ss-FKew8Z8R8WtY7Rcejy0_d_VeMnID4gi2TeGG9Ur2DKGgyCkTT150NMLurtS-k1_wOLi9wKrF_LjIDBpl9EBX4ansK2vFm.mbq80f--NLge4HAMsKvVkw"));
	}
}
