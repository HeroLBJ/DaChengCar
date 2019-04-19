/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.njh.common.core;

/**
 * ================================================
 * RouterHub 用来定义路由器的路由地址, 以组件名作为前缀, 对每个组件的路由地址进行分组, 可以统一查看和管理所有分组的路由地址
 * <p>
 * RouterHub 存在于基础库, 可以被看作是所有组件都需要遵守的通讯协议, 里面不仅可以放路由地址常量, 还可以放跨组件传递数据时命名的各种 Key 值
 * 再配以适当注释, 任何组件开发人员不需要事先沟通只要依赖了这个协议, 就知道了各自该怎样协同工作, 既提高了效率又降低了出错风险, 约定的东西自然要比口头上说强
 * <p>
 * 如果您觉得把每个路由地址都写在基础库的 RouterHub 中, 太麻烦了, 也可以在每个组件内部建立一个私有 RouterHub, 将不需要跨组件的
 * 路由地址放入私有 RouterHub 中管理, 只将需要跨组件的路由地址放入基础库的公有 RouterHub 中管理, 如果您不需要集中管理所有路由地址的话
 * 这也是比较推荐的一种方式
 * <p>
 * 路由地址的命名规则为 组件名 + 页面名, 如订单组件的订单详情页的路由地址可以命名为 "/order/OrderDetailActivity"
 * <p>
 * ARouter 将路由地址中第一个 '/' 后面的字符称为 Group, 比如上面的示例路由地址中 order 就是 Group, 以 order 开头的地址都被分配该 Group 下
 * 切记不同的组件中不能出现名称一样的 Group, 否则会发生该 Group 下的部分路由地址找不到的情况!!!
 * 所以每个组件使用自己的组件名作为 Group 是比较好的选择, 毕竟组件不会重名
 *
 * @author niejiahuan
 * @see <a href="https://github.com/JessYanCoding/ArmsComponent/wiki#3.4">RouterHub wiki 官方文档</a>
 * Created by JessYan on 30/03/2018 18:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface RouterHub {
    /**
     * 组名
     */
    String APP = "/app";//宿主 App 组件
    String MALL = "/Mall";//Mall
    String COMMUNITY = "/Community";//Community

    /**
     * 宿主 App 分组
     */
    String APP_SPLASHACTIVITY = APP + "/SplashActivity";
    String APP_MAINACTIVITY = APP + "/MainActivity";


    /**
     * 商城
     */
    String MALL_fmt = MALL + "/MallFmt";
    /**
     * 社区
     */
    String COMMUNITY_FMT = COMMUNITY + "/CommunityFmt";

    interface Home {
        String Home = "/home/";//Home
        String ADD_FRIEND = Home + "AddFriend";

        String ROOT = Home + "Root";
        String HOME_SELECTED = Home + "selected";
        String HOME_DYNAMIC = Home + "dynamic";
        String HOME_ACTIVITY = Home + "activity";
        String HOME_INFO = Home + "info";
        String SEND_DYNAMIC = Home + "SendDynamic";
    }

    interface Service {
        String SERVICE = "/service/";
        String ROOT = SERVICE + "Root";
        String NAVIGATION = SERVICE + "Navigation";
    }


    interface Mine {
        String MINE = "/mine/";//MINE
        String FANS = MINE + "Fans";
        String FOLLOW = MINE + "Follow";
        String SCORE = MINE + "Score";
        String GROW = MINE + "Grow";
        String ACTIVITY = MINE + "Activity";
        String RELEASE = MINE + "Release";
        String INFO = MINE + "Info";
        String COLLECT = MINE + "Collect";
        String SCORE_BILL = MINE + "ScoreBill";
        String CAR_BILL = MINE + "CarBill";
        String SERVICE_BILL = MINE + "ServiceBill";
        String COMMENT = MINE + "Comment";
        String SUPPORT = MINE + "Support";
        String COMPLAIN = MINE + "Complain";
        String MY_CAR = MINE + "MyCar";
        String USER_CENTER = MINE + "UserCenter";

        /**
         * 用户登录
         */
        String LOGIN_ACT = MINE + "LoginAct";
        /**
         * 用户注册
         */
        String REGISTER_ACT = MINE + "RegisterAct";
        /**
         * 用户忘记密码--验证手机号
         */
        String FORGET_PWD_SMS_ACT = MINE + "ForgetPwdSmsAct";
        /**
         * 用户忘记密码
         */
        String FORGET_PWD_ACT = MINE + "ForgetPwdAct";
        /**
         * 设置中心
         */
        String SETTING_ACT = MINE + "settingAct";
        /**
         * 个人中心
         */
        String MINE_FMT = MINE + "mineFmt";
    }

    interface Photo {
        String PHOTO = "/photo/";
        String PREVIEW = PHOTO + "Preview";
        String CLIP_IMAGE = PHOTO + "ClickImage";
        String IMAGE_SELECTOR = PHOTO + "ImageSelector";
    }

    interface Web {
        String H5 = "/h5/";
        String WEB = H5 + "Web";
    }
}
