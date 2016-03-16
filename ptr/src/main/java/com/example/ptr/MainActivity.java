package com.example.ptr;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * 类描述: 下拉刷新测试
 * ViewPager滑动冲突: disableWhenHorizontalMove()
 * 长按LongPressed, setInterceptEventWhileWorking()
 * <p/>
 * 创建人: chenyang
 * QQ: 454725164
 * 邮箱: ink1991@163.com
 * 创建时间: 2012-12-18 上午11:46:14
 * 修改人:
 * 修改时间:
 * 修改备注:
 * 版本:
 */
public class MainActivity extends Activity {
	private Handler mHandler = new Handler();
	private PtrFrameLayout mPtrFrameLayout;
	private ListView mListView;
	List<String> strs = new ArrayList<String>();
	int temp;

	private ArrayAdapter<String> adapter;
	private LoadMoreListViewContainer loadMoreListViewContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
	}

	public void initViews() {
		setContentView(R.layout.activity_main);
		strs.add("first");
		strs.add("second");
		strs.add("third");
		strs.add("first");
		strs.add("second");
		strs.add("third");
		strs.add("first");
		strs.add("second");
		strs.add("third");
		setPullToRefresh();
		setLoadMore();
	}

	private void setLoadMore() {
		// list view
		mListView = (ListView) findViewById(R.id.load_more_small_image_list_view);
		View footView = getLayoutInflater().inflate(R.layout.footer, null);

		mListView.addFooterView(footView);

		// load more container
		loadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
		loadMoreListViewContainer.useDefaultHeader();
//		loadMoreListViewContainer.setAutoLoadMore(false);//自动加载更多
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
		mListView.setAdapter(adapter);

		loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
			@Override
			public void onLoadMore(LoadMoreContainer loadMoreContainer) {
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						temp++;
						if (temp > 3) {
							loadMoreListViewContainer.loadMoreFinish(true, false);
						} else {
							for (int i = 0; i < 10; i++) {
								strs.add("item" + i);
							}
							// load more
							loadMoreListViewContainer.loadMoreFinish(false, true);
							adapter.notifyDataSetChanged();
						}
					}
				}, 2000);
			}
		});

	}

	/**
	 * 设置刷新属性
	 */
	private void setPullToRefresh() {
		mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.material_style_ptr_frame);
		// 设置头部属性
		final MaterialHeader header = new MaterialHeader(this);
		int[] colors = getResources().getIntArray(R.array.google_colors);
		header.setColorSchemeColors(colors);
		header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
		header.setPadding(0, 15, 0, 20);
		header.setPtrFrameLayout(mPtrFrameLayout);

		// 设置下拉刷新属性
		mPtrFrameLayout.setPinContent(true);//刷新保持头部不动
		mPtrFrameLayout.setLoadingMinTime(1000);//播放刷新动画最小时间
		mPtrFrameLayout.setDurationToClose(300);//回弹到刷新高度所用时间,越大回弹越慢
		mPtrFrameLayout.setDurationToCloseHeader(1500);//动画移动出来的时间，时间越短出来越快
		mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);//移动达到头部高度1.2倍时可触发刷新操作
		mPtrFrameLayout.setResistance(1.7f);//越大，感觉下拉时越吃力。
//		mPtrFrameLayout.setPullToRefresh(false);//默认是false，释放刷新，true下拉就刷新了
		mPtrFrameLayout.setHeaderView(header);
		mPtrFrameLayout.addPtrUIHandler(header);

		mPtrFrameLayout.setPtrHandler(new PtrHandler() {
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
				// TODO: 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
//				return true;//如果只是下拉刷新
				return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);//下拉和加载更多
			}

			@Override
			public void onRefreshBegin(final PtrFrameLayout frame) {
				// TODO:刷新开始出发，需要加载数据时触发
				frame.postDelayed(new Runnable() {
					@Override
					public void run() {
						frame.refreshComplete();
					}
				}, 1000);
			}
		});
	}

	public void initData() {
		// TODO: 自动刷新
		mPtrFrameLayout.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPtrFrameLayout.autoRefresh(true);//是否可以自动刷新
			}
		}, 2000);
	}
}
