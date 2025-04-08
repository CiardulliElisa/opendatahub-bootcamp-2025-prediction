package JoEl.src.main.java;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Main {
	
	private static final String TOKEN_URL = "https://auth.opendatahub.com/auth/realms/noi/protocol/openid-connect/token";
    private static final String CLIENT_ID = "opendatahub-bootcamp-2025";
    private static final String CLIENT_SECRET = "QiMsLjDpLi5ffjKRkI7eRgwOwNXoU9l1";
    private static final String API_URL = "https://api.opendatahub.com/your-api-endpoint";

    public static void main(String[] args) {
    	String station = null; 
    	String wochentag = null; 
    	String startingHour = null;
    	String endingHour = null;
    	
//    	System.out.println("Please choose the station, for which you want to have the prediction (Please insert the code of the station)");
//    	// Enter data using BufferReader
//        Scanner sc = new Scanner(System.in);
//        String station = sc.nextLine();
//        
//        System.out.println("Please choose the day of the week, for which you want to have the prediction (e.g. Tuesday)");
//    	// Enter data using BufferReader
//        String wochentag = sc.nextLine();
//        
//        System.out.println("Please choose the starting hour, for which you want to have the prediction (e.g. 14)");
//    	// Enter data using BufferReader
//        String startingHour = sc.nextLine();
//        
//        System.out.println("Please choose the ending hour, for which you want to have the prediction (e.g. 14)");
//    	// Enter data using BufferReader
//        String endingHour = sc.nextLine();
        
        
    	
    	String data = "";
    
    	try {
            String accessToken = getAccessToken();
            accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJtQkpEbmJCbnY2c3FNcjVLWEt6cXZWajZWZnFVLTh1NU5SSkNraU42X3VnIn0.eyJleHAiOjE3NDQxMzAxOTUsImlhdCI6MTc0NDEyNjU5NSwianRpIjoiMTI0MTg5NTMtMWYyNy00NmJkLTlmYjgtNzY3N2Q5ZTQ4MTYzIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLm9wZW5kYXRhaHViLmNvbS9hdXRoL3JlYWxtcy9ub2kiLCJhdWQiOlsiaXQuYnoubm9pLnZpcnR1YWwiLCJvZGgtbW9iaWxpdHktdjIiLCJpdC5iei5ub2kuY29tbXVuaXR5IiwiYWNjb3VudCJdLCJzdWIiOiIzMTRkNjRkMS1mZDAyLTQwMTUtOGNjNC1lMmY0NDhkY2QwMjkiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJvcGVuZGF0YWh1Yi1ib290Y2FtcC0yMDI1IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtbm9pIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJpdC5iei5ub2kudmlydHVhbCI6eyJyb2xlcyI6WyJWaXJ0dWFsVmlsbGFnZU1hbmFnZXIiXX0sIm9kaC1tb2JpbGl0eS12MiI6eyJyb2xlcyI6WyJPREhfUk9MRV9CQVNJQyJdfSwiaXQuYnoubm9pLmNvbW11bml0eSI6eyJyb2xlcyI6WyJBQ0NFU1NfR1JBTlRFRCJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwiZGVsZXRlLWFjY291bnQiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoib3BlbmRhdGFodWItYm9vdGNhbXAtMjAyNSIsImNsaWVudEhvc3QiOiI0Ni4xOC4yOC4yNDIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtb3BlbmRhdGFodWItYm9vdGNhbXAtMjAyNSIsImNsaWVudEFkZHJlc3MiOiI0Ni4xOC4yOC4yNDIifQ.OcNS0LMSUT-JPsV03Lo-guAm_MjH3Oc-GXMgcC5kubScU-SQQSGzutXAwfxdmX4GlCzu4f7E0BbrV48Y6XPYD9YwDJiOQgb_pCNeCpyJVZths0UCwqPtlAItEZtdSVY-QCyZjnxHzwV90BsG16kVbLgD9cglpYBeRwQFX2LRUzm0pwZqgjHL5bSLgr42LiHqtm2HSFvj8Z6pNIp-ojeWMvw_2Jd4kQ-RFGeb0-LzVS8NGGuy-ChbOkCEmckkyXX1jvyA1W6QHoszze_T3z1E2ygZqsy8IwdRVpDQE7QktGQpOX61mVaHVvm3YSwXx-DLtU9zI3UqSEBj0ME-gAzhig";
            data = getData(accessToken, station, wochentag);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println(data);
        saveData(data);
        getPrediction(data, wochentag, startingHour, endingHour);
    }
    
    

        private static String getAccessToken() throws IOException {
            URL url = new URL(TOKEN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            Map<String, String> parameters = new HashMap<>();
            parameters.put("grant_type", "client_credentials");
            parameters.put("client_id", CLIENT_ID);
            parameters.put("client_secret", CLIENT_SECRET);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : parameters.entrySet()) {
                if (postData.length() != 0) {
                    postData.append("&");
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append("=");
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            connection.setDoOutput(true);
            connection.getOutputStream().write(postData.toString().getBytes("UTF-8"));

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Token request failed: " + connection.getResponseMessage());
            }

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }

            Map<String, Object> responseMap = parseJsonResponse(response.toString());
            return (String) responseMap.get("access_token");
        }

        private static Map<String, Object> parseJsonResponse(String jsonResponse) {
            // Implement your own JSON parsing logic here
            // This is a simplified example, you may need to use a JSON parsing library
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("access_token", "your_access_token");
            return responseMap;
        }

    public static String getData(String accessToken, String code, String dayOfWeek) throws IOException{
//    	LocalDate tomorrow = LocalDate.now( ).plusDays(1);
//    	int day = LocalDate.now().getDayOfWeek().toString().compareTo(dayOfWeek);
//    	LocalDate beforeOneWeek = LocalDate.now().minusDays(day);
    	String url = "https://mobility.api.opendatahub.com/v2/flat/ParkingStation/free/2024-12-07/2025-04-09?limit=-1&offset=0&where=scode.eq.%22103%22&shownull=true&distinct=false&timezone=UTC&select=mvalue,mvalidtime,mperiod";
//    	String url2 = "https://mobility.api.opendatahub.com/v2/flat/ParkingStation/free/"+ beforeOneWeek+"/"+ tomorrow+"?limit=-1&offset=0&where=scode.eq.%22"+code+"%22&shownull=true&distinct=false&timezone=UTC&select=mvalue,mvalidtime,mperiod";
    	
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                String jsonResponse = response.toString();
                return jsonResponse;
            } else {
                throw new IOException("Response status: " + responseCode);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage() + "check");
        }
		return null;
    }

    


 

    public static void saveData(String jsonData) {
    	
    	ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	Response PiazzaWalther = null;
    	
    	try {

    	    PiazzaWalther = objectMapper.readValue(jsonData, Response.class);


    	} catch (IOException e) {
    	    e.printStackTrace();
    	}

    	File myCsv = new File ("./parkingLotData.csv");
    	Object[] collection = new Object[1];
    	
		String[] headers = new String[] {"timestamp", "mperiod", "mvalue" };
		
		try(Writer writer = new FileWriter("C:/Users/184826/eclipse-workspace_2/Joel/parkingLotData.csv");
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers))
						) {
			
			for(ParkingStation one : PiazzaWalther.data) {
				csvPrinter.printRecord(one._timestamp, one.mperiod, one.mvalue);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    
    private static int getPrediction(String data, String dayOfWeek, String startingHour, String endingHour) {
//    	int day = LocalDate.now().getDayOfWeek().toString().compareTo(dayOfWeek);
//    	LocalDate beforeOneWeek = LocalDate.now().minusDays(day);
    	ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	Response PiazzaWalther = null;
    	
    	try {

    	    PiazzaWalther = objectMapper.readValue(data, Response.class);


    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    	
    	List<Integer> freeLots = new ArrayList<>();
    	
    	for(ParkingStation one : PiazzaWalther.data) {
    		if(one._timestamp.contains(LocalDate.now().toString())) {
    			freeLots.add(one.mvalue);
    		}
    	}
    	
    	
    	int numbers = freeLots.size();
    	int freeAdd = 0;
    	for(Integer one : freeLots) {
    		freeAdd += one;
    	}
    	
    	int prediction = freeAdd/numbers;
    	System.out.println(prediction);
    	
    	return prediction;
    }

}






//private static void callOpenDataHubApi(String accessToken) throws IOException {
//URL url = new URL(API_URL);
//HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//connection.setRequestMethod("GET");
//connection.setRequestProperty("Authorization", "Bearer " + accessToken);
//connection.setRequestProperty("Content-Type", "application/json");
//
//int responseCode = connection.getResponseCode();
//if (responseCode != HttpURLConnection.HTTP_OK) {
//  throw new IOException("API request failed: " + connection.getResponseMessage());
//}
//
//Scanner scanner = new Scanner(connection.getInputStream());
//StringBuilder response = new StringBuilder();
//while (scanner.hasNextLine()) {
//  response.append(scanner.nextLine());
//}
//
//System.out.println("API Response: " + response.toString());
//}