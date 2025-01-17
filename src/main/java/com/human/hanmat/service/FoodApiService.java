package com.human.hanmat.service;

import com.human.hanmat.dto.FoodDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Deprecated
public class FoodApiService {
    private final CommonApiService CommonApiService = new CommonApiService();
    private static final String TOKEN = "nTSaKfYMjxWt6GPwWlIOyh3Kyn6BDPCU52gFe2W7f7Ea4j9iCw4CHWIJFBlCEYoG";
    private static final String PATH = "https://seoul.openapi.redtable.global/api";

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

        return CommonApiService.getDataString(urlConnection);
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

    public List<FoodDTO> getAllFoodList(String lang, int amount) {
        try {
            JSONParser jsonParser = new JSONParser();

            // 이미지 데이터 받아오기
            JSONArray imgBody = new JSONArray();
            List<Integer> menuIdList = new ArrayList<>();
            JSONObject imageData = (JSONObject) jsonParser.parse(connect("food", "img"));
            JSONArray imageBody = (JSONArray) imageData.get("body");
            for (Object obj : imageBody) {
                int menuId = Integer.parseInt(((JSONObject) obj).get("MENU_ID").toString());
                menuIdList.add(menuId);
                imgBody.add(obj);
            }

            List<String> menuNameList = new ArrayList<>();
            List<FoodDTO> foodList = new ArrayList<>();
            int pageNo = 1;

            while (true) {
                JSONObject data = (JSONObject) jsonParser.parse(connect("menu-dscrn", lang, pageNo));
                JSONArray body = (JSONArray) data.get("body");
                if (body.isEmpty()) {
                    break;
                }

                for (Object obj : body) {
                    // 중복 방지
                    String menuName = ((JSONObject) obj).get("MENU_NM").toString();
                    if (menuNameList.contains(menuName)) {
                        continue;
                    } else {
                        menuNameList.add(menuName);
                    }

                    // 이미지가 있는 메뉴만 가져오기
                    int menuId = Integer.parseInt(((JSONObject) obj).get("MENU_ID").toString());
                    if (!menuIdList.contains(menuId)) {
                        continue;
                    }

                    // 한식 메뉴만 가져오기
                    String menuCtgryLclasNm = ((JSONObject) obj).get("MENU_CTGRY_LCLAS_NM").toString();
                    if (!menuCtgryLclasNm.equals("한식") && !menuCtgryLclasNm.equals("Korean Cuisine") && !menuCtgryLclasNm.equals("韓国料理")) {
                        continue;
                    }

                    // 메뉴 정보 가져오기
                    JSONObject jsonObj = (JSONObject) obj;
                    String menuNm = jsonObj.get("MENU_NM").toString();
                    String menuDscrn = (jsonObj.get("MENU_DSCRN") == null) ? "" : jsonObj.get("MENU_DSCRN").toString();
                    String menuCtgrySclasNm = (jsonObj.get("MENU_CTGRY_SCLAS_NM") == null) ? "" : jsonObj.get("MENU_CTGRY_SCLAS_NM").toString();
                    String menuImg = findMenuImageById(String.valueOf(menuId), Objects.requireNonNull(imgBody));

                    // DTO에 저장
                    FoodDTO foodDTO = new FoodDTO();
                    foodDTO.setId(menuId);
                    foodDTO.setName(menuNm);
                    foodDTO.setDscrn(menuDscrn);
                    foodDTO.setCategory(menuCtgrySclasNm);
                    foodDTO.setImage(menuImg);

                    foodList.add(foodDTO);

                    // amount만큼 가져왔으면 종료
                    if (foodList.size() >= amount) {
                        break;
                    }
                }

                if (foodList.size() >= amount || pageNo > 10) {
                    break;
                } else {
                    pageNo++;
                }
            }

            return foodList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
