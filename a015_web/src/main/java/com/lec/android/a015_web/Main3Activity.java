package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/*XML, JSON 파싱 연습
 *
 * ■서울시 지하철 역사 정보
http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

샘플url

XML 버젼
http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/서울

JSON 버젼
http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/서울
 */

public class Main3Activity extends AppCompatActivity {

    TextView tvResult;
    EditText editText;

    int startIndex;
    int endIndex;

    String statNm;

    String url_address = "";
    StringBuffer sb;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);
        editText = findViewById(R.id.editText);

        Button btnXML = findViewById(R.id.btnXML);
        Button btnJSON = findViewById(R.id.btnJSON);
        Button btnParse = findViewById(R.id.btnParse);

        startIndex = 1;
        endIndex = 5;

        statNm = editText.getText().toString().trim();

        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urlStr = buildUrlAddress("xml", startIndex, endIndex, statNm);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();
            }
        });

        btnJSON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String urlStr = buildUrlAddress("json", startIndex, endIndex, statNm);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            request(urlStr);
                        }
                    }).start();
            }
        });
    } //onCreate

    public static String buildUrlAddress(String reqType, int startIndex, int endIndex, String date){

        String url_address = null;
        try {
            url_address = String.format(
                    "http://swopenapi.seoul.go.kr/api/subway/7655456c64676f6c32394879425774/%s/stationInfo/%d/%d/%s",
                    reqType, startIndex, endIndex, URLEncoder.encode(date, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return url_address;
    } // buildUrlAddress

    public void request(String urlStr) {
        final StringBuilder sb = new StringBuilder();

        BufferedReader reader = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlStr);

            conn = (HttpURLConnection) url.openConnection();

            if (conn != null) {
                conn.setConnectTimeout(5000); // timeout 시간 설정, 경과하면 SocketTimeoutException 발생
                conn.setUseCaches(false); // 캐시사용 안함.
                conn.setRequestMethod("GET"); // GET 방식 request

                conn.setDoInput(true); // URLConncetion 을 입력으로 사용. (true), (false) -> 출력용

                int responseCode = conn.getResponseCode(); // response code 값. 성공하면 200

                if (responseCode == HttpURLConnection.HTTP_OK) { // 200 HTTP_OK
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;

                    while (true) {
                        line = reader.readLine();
                        if (line == null) break;
                        sb.append(line + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (conn != null) conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvResult.setText(sb.toString());
            }
        });
    } //request

    public static void parseXML(String xmlText) {
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;

        // DOM parser 객체 생성
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();

            // String -> InputStream 변환
            InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));

            // DOM 객체 생성
            Document dom = dBuilder.parse(in);

            // DOM 최상위 document element 추출
            Element docElement = dom.getDocumentElement(); // DOM의 최상위 element

            // 파싱하기전 normalize()
            docElement.normalize(); // 흩어진 text node들을 정렬하는 등의 절차, 불필요한 (엔터, 스페이스바) 텍스트 노드를 한 쪽으로 몰아준다
            // xml 파싱하기 전에 꼭 normalize()부터 해주자

            // DOM 내의 데이터 파싱
            NodeList nList = docElement.getElementsByTagName("row"); // <row> ... </row> element들로 구성된 NodeList 리턴
            System.out.println(xmlText);
            System.out.println("<row> 개수 : " + nList.getLength());

            System.out.println();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                // element node 인 경우만 파싱 진행
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element rowElement = (Element) node;

                String statnNm = rowElement.getElementsByTagName("statnNm").item(0).getChildNodes().item(0)
                        .getNodeValue().trim();
                String subwayId = rowElement.getElementsByTagName("subwayId").item(0).getChildNodes().item(0)
                        .getNodeValue().trim();
                String subwayNm = rowElement.getElementsByTagName("subwayNm").item(0).getChildNodes().item(0)
                        .getNodeValue().trim();

                System.out.printf("%d: %s역 %s %s\n", (i + 1), statnNm, subwayId, subwayNm);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // parseXML

    public static void parseJSON(String jsonText) throws JSONException {


        JSONObject jObj = new JSONObject(jsonText); // JSON 파싱 : JSONObject <- String
        JSONArray row = jObj.getJSONArray("stationList");

        System.out.println(jsonText);
        System.out.println("row의 갯수 : " + row.length());
        System.out.println();
        for (int i = 0; i < row.length(); i++) {
            JSONObject station = row.getJSONObject(i);

            String statnNm = station.getString("statnNm");
            String subwayId = station.getString("subwayId");
            String subwayNm = station.getString("subwayNm");

            System.out.printf("%d: %s역 %s %s\n", (i + 1), statnNm, subwayId, subwayNm);
        }
    }
}
