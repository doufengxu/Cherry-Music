package com.nine.music.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.nine.music.lrc.LrcHandle;
import com.nine.music.service.MediaPlayService;

public class WordView extends TextView {
	private List mWordsList = new ArrayList();
	private Paint mLoseFocusPaint;
	private Paint mOnFocusePaint;
	private float mX = 0;
	private float mMiddleY = 0;
	private float mY = 0;
	private static final int DY = 50;
	private int mIndex = 0;

	public WordView(Context context) throws IOException {
		super(context);
		init();
	}

	public WordView(Context context, AttributeSet attrs) throws IOException {
		super(context, attrs);
		init();
	}

	public WordView(Context context, AttributeSet attrs, int defStyle)
			throws IOException {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawColor(Color.TRANSPARENT);

		Paint p = mLoseFocusPaint;
		p.setTextAlign(Paint.Align.CENTER);
		Paint p2 = mOnFocusePaint;
		p2.setTextAlign(Paint.Align.CENTER);

		if (mWordsList.size() == 0) {
			canvas.drawText("暂无歌词", mX, mMiddleY, p2);
		} else {
			// System.out.println((String) mWordsList.get(mIndex));
			
			canvas.drawText((String) mWordsList.get(mIndex), mX, mMiddleY, p2);
			System.out.println((String) mWordsList.get(mIndex)+"===99988===");
		}

		int alphaValue = 25;
		float tempY = mMiddleY;
		for (int i = mIndex - 1; i >= 0; i--) {
			tempY -= DY;
			if (tempY < 0) {
				break;
			}
			p.setColor(Color.argb(255 - alphaValue, 245, 245, 245));
			

			if (mWordsList.size() == 0) {
				canvas.drawText("暂无歌词", mX, mMiddleY, p2);
			} else {

				canvas.drawText((String) mWordsList.get(i), mX, tempY, p);
//				System.out.println((String) mWordsList.get(i)+"===000666===");
			}

			alphaValue += 25;
		}
		alphaValue = 25;
		tempY = mMiddleY;
		if (mWordsList.size() != 0) {

			for (int i = mIndex + 1, len = mWordsList.size(); i < len; i++) {
				System.out.println(len);
				tempY += DY;
				if (tempY > mY) {
					
				
					break;
				}
				p.setColor(Color.argb(255 - alphaValue, 245, 245, 245));
				canvas.drawText((String) mWordsList.get(i), mX, tempY, p);
//				System.out.println((String) mWordsList.get(i)+"===0007777===");
				alphaValue += 25;
			}
			mIndex++;

		}

	}

	@Override
	protected void onSizeChanged(int w, int h, int ow, int oh) {
		super.onSizeChanged(w, h, ow, oh);

		mX = w * 0.5f;
		mY = h;
		mMiddleY = h * 0.3f;
	}

	@SuppressLint("SdCardPath")
	private void init() throws IOException {
		setFocusable(true);

		// LrcHandle lrcHandler = new LrcHandle();
		// lrcHandler.readLRC(new
		// File(Environment.getExternalStorageDirectory(),
		// "123.lrc"));

		LrcHandle lrcHandler = new LrcHandle();

		if (MediaPlayService.mediaPlayer == null
				|| MediaPlayService.list_music.size() == 0) {
			//
		} else {

			String path = MediaPlayService.list_music
					.get(MediaPlayService.CurrentPosition).get("lrc")
					.toString();
			if (path == null || path.equals("")) {

			} else {
				// System.out.println("---------path------"+path);
				lrcHandler.readLRC(path);
				mWordsList = lrcHandler.getWords();
			}

		}

		mLoseFocusPaint = new Paint();
		mLoseFocusPaint.setAntiAlias(true);
		mLoseFocusPaint.setTextSize(22);
		mLoseFocusPaint.setColor(Color.WHITE);
		mLoseFocusPaint.setTypeface(Typeface.SERIF);

		mOnFocusePaint = new Paint();
		mOnFocusePaint.setAntiAlias(true);
		mOnFocusePaint.setColor(Color.YELLOW);
		mOnFocusePaint.setTextSize(30);
		mOnFocusePaint.setTypeface(Typeface.SANS_SERIF);
	}
}
