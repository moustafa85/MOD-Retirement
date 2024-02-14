package AddingNewRequests;

import Pages.utils.ManipulateDB;
import okhttp3.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Properties;

public class addReqDetails {

    public static Properties prop;

    public String sendRequest(Map<String, String> map, String requestType, String requestBody) throws IOException {

        FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\Properties\\Configuration");
        prop = new Properties();
        prop.load(ip);
        String cookie = "";
        String requestNo="";

        if(map.get("type").equals("1"))
            cookie=prop.getProperty("Adv-Officer-Cookie");
        else
            cookie=prop.getProperty("Adv-Ind-Cookie");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType,requestBody);
        Request request = new Request.Builder()
                .url(prop.getProperty(requestType))
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
        System.out.println(requestType+" :\t"+response.code());

        if(response.code()==200)
        {
            //assert response.body() != null;
            requestNo=response.body().string();
            System.out.println("Success:  \t"+"add "+requestType+" :\t"+requestNo);
        }else {
            System.out.println("Failed due to: \t" + response.body().string());
            requestNo = "-1";
        }

        //write return code to file
        return requestNo;

    }

    public String AddAttchment(Map<String, String> map, String requestType, String requestBody) throws IOException {

        FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\Properties\\Configuration");
        prop = new Properties();
        prop.load(ip);
        String cookie = "";
        String requestNo="";

        if(map.get("type").equals("1"))
            cookie=prop.getProperty("Validator-Off-Cookie");
        else
            cookie=prop.getProperty("Validator-Ind-Cookie");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType,requestBody);
        Request request = new Request.Builder()
                .url(prop.getProperty(requestType))
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
        System.out.println(requestType+" :\t"+response.code());

        if(response.code()==200)
        {
            //assert response.body() != null;
            requestNo=response.body().string();
            System.out.println("Success:  \t"+"add "+requestType+" :\t"+requestNo);
        }else {
            System.out.println("Failed due to: \t" + response.body().string());
            requestNo = "-1";
        }

        //write return code to file
        return requestNo;

    }

    public String submitValidatorReq(Map<String, String> map, String requestType, String requestBody) throws IOException {

        FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\Properties\\Configuration");
        prop = new Properties();
        prop.load(ip);
        String cookie = "";
        String requestNo="";

        if(map.get("type").equals("1"))
            cookie=prop.getProperty("Validator-Off-Cookie");
        else
            cookie=prop.getProperty("Validator-Ind-Cookie");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType,requestBody);
        Request request = new Request.Builder()
                .url(prop.getProperty(requestType))
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
        System.out.println(requestType+" :\t"+response.code());

        if(response.code()==200)
        {
            //assert response.body() != null;
            requestNo=response.body().string();
            System.out.println("Success:  \t"+"Submit "+requestType+" :\t"+requestNo);
        }else {
            System.out.println("Failed due to: \t" + response.body().string());
            requestNo = "-1";
        }

        //write return code to file
        return requestNo;

    }

    public String submitSupervisorReq(Map<String, String> map, String requestType, String requestBody) throws IOException {

        FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\Properties\\Configuration");
        prop = new Properties();
        prop.load(ip);
        String cookie = "";
        String requestNo="";

        if(map.get("type").equals("1"))
            cookie=prop.getProperty("Off-supervisor-Cookie");
        else
            cookie=prop.getProperty("Ind-supervisor-Cookie");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType,requestBody);
        Request request = new Request.Builder()
                .url(prop.getProperty(requestType))
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
        System.out.println(requestType+" :\t"+response.code());

        if(response.code()==200)
        {
            //assert response.body() != null;
            requestNo=response.body().string();
            System.out.println("Success:  \t"+"Submit "+requestType+" :\t"+requestNo);
        }else {
            System.out.println("Failed due to: \t" + response.body().string());
            requestNo = "-1";
        }

        //write return code to file
        return requestNo;

    }

    public String getCurrentServiceBody(Map<String, String> map )
    {

        /**
         * {
         *     "requestId": "3197",
         *     "appointmentNo": "3222",
         *     "appointmentDate": "1444-11-05",
         *     "terminationResolutionNo": "45678",
         *     "terminationResolutionDate": "1444-11-19"
         * }
         * {"requestId":"3176","appointmentNo":"3176","appointmentDate":"1444-11-03","terminationResolutionNo":"3176","terminationResolutionDate":"1444-11-22"}
         */

        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"appointmentNo\": \""+map.get("ReqNo")+"\",";
        body +="\r\n    \"appointmentDate\": \"1444-11-01\",";
        body +="\r\n    \"terminationResolutionNo\": \""+map.get("ReqNo")+"\",";
        body +="\r\n    \"terminationResolutionDate\":  \"1444-11-05\"\r\n}";
        return body;

    }

    public String getExtraServiceBody(Map<String, String> map)
    {

        /**
         {"requestId":"3197","startDate":"1445-11-04","endDate":"1446-11-04","sideId":2,"endReasonCodeId":40,"organizationCodeId":3,"salaryModel":{"requestId":"3197","jobCategoryCodeId":null,"periodCodeId":null,"periodAdditionCodeId":null,"extraServicesId":null},"salaryRecordModel":{"requestId":"3197","extraServicesId":null,"careerLadderCodeId":null,"rankCodeId":null,"gradeCodeId":null,"salary":null}}
         */
        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"startDate\": \"1440-11-04\",";
        body +="\r\n    \"endDate\": \"1444-11-04\",";
        body +="\r\n    \"sideId\": 2,";
        //prepare endReasonCodeId - return value from DB
        String endServiceCode = ManipulateDB.executeQuery("select id from EndReasonCode where EndReasonCodeId = "+map.get("TerminationID"),"id");
        if (endServiceCode.equals("-1"))
            return "-1";
        //System.out.println("endServiceCode:\t"+endServiceCode);
        body +="\r\n    \"endReasonCodeId\": "+endServiceCode+",\r\n    \"organizationCodeId\": 3,";
        body +="\r\n    \"salaryModel\": {\r\n        \"requestId\":  \""+map.get("ReqNo")+"\",";
        body +="\r\n        \"jobCategoryCodeId\":  null,\r\n        \"periodCodeId\":  null,";
        body +="\r\n        \"periodAdditionCodeId\": null,\r\n        \"extraServicesId\": null \n\r    },";
        body +="\r\n    \"salaryRecordModel\": {\r\n        \"requestId\":  \""+map.get("ReqNo")+"\",";
        body +="\r\n        \"extraServicesId\":  null,\r\n        \"careerLadderCodeId\":  null,";
        body +="\r\n        \"rankCodeId\": null,\r\n        \"gradeCodeId\": null,";
        body +="\r\n        \"salary\": null \n\r    }\n}";
        return body;

    }

    public String getAddAttchmentBody(Map<String, String> map, int AttachmnetType)
    {
        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"attachmentTypeId\": "+AttachmnetType+",";
        body +="\r\n    \"formDate\": \"1445-02-05\",";
        body +="\r\n    \"attachmentName\": \""+map.get("ReqNo")+"-11\",";
        body +="\r\n    \"isNew\": true,";
        body +="\r\n    \"isByanForm\": null,";
        body +="\r\n    \"notes\": \"اتوميشن\"\n}";
        return body;
    }

    public String getSalaryRecordBody(Map<String, String> map,String extraServiceID )
    {

        /**\
         * {
         *     "requestId": "3197",
         *     "extraServicesId": 267,
         *     "careerLadderCodeId": 1,
         *     "rankCodeId": 3,
         *     "gradeCodeId": 8,
         *     "salary": "3333",
         *     "isLastPeriod": true,
         *     "salaryStartHijriDate": "1441-10-05",
         *     "salaryEndHijriDate": "1444-10-24"
         * }
         */

        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"extraServicesId\": "+extraServiceID+",";
        body +="\r\n    \"careerLadderCodeId\": 1,\r\n    \"rankCodeId\": 1,";
        body +="\r\n    \"gradeCodeId\": 8,\r\n    \"salary\": 9000,\r\n    \"isLastPeriod\": true,";
        body +="\r\n    \"salaryStartHijriDate\": \""+map.get("StartDate")+"\",";
        body +="\r\n    \"periodEndDate\": null,";
        body +="\r\n    \"isConfirmed\": false,";
        body +="\r\n    \"salaryEndHijriDate\":  \""+map.get("EndDate")+"\"\r\n}";
        //System.out.println(body);
        return body;

    }

    public String getSubmitValidatorBody(Map<String, String> map )
    {

        /**\
         * {
         *     "requestId": "7089",
         *     "notes": "لا يوجد اى ملاحظات",
         *     "isApprovalRecommendation": true,
         *     "transactionExportDate": "1445-02-15"
         * }
         */

        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"notes\": \"لا يوجد اى ملاحظات\",";
        body +="\r\n    \"isApprovalRecommendation\": true,\r\n    \"transactionExportDate\": \"1445-02-15\"\r\n}";
        //System.out.println(body);
        return body;

    }

    public String getSubmitSupervisorBody(Map<String, String> map )
    {

        /**\
         {"requestId":"7088","notes":"لا يوجد اى ملاحظات","isApprovalRecommendation":null,"transactionExportDate":""}
         */

        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"notes\": \"لا يوجد اى ملاحظات\",";
        body +="\r\n    \"isApprovalRecommendation\": null,\r\n    \"transactionExportDate\": \"\"\r\n}";
        //System.out.println(body);
        return body;

    }

    public String getdefictBody(Map<String, String> map)
    {
        /**
         * {
         * 	"requestId": "3127",
         * 	"deficitReasonCodeId": 11,
         * 	"deficitHijriDate": "1444-11-10",
         * 	"reportNumber": "400",
         * 	"issueReportHijriDate": "1444-11-10",
         * 	"issuerReportOrganization": "الحرب العالميه الاولى",
         * 	"deficitDetails": "بدايه 1922"
         * }
         */

        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"deficitReasonCodeId\": 11,";
        body +="\r\n    \"deficitHijriDate\": \"1441-10-05\",";
        body +="\r\n    \"reportNumber\": \"808\",";
        body +="\r\n    \"issueReportHijriDate\":  \"1444-10-05\",";
        body +="\r\n    \"issuerReportOrganization\": \"الحرب العالميه الاولى\",";
        body +="\r\n    \"deficitDetails\": \"اتمته\"\r\n}";

        return body;

    }

    public String getLostBody(Map<String, String> map)
    {
        /**
         * {
         * 	"requestId": "3149",
         * 	"lossTypeCodeId": 33,
         * 	"certificationHijriDate": "1444-11-09",
         * 	"requiredPeriodToConsiderDeadInMonth": "09",
         * 	"certificationNumber": "778",
         * 	"lossHijriDate": "1444-11-04",
         * 	"certificationIssuePlace": "اليمن"
         * }
         */

        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"lossTypeCodeId\": 33,";
        body +="\r\n    \"certificationHijriDate\": \"1441-10-05\",";
        body +="\r\n    \"requiredPeriodToConsiderDeadInMonth\": \"808\",";
        body +="\r\n    \"certificationNumber\": \"808\",";
        body +="\r\n    \"issueReportHijriDate\":  \"1444-10-05\",";
        body +="\r\n    \"lossHijriDate\": \"1441-10-05\",";
        body +="\r\n    \"certificationIssuePlace\": \"الحرب العالميه الاولى\"\r\n}";

        return body;

    }

    public String getDeathInfoBody(Map<String, String> map)
    {
        /**
         * {
         * 	"requestId": "3126",
         * 	"deathReasonCodeId": 31,
         * 	"deathHijriDate": "1444-11-03",
         * 	"certificationHijriDate": "1444-11-04",
         * 	"certificationNumber": "778",
         * 	"certificationIssuePlace": "الحرب"
         * }
         */

        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"deathReasonCodeId\": 31,";
        body +="\r\n    \"deathHijriDate\": \"1441-10-05\",";
        body +="\r\n    \"certificationHijriDate\":  \"1444-10-05\",";
        body +="\r\n    \"certificationNumber\": \"808\",";
        body +="\r\n    \"certificationIssuePlace\": \"الحرب العالميه الاولى\"\r\n}";

        return body;

    }

    public String getJobCategoryBody(Map<String, String> map,String extraServiceID )
    {

        /**
         * {
         *     "requestId": "3197",
         *     "extraServicesId": 267,
         *     "jobCategoryCodeId": 211,
         *     "periodCodeId": 11,
         *     "periodAdditionCodeId": 1,
         *     "periodStartDate": "1441-10-11",
         *     "periodEndDate": "1442-10-24"
         * }
         */
        String body = "{\r\n    \"requestId\": \""+map.get("ReqNo")+"\",\r\n    \"extraServicesId\": "+extraServiceID+",";
        body +="\r\n    \"jobCategoryCodeId\": 211,\r\n    \"periodCodeId\": 11,";
        body +="\r\n    \"periodAdditionCodeId\": 1,";
        body +="\r\n    \"periodStartDate\": \"1441-10-05\",";
        body +="\r\n    \"periodEndDate\":  \"1444-10-05\"\r\n}";

        return body;

    }

    public String fillRequestDetails(Map<String, String> map) throws IOException {

        String retValue ="";
        //AddCurrentService
        System.out.println("**** Add Current Service *****");
        retValue +=sendRequest(map,"AddCurrentService",getCurrentServiceBody(map));

        if(retValue.equals("-1")) {
            System.out.println("Failed Adding Current Service:\t"+map.get("ReqNo"));
            return "-1";
        }
        System.out.println("**** Add Extra Services *****");

        //AddExtraServices - if return -1, then neglect other services call
        //Modified due to adding automatic adding extra service
        /*
        //retValue =sendRequest(map,"AddExtraServices",getExtraServiceBody(map));
        if(retValue.equals("-1")) {
            System.out.println("Failed Add Extra Services:\t"+map.get("ReqNo"));
            return "-1";
        }
        //System.out.println("Adding Extra Service:\t"+retValue);
        //return ExtraService - return value from DB
         */

        String extraserviceID = ManipulateDB.executeQuery("select id from ExtraServices where RequestId = "+map.get("ReqNo"),"id");

        if (extraserviceID.equals("-1")){
            System.out.println("Failed Add Extra Services:\t"+map.get("ReqNo"));
            return "-1";
        }
        System.out.println("**** Add Salary Record *****");
        //add-salary-record
        retValue =sendRequest(map,"AddSalaryRecord",getSalaryRecordBody(map,extraserviceID));
        //AddJobCategory
        if(retValue.equals("-1")) {
            System.out.println("Failed Add AddSalaryRecord Services:\t"+map.get("ReqNo"));
            return "-1";
        }

        /*
        *
        *
        System.out.println("**** Add Job Category *****");
        retValue =sendRequest(map,"AddJobCategory",getJobCategoryBody(map,extraserviceID));

        if(retValue.equals("-1")) {
            System.out.println("Failed Add AddJobCategory Services:\t"+map.get("ReqNo"));
            return "-1";
        }
        */
        String Reason = map.get("TerminationID");

        if(Reason.equals("201")||Reason.equals("202")||Reason.equals("203"))
            retValue =sendRequest(map,"defict-medical-info",getdefictBody(map));

        if(Reason.equals("301")||Reason.equals("302")||Reason.equals("303"))
            retValue =sendRequest(map,"defict-medical-info",getdefictBody(map));

        if(Reason.equals("503"))
            retValue =sendRequest(map,"loss-info",getLostBody(map));

        if(Reason.equals("401")||Reason.equals("402")||Reason.equals("403"))
            retValue =sendRequest(map,"death-info",getDeathInfoBody(map));

        return retValue;
    }

    public String addAttachmentToRequest(Map<String, String> map) throws IOException {

        String retValue ="";

        System.out.println("**** Add Template 11 to request ***** \t");
        //add attachment Type 11
        retValue =AddAttchment(map,"create-request-form-attachment",getAddAttchmentBody(map,11));

        if(retValue.equals("-1")) {
            System.out.println("Failed Add template:\t"+map.get("ReqNo"));
            return "-1";
        }
        System.out.println("**** Add Template 13 to request ***** \t");
        //add attachment Type 13
        retValue =AddAttchment(map,"create-request-form-attachment",getAddAttchmentBody(map,13));

        if(retValue.equals("-1")) {
            System.out.println("Failed Add template:\t"+map.get("ReqNo"));
            return "-1";
        }
        System.out.println("**** Add Template 9 to request ***** \t");
        retValue =AddAttchment(map,"create-request-form-attachment",getAddAttchmentBody(map,9));

        if(retValue.equals("-1")) {
            System.out.println("Failed Add template:\t"+map.get("ReqNo"));
            return "-1";
        }


        return retValue;
    }

    public String submitValidatorRequest(Map<String, String> map) throws IOException {

        String retValue ="";

        System.out.println("**** Submit Request from Validator ***** \t");
        //add attachment Type 11
        retValue =submitValidatorReq(map,"submitValidator",getSubmitValidatorBody(map));

        if(retValue.equals("-1")) {
            System.out.println("Failed Add template:\t"+map.get("ReqNo"));
            return "-1";
        }

        return retValue;
    }


    public String submitSupervisorRequest(Map<String, String> map) throws IOException {

        String retValue ="";

        System.out.println("**** Submit Officer Request ***** \t");
        //add attachment Type 11
        retValue = submitSupervisorReq(map,"submitSupervisorRequest",getSubmitSupervisorBody(map));

        if(retValue.equals("-1")) {
            System.out.println("Failed Add template:\t"+map.get("ReqNo"));
            return "-1";
        }

        return retValue;
    }

    public String submitRequest(Map<String, String> map) throws IOException {
        String retValue = "";
        retValue = sendRequest(map,"submitRequest","\""+map.get("ReqNo")+"\"");
        return retValue;
    }

    public static void main(String[]args) throws IOException, ParseException, InterruptedException {
        addReqDetails obj = new addReqDetails();

    }


}
