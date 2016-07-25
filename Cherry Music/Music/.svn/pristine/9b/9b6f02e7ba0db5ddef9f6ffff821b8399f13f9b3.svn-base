package com.nine.music.config;

import java.io.File;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nine.music.R;
import com.nine.music.constants.Constants;

public class RankDownLoad {
	public static HttpUtils httpUtils = new HttpUtils();

	public static void download(final String url, final String path,
			final ImageView imageView,final Context context,final NotificationManager manager,final String music_name) {

		httpUtils.download(Constants.download[0] + url, path,
				new RequestCallBack<File>() {

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						// TODO Auto-generated method stub
						super.onLoading(total, current, isUploading);

						NotificationCompat.Builder builder = new NotificationCompat.Builder(
								context);
						builder.setSmallIcon(R.drawable.ic_logo_text);
						builder.setContentTitle(music_name);
						int currents = (int) (current / (float) total * 100);

						builder.setProgress(100, currents, false);
						builder.setContentText(currents + "%");
						manager.notify(1, builder.build());

					}

					@Override
					public void onSuccess(ResponseInfo<File> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "下载成功！", Toast.LENGTH_SHORT)
								.show();

						imageView.setVisibility(View.GONE);

					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						httpUtils.download(Constants.download[1] + url, path,
								new RequestCallBack<File>() {

									@Override
									public void onLoading(long total,
											long current, boolean isUploading) {
										// TODO Auto-generated method stub
										super.onLoading(total, current,
												isUploading);

										NotificationCompat.Builder builder = new NotificationCompat.Builder(
												context);
										builder.setSmallIcon(R.drawable.ic_logo_text);

										builder.setContentTitle(music_name);
										int currents = (int) (current
												/ (float) total * 100);

										builder.setProgress(100, currents,
												false);
										builder.setContentText(currents + "%");
										manager.notify(1, builder.build());

									}

									@Override
									public void onSuccess(
											ResponseInfo<File> arg0) {
										// TODO Auto-generated method stub
										Toast.makeText(context, "下载成功！",
												Toast.LENGTH_SHORT).show();

										imageView.setVisibility(View.GONE);

									}

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										// TODO Auto-generated method stub

										httpUtils.download(
												Constants.download[2] + url,
												path,
												new RequestCallBack<File>() {

													@Override
													public void onLoading(
															long total,
															long current,
															boolean isUploading) {
														// TODO Auto-generated
														// method stub
														super.onLoading(total,
																current,
																isUploading);

														NotificationCompat.Builder builder = new NotificationCompat.Builder(
																context);
														builder.setSmallIcon(R.drawable.ic_logo_text);

														builder.setContentTitle(music_name);
														int currents = (int) (current
																/ (float) total * 100);

														builder.setProgress(
																100, currents,
																false);
														builder.setContentText(currents
																+ "%");
														manager.notify(1,
																builder.build());

													}

													@Override
													public void onSuccess(
															ResponseInfo<File> arg0) {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																context,
																"下载成功！",
																Toast.LENGTH_SHORT)
																.show();

														imageView
																.setVisibility(View.GONE);

													}

													@Override
													public void onFailure(
															HttpException arg0,
															String arg1) {
														// TODO Auto-generated
														// method stub

													}
												});
									}
								});
					}
				});
	}
}
