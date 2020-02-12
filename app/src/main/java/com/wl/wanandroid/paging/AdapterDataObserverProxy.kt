package com.wl.wanandroid.paging

import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView


// 1.新建一个 AdapterDataObserverProxy 类继承 RecyclerView.AdapterDataObserver
internal class AdapterDataObserverProxy(
    var adapterDataObserver: RecyclerView.AdapterDataObserver,
    var headerCount: Int
) : RecyclerView.AdapterDataObserver() {
    override fun onChanged() {
        adapterDataObserver.onChanged()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeChanged(positionStart + headerCount, itemCount)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, @Nullable payload: Any?) {
        adapterDataObserver.onItemRangeChanged(positionStart + headerCount, itemCount, payload)
    }

    // 当第n个数据被获取，更新第n+1个position
    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeInserted(positionStart + headerCount, itemCount)
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeRemoved(positionStart + headerCount, itemCount)
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        super.onItemRangeMoved(fromPosition + headerCount, toPosition + headerCount, itemCount)
    }
}
