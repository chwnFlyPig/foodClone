package chwn.com.boohee.food.util;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ACacheManager {

	protected File mDir;
	private final AtomicLong mFilesTotalLength;
	private final AtomicInteger mFileCount;
	private final long mMaxLength;
	private final int mMaxFileCount;
	private final Map<File, Long> mFileModifyTimeSyncMap;

	public ACacheManager(FileCache paramFileCache, File paramFile, long paramLong, int paramInt) {
		mFileModifyTimeSyncMap = Collections.synchronizedMap(new HashMap());
		mDir = paramFile;
		mMaxLength = paramLong;
		mMaxFileCount = paramInt;
		mFilesTotalLength = new AtomicLong();
		mFileCount = new AtomicInteger();
		startACacheThread();
	}

	public File a(String paramString) {
		File localFile = createFile(paramString);
		Long localLong = Long.valueOf(System.currentTimeMillis());
		long l = localLong.longValue();
		localFile.setLastModified(l);
		mFileModifyTimeSyncMap.put(localFile, localLong);
		return localFile;
	}

	private void startACacheThread() {
		new Thread(new ACacheRunnable()).start();
	}

	public void a(File paramFile) {
		for (int i = mFileCount.get();; i = mFileCount.addAndGet(-1)) {
			int j = i + 1;
			int k = mMaxFileCount;
			if (j <= k)
				break;
			long l2 = deleteLruCacheFile();
			long l3 = -l2;
			mFilesTotalLength.addAndGet(l3);
		}
		mFileCount.addAndGet(1);
		long l5 = getFileLength(paramFile);
		long l1 = mFilesTotalLength.get();
		while (true) {
			long l6 = l1 + l5;
			long l7 = mMaxLength;
			if (l6 <= l7) {
				break;
			}
			long l8 = deleteLruCacheFile();

			long l9 = -l8;
			l1 = mFilesTotalLength.addAndGet(l9);
		}
		long l10 = mFilesTotalLength.addAndGet(l5);
		Long localLong = Long.valueOf(System.currentTimeMillis());
		long l11 = localLong.longValue();
		paramFile.setLastModified(l11);
		mFileModifyTimeSyncMap.put(paramFile, localLong);
	}

	/**
	 * 根据最近最少使用原则删除一个文件
	 **/
	private long deleteLruCacheFile() {
		long l1 = 0L;
		Object localObject1 = null;
		if (mFileModifyTimeSyncMap.isEmpty()) {
			return l1;
		}
		Iterator iterator = mFileModifyTimeSyncMap.entrySet().iterator();
		File lastFile = null;
		Long lastTime = Long.valueOf(System.currentTimeMillis());
		File itemFile;
		Long itemTime;

		long len = 0L;
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = (Entry<String, String>) iterator.next();
			itemFile = (File) ((Map.Entry) entry).getKey();
			itemTime = (Long) ((Map.Entry) entry).getValue();
			if (itemTime <= lastTime) {
				lastFile = itemFile;
				lastTime = itemTime;
			}
		}
		if (lastFile != null) {
			if (lastFile.exists()) {
				len = this.getFileLength(lastFile);
			}
			if (lastFile.delete()) {
				l1 = len;
			}
			mFileModifyTimeSyncMap.remove(localObject1);
		}
		return l1;
	}

	private long getFileLength(File paramFile) {
		return paramFile.length();
	}

	public File createFile(String paramString) {
		StringBuilder localStringBuilder = new StringBuilder();
		int i = paramString.hashCode();
		String str = i + "";
		return new File(mDir, str);
	}

	public boolean c(String paramString) {
		return a(paramString).delete();
	}

	class ACacheRunnable implements Runnable {
		public void run() {
			int i = 0;
			File[] arrayOfFile = mDir.listFiles();
			if (arrayOfFile == null)
				return;
			int fileCount = arrayOfFile.length;
			int k = 0;
			int l = 0;
			while (i < fileCount) {
				File localFile = arrayOfFile[i];
				long l1 = l;
				long l2 = getFileLength(localFile);
				l = (int) (l1 + l2);
				k += 1;
				Long localLong = Long.valueOf(localFile.lastModified());
				mFileModifyTimeSyncMap.put(localFile, localLong);
				i += 1;
			}
			mFilesTotalLength.set(l);
			mFileCount.set(k);
		}
	}

}
