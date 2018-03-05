/**
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

/**
 * This is the detector program that has the ability to detect keyloggers based
 * on signatures.
 * 
 * 
 * @version 1.0
 */
public class Detector {

	private File file;
	private File sigantures;
	private File affectedFiles;
	private File resultFile;
	private File errorFile;

	byte[][] globalMd5 = new byte[1024][1024];
	Scanner scanner;

	/**
	 * This is a constructor used to initialize the Detector program.
	 * 
	 * @param path
	 *            The path to scan
	 */
	public Detector(String path) {
		this.file = new File(path);
		if (this.file == null) {
			try {
				writeResult("Invlid file path " + path, errorFile);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		this.affectedFiles = new File("C:\\KeyDetect\\affected.txt");
		this.resultFile = new File("C:\\KeyDetect\\result.txt");
		this.sigantures = new File("C:\\KeyDetect\\signatures.txt");
		this.errorFile = new File("C:\\KeyDetect\\errors.txt");
		try {

			this.scanner = new Scanner(sigantures);
			int i = 0;
			while (scanner.hasNext()) {
				globalMd5[i] = scanner.nextLine().getBytes();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	/**
	 * This is the wrapper function that is made publicly available to call from
	 * other classes.
	 */
	public void detect() {
		detect(file);
	}

	/**
	 * This is the function that actually detects the signatures.
	 * 
	 * @param file
	 *            The file to detect.
	 */
	private void detect(File file) {
		if (file != null) {
			FileInputStream mFileInputStream = null;
			String md5 = null;
			try {
				writeResult("scanning file " + file.getName() + " in " + file.getAbsolutePath(), resultFile);

			} catch (IOException e1) {

				e1.printStackTrace();
			}

			if (file.isDirectory()) {
				File[] contents = file.listFiles();
				for (File _file : contents) {
					detect(_file);
				}
			} else {

				try {
					mFileInputStream = new FileInputStream(file);
					md5 = DigestUtils.sha1Hex(IOUtils.toByteArray(mFileInputStream));
				} catch (IOException e1) {
					try {
						writeResult(
								"=============================================================================================================================",
								errorFile);
						writeResult("The following error occured while scanning the file " + file.getAbsolutePath()
								+ " the error is shown below", errorFile);
						writeResult(e1.getMessage(), file);
					} catch (IOException ioe) {

						ioe.printStackTrace();
					}
				} finally {
					try {
						if (mFileInputStream != null)
							mFileInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				for (byte[] bs : globalMd5) {
					String signature = new String(bs);
					if (md5 != null && (md5.equalsIgnoreCase(signature) || md5.contains(signature))) {
						try {
							writeResult(file.getAbsolutePath(), affectedFiles);
							break;
						} catch (IOException e) {
							try {
								writeResult(
										"=============================================================================================================================",
										errorFile);
								writeResult("The following error occured while scanning the file "
										+ file.getAbsolutePath() + " The error is shown below", errorFile);
								writeResult(e.getMessage(), file);
							} catch (IOException e1) {

								e1.printStackTrace();
							}
						}
					}

				}

			}
		} else {
			try {
				writeResult("Invlid file entry ", errorFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This function is used to write the final result to the "affected.txt"
	 * file.
	 * 
	 * @param result
	 * @throws IOException
	 */
	private void writeResult(String result, File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		writer.append(result);
		writer.newLine();
		writer.flush();
		writer.close();
	}

}
