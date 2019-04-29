package com.bocweb.mine.bean;

import java.io.Serializable;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/25
 */
public class MyScore implements Serializable {
    private String integral; // 总积分
    private String birth; // 生日积分
    private String register; // 注册积分
    private DaySign daySign;
    private DayForward dayForward;
    private FirstService firstService;
    private FiveStart fiveStart;
    private MemberInfo memberInfo;
    private CarOwner carOwner;

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public DaySign getDaySign() {
        return daySign;
    }

    public void setDaySign(DaySign daySign) {
        this.daySign = daySign;
    }

    public DayForward getDayForward() {
        return dayForward;
    }

    public void setDayForward(DayForward dayForward) {
        this.dayForward = dayForward;
    }

    public FirstService getFirstService() {
        return firstService;
    }

    public void setFirstService(FirstService firstService) {
        this.firstService = firstService;
    }

    public FiveStart getFiveStart() {
        return fiveStart;
    }

    public void setFiveStart(FiveStart fiveStart) {
        this.fiveStart = fiveStart;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public CarOwner getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
    }

    public static class DaySign {
        private String todaySign; // 0：未签到 1：已签到
        private String integral; // 签到赠送的积分

        @Override
        public String toString() {
            return "DaySign{" +
                    "todaySign='" + todaySign + '\'' +
                    ", integral='" + integral + '\'' +
                    '}';
        }

        public String getTodaySign() {
            return todaySign;
        }

        public void setTodaySign(String todaySign) {
            this.todaySign = todaySign;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }
    }

    public static class DayForward {
        private String max; // 0-未达到上限 1-已达到上限
        private String integral; // 转发获得的积分

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }
    }

    public static class FirstService {
        private String integral; // 首次服务获得积分
        private String complete; // 是否完成

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getComplete() {
            return complete;
        }

        public void setComplete(String complete) {
            this.complete = complete;
        }
    }

    public static class FiveStart {
        private String exist; // 是否还有未评价的 0-无 1-有
        private String integral; // 五星好评获得的积分

        public String getExist() {
            return exist;
        }

        public void setExist(String exist) {
            this.exist = exist;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }
    }

    public static class MemberInfo {
        private String isUpdate; // 是否完善个人信息 0-否 1-是
        private String integral; // 完善信息获得的积分

        public String getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(String isUpdate) {
            this.isUpdate = isUpdate;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }
    }

    public static class CarOwner {
        private String isCarOwner; //  是否车主 0-否 1-是
        private String integral; // 认证车主获得的积分

        public String getIsCarOwner() {
            return isCarOwner;
        }

        public void setIsCarOwner(String isCarOwner) {
            this.isCarOwner = isCarOwner;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }
    }
}
