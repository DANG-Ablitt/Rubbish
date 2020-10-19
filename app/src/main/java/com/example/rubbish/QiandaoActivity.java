package com.example.rubbish;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.rubbish.adapter.DateAdapter;
import com.example.rubbish.view.SpecialCalendar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QiandaoActivity extends Activity implements GestureDetector.OnGestureListener {
	private ViewFlipper flipper1 = null;
	// private ViewFlipper flipper2 = null;
	private static String TAG = "ZzL";
	private GridView gridView = null;
	private GestureDetector gestureDetector = null;
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private int week_c = 0;
	private int week_num = 0;
	private String currentDate = "";
	private static int jumpWeek = 0;
	private static int jumpMonth = 0;
	private static int jumpYear = 0;
	private DateAdapter dateAdapter;
	private int daysOfMonth = 0; // 某月的天数
	private int dayOfWeek = 0; // 具体某一天是星期几
	private int weeksOfMonth = 0;
	private SpecialCalendar sc = null;
	private boolean isLeapyear = false; // 是否为闰年
	private int selectPostion = 0;
	private String dayNumbers[] = new String[7];
	private int currentYear;
	private int currentMonth;
	private int currentWeek;
	private int currentDay;
	private int currentNum;
	private boolean isStart;// 是否是交接的月初
	private ImageView iv;// 点击猫的控件
	private ImageView back;// 点击返回时的按钮
	private RelativeLayout layout;// 点击猫时候的背景布局
	private PopupWindow show_window;// 点击领取之后，显示的获得铂隆币
	private ImageView show_iv;// 显示动画的容器iv
	private Animation animation;// 动画的anim
	private boolean isClick = true;
	private String str;// 判断传过来的数字 来显示所给的铂隆币
	private int state;
	private int  s;
	private SharedPreferences shaPreferences;//保存是否已经签到数据
	final Handler handler=new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case 1:
					//ToastUtil.show(QianDaoActivity.this, "您的网络未连接，请连接网络哦");
					break;
				case 2:
					//ToastUtil.show(QianDaoActivity.this, "服务器未响应");
					break;
			}
		}
	};


	public QiandaoActivity() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		currentDate = sdf.format(date);
		year_c = Integer.parseInt(currentDate.split("-")[0]);
		month_c = Integer.parseInt(currentDate.split("-")[1]);
		day_c = Integer.parseInt(currentDate.split("-")[2]);
		currentYear = year_c;
		currentMonth = month_c;
		currentDay = day_c;
		sc = new SpecialCalendar();
		getCalendar(year_c, month_c);
		week_num = getWeeksOfMonth();
		currentNum = week_num;
		if (dayOfWeek == 7) {
			week_c = day_c / 7 + 1;
		} else {
			if (day_c <= (7 - dayOfWeek)) {
				week_c = 1;
			} else {
				if ((day_c - (7 - dayOfWeek)) % 7 == 0) {
					week_c = (day_c - (7 - dayOfWeek)) / 7 + 1;
				} else {
					week_c = (day_c - (7 - dayOfWeek)) / 7 + 2;
				}
			}
		}
		currentWeek = week_c;
		getCurrent();

	}

	/**
	 * 判断某年某月所有的星期数
	 * 
	 * @param year
	 * @param month
	 */
	public int getWeeksOfMonth(int year, int month) {
		// 先判断某月的第一天为星期几
		int preMonthRelax = 0;
		int dayFirst = getWhichDayOfWeek(year, month);
		int days = sc.getDaysOfMonth(sc.isLeapYear(year), month);
		if (dayFirst != 7) {
			preMonthRelax = dayFirst;
		}
		if ((days + preMonthRelax) % 7 == 0) {
			weeksOfMonth = (days + preMonthRelax) / 7;
		} else {
			weeksOfMonth = (days + preMonthRelax) / 7 + 1;
		}
		return weeksOfMonth;

	}

	/**
	 * 判断某年某月的第一天为星期几
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public int getWhichDayOfWeek(int year, int month) {
		return sc.getWeekdayOfMonth(year, month);

	}

	/**
	 * 
	 * @param year
	 * @param month
	 */
	public int getLastDayOfWeek(int year, int month) {
		return sc.getWeekDayOfLastMonth(year, month,
				sc.getDaysOfMonth(isLeapyear, month));
	}

	public void getCalendar(int year, int month) {
		isLeapyear = sc.isLeapYear(year); // 是否为闰年
		daysOfMonth = sc.getDaysOfMonth(isLeapyear, month); // 某月的总天数
		dayOfWeek = sc.getWeekdayOfMonth(year, month); // 某月第一天为星期几
	}

	public int getWeeksOfMonth() {
		// getCalendar(year, month);
		int preMonthRelax = 0;
		if (dayOfWeek != 7) {
			preMonthRelax = dayOfWeek;
		}
		if ((daysOfMonth + preMonthRelax) % 7 == 0) {
			weeksOfMonth = (daysOfMonth + preMonthRelax) / 7;
		} else {
			weeksOfMonth = (daysOfMonth + preMonthRelax) / 7 + 1;
		}
		return weeksOfMonth;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qiandao);
		qiandaoRequest();
		SystemClock.sleep(50);
	    //EventBus.getDefault().register(this);
		System.out.println(1);
		gestureDetector = new GestureDetector(this);
		System.out.println(2);
		flipper1 = (ViewFlipper) findViewById(R.id.flipper1);
		System.out.println(3);
		dateAdapter = new DateAdapter(this, getResources(), currentYear,
				currentMonth, currentWeek, currentNum, selectPostion,
				currentWeek == 1 ? true : false);
		System.out.println(4);
		addGridView();
		System.out.println(5);
		dayNumbers = dateAdapter.getDayNumbers();
		gridView.setAdapter(dateAdapter);
		selectPostion = dateAdapter.getTodayPosition();
		gridView.setSelection(selectPostion);
		flipper1.addView(gridView, 0);
		iv = (ImageView) findViewById(R.id.qiandao_mao);
		layout = (RelativeLayout) findViewById(R.id.qiandao_mao_bg);
		back = (ImageView) findViewById(R.id.popuwindow_qiandao_back);
		System.out.println("ppppppp"+state);
		if (state==1)
		{

			iv.setImageResource(R.drawable.qiandao_mao_pre);
			layout.setBackgroundResource(R.drawable.qiandao_bg_after);
			iv.setClickable(false);

		}
		else
			{
				iv.setOnClickListener(qiandao_listener);
				back.setOnClickListener(back_listener);
			}


	}

	// 点击签到的小猫时候的监听
	private View.OnClickListener qiandao_listener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			qiandao();
			huikui();
		}
	};

	//通讯事件
	//public void onEvent(String event){
		//str=event;
	//}
	private void qiandao() {


			iv.setImageResource(R.drawable.qiandao_mao_pre);

			if (state==0) {
				layout.setBackgroundResource(R.drawable.qiandao_bg_after);
				setWindow();
				show_iv.setBackgroundResource(R.drawable.qiandao_x1);
				show_window.showAtLocation(
						findViewById(R.id.qiandao_layout_continer), Gravity.CENTER,
						0, 0);
				animation = AnimationUtils.loadAnimation(QiandaoActivity.this,
						R.anim.qiandao_bolongbi_anim);
				show_iv.startAnimation(animation);
				animation.setAnimationListener(anim_listener);
				isClick = false;
			} /*else if (str == "4"&&state==0) {
				layout.setBackgroundResource(R.drawable.qiandao_bg_after_x4);
				setWindow();
				show_iv.setBackgroundResource(R.drawable.qiandao_x4);
				show_window.showAtLocation(
						findViewById(R.id.qiandao_layout_continer), Gravity.CENTER,
						0, 0);
				animation = AnimationUtils.loadAnimation(QianDaoActivity.this,
						R.anim.qiandao_bolongbi_anim);
				show_iv.startAnimation(animation);
				animation.setAnimationListener(anim_listener);
				isClick = false;
			}*/
			else{
				//ToastUtil.show(QianDaoActivity.this, "您今天已经签到，请明天再来哦");
			}
			
			
		}


	// 动画效果的监听
	private Animation.AnimationListener anim_listener = new Animation.AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			show_window.dismiss();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}
	};
	// 点击返回时的监听
	private View.OnClickListener back_listener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			finish();
		}
	};

	// popupwindw显示
	private void setWindow() {
		ViewGroup group1 = (ViewGroup) LayoutInflater.from(this).inflate(
				R.layout.popupwindow_qiandao_jiangli_bolongbi, null);

		show_window = new PopupWindow(group1,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT, true);
		Display display = getWindowManager().getDefaultDisplay();
		show_window.setHeight(display.getHeight() * 1);
		show_iv = (ImageView) group1.findViewById(R.id.popupwindow_qiandao_iv);
		show_window.setBackgroundDrawable(new ColorDrawable());
		show_window.setFocusable(true);
		show_window.setOutsideTouchable(false);

	}

	private void addGridView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		gridView = new GridView(this);
		gridView.setNumColumns(7);
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		gridView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return QiandaoActivity.this.gestureDetector.onTouchEvent(event);
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i(TAG, "day:" + dayNumbers[position]);
				selectPostion = position;
				dateAdapter.setSeclection(position);
				dateAdapter.notifyDataSetChanged();
			}
		});
		gridView.setLayoutParams(params);
	}

	@Override
	protected void onPause() {
		super.onPause();
		jumpWeek = 0;
	}

	@Override
	public boolean onDown(MotionEvent e) {

		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	//判断是否已经签到过
	private void qiandaoRequest()
	{

	}

	//签到成功后回馈给服务器的数据
	private void huikui()
	{

	}


	/**
	 * 重新计算当前的年月
	 */
	public void getCurrent() {
		if (currentWeek > currentNum) {
			if (currentMonth + 1 <= 12) {
				currentMonth++;
			} else {
				currentMonth = 1;
				currentYear++;
			}
			currentWeek = 1;
			currentNum = getWeeksOfMonth(currentYear, currentMonth);
		} else if (currentWeek == currentNum) {
			if (getLastDayOfWeek(currentYear, currentMonth) == 6) {
			} else {
				if (currentMonth + 1 <= 12) {
					currentMonth++;
				} else {
					currentMonth = 1;
					currentYear++;
				}
				currentWeek = 1;
				currentNum = getWeeksOfMonth(currentYear, currentMonth);
			}

		} else if (currentWeek < 1) {
			if (currentMonth - 1 >= 1) {
				currentMonth--;
			} else {
				currentMonth = 12;
				currentYear--;
			}
			currentNum = getWeeksOfMonth(currentYear, currentMonth);
			currentWeek = currentNum - 1;
		}
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int gvFlag = 0;
		if (e1.getX() - e2.getX() > 80) {
			// 向左滑
			addGridView();
			currentWeek++;
			getCurrent();
			dateAdapter = new DateAdapter(this, getResources(), currentYear,
					currentMonth, currentWeek, currentNum, selectPostion,
					currentWeek == 1 ? true : false);
			dayNumbers = dateAdapter.getDayNumbers();
			gridView.setAdapter(dateAdapter);

			gvFlag++;
			flipper1.addView(gridView, gvFlag);
			dateAdapter.setSeclection(selectPostion);
			this.flipper1.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_in));
			this.flipper1.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_out));
			this.flipper1.showNext();
			flipper1.removeViewAt(0);
			return true;

		} else if (e1.getX() - e2.getX() < -80) {
			addGridView();
			currentWeek--;
			getCurrent();
			dateAdapter = new DateAdapter(this, getResources(), currentYear,
					currentMonth, currentWeek, currentNum, selectPostion,
					currentWeek == 1 ? true : false);
			dayNumbers = dateAdapter.getDayNumbers();
			gridView.setAdapter(dateAdapter);

			gvFlag++;
			flipper1.addView(gridView, gvFlag);
			dateAdapter.setSeclection(selectPostion);
			this.flipper1.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_in));
			this.flipper1.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_out));
			this.flipper1.showPrevious();
			flipper1.removeViewAt(0);
			return true;

		}
		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		finish();

		System.gc();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return this.gestureDetector.onTouchEvent(event);
	}

}
