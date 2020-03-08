package com.eeepay.zzq.rxhttpdemo.bean;

import java.io.Serializable;

/**
 * 描述：登录的信息实体bean
 * 作者：zhuangzeqin
 * 时间: 2019/5/15-11:40
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */

public class LoginInfo implements Serializable {
    /**
     * code : 200
     * message :
     * data : {"userName":"前海移联直营","password":"","agentOem":"200010","loginToken":"57005982-4283-43c8-a776-96f974d3dc4c","userId":"1000000000000002897","agentNo":"1446","agentNode":"0-1446-","agentName":"E前海移联直营read","agentLevel":1,"parentId":"0","oneLevelId":"1446","mobilePhone":"13888888888","manage":"1","lockTime":null,"wrongPasswordCount":0}
     * count : 0
     * success : true
     */

    private int code;
    private String message;
    private DataBean data;
    private int count;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * userName : 前海移联直营
         * password :
         * agentOem : 200010
         * loginToken : 57005982-4283-43c8-a776-96f974d3dc4c
         * userId : 1000000000000002897
         * agentNo : 1446
         * agentNode : 0-1446-
         * agentName : E前海移联直营read
         * agentLevel : 1
         * parentId : 0
         * oneLevelId : 1446
         * mobilePhone : 13888888888
         * manage : 1
         * lockTime : null
         * wrongPasswordCount : 0
         */

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
    }
}
