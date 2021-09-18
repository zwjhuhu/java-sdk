package cn.com.nexwise.sdk.common.dto;

import java.util.ArrayList;
import java.util.List;


public class AccessTokenResult extends ApiResult {

    private String accessToken;


    /**
     * token 过期时间，秒级时间戳
     */
    private long expireTime;

    private List<String> zoneIds = new ArrayList<>();

    private List<String> projectIds = new ArrayList<>();

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public List<String> getZoneIds() {
        return zoneIds;
    }

    public void setZoneIds(List<String> zoneIds) {
        this.zoneIds = zoneIds;
    }

    public List<String> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }

}

