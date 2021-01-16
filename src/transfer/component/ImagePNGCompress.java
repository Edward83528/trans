
package transfer.component;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class ImagePNGCompress {
	public static void main(String[] args) {
	}

	private final String commandPath;
	private Process process = null;

	public ImagePNGCompress(String path) {
		super();
		this.commandPath = path;
	}

	public int execute(String file) throws IOException, InterruptedException {

		ProcessBuilder processBuilder = new ProcessBuilder(commandPath, "--speed", "1", "--ext", ".png", "--force",
				file);

		process = processBuilder.start();
		InputStream errorStream = process.getErrorStream();

		IOUtils.toString(errorStream, "UTF-8"); // stderr
		IOUtils.closeQuietly(errorStream);

		InputStream inputStream = process.getInputStream();

		IOUtils.toString(inputStream, "UTF-8"); // stdout
		IOUtils.closeQuietly(inputStream);

		return process.waitFor();
	}

	public void destroy() {
		if (process != null) {
			process.destroy();
		}
	}

}
