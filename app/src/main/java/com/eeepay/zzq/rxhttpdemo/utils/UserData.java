package com.eeepay.zzq.rxhttpdemo.utils;


import java.io.Serializable;

/**
 * Created by zw on 2016/8/11 0011.
 * 用户信息
 */
public final class UserData implements Serializable {
    private static final String USER_INFO = "agent_info";
    private static volatile UserData instance = null;
    /* {
             "userName":"前海移联直营",
             "password":"",
             "agentOem":"200010",
             "loginToken":"57005982-4283-43c8-a776-96f974d3dc4c",
             "userId":"1000000000000002897",
             "agentNo":"1446",
             "agentNode":"0-1446-",
             "agentName":"E前海移联直营read",
             "agentLevel":1,
             "parentId":"0",
             "oneLevelId":"1446",
             "mobilePhone":"13888888888",
             "manage":"1",
             "lockTime":null,
             "wrongPasswordCount":0
     }*/
    private String userName;
    private String password;
    private String agentOem;
    private String loginToken;
    private String userId;
    private String agentNo;
    private String agentNode;
    private String agentName;
    private int agentLevel;
    private String parentId;
    private String oneLevelId;
    private String mobilePhone;
    private String manage;
    private String lockTime;
    private int wrongPasswordCount;
    private String oneAgentNo;//一级代理商编号
    private String teamId;//组织id


    public String getOneAgentNo() {
        return oneAgentNo;
    }

    public void setOneAgentNo(String oneAgentNo) {
        this.oneAgentNo = oneAgentNo;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public int getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(int agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOneLevelId() {
        return oneLevelId;
    }

    public void setOneLevelId(String oneLevelId) {
        this.oneLevelId = oneLevelId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public int getWrongPasswordCount() {
        return wrongPasswordCount;
    }

    public void setWrongPasswordCount(int wrongPasswordCount) {
        this.wrongPasswordCount = wrongPasswordCount;
    }


//    public PubDataInfo.DataBean getPubDataBean() {
//        return pubDataBean;
//    }
//
//    public void setPubDataBean(PubDataInfo.DataBean pubDataBean) {
//        this.pubDataBean = pubDataBean;
//    }
//
//    //公共数据各种开关
//    private PubDataInfo.DataBean pubDataBean;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    private boolean isLogin = false;//是否登录过

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgentOem() {
        return agentOem;
    }

    public void setAgentOem(String agentOem) {
        this.agentOem = agentOem;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getAgentNode() {
        return agentNode;
    }

    public void setAgentNode(String agentNode) {
        this.agentNode = agentNode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }


    private UserData() {
    }

    public static UserData getInstance() {
        if (instance == null) {
            synchronized (UserData.class) {
                if (instance == null) {
                    instance = new UserData();
                }
            }
        }
        return instance;
    }

//    public static UserData getUserDataInSP() {
//        if (instance == null) {
//            String infoStr = PreferenceUtils.getStringParam(USER_INFO);
//            String key = PreferenceUtils.getStringParam(BaseCons.AES_PWD);
//            String decInfo = AESUtils.decrypt(key, infoStr);
//            instance = (UserData) SerializeUtils.deSerialization(decInfo);//反序列化
//            if (instance == null) {
//                getInstance();
//            }
//        }
//        return instance;
//    }

//    public void saveUserInfo() {
//        if (instance != null) {
//            String serialize = SerializeUtils.serialize(instance);//序列化
//            String encode = Base64.encode(serialize);
//            String key = Md5.encode(encode+BaseCons.KEY_VALUE);//秘钥
//
////            String key = Md5.encode("12345678987654321");//把代理商编号MD5值当做密钥
//            String encrypt = AESUtils.encrypt(key, serialize);//加密
//            PreferenceUtils.saveParam(BaseCons.AES_PWD, key);//保存key
//            PreferenceUtils.saveParam(USER_INFO, encrypt);
//        } else {
//            Logger.d("保存用户信息失败");
//        }
//    }

    public  void removeUserInfo() {
        instance = null;
//        PreferenceUtils.removeKey(USER_INFO);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", agentOem='" + agentOem + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", userId='" + userId + '\'' +
                ", agentNo='" + agentNo + '\'' +
                ", agentNode='" + agentNode + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentLevel=" + agentLevel +
                ", parentId='" + parentId + '\'' +
                ", oneLevelId='" + oneLevelId + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", manage='" + manage + '\'' +
                ", lockTime='" + lockTime + '\'' +
                ", wrongPasswordCount=" + wrongPasswordCount +
                ", oneAgentNo='" + oneAgentNo + '\'' +
                ", teamId='" + teamId + '\'' +
                ", isLogin=" + isLogin +
                '}';
    }
}
