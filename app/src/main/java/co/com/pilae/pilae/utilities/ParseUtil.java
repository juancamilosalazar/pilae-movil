package co.com.pilae.pilae.utilities;

public class ParseUtil {
    public static  Double stringToDouble(String valor) {
        return "".equals(valor) ? 0 : Double.parseDouble(valor);
    }

    public static  Long stringToLong(String valor) {
        return "".equals(valor) ? 0 : Long.parseLong(valor);
    }
    public static  Integer stringToInteger(String valor) {
        return "".equals(valor) ? 0 : Integer.parseInt(valor);
    }

    public static String intToString(int id) {
        return String.valueOf(id);
    }
}
