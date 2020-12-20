package com.utils.http;


public class HttpUtils {

    public static void main(String[] args) {

        System.out.println("HTTP...");

    }
	
/*
	public void httpRequest() {
		HttpClient httpClient = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		HttpPost httpPost = new HttpPost(urls[0]);

		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			StatusLine statusLine = httpResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {
				HttpEntity entity = httpResponse.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
}
