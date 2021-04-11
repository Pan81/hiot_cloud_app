package com.huatec.hiot_cloud.Base;

/*MVP架构presenter层基类*/
public class BasePresenter<V extends BaseView> {
    private V view;

    public BasePresenter() {

    }

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    public void destory() {
        if (viewAttached()) {
            view = null;
        }
    }

    public boolean viewAttached() {
        return view != null;
    }
}
