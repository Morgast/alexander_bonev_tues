package org.elsys.bg.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GetRequest {
	private static final String HTTP_HOST = "google.com";
	private static final int HTTP_PORT = 80;
	private static final String HTTP_METHOD = "GET";
	private static final String PROTOCOL_VERSION = "HTTP/1.1";
	private static final String HTTP_PATH = "/index.html";

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		final GetRequest http = new GetRequest();
		final HttpResponse response = http.sendRequestGetResponse(HTTP_HOST,
				HTTP_METHOD, HTTP_PATH);
		System.out.println(new String(response.getBody()));
	}

	public HttpResponse sendRequestGetResponse(String host, String method,
			String path) throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(host, HTTP_PORT);
		try {
			final InputStream inputStream = clientSocket.getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();
			final InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			final PrintWriter out = new PrintWriter(outputStream);
			writeRequest(out, host, method, path);
			out.flush();
			return getResponse(in, method, path);
		} finally {
			clientSocket.close();
		}
	}

	private HttpResponse getResponse(BufferedReader in, String method,
			String path) throws IOException {
		final HttpResponse result = new HttpResponse();
		result.setStatusLine(in.readLine());
		String next;
		while (!(next = in.readLine()).equals("")) {
			result.getHeaders().add(HttpHeader.createFromHeaderLine(next));
		}
		String location = null;
		if (parseStatusLine(result.getStatusLine()) == 301) {
			for (HttpHeader next2 : result.getHeaders()) {
				if (next2.getName().equals("Location")) {
					location = parseHostLocation(next2.getValue(), path);
				}
			}
			return sendRequestGetResponse(location, method, path);
		}
		in.read(result.getBody());
		return result;
	}

	private String parseHostLocation(String value, String path) {
		value = value.replaceAll("http://", "");
		value = value.replaceAll(path, "");
		return value;
	}

	private void writeRequest(PrintWriter out, String host, String method,
			String path) {
		out.printf("%s %s %s\n", method, path, PROTOCOL_VERSION);
		out.printf("Host: %s\n", host);
		out.println();
	}

	private int parseStatusLine(String status) {
		final String[] statusParts = status.split(" ");
		final int result = Integer.parseInt(statusParts[1]);
		return result;
	}
}