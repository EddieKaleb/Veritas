package Convert;

public class Convert {
	public static String byte2hex(byte[] dado_byte) {
        String dado_hexa = "";
        String stmp = "";
        for (int n = 0; n < dado_byte.length; n++) {
            stmp = Integer.toHexString(dado_byte[n] & 0xFF);
            if (stmp.length() == 1) {
                dado_hexa += ("0" + stmp);
            } else {
                dado_hexa += stmp;
            }
        }
        return dado_hexa.toUpperCase();
    }

    public static byte[] hex2byte(byte[] dado_hexa) {
        if ((dado_hexa.length % 2) != 0) {
            throw new IllegalArgumentException("Error hex2byte. Lenght not divisible for 2.");
        }

        byte[] dado_byte = new byte[dado_hexa.length / 2];

        for (int n = 0; n < dado_hexa.length; n += 2) {
            String item = new String(dado_hexa, n, 2);
            dado_byte[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return dado_byte;
    }
}
