package com.shtokal.tools.level;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

@SuppressLint("DrawAllocation")
public class LevelView extends View {

	private int width, height;
	private int x, y;
	private int[] center = new int[2];

	public LevelView(Context context) {
		super(context);
	}

	public LevelView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LevelView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setParams(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setXY(float[] orientation) {
		if (width == 0 && height == 0) {
			width = getWidth();
			height = getHeight();
			Log.d("setXY", "width: " + width);
			Log.d("setXY", "height: " + height);
			center[0] = width / 2;
			center[1] = height / 2;
		}
		
		float pitch = 100 * orientation[1];
		float roll = 100 * orientation[2];
		x = (int) (center[0] + pitch + .5);
		y = (int) (center[1] + roll + .5);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint background = new Paint();
		background.setColor(0xffd4d614);
		canvas.drawRect(0, 0, width, height, background);

		Paint dot = new Paint();
		dot.setColor(0x80ffffff);
		canvas.drawCircle(x + 20, y + 20, 40, dot);

		Paint circle = new Paint();
		circle.setColor(Color.MAGENTA);
		circle.setStyle(Paint.Style.STROKE);
		circle.setStrokeWidth(2);
		canvas.drawCircle(center[0] + 20, center[1] + 20, 40, circle);
	}
}