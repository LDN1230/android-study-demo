package ldn.personalproject3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: LDN
 * @date: 2020/5/9
 */
public class Convert {
    public ArrayList<String> string2Array(String s){
        if(s.isEmpty())
            return new ArrayList<>();
        String[] strings = s.split("[$]");
        ArrayList<String> list = new ArrayList(strings.length);
        Collections.addAll(list, strings);
        return list;
    }
    public String array2String(ArrayList<String> arrayList){
        StringBuffer stringBuffer = new StringBuffer();
        for(String i : arrayList){
            stringBuffer.append(i);
            stringBuffer.append("$");
        }
        return  stringBuffer.toString();
    }
}
