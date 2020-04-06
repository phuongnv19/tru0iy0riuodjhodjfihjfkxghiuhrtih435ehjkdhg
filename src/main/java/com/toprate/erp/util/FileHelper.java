
package com.toprate.erp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class FileHelper {
	private static final String DIR_SEPARATOR = "/";

	// -------------------------------------------------------------------------

	public static String pathConcat(String... names) {
		return StringUtils.join(names, DIR_SEPARATOR);
	}

	public static String getExtension(String path) {
		String ret = "";

		if (path == null)
			return ret;

		int index = path.lastIndexOf(".");

		if ((index < 0) || (index >= path.length() - 1))
			return ret;

		ret = path.substring(index + 1);

		return ret;
	}

	public static String getBasename(String path) {
		File file = new File(path);

		StringBuilder ret = new StringBuilder(file.getName());

		int index = path.lastIndexOf(".");

		if (index >= 0)
			ret.delete(index, ret.length());

		return ret.toString();
	}

	// -------------------------------------------------------------------------

	public static void delEmptyAncestor(File dir, String stopName) {
		if (dir == null)
			return;

		try {
			String stopFileName = (new File(stopName)).getAbsolutePath();

			while (dir.exists()) {
				if (dir.getName().equals(stopName) || dir.getAbsolutePath().equals(stopFileName)) {
					break;
				}

				File[] children = dir.listFiles();

				if ((children == null) || (children.length > 0))
					break;

				if (!dir.delete())
					break;

				dir = dir.getParentFile();
			}
		} catch (Exception ex) {
		} // 無視
	}

	// -------------------------------------------------------------------------

	private static final Set<String> ignoreFileNameList;
	static {
		ignoreFileNameList = new HashSet<String>(1);
		ignoreFileNameList.add("Thumbs.db");
	}

	public static File getChildFile(String dirPath) throws IOException {
		if (dirPath == null)
			return null;

		File dir = new File(dirPath);

		if (!dir.exists()) {
			throw new FileNotFoundException(dirPath);
		}

		File[] files = dir.listFiles();

		if (files == null) {
			throw new IOException(dirPath + " is not a directory.");
		}

		if (files.length < 1) {
			return null; // 空ディレクトリ
		}

		if (files.length > ignoreFileNameList.size() + 1) {
			throw new IOException(dirPath + " contains too many files.");
		}

		File found = null;
		for (File file : files) {
			if (ignoreFileNameList.contains(file.getName()))
				continue;

			if (found != null) {
				throw new IOException(dirPath + " contains too many files.");
			}

			found = file;
		}

		return found;
	}
}
