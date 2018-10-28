package com.jmed.dzdp.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by shenpengfei on 2017/7/26.
 */

@Component
public class SignUtils {
	private static String appKey;

	public static String makeSign(Map<String, Object> params, String appKey) {
        if (MapUtils.isEmpty(params) || StringUtils.isEmpty(appKey)) {
            throw new NullPointerException("签名参数为空");
        }
        ArrayList keyList = Lists.newArrayList(params.keySet());
        Collections.sort(keyList);
        Iterator i$ = keyList.iterator();
        List<String> paramList = Lists.newArrayList();
        while (i$.hasNext()) {
            String key = (String) i$.next();
            if (!key.equals("sign")) {
	            String param = key + params.get(key);
	            paramList.add(param);
            }
        }
        String signStr = Joiner.on("").join(paramList) + appKey;
        String result = DigestUtils.md5Hex(signStr);
        return result.toUpperCase();
    }
    
    public static boolean isSignMatch(Map params) {
    	String sign = (String) params.get("sign");
    	String signMade = makeSign(params, appKey);
    	return sign != null  && sign.equals(signMade);
    }
    
    @Value("${appkey}")
    public void setAppkey(String key) {
    	appKey = key;
    }
    
    public static String getAppkey() {
    	return appKey;
    }
}
