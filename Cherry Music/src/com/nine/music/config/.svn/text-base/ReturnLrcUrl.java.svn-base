package com.nine.music.config;

public class ReturnLrcUrl {
	
	public static String retrnLrcUrl(String music_id, String artist_id) {
		String url = null;
		// 215254569
		// 514368114 <<----有毛病
		switch (music_id.length()) {
		case 7:
			String cc01 = music_id.substring(0, 5);
			String cc02 = null;

			if (artist_id.length() == 3) {
				cc02 = artist_id.replace(artist_id.substring(1, 3), "");
			} else if (artist_id.length() == 4) {
				cc02 = artist_id.replace(artist_id.substring(1, 4), "");
			} else if (artist_id.length() == 5) {
				cc02 = artist_id.replace(artist_id.substring(1, 5), "");
			} else if (artist_id.length() == 6) {
				cc02 = artist_id.replace(artist_id.substring(1, 6), "");
			}

			char[] chaa = new char[3];
			cc01.getChars(2, 5, chaa, 0);
			char chaa1 = chaa[0];
			char chaa2 = chaa[1];
			char chaa3 = chaa[2];
			url = ".chrrs.com/" + chaa1 + "/" + chaa2 + "/" + chaa3 + "/" + 30
					+ cc01 + ".lrc";
			break;

		case 8:
			String demo01 = music_id.substring(0, 6);
			String demo02 = null;
			if (artist_id.length() == 3) {
				demo02 = artist_id.replace(artist_id.substring(1, 3), "");
			} else if (artist_id.length() == 4) {
				demo02 = artist_id.replace(artist_id.substring(1, 4), "");
			} else if (artist_id.length() == 5) {
				demo02 = artist_id.replace(artist_id.substring(1, 5), "");
			} else if (artist_id.length() == 6) {
				demo02 = artist_id.replace(artist_id.substring(1, 6), "");
			}

			char[] cha = new char[3];
			demo01.getChars(3, 6, cha, 0);
			char cha1 = cha[0];
			char cha2 = cha[1];
			char cha3 = cha[2];
			url = ".chrrs.com/" + cha1 + "/" + cha2 + "/" + cha3 + "/" + 30
					+ demo01 + ".lrc";

			break;

		case 9:
			// 514368165
			String text01 = music_id.substring(0, 7);
			String text02 = null;

			if (artist_id.length() == 3) {
				text02 = artist_id.replace(artist_id.substring(1, 3), "");
			} else if (artist_id.length() == 4) {
				text02 = artist_id.replace(artist_id.substring(1, 4), "");
			} else if (artist_id.length() == 5) {
				text02 = artist_id.replace(artist_id.substring(1, 5), "");
			} else if (artist_id.length() == 6) {
				text02 = artist_id.replace(artist_id.substring(1, 6), "");
			}
			char[] ch = new char[3];

			text01.getChars(4, 7, ch, 0);
			char ch1 = ch[0];
			char ch2 = ch[1];
			char ch3 = ch[2];

			if (music_id.replace(music_id.substring(2, 9), "").equals("10")) {
				url = ".chrrs.com/" + ch1 + "/" + ch2 + "/" + ch3 + "/"
						+ music_id + ".lrc";

			} else {
				url = ".chrrs.com/" + ch1 + "/" + ch2 + "/" + ch3 + "/" + 3
						+ text01 + ".lrc";
			}
			break;
		case 10:
			// 1234567890
			String a1 = music_id.substring(0, 8);
			String a2 = null;
			if (artist_id.length() == 3) {
				a2 = artist_id.replace(artist_id.substring(1, 3), "");
			} else if (artist_id.length() == 4) {
				a2 = artist_id.replace(artist_id.substring(1, 4), "");
			} else if (artist_id.length() == 5) {
				a2 = artist_id.replace(artist_id.substring(1, 5), "");
			} else if (artist_id.length() == 6) {
				a2 = artist_id.replace(artist_id.substring(1, 6), "");
			}

			char[] aa = new char[3];
			a1.getChars(5, 8, aa, 0);
			char aa1 = aa[0];
			char aa2 = aa[1];
			char aa3 = aa[2];

			if (music_id.replace(music_id.substring(2, 10), "").equals("10")) {
				url = ".chrrs.com/" + aa1 + "/" + aa2 + "/" + aa3 + "/"
						+ music_id + ".lrc";

			} else {
				url = ".chrrs.com/" + aa1 + "/" + aa2 + "/" + aa3 + "/" + 3
						+ a1 + ".lrc";
			}

			break;
		case 11:
			String b1 = music_id.substring(0, 9);
			String b2 = null;
			if (artist_id.length() == 3) {
				b2 = artist_id.replace(artist_id.substring(1, 3), "");
			} else if (artist_id.length() == 4) {
				b2 = artist_id.replace(artist_id.substring(1, 4), "");
			} else if (artist_id.length() == 5) {
				b2 = artist_id.replace(artist_id.substring(1, 5), "");
			} else if (artist_id.length() == 6) {
				b2 = artist_id.replace(artist_id.substring(1, 6), "");
			}
			System.out.println(b1);
			char[] bb = new char[3];
			b1.getChars(6, 9, bb, 0);
			char bb1 = bb[0];
			char bb2 = bb[1];
			char bb3 = bb[2];

			if (music_id.replace(music_id.substring(2, 11), "").equals("10")) {
				url = ".chrrs.com/" + bb1 + "/" + bb2 + "/" + bb3 + "/" + b1
						+ ".lrc";

			} else {
				url = ".chrrs.com/" + bb1 + "/" + bb2 + "/" + bb3 + "/" + 3
						+ b1 + ".lrc";
			}
			break;

		default:
			break;
		}
		return url;
	}

}
