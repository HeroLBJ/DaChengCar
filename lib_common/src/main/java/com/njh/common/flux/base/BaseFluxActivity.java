package com.njh.common.flux.base;

import com.njh.base.ui.act.BaseAct;
import com.njh.base.ui.view.BaseView;
import com.njh.common.flux.actions.ActionsCreator;
import com.njh.common.flux.annotation.BindEvent;
import com.njh.common.flux.dispatcher.Dispatcher;
import com.njh.common.flux.stores.Store;
import com.njh.common.widget.CustomToast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author niejiahuan
 */
public abstract class BaseFluxActivity<STORE extends Store, CREATER extends ActionsCreator>
        extends BaseAct {
    protected Dispatcher dispatcher;
    private STORE store;
    private CREATER actionCreater;

    /**
     * 是否启用flux模式
     *
     * @return
     */
    protected boolean flux() {
        return false;
    }


    @Override
    public void initBus() {
        if (flux()) {
            dispatcher = Dispatcher.get(new EventBus());
        }
        //进行订阅注册
        if (flux() && store() != null) {
            dispatcher.start();
            dispatcher.register(this);
            dispatcher.register(store());
        }
    }

    public void toast(Object obj) {
        CustomToast.getInstance().showToast(obj.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CustomToast.getInstance().cancelToast();
        //解绑
        if (flux() && store() != null) {
            dispatcher.stop();
            dispatcher.unregister(this);
            dispatcher.unregister(store());
        }
    }

    /**
     * store 的订阅者方法,用于store.emitStoreChange的响应
     *
     * @param event
     */
    @BindEvent
    public void onEvent(Store.StoreChangeEvent event) {
        updateView(event);
    }


    /**
     * UI的更新
     *
     * @param event
     */
    protected void updateView(Store.StoreChangeEvent event) {
    }

    /**
     * 实例化store
     *
     * @return
     */
    protected final STORE store() {
        if (store == null) {
            store = (STORE) newInstance(getType(Store.class.getName()),
                    new Class<?>[]{Dispatcher.class}, dispatcher);
        }
        return store;
    }

    /**
     * 实例化actionsCreator
     *
     * @return
     */
    protected final CREATER actionsCreator() {
        if (actionCreater == null) {
            actionCreater = (CREATER) newInstance(getType(ActionsCreator.class.getName()),
                    new Class<?>[]{Dispatcher.class, BaseView.class},
                    dispatcher, this);
        }
        return actionCreater;
    }

    /**
     * 在实现类中向上寻找,获取范型的实际类型,并将类型返回
     * <p>
     * 获取子类的泛型参数
     *
     * @return
     */
    protected Class<?> getType(String name) {
        Type superclass = getClass().getGenericSuperclass();
        while (superclass != null && !(superclass instanceof ParameterizedType)) {
            superclass = ((Class) superclass).getGenericSuperclass();
        }
        if (superclass == null) {
            throw new RuntimeException("Missing type parameter.");
        }
        Type[] types = ((ParameterizedType) superclass).getActualTypeArguments();
        for (int i = 0; i < types.length; i++) {
//            Class<?> cls = (Class<?>) types[i];
            Class<?> cls = null;
            if (types[i] instanceof Class) {
                cls = (Class<?>) types[i];
            } else {
                Type t = types[i];
                if (t instanceof ParameterizedType) {
                    cls = (Class<?>) ((ParameterizedType) t).getRawType();
                }
            }
            if (cls == null) {
                throw new RuntimeException("Missing type parameter.");
            }
            if (cls.getName().equals(name)) {
                return cls;
            }
            Class c = cls;
            while ((c = c.getSuperclass()) != null && !c.getName().equals("java.lang.Object")) {
                if (c.getName().equals(name)) {
                    return cls;
                }
            }
        }
        return null;
    }

    /**
     * 实例化某个类,通过反射的构造方法生成实例
     *
     * @param cls
     * @param params
     * @param args
     * @param <T>
     * @return
     */
    protected <T> T newInstance(Class<T> cls, Class<?>[] params, Object... args) {
        try {
            Constructor<?> constructor = cls.getDeclaredConstructor(params);
            constructor.setAccessible(true);
            return (T) constructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void onError(int code, String msg, String tag) {
        if (code == 114) {
            //TODO
        } else {
            showToast(msg);
        }
    }
}
