package AddingNewRequests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import logout.Pages.LogoutPage;
import net.minidev.json.JSONObject;
import okhttp3.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import Pages.utils.TestUtils;
import org.testng.annotations.AfterSuite;

import static Pages.utils.ExtentManager.createInstance;

public class generateRequests {

    public static Properties prop;



    public String generateAPIRequests(Map<String, String> map) throws IOException {

        FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\Properties\\Configuration");
        prop = new Properties();
        prop.load(ip);
        String cookie = "";
        String requestNo="";

        if(map.get("type").equals("1"))
            cookie=prop.getProperty("Officer-Cookie");
        else
            cookie=prop.getProperty("Ind-Cookie");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType,prepareJson(map));
        Request request = new Request.Builder()
                .url(prop.getProperty("CreateOfficerRequest"))
                .method("POST", body)
                .addHeader("Accept", "*/*")
                .addHeader("Accept-Language", "en")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie)
                .addHeader("Origin", prop.getProperty("Origin"))
                .addHeader("Referer",  prop.getProperty("Referer"))
                .addHeader("User-Agent", prop.getProperty("User-Agent"))
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.code());

        if(response.code()==200)
        {
            //assert response.body() != null;
            requestNo=response.body().string();
            System.out.println("Success: \t"+requestNo);
        }else {
            System.out.println("Failed due to: \t" + response.body().string());
            requestNo = "-1";
        }

        //write return code to file
        return requestNo;

    }

    public String prepareJson(Map<String, String> map )
    {
        String body = "{\r\n    \"id\": null,\r\n    \"hasPreviousRequest\": 1,\r\n    \"retirementPartyTypeId\": "+map.get("type")+",\r\n    ";
        body +="\"reasonTerminationServiceId\": "+map.get("TerminationID")+",\r\n    \"genderId\": 1,\r\n    \"disabilityRatio\": \"20\",\r\n    ";
        body +="\"firstName\": \""+map.get("Reason").substring(0,map.get("Reason").length()%15)+"\",\r\n    \"fatherName\": \""+map.get("secondName")+"\",\r\n    \"grandfatherName\": \""+map.get("thirdName")+"\",\r\n    \"familyName\": \""+map.get("familyName")+"\"";
        body+=",\r\n    \"thighName\": \""+map.get("familyName")+"\",\r\n    \"identityNo\": \""+map.get("Identity")+"\",\r\n    \"currentRankId\": "+map.get("Rank")+",\r\n    \"oldRankId\": "+map.get("Rank")+",\r\n    \"rankSupplement\": \"\"";
        body+=",\r\n    \"ispilot\":"+map.get("isPilot")+"  ,\r\n    \"militaryNumber\": \""+map.get("Identity")+"\",\r\n    \"unitId\": 1010,\r\n    \"sideId\": 2,\r\n";
        body+="    \"soulsWalletNo\": null,\r\n    \"soulsWalletSourceId\": null,\r\n    \"incomingTransactionNo\": \"3\",\r\n    \"job\": \"اتمته\",\n";
        body+="    \"notes\": \"اتمته النظام\",\r\n    \"cityId\":  \"0101000\",\r\n    \"regionId\": 1,\r\n    \"bloodTypeId\": null,\r\n    \"homePhone\": \"\",\r\n";
        body+="    \"basicMobile\": \"0505990588\",\r\n    \"backupMobile\": \"\",\r\n    \"email\": \"Moustafa."+map.get("Identity")+"@gmail.com\",\r\n    \"poBox\": null";
        body+=",\r\n    \"postalCode\": null,\r\n    \"nearbyContacted\": null,\r\n    \"nearbyContactedName\": \"\",\r\n    \"cityExchangeId\": 1,\r\n    ";
        body+="\"bankId\": 12,\r\n    \"iban\": \"0380000000608010167519\",\r\n    \"statementNo\": \"\",\r\n    \"serviceStartDateHijri\": \"" +map.get("StartDate")+"\"";
        body+=",\r\n    \"soulsWalletDate\": \"\",\r\n    \"serviceEndDateHijri\": \""+map.get("EndDate")+"\",\r\n    \"retirementDecisionDate\": \"1444-03-01\",\r\n    ";
        body+="\"incomingTransactionDate\": \"1444-03-01\",\r\n    \"cardGrantDate\": \"\",\r\n    \"cardExpiryDate\": \"\",\r\n    ";
        body+="\"dateDecision\": \"1444-06-15\",\r\n    \"statementDate\": \"\",\r\n    \"birthDate\": \""+map.get("DOB")+"\",\r\n    ";
        body+="\"isDraft\": false,\r\n    \"birthCountryCodeId\": \"SAU\",\r\n    \"birthPlaceCodeId\": \"1001004\",\n" +
                "    \"transactionExportDate\": \"1445-02-07\"\r\n}";
        //System.out.println(body);
        return body;

    }

    public static void main(String[]args) throws IOException, ParseException, InterruptedException {
        generateRequests obj = new generateRequests();
        //obj.tryOkay();
    }


}
