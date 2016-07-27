package com.ice.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ParseException;

public class HttpPostUrl {
	private static final String TAG = "newtab";
	private static DefaultHttpClient client;
	private static CookieStore cookieStore;

	public static DefaultHttpClient getClient() {
		if (client == null) {
			client = new DefaultHttpClient();
		}

		return client;
	}

	public static boolean isLoggedIn() {
		if (cookieStore == null)
			return false;

		for (Cookie cookie : cookieStore.getCookies()) {
			if (cookie.getName().equals("PHPSESSID")) {
				return true;
			}
		}
		return false;
	}

	public static void logout() {
		if (cookieStore != null) {
			cookieStore.clear();
			cookieStore = null;
		}
		client = null;
	}

    public static String buildUrl(String uri) {

        String url = uri;

        return url;
    }

    public static String makeHttpPostCall(String url) {
    	HttpClient client = getClient();
        HttpPost request = new HttpPost(url);

        HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return httpResponseToString(response);
    }

    public static void setCookieStore(CookieStore store) {
    	cookieStore = store;
    }

    public static String makeHttpPostCall(String url, List<NameValuePair> postParameters) {
    	DefaultHttpClient client = getClient();
        HttpPost request = new HttpPost(url);
        if (cookieStore != null)
        	client.setCookieStore(cookieStore);

        UrlEncodedFormEntity formEntity = null;
		try {
			formEntity = new UrlEncodedFormEntity(postParameters);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		request.setEntity(formEntity);

		final boolean verbose = false;

		if (verbose) {
			try {
				String entityString;
				entityString = EntityUtils.toString(formEntity, HTTP.UTF_8);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

        HttpResponse response = null;
		try {
			response = client.execute(request);
//            HttpEntity entity = response.getEntity();

            cookieStore = client.getCookieStore();

    		if (verbose) {
    			try {
    				String entityString;
    				entityString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
    			} catch (ParseException e1) {
    				e1.printStackTrace();
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
    		}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return httpResponseToString(response);
    }

    public static String httpResponseToString(HttpResponse response) {
    	HttpEntity returnEntity = response.getEntity();
    	InputStream instream = null;
		try {
			instream = returnEntity.getContent();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader((InputStream) instream));

        // Read response until the end
        try {
			while ((line = rd.readLine()) != null) {
			    total.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(total.toString());

        return total.toString();
    }

    public static String buildUrl(Context c, int urlResourceId) {

        String url = c.getString(urlResourceId);

        System.out.println(url);

        return url;
    }

}
