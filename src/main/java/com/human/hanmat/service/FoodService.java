package com.human.hanmat.service;

import com.human.hanmat.dto.FoodDTO;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FoodService {
    private static final String TOKEN = "nTSaKfYMjxWt6GPwWlIOyh3Kyn6BDPCU52gFe2W7f7Ea4j9iCw4CHWIJFBlCEYoG";
    private static final String PATH = "https://seoul.openapi.redtable.global/api";

    public String getDataString(HttpURLConnection urlConnection) throws Exception {
        InputStream is = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        urlConnection.disconnect();
        return sb.toString();
    }

    private String connect(String type, String lang) throws Exception {
        return connect(type, lang, 1);
    }

    private String connect(String type, String lang, int pageNo) throws Exception {
        StringBuilder apiUrl = new StringBuilder(PATH);
        apiUrl.append("/").append(type);
        apiUrl.append("/").append(lang);
        apiUrl.append("?serviceKey=").append(TOKEN);
        apiUrl.append("&pageNo=").append(pageNo);

        URL url = new URI(apiUrl.toString()).toURL();
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        return getDataString(urlConnection);
    }

    private String findMenuImageById(String menuId, JSONArray imgBody) {
        for (Object obj : imgBody) {
            JSONObject jsonObj = (JSONObject) obj;
            String imgMenuId = jsonObj.get("MENU_ID").toString();
            if (Objects.equals(imgMenuId, menuId)) {
                return jsonObj.get("FOOD_IMG_URL").toString();
            }
        }
        return null;
    }

    public List<FoodDTO> getAllFoodList(String lang) {
        try {
            JSONParser jsonParser = new JSONParser();

            JSONArray infoBody = new JSONArray();
            JSONArray imgBody = new JSONArray();

            for (int i = 1; i <= 5; i++) {
                JSONObject data = (JSONObject) jsonParser.parse(connect("menu-dscrn", lang));
                JSONArray body = (JSONArray) data.get("body");
                for (Object obj : body) {
                    infoBody.add(obj);
                }

                JSONObject imageData = (JSONObject) jsonParser.parse(connect("food", "img"));
                JSONArray imageBody = (JSONArray) imageData.get("body");
                for (Object obj : imageBody) {
                    imgBody.add(obj);
                }
            }

            List<FoodDTO> foodList = new ArrayList<>();

            for (Object obj : Objects.requireNonNull(infoBody)) {
                JSONObject jsonObj = (JSONObject) obj;
                System.out.println(jsonObj);
                String menuId = jsonObj.get("MENU_ID").toString();
                String menuNm = jsonObj.get("MENU_NM").toString();
                String menuDscrn = (jsonObj.get("MENU_DSCRN") == null) ? "" : jsonObj.get("MENU_DSCRN").toString();
                String menuCtgryLclasNm = jsonObj.get("MENU_CTGRY_LCLAS_NM").toString();

                if (!menuCtgryLclasNm.equals("한식") && !menuCtgryLclasNm.equals("Korean Cuisine") && !menuCtgryLclasNm.equals("韩国料理")) {
                    continue;
                }

                String menuImg = findMenuImageById(menuId, Objects.requireNonNull(imgBody));

                if (menuImg == null) {
                    continue;
                }

                FoodDTO foodDTO = new FoodDTO();
                foodDTO.setId(menuId);
                foodDTO.setName(menuNm);
                foodDTO.setDscrn(menuDscrn);
                foodDTO.setCategory(menuCtgryLclasNm);
                foodDTO.setImage(menuImg);

                foodList.add(foodDTO);
            }

            return foodList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
