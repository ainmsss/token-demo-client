import com.alibaba.fastjson.JSON;
import model.Token;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Main {

    static String getApplyInfo(){
        String params = "{\n" +
                "    \"firstNameEnglish\": \"Xiu Lian\",\n" +
                "    \"lastNameEnglish\": \"Lee\",\n" +
                "    \"firstNameChinese\": \"秀莲\",\n" +
                "    \"lastNameChinese\": \"李\",\n" +
                "    \"formerFirstNameEnglish\": \"Xiu Lian\",\n" +
                "    \"formerLastNameEnglish\": \"Li\",\n" +
                "    \"formerFirstNameChinese\": \"绣莲\",\n" +
                "    \"formerLastNameChinese\": \"李\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"dateOfBirth\": \"1980-12-31\",\n" +
                "    \"placeOfBirth\": \"Shenzhen\",\n" +
                "    \"maritalStatus\": \"Married\",\n" +
                "    \"identityNumber\": \"622825199303180010\",\n" +
                "    \"nationality\": \"Chinese\",\n" +
                "    \"ethnicity\": \"Han\",\n" +
                "    \"ancestralHome\": \"Suzhou\",\n" +
                "    \"domicile\": \"1881 Bao'an S Rd, CaiWuWei, Luohu Qu, Shenzhen Shi, Guangdong Sheng, China, 518000\",\n" +
                "    \"photoUrl\": \"\",\n" +
                "    \"passport\": {\n" +
                "      \"number\": \"G05473471\",\n" +
                "      \"type\": \"P - Ordinary\",\n" +
                "      \"placeOfIssue\": \"Beijing\",\n" +
                "      \"pictureUrl\": \"\"\n" +
                "    },\n" +
                "    \"visa\": {\n" +
                "      \"category\": \"Tourist Visa\",\n" +
                "      \"type\": \"Single Entry Visa\"\n" +
                "    },\n" +
                "    \"phoneNumber\": \"+86 13522568027\",\n" +
                "    \"email\": \"xiulian@qq.com\"\n" +
                "    \"creditscore\": \"0\"\n" +
                "}";
        return params;
    }

    public static void main(String[] args) {
        String content = getToken();
        Token token = JSON.parseObject(content, Token.class);
        System.out.println(token.getAccess_token());
        String result = applicants(token.getClient_id(), token.getAccess_token());
        System.out.println(result);
//        System.out.println(getToken());
    }

    private static String getToken() {
        String url = "http://39.108.144.152:8100/api/token?client_id=123456&client_secret=123abc";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        try {
            CloseableHttpResponse response = client.execute(get);
            return IOUtils.toString(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String applicants(String client_id, String token) {
        String applyInfo = getApplyInfo();
        String url = "http://39.108.144.152:8100/api/applicants?client_id=" + client_id + "&access_token=" + token;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        StringEntity requestEntity = new StringEntity(applyInfo,"utf-8");
        requestEntity.setContentEncoding("UTF-8");
        post.setHeader("Content-type", "application/json");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        post.setEntity(requestEntity);
        try {
            CloseableHttpResponse response = client.execute(post);
            return IOUtils.toString(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
