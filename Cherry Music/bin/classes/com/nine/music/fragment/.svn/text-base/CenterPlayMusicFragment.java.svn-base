package com.nine.music.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.nine.music.R;
import com.nine.music.activity.MainActivity;
import com.nine.music.adapter.MusicControlAdapter;
import com.nine.music.dbhelper.MyContentProvider;
import com.nine.music.lrc.LrcHandle;
import com.nine.music.service.MediaPlayService;
import com.nine.music.utils.listener.MusicControlListener;
import com.nine.music.view.CircularSeekBar;
import com.nine.music.view.CircularSeekBar.OnSeekChangeListener;
import com.nine.music.view.WordView;

@SuppressLint("ResourceAsColor")
public class CenterPlayMusicFragment extends Fragment {
	private ImageView imageView_back;
	private ImageView imageView_share;
	private ImageView imageView_Mode;
	private ImageView imageView_like;
	private View view;
	private SharedPreferences sharedPreferences;
	private ViewPager music_control;
	private List<ImageView> list_music_control = new ArrayList<ImageView>();
	private int statenum = 0;
	private LinearLayout layout_musicimage;
	private LinearLayout layout_bottomcontrol;
	private LinearLayout linearLayout_lrc;
	private LinearLayout centreMusicImage;
	private static CircularSeekBar circularSeekbar;
	private ContentResolver contentResolver;
	public static TextView textView_musicName;
	public static TextView textView_singerName;
	private List<Map<String, Object>> music_list;
	public int localListeningMusic;
	private static List<Long> mTimeList;
	static LrcHandle lrcHandler;

	private static WordView mWordView;
	public static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {

				if (circularSeekbar != null) {

					circularSeekbar.setProgress(0);
					circularSeekbar.setMaxProgress(MediaPlayService.mService
							.getMaxSize());
					new Thread(new Runnable() {

						@Override
						public void run() {

							while (MediaPlayService.mService.isPlay()) {
								Message msg = Message.obtain();
								msg.obj = MediaPlayService.mService
										.getCurrentPosition();
								msg.what = 2;
								handler.sendMessage(msg);
							}

						}
					}).start();

				}
			}
			if (msg.what == 2) {

				// System.out.println("99999999999999");
				circularSeekbar.setProgress((Integer) msg.obj);
				circularSeekbar.invalidate();

			}
			if (msg.what == 3) {

				new Thread(new Runnable() {

					@Override
					public void run() {

						while (MediaPlayService.mService.isPlay()) {
							Message msg = Message.obtain();
							msg.obj = MediaPlayService.mService
									.getCurrentPosition();
							msg.what = 2;
							handler.sendMessage(msg);
						}

					}
				}).start();

				if (MediaPlayService.list_music
						.get(MediaPlayService.CurrentPosition).get("lrc")
						.toString().equals("")) {
					System.out.println("--------------aaaaaaaaaaa");
				} else {

					lrcHandler = new LrcHandle();
					lrcHandler.readLRC(MediaPlayService.list_music
							.get(MediaPlayService.CurrentPosition).get("lrc")
							.toString());
					mTimeList = lrcHandler.getTime();

					new Thread(new Runnable() {
						int i = 0;
						boolean lrc = true;

						@Override
						public void run() {
							while (MediaPlayService.mService.isPlay() && lrc) {
								handler.post(new Runnable() {

									@Override
									public void run() {
										System.out.println("---------" + i);
										mWordView.invalidate();
									}
								});
								try {
									Thread.sleep(mTimeList.get(i + 1)
											- mTimeList.get(i));

								} catch (InterruptedException e) {

								}
								i++;
								if (i == mTimeList.size() - 1) {
									lrc = false;
									break;
								}
							}
						}
					}).start();
				}

			}

		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_center_music_play, null);

		initView();
		init();
		setMusicControl();
		setOnclick();

		setCentreMusic();
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	private void initView() {

		// sharedPreferences = getActivity().getSharedPreferences(
		// "CenterMusicConfig", 0);
		//
		music_list = MediaPlayService.list_music;
		System.out.println(music_list.size());
		if (music_list != null && music_list.size() > 0) {

			System.out.println("======initView=========");

			// localListeningMusic = sharedPreferences.getInt(
			// "LocalListeningMusic", 0);

			textView_musicName = (TextView) view
					.findViewById(R.id.textView_SongTitle_PlayFragment);
			textView_musicName.setText(music_list
					.get(MediaPlayService.CurrentPosition).get("title")
					.toString());
			textView_singerName = (TextView) view
					.findViewById(R.id.textViewtextView_SongArtist_PlayFragment);
			textView_singerName.setText(music_list
					.get(MediaPlayService.CurrentPosition).get("artist")
					.toString());

		}

	}

	private void setCentreMusic() {
		circularSeekbar = new CircularSeekBar(getActivity());
		circularSeekbar.setProgressColor(Color.rgb(230, 49, 116));
		circularSeekbar.setMaxProgress(100);
		circularSeekbar.setProgress(0);
		circularSeekbar.invalidate();
		centreMusicImage.addView(circularSeekbar);
		if (MediaPlayService.mediaPlayer != null
				&& MediaPlayService.mService.isPlay()) {

			circularSeekbar.setProgress(0);
			circularSeekbar.setMaxProgress(MediaPlayService.mService
					.getMaxSize());
			new Thread(new Runnable() {

				@Override
				public void run() {

					while (MediaPlayService.mService.isPlay()) {
						Message msg = Message.obtain();
						msg.obj = MediaPlayService.mService
								.getCurrentPosition();
						msg.what = 2;
						handler.sendMessage(msg);
					}

				}
			}).start();

			if (MediaPlayService.list_music
					.get(MediaPlayService.CurrentPosition).get("lrc")
					.toString().equals("")) {
				System.out.println("--------------aaaaaaaaaaa");
			} else {

				lrcHandler = new LrcHandle();
				lrcHandler.readLRC(MediaPlayService.list_music
						.get(MediaPlayService.CurrentPosition).get("lrc")
						.toString());
				mTimeList = lrcHandler.getTime();
				new Thread(new Runnable() {
					int i = 0;
					boolean lrc = true;

					@Override
					public void run() {
						while (MediaPlayService.mService.isPlay() && lrc) {
							handler.post(new Runnable() {

								@Override
								public void run() {
									System.out.println("---------" + i);
									mWordView.invalidate();
								}
							});
							try {
								Thread.sleep(mTimeList.get(i + 1)
										- mTimeList.get(i));

							} catch (InterruptedException e) {

							}
							i++;
							if (i == mTimeList.size() - 1) {
								lrc = false;
								break;
							}
						}
					}
				}).start();
			}

			// }

		}

		if (MediaPlayService.list_music != null
				&& MediaPlayService.list_music.size() != 0) {

			if (!MediaPlayService.list_music
					.get(MediaPlayService.CurrentPosition).get("lrc")
					.toString().equals("")) {
				System.out.println("-----999-----");
				lrcHandler = new LrcHandle();
				lrcHandler.readLRC(MediaPlayService.list_music
						.get(MediaPlayService.CurrentPosition).get("lrc")
						.toString());
				mTimeList = lrcHandler.getTime();
				// mWordView.invalidate();

			}
		}

		// circularSeekbar.set
		circularSeekbar.setSeekBarChangeListener(new OnSeekChangeListener() {

			@Override
			public void onProgressChange(CircularSeekBar view, int newProgress) {

			}

		});

	}

	private void setMusicControl() {
		for (int i = 0; i < 3; i++) {
			ImageView imageView = new ImageView(getActivity());
			list_music_control.add(imageView);
		}
		if (MediaPlayService.mediaPlayer != null) {
			if (MediaPlayService.mService.isPlay()) {
				list_music_control.get(1).setImageResource(
						R.drawable.theme_pink_controll_pause);
			} else {
				list_music_control.get(1).setImageResource(
						R.drawable.theme_pink_controll_play);
			}
		} else {
			list_music_control.get(1).setImageResource(
					R.drawable.theme_pink_controll_play);

		}

		MusicControlAdapter adapter = new MusicControlAdapter(
				list_music_control);
		music_control.setAdapter(adapter);
		music_control.setCurrentItem(1);
		music_control.setOnPageChangeListener(new MusicControlListener(
				music_control, statenum, getActivity(), list_music_control,
				sharedPreferences));

	}

	private void setOnclick() {
		linearLayout_lrc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String lookinglrc = sharedPreferences.getString("lookinglrc",
						"no");
				if (lookinglrc.equals("no")) {
					layout_musicimage.setVisibility(View.GONE);
					layout_bottomcontrol.setVisibility(View.GONE);

					sharedPreferences.edit().putString("lookinglrc", "yes")
							.commit();

				} else {

					layout_musicimage.setVisibility(View.VISIBLE);
					layout_bottomcontrol.setVisibility(View.VISIBLE);
					sharedPreferences.edit().putString("lookinglrc", "no")
							.commit();
				}

			}
		});

		imageView_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String lookinglrc = sharedPreferences.getString("lookinglrc",
						"no");
				if (lookinglrc.equals("yes")) {
					sharedPreferences.edit().putString("lookinglrc", "no")
							.commit();

				}

				getActivity().finish();

			}
		});

		imageView_like.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!(MediaPlayService.mService != null
						&& MediaPlayService.list_music != null && MediaPlayService.list_music
						.size() != 0)) {
					Toast.makeText(getActivity(), "请先选择音乐", 0).show();

				} else {

					int num = MediaPlayService.CurrentPosition;

					//
					Cursor cursor = contentResolver.query(
							Uri.parse(MyContentProvider.favourite),
							new String[] { "_id", "music_id" }, null, null,
							null);
					if (cursor.moveToNext()) {
						System.out.println("--------------");
						String music_id = cursor.getString(cursor
								.getColumnIndex("music_id"));
						if (MediaPlayService.list_music.get(num).get("id") == null) {
							System.out.println("---------09090909--");

						} else {

							String music_id2 = MediaPlayService.list_music
									.get(num).get("id").toString();
							System.out.println(music_id == null);
							if (music_id != null && music_id.equals(music_id2)) {
								delete(music_id);
							} else {
								System.out.println("------kkkk------");
								insert(num);
							}
						}
						// System.out.println(music_id2);
						// System.out.println(music_id);
					} else {
						// System.out.println("-----222---------");
						insert(num);
					}
					//
					// System.out.println(cursor.moveToNext());

				}

			}

			private void delete(String music_id) {
				contentResolver.delete(Uri.parse(MyContentProvider.favourite),
						"music_id=?", new String[] { music_id });
				Toast.makeText(getActivity(), "从我的收藏移除", 0).show();
				imageView_like.setImageResource(R.drawable.theme_pink_ic_like);

			}

			private void insert(int num) {
				ContentValues values = new ContentValues();
				// System.out.println(num);
				// System.out.println(MediaPlayService.list_music.get(num).get(
				// "title") == null);
				values.put(MediaStore.Audio.Media.TITLE,
						MediaPlayService.list_music.get(num).get("title")
								.toString());

				values.put(MediaStore.Audio.Media.DATA,
						MediaPlayService.list_music.get(num).get("path")
								.toString());

				values.put(MediaStore.Audio.Media.ARTIST,
						MediaPlayService.list_music.get(num).get("artist")
								.toString());
				if (MediaPlayService.list_music.get(num).get("duration") != null) {

					values.put(MediaStore.Audio.Media.DURATION,
							MediaPlayService.list_music.get(num)
									.get("duration").toString());
				}
				if (MediaPlayService.list_music.get(num).get("image") != null) {

					values.put("image", MediaPlayService.list_music.get(num)
							.get("image").toString());
				}
				if (MediaPlayService.list_music.get(num).get("id") != null) {

					values.put("music_id", MediaPlayService.list_music.get(num)
							.get("id").toString());
				}
				contentResolver.insert(Uri.parse(MyContentProvider.favourite),
						values);
				Toast.makeText(getActivity(), "收藏成功", 0).show();
				imageView_like
						.setImageResource(R.drawable.theme_pink_ic_like_p);

			}
		});
		imageView_Mode.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int mode = sharedPreferences.getInt("music_mode", 0);
				mode++;
				if (mode == 3) {
					mode = 0;
				}
				imageView_Mode.setImageLevel(mode);
				sharedPreferences.edit().putInt("music_mode", mode).commit();
				System.out.println(mode);

			}
		});
		imageView_share.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(getActivity());

				View view = View.inflate(getActivity(),
						R.layout.alertdialog_share, null);
				builder.setView(view);

				final AlertDialog dialog = builder.create();
				dialog.show();
				view.findViewById(R.id.textView1).setOnClickListener(
						new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								String name = sharedPreferences.getString(
										"music_name", "");
								if (MediaPlayService.list_music.size() == 0) {
									Toast.makeText(getActivity(), "请先选择音乐", 0)
											.show();
									dialog.dismiss();
								} else {
									showShare();
								}

							}

						});
				view.findViewById(R.id.textView2).setOnClickListener(
						new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								String name = sharedPreferences.getString(
										"music_name", "");
								if (MediaPlayService.list_music.size() == 0) {
									Toast.makeText(getActivity(), "请先选择音乐", 0)
											.show();
									dialog.dismiss();
								} else {
									showShare();
								}

							}
						});

			}
		});

	}

	private void showShare() {
		ShareSDK.initSDK(getActivity());
		OnekeyShare oks = new OnekeyShare();

		// 分享时Notification的图标和文字
		// ((Object) oks).setNotification(R.drawable.ic_launcher,
		// getActivity().getString(R.string.app_name));
		// address是接收人地址，仅在信息和邮件使用
		oks.setAddress("12345678901");
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getActivity().getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		// oks.setText(getActivity().getString(R.string.share_content));
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath(MainActivity.TEST_IMAGE);
		// imageUrl是图片的网络路径，新浪微博、人人网、QQ空间、
		// 微信的两个平台、Linked-In支持此字段
		oks.setImageUrl("http://img.appgo.cn/imgs/sharesdk/content/2013/07/25/1374723172663.jpg");
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// appPath是待分享应用程序的本地路劲，仅在微信中使用
		// oks.setAppPath(MainActivity.TEST_IMAGE);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment(getActivity().getString(R.string.share));
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getActivity().getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");
		// venueName是分享社区名称，仅在Foursquare使用
		oks.setVenueName("Southeast in China");
		// venueDescription是分享社区描述，仅在Foursquare使用
		oks.setVenueDescription("This is a beautiful place!");
		// latitude是维度数据，仅在新浪微博、腾讯微博和Foursquare使用
		oks.setLatitude(23.122619f);
		// longitude是经度数据，仅在新浪微博、腾讯微博和Foursquare使用
		oks.setLongitude(113.372338f);
		// 是否直接分享（true则直接分享）
		oks.setSilent(true);
		// 指定分享平台，和slient一起使用可以直接分享到指定的平台
		// if (platform != null) {
		// oks.setPlatform(platform);
		// }
		// 去除注释可通过OneKeyShareCallback来捕获快捷分享的处理结果
		// oks.setCallback(new OneKeyShareCallback());
		// 通过OneKeyShareCallback来修改不同平台分享的内容
		// oks.setShareContentCustomizeCallback(new
		// ShareContentCustomizeDemo());

		oks.show(getActivity());

	}

	private void init() {
		mWordView = (WordView) view.findViewById(R.id.WordView_lrc);
		imageView_like = (ImageView) view.findViewById(R.id.imageView_like);
		imageView_Mode = (ImageView) view.findViewById(R.id.imageView_Mode);
		centreMusicImage = (LinearLayout) view
				.findViewById(R.id.linearLayout_centreMusicImage);
		layout_musicimage = (LinearLayout) view
				.findViewById(R.id.linearLayout_musicimage);
		linearLayout_lrc = (LinearLayout) view
				.findViewById(R.id.linearLayout_lrc);
		layout_bottomcontrol = (LinearLayout) view
				.findViewById(R.id.linearLayout_bottomcontrol);
		music_control = (ViewPager) view
				.findViewById(R.id.viewPager_musiccontrol);
		imageView_share = (ImageView) view
				.findViewById(R.id.ImageView_Share_PlayFragment);
		imageView_back = (ImageView) view
				.findViewById(R.id.imageView_backtomain);

		sharedPreferences = getActivity().getSharedPreferences(
				"CenterMusicConfig", 0);
		contentResolver = getActivity().getContentResolver();
	}

}
