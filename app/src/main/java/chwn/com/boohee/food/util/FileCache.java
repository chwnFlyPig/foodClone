package chwn.com.boohee.food.util;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONObject;

public class FileCache {
	private final static String cacheFolder = "ACache";
	private static Map<String, FileCache> mFileCacheMap = new HashMap();
	private ACacheManager mACacheManager;
	private File mDir = null;

	private FileCache(File paramFile, long paramLong, int paramInt) {
		if ((!paramFile.exists()) && (!paramFile.mkdirs())) {
			StringBuilder localStringBuilder = new StringBuilder().append("can't make dirs in ");
			String str1 = paramFile.getAbsolutePath();
			throw new RuntimeException(str1);
		}
		mDir = paramFile;
		mACacheManager = new ACacheManager(this, paramFile, paramLong, paramInt);
	}

	public static FileCache getInstance(Context context) {
		return getInstance(context, cacheFolder);
	}

	public static FileCache getInstance(Context context, String paramString) {
		File dir = context.getCacheDir();
		return getInstance(new File(dir, paramString), 50000000L, Integer.MAX_VALUE);
	}

	/**
	 * 每个进程1对应一个FileCache
	 */
	public static FileCache getInstance(File dir, long paramLong, int paramInt) {
		String str1 = getProcessPid();
		FileCache localFileCache = (FileCache) mFileCacheMap.get(str1);
		if (localFileCache == null) {
			localFileCache = new FileCache(dir, paramLong, paramInt);
			mFileCacheMap.put(str1, localFileCache);
		}
		return localFileCache;
	}

	private static String getProcessPid() {
		StringBuilder localStringBuilder = new StringBuilder().append("_");
		localStringBuilder.append(Process.myPid());
		return localStringBuilder.toString();
	}

	public String readFile(String paramString) {
		String jsonStrs = "";
		if (TextUtils.isEmpty(paramString)) {
			return jsonStrs;
		}
		File file = mACacheManager.createFile(paramString);
		if (!file.exists()) {
			return jsonStrs;
		}

		StringBuilder sb = new StringBuilder();
		String line = null;
		BufferedReader br = null;
		try {
			// 将指定输入流包装成BufferedReader
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			jsonStrs = sb.toString();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStrs;
	}

	public void a(String paramString1, String paramString2) {
		File file = mACacheManager.a(paramString1);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(paramString2);
			bw.flush();
			bw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		mACacheManager.a(file);
	}

	public void saveJson(String paramString, JSONObject paramJSONObject) {
		String str = paramJSONObject.toString();
		a(paramString, str);
	}

	public JSONObject getCachedJson(String paramString) {
		String str = readFile(paramString);
		JSONObject localJSONObject = null;
		try {
			if (TextUtils.isEmpty(str)) {
				return localJSONObject;
			}
			localJSONObject = new JSONObject(str);
			return localJSONObject;
		} catch (Exception localException) {
			localException.printStackTrace();
			localJSONObject = null;
		}
		return localJSONObject;
	}

	public boolean c(String paramString) {
		boolean flag = false;
		if (mACacheManager != null) {
			flag = mACacheManager.c(paramString);
		}
		return flag;
	}
}