package com.human.hanmat.service;

import com.human.hanmat.dto.MapDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {
    private final CommonApiService CommonApiService = new CommonApiService();
    private static final String URL = "https://openapi.naver.com/v1/search/image";
    private static final String CLIENT_ID = "Xs8udJjhMhH_I_6pLk3F";
    private static final String CLIENT_SECRET = "hMAAjvehvN";

    private String connect(String name) throws Exception {
        String query = URLEncoder.encode(name, StandardCharsets.UTF_8);
        StringBuilder apiUrl = new StringBuilder(URL);
        apiUrl.append("?query=").append(query);
        apiUrl.append("&display=4");

        URL url = new URI(apiUrl.toString()).toURL();
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
        urlConnection.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

        return CommonApiService.getDataString(urlConnection);
    }

    public void find() {
        System.out.println("find");
    }
    public List<MapDTO> get(String name) throws Exception {
        String result = connect(name);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");

        List<MapDTO> mapDTOList = new ArrayList<>();
        for (Object item : jsonArray) {
            JSONObject obj = (JSONObject) item;
            MapDTO mapDTO = new MapDTO();
            mapDTO.setLink(obj.get("link").toString());
            mapDTO.setThumbnail(obj.get("thumbnail").toString());

            mapDTOList.add(mapDTO);
        }

        return mapDTOList;
    }
}
