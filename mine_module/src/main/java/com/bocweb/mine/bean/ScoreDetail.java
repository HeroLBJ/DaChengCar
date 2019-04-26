package com.bocweb.mine.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/26
 */
public class ScoreDetail implements Serializable {
    private String usable;
    private String income;
    private String expend;
    private ScoreDetailCount list;

    public static class ScoreDetailCount{
        private int count;
        private List<ScoreDetailList> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ScoreDetailList> getList() {
            return list;
        }

        public void setList(List<ScoreDetailList> list) {
            this.list = list;
        }
    }
    public static class ScoreDetailList{
        private String id;
        private String accountId;
        private String credit;
        private String carName;
        private String carFrame;
        private String reson;
        private String timeline;
        private String startime;
        private String endtime;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getCarFrame() {
            return carFrame;
        }

        public void setCarFrame(String carFrame) {
            this.carFrame = carFrame;
        }

        public String getReson() {
            return reson;
        }

        public void setReson(String reson) {
            this.reson = reson;
        }

        public String getTimeline() {
            return timeline;
        }

        public void setTimeline(String timeline) {
            this.timeline = timeline;
        }

        public String getStartime() {
            return startime;
        }

        public void setStartime(String startime) {
            this.startime = startime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public String getUsable() {
        return usable;
    }

    public void setUsable(String usable) {
        this.usable = usable;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpend() {
        return expend;
    }

    public void setExpend(String expend) {
        this.expend = expend;
    }

    public ScoreDetailCount getList() {
        return list;
    }

    public void setList(ScoreDetailCount list) {
        this.list = list;
    }
}
