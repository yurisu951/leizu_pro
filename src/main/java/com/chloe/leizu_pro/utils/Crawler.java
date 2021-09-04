package com.chloe.leizu_pro.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Crawler {

    public static JSONArray getIdList() throws IOException {
        final String baseurl = "https://www.lativ.com.tw/";
//        String[] list = new String[]{"OnSale/D100/WOMEN", "OnSale/D100/MEN", "OnSale/3P80DUP/WOMEN", "OnSale/3P80DUP/MEN",
//                "OnSale/sport/WOMEN", "OnSale/sport/MEN", "OnSale/sale/WOMEN", "OnSale/sale/MEN", "OnSale/1P299/MEN", "OnSale/1P299/WOMEN"};

        String[] list = new String[]{"OnSale/1P299/WOMEN"};
        BufferedReader reader = null;
        JSONArray result = null;

        for (String item : list) {
            String targetUrl = baseurl + item;
            String categoryNum = item.contains("WOMEN") ? "2" : "1";
            String promoCode = item.substring(item.indexOf("/")+1, item.lastIndexOf("/"));
            try {
//            将url从字符串转换成URL对象
                URL url = new URL(targetUrl);
//            初始化url的链接
                URLConnection urlConnection = url.openConnection();
//            开启链接
                urlConnection.connect();

//            初始化输入流来读取url的响应
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                result = new JSONArray();

                while ((line = reader.readLine()) != null) {
//                获取商品的id列表
                    if (line.contains("data-pname")) {
                        JSONObject obj = new JSONObject();
                        String product_name = line.substring(line.indexOf("data-pname=\"") + 12, line.indexOf("\">"));
                        String product_idPath = null;
                        String product_id = null;
                        String product_newPrice = null;
                        String product_originPrice = null;
                        String product_image_url = null;
                        String product_color_id = null;
                        String product_color_url = null;
                        do {

                            if (line.contains("/Detail")) {
                                product_idPath = line.substring(line.indexOf("/Detail/") + 8, line.indexOf("\" onclick"));
                                product_color_id = product_idPath.substring(0,7);
                                product_id = product_idPath.substring(0,5);
                                product_image_url = "https://s4.lativ.com.tw/i/" + product_id + "/" + product_color_id + "1/" + product_color_id + "_500.jpg";
                                product_color_url = "https://s4.lativ.com.tw/i/" + product_id + "/" + product_color_id + "1/" + product_color_id + "_48.jpg";

                            }
                            if (line.contains("原價")) {
                                int index = line.indexOf("data-opricing") + 15;
                                product_originPrice = line.substring(index, index + 3);
                                index = line.indexOf("data-apricing") + 15;
                                product_newPrice = line.substring(index, index + 3);

                            }


                            if (line.contains("</li>")) {
                                obj.put("pathId", product_idPath);
                                obj.put("id", product_id);
                                obj.put("categoryNum", categoryNum);
                                obj.put("name", product_name);
                                obj.put("price", product_originPrice);
                                obj.put("promoCode", promoCode);
                                obj.put("promoPrice", product_newPrice);
                                obj.put("mainImageUrl", product_image_url);
                                obj.put("product_color_id", product_color_id);
                                obj.put("product_color_url", product_color_url);
                                result.add(obj);
                                break;
                            }
                        } while ((line = reader.readLine()) != null);
                    }

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }

        }
        return result;
    }

    //爬取詳細頁面的圖片們
    public static JSONArray getProductDetailsOnlyImg(List<Integer> list) throws IOException {
        final String baseUri = "https://www.lativ.com.tw/Detail/";
        JSONArray result = new JSONArray();
        BufferedReader in = null;

        for (int i = 0; i < list.size(); i++) {

//            JSONObject item = (JSONObject) list.get(i);
//            String id = (String) item.get("pathId");
            Integer id = list.get(i);
            String baseurl = baseUri + id + "1";

            JSONObject obj = new JSONObject();

            URL url = new URL(baseurl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            String colorName;
            obj.put("id7Word", id);
            while ((line = in.readLine()) != null) {
                if (line.contains("class=\"title1\"")) {
                    colorName = line.substring(line.indexOf("icolor") + 8, line.indexOf("</span>"));
                    obj.put("colorName", colorName);
                }
                if (line.contains("oldPic show")) {
                    line = in.readLine();
                    Integer tempIndex = 0;
                    JSONArray array = new JSONArray();
                    while (true) {
                        tempIndex = line.indexOf("lativ.com.tw/i/", tempIndex);
                        if (tempIndex == -1) {
                            obj.put("content", array);
                            if (in != null) in.close();
                            break;
                        }
                        String imageUri = line.substring(tempIndex, line.indexOf("g\" ", tempIndex++) + 1);
                        String imageUrl = "https://s4." + imageUri;
                        array.add(imageUrl);
                    }
                }
            }

            result.add(obj);

        }
        return result;
    }

//    public static String catchImages(String path){
//        byte[] bytes = new byte[1024*1024];
//        File destince = new File();
//
//        try {
//            URL url = new URL(path);
//            URLConnection urlConnection = url.openConnection();
//            InputStream in = urlConnection.getInputStream();
//            int read = in.read(bytes);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    //    舊寫法的爬蟲，可以爬關鍵字內容，但是缺少img單標籤的圖片
//    public void getProductDetailsHasKeyWordUnComplited(JSONArray list) throws IOException {
//        final String baseurl = "https://www.lativ.com.tw/Detail/53416021";
//        final Integer id =5403402;
//
//        JSONObject result = new JSONObject();
//
//        URL url = new URL(baseurl);
//        URLConnection urlConnection = url.openConnection();
//        urlConnection.connect();
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//        String line;
//        String colorName;
//        result.put("id7Word", id);
//        while ((line = in.readLine()) != null){
//            if(line.contains("class=\"title1\"")){
//                colorName  = line.substring(line.indexOf("icolor") + 8, line.indexOf("</span>"));
//                result.put("colorName", colorName);
//            }
//            if(line.contains("oldPic show")){
//                line = in.readLine();
//                Integer tempIndex = 0;
//
////                有keyWord字串的处理
//                if (line.contains("keyWord")){
//                    tempIndex = line.indexOf("keyWord", tempIndex);
//                    String searchUrl = line.substring(tempIndex + 8, line.indexOf("\" target=", tempIndex));
//                    String contentImageUrl = line.substring(line.indexOf("img src=\"", tempIndex) + 9, line.indexOf("\" alt=\"", tempIndex));
//                    tempIndex++;
//                    JSONArray content = new JSONArray();
//                    JSONObject item = new JSONObject();
//                    item.put("searchKeyWord", searchUrl);
//                    item.put("ImageUrl", contentImageUrl);
//                    content.add(item);
//
////                    keyWord的循环
//                    while (true){
//                        tempIndex = line.indexOf("keyWord", tempIndex);
//                        if(tempIndex == -1) break;
//
//                        searchUrl = line.substring(tempIndex + 8, line.indexOf("\" target=", tempIndex));
//                        JSONObject item2 = new JSONObject();
////                        item2.put("searchKeyWord", searchUrl);
//                        JSONArray contentImages = new JSONArray();
//                        Integer endTempIndex = line.indexOf("</a>", tempIndex);
//                        String tempLine = line.substring( tempIndex, endTempIndex);
//
//                        Integer tempIndex2 = 0;
//                        //      data-origin的循环
//                        while(true){
//                            tempIndex2 = tempLine.indexOf("data-origin", tempIndex2);
//                            if (tempIndex2 == -1) {
//                                item2.put(searchUrl, contentImages);
//                                tempIndex = endTempIndex;
//                                break;
//                            }
//
//                            contentImageUrl = tempLine.substring( tempIndex2 + 15, tempLine.indexOf("\" />", tempIndex2));
//                            contentImages.add(contentImageUrl);
//                            tempIndex2++;
//                        }
//
//
//                    }
//
////                没有keyWord字串的处理
//                } else {
//
//                }
//
//            }
//
//        }
//        System.out.println(result);
////        return result;
//    }
}

