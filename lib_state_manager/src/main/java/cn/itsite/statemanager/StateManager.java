package cn.itsite.statemanager;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 用于管理StateLayout。
 */
public class StateManager {
    private static final String TAG = StateManager.class.getSimpleName();

    private StateLayout mStateLayout;
    private static Config defConfig = new Config();   //配置

    public static Config getConfig() {
        return defConfig;
    }

    private StateManager(Builder builder) {
        ViewGroup contentParent;
        Context context;
        if (builder.content instanceof Activity) {
            Activity activity = (Activity) builder.content;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (builder.content instanceof Fragment) {
            Fragment fragment = (Fragment) builder.content;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());

        } else if (builder.content instanceof View) {
            View view = (View) builder.content;
            contentParent = (ViewGroup) (view.getParent());
            if (contentParent == null) {
                throw new IllegalArgumentException("the view must already has a parent ");
            }
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("the container's type must be Fragment or Activity or a view ");
        }

        int childCount = contentParent.getChildCount();
        int index = 0;
        View oldContent;
        if (builder.content instanceof View) {
            oldContent = (View) builder.content;
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    index = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0);
        }
        contentParent.removeView(oldContent);
        StateLayout stateLayout = new StateLayout(context);
        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        stateLayout.setContentView(oldContent);
        contentParent.addView(stateLayout, index, lp);
        initStateLayout(stateLayout, builder);
    }

    private StateManager(StateLayout stateLayout, Builder builder) {
        initStateLayout(stateLayout, builder);
    }


    private void initStateLayout(StateLayout stateLayout, Builder builder) {
        stateLayout.setLoadingView(builder.config.loadingLayoutId)
                .setLoadingView(builder.config.loadingView)
                .setLoadingText(builder.config.loadingText);

        stateLayout.setEmptyView(builder.config.emptyLayoutId)
                .setEmptyView(builder.config.emptyView)
                .setEmptyImage(builder.config.emptyImageId)
                .setEmptyText(builder.config.emptyText);

        stateLayout.setErrorView(builder.config.errorLayoutId)
                .setErrorView(builder.config.errorView)
                .setErrorImage(builder.config.errorImageId)
                .setErrorText(builder.config.errorText);

        stateLayout.setNetErrorView(builder.config.netErrorLayoutId)
                .setNetErrorView(builder.config.netErrorView)
                .setNetErrorImage(builder.config.netErrorImageId)
                .setNetErrorText(builder.config.netErrorText);

        stateLayout.setEmptyOnClickListener(builder.emptyListener)
                .setErrorOnClickListener(builder.errorListener)
                .setNetErrorOnClickListener(builder.netErrorListener)
                .setConvertListener(builder.convertListener);
        mStateLayout = stateLayout;
    }

    public void showLoading() {
        mStateLayout.showLoading();
    }

    public void showError() {
        mStateLayout.showError();
    }

    public void showNetError() {
        mStateLayout.showNetError();
    }

    public void showContent() {
        mStateLayout.showContent();
    }

    public void showEmpty() {
        mStateLayout.showEmpty();
    }

    public void setStateLayout(StateLayout stateLayout) {
        this.mStateLayout = stateLayout;
    }

    public StateLayout getStateLayout() {
        return mStateLayout;
    }
    /**
     * 全局配置的Class，对所有使用到的地方有效
     */
    public static class Config {
        private int loadingLayoutId = R.layout.state_loading;
        private View loadingView;
        private CharSequence loadingText;
        private int emptyLayoutId = R.layout.state_empty;
        private View emptyView;
        private int errorLayoutId = R.layout.state_error;
        private View errorView;
        private int netErrorLayoutId = R.layout.state_net_error;
        private View netErrorView;
        private int emptyImageId=R.drawable.define_empty;
        private CharSequence emptyText="暂无数据";
        private int errorImageId=R.drawable.define_error;
        private CharSequence errorText="加载失败，请稍后重试···";
        private int netErrorImageId=R.drawable.define_nonetwork;
        private CharSequence netErrorText="无网络连接，请检查网络···";

        public int getLoadingLayoutId() {
            return loadingLayoutId;
        }

        public void setLoadingLayoutId(int loadingLayoutId) {
            this.loadingLayoutId = loadingLayoutId;
        }

        public View getLoadingView() {
            return loadingView;
        }

        public void setLoadingView(View loadingView) {
            this.loadingView = loadingView;
        }

        public CharSequence getLoadingText() {
            return loadingText;
        }

        public void setLoadingText(CharSequence loadingText) {
            this.loadingText = loadingText;
        }

        public int getEmptyLayoutId() {
            return emptyLayoutId;
        }

        public void setEmptyLayoutId(int emptyLayoutId) {
            this.emptyLayoutId = emptyLayoutId;
        }

        public View getEmptyView() {
            return emptyView;
        }

        public void setEmptyView(View emptyView) {
            this.emptyView = emptyView;
        }

        public int getErrorLayoutId() {
            return errorLayoutId;
        }

        public void setErrorLayoutId(int errorLayoutId) {
            this.errorLayoutId = errorLayoutId;
        }

        public View getErrorView() {
            return errorView;
        }

        public void setErrorView(View errorView) {
            this.errorView = errorView;
        }

        public int getNetErrorLayoutId() {
            return netErrorLayoutId;
        }

        public void setNetErrorLayoutId(int netErrorLayoutId) {
            this.netErrorLayoutId = netErrorLayoutId;
        }

        public View getNetErrorView() {
            return netErrorView;
        }

        public void setNetErrorView(View netErrorView) {
            this.netErrorView = netErrorView;
        }

        public int getEmptyImageId() {
            return emptyImageId;
        }

        public void setEmptyImageId(int emptyImageId) {
            this.emptyImageId = emptyImageId;
        }

        public CharSequence getEmptyText() {
            return emptyText;
        }

        public void setEmptyText(CharSequence emptyText) {
            this.emptyText = emptyText;
        }

        public int getErrorImageId() {
            return errorImageId;
        }

        public void setErrorImageId(int errorImageId) {
            this.errorImageId = errorImageId;
        }

        public CharSequence getErrorText() {
            return errorText;
        }

        public void setErrorText(CharSequence errorText) {
            this.errorText = errorText;
        }

        public int getNetErrorImageId() {
            return netErrorImageId;
        }

        public void setNetErrorImageId(int netErrorImageId) {
            this.netErrorImageId = netErrorImageId;
        }

        public CharSequence getNetErrorText() {
            return netErrorText;
        }

        public void setNetErrorText(CharSequence netErrorText) {
            this.netErrorText = netErrorText;
        }
    }
    public static class Builder {
        private Context context;
        private Object content;
        private Config config;
        private StateListener.OnClickListener netErrorListener;
        private StateListener.OnClickListener errorListener;
        private StateListener.OnClickListener emptyListener;
        private StateListener.ConvertListener convertListener;

        public Builder(@NonNull Context context) {
            this.context = context;
            config=getConfig();
        }

        public Builder setLoadingView(@LayoutRes int loadingLayoutId) {
            config.loadingLayoutId = loadingLayoutId;
            config.loadingView = null;
            return this;
        }

        public Builder setLoadingView(@NonNull View view) {
            config.loadingLayoutId = 0;
            config.loadingView = view;
            return this;
        }

        public Builder setErrorView(@LayoutRes int errorLayoutId) {
            config.errorLayoutId = errorLayoutId;
            config.errorView = null;
            return this;
        }

        public Builder setErrorView(@NonNull View view) {
            config.errorLayoutId = 0;
            config.errorView = view;
            return this;
        }

        public Builder setEmptyView(@LayoutRes int emptyLayoutId) {
            config.emptyLayoutId = emptyLayoutId;
            config.emptyView = null;
            return this;
        }

        public Builder setEmptyView(@NonNull View view) {
            config.emptyLayoutId = 0;
            config.emptyView = view;
            return this;
        }

        public Builder setNetErrorView(@LayoutRes int netErrorLayoutId) {
            config.netErrorLayoutId = netErrorLayoutId;
            config.netErrorView = null;
            return this;
        }

        public Builder setNetErrorView(@NonNull View view) {
            config.netErrorLayoutId = 0;
            config.netErrorView = view;
            return this;
        }

        public Builder setContent(@NonNull Object content) {
            this.content = content;
            return this;
        }

        public Builder setEmptyImage(@DrawableRes int emptyImageId) {
            config.emptyImageId = emptyImageId;
            return this;
        }

        public Builder setEmptyText(@Nullable CharSequence emptyText) {
            config.emptyText = emptyText;
            return this;
        }

        public Builder setEmptyText(@StringRes int emptyTextId) {
            config.emptyText = context.getText(emptyTextId);
            return this;
        }

        public Builder setErrorImage(@DrawableRes int errorImageId) {
            config.errorImageId = errorImageId;
            return this;
        }

        public Builder setErrorText(@Nullable CharSequence errorText) {
            config.errorText = errorText;
            return this;
        }

        public Builder setErrorText(@StringRes int errorTextId) {
            config.errorText = context.getText(errorTextId);
            return this;
        }


        public Builder setNetErrorImage(@DrawableRes int netErrorImageId) {
            config.netErrorImageId = netErrorImageId;
            return this;
        }

        public Builder setNetErrorText(@Nullable CharSequence netErrorText) {
            config.netErrorText = netErrorText;
            return this;
        }

        public Builder setNetErrorText(@StringRes int netErrorTextId) {
            config.netErrorText = context.getText(netErrorTextId);
            return this;
        }

        public Builder setLoadingText(@Nullable CharSequence loadingText) {
            config.loadingText = loadingText;
            return this;
        }

        public Builder setLoadingText(@StringRes int loadingText) {
            config.loadingText = context.getText(loadingText);
            return this;
        }

        public Builder setNetErrorOnClickListener(StateListener.OnClickListener listener) {
            this.netErrorListener = listener;
            return this;
        }

        public Builder setErrorOnClickListener(StateListener.OnClickListener listener) {
            this.errorListener = listener;
            return this;
        }

        public Builder setEmptyOnClickListener(StateListener.OnClickListener listener) {
            this.emptyListener = listener;
            return this;
        }

        public Builder setConvertListener(StateListener.ConvertListener listener) {
            this.convertListener = listener;
            return this;
        }

        public StateManager build() {
            return new StateManager(this);
        }

        public StateManager build(StateLayout mStateLayout) {
            return new StateManager(mStateLayout, this);
        }
    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }
}
