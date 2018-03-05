
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class Test {

	public static void main(String[] args) {
		FileInputStream mFileInputStream = null;
		String md5 = null;
		String sha256 = null;
		String sha1 = null;
		File file = new File(args[0]);
		try {
			mFileInputStream = new FileInputStream(file);
			md5 = DigestUtils.md5Hex(IOUtils.toByteArray(mFileInputStream));
			sha256 = DigestUtils.sha256Hex(IOUtils.toByteArray(mFileInputStream));
			sha1 = DigestUtils.sha1Hex(IOUtils.toByteArray(mFileInputStream));
		} catch (IOException e1) {

		} finally {
			try {
				mFileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(md5);
		System.out.println(sha1);
		System.out.println(sha256);

	}

}
