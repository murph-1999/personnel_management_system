package util;

public class GetParamsUtil {
    public static String getParam(String str,String key) {
        String rtn = null;
        String arr [] = str.split("&");
        System.out.println(arr);
        for(String s : arr)
        {
            String keyInArr = s.split("=")[0];
            String value = s.split("=")[1];
            if(keyInArr.equals(key))
            {
                rtn = value;
                break;
            }
        }
        return rtn;
    }
}
