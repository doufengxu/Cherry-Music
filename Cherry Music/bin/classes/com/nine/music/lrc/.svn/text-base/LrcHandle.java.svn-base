package com.nine.music.lrc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LrcHandle {
	public List mWords = new ArrayList();
	public List<Long> mTimeList = new ArrayList<Long>();

	// 处理歌词文件
	public void readLRC(String path) {
		File file = new File(path);
	

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fileInputStream, "gbk");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String s = "";

			while ((s = bufferedReader.readLine()) != null) {
				
				addTimeToList(s);
				
				if (!String.valueOf(s.charAt(0)).equals("[")) {
					
//					System.out.println("-------------");
				}else{
					
					if ((s.indexOf("[ar:") != -1) || (s.indexOf("[ti:") != -1)
							|| (s.indexOf("[by:") != -1)) {
						s = s.substring(s.indexOf(":") + 1, s.indexOf("]"));
						// System.out.println(s);
					} else {
						
						// System.out.println(s.equals(""));
						if (!s.equals("")) {
							// System.out.print/ln("---"+s);
							String ss = s.substring(s.indexOf("["),
									s.indexOf("]") + 1);
							s = s.replace(ss, "");
						}
					}
					mWords.add(s);
					
				}
				
			}

			bufferedReader.close();
			inputStreamReader.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			mWords.add("没有歌词文件，赶紧去下载");
		} catch (IOException e) {
			e.printStackTrace();
			mWords.add("没有读取到歌词");
		}
	}

	public List getWords() {
		return mWords;
	}

	public List getTime() {
		return mTimeList;
	}

	// 分离出时间
	private long timeHandler(String string) {
		string = string.replace(".", ":");
		String timeData[] = string.split(":");
		// 分离出分、秒并转换为整型
		long minute = Integer.parseInt(timeData[0]);
		long second = Integer.parseInt(timeData[1]);
		long millisecond = Integer.parseInt(timeData[2]);

		// 计算上一行与下一行的时间转换为毫秒数
		long currentTime = (minute * 60 + second) * 1000 + millisecond * 10;

		return currentTime;
	}

	private void addTimeToList(String string) {
		System.out.println(string);
		if (!String.valueOf(string.charAt(0)).equals("[")) {
			
			
		} else {

			Matcher matcher = Pattern.compile(
					"\\[\\d{1,2}:\\d{1,2}([\\.:]\\d{1,2})?\\]").matcher(string);
			if (matcher.find()) {
				String str = matcher.group();
				//
				mTimeList.add(new LrcHandle().timeHandler(str.substring(1,
						str.length() - 1)));
			}
		}

	}
}
