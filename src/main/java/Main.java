import com.alibaba.fastjson.JSON;
import model.Token;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main {

    static String getApplyInfo(){
        String content = "{" +
                "\"firstNameEnglish\": \"Xiu Lian\", " +
                "\"lastNameEnglish\": \"Lee\", " +
                "\"firstNameChinese\": \"秀莲\", " +
                "\"lastNameChinese\": \"李\", " +
                "\"formerFirstNameEnglish\": \"Xiu Lian\", " +
                "\"formerLastNameEnglish\": \"Li\", " +
                "\"formerFirstNameChinese\": \"绣莲\", " +
                "\"formerLastNameChinese\": \"李\", " +
                "\"gender\": \"Female\",\n" +
                "\"dateOfBirth\": \"1980-12-31\", " +
                "\"placeOfBirth\": \"Shenzhen\", " +
                "\"maritalStatus\": \"Married\", " +
                "\"identityNumber\": \"622825199303180010\"," +
                "\"nationality\": \"Chinese\",\n" +
                "\"ethnicity\": \"Han\",\n" +
                "\"ancestralHome\": \"Suzhou\",\n" +
                "\"domicile\": \"1881 Bao'an S Rd, CaiWuWei, Luohu Qu, Shenzhen Shi, Guangdong Sheng,\n" +
                "China, 518000\",\n" +
                "\"photoUrl\": \"\", " +
                "\"passport\": {\n" +
                "\"number\": \"G05473471\",\n" +
                "\"type\": \"P - Ordinary\",\n" +
                "\"placeOfIssue\": \"Beijing\", \"pictureUrl\": \"\"\n" +
                "}, " +
                "\"visa\": {\n" +
                "\"category\": \"Tourist Visa\",\n" +
                " \"type\": \"Single Entry Visa\"\n" +
                "}\n" +
                "},\n" +
                "\"phoneNumber\": \"+86 13522568027\", " +
                "\"email\": \"xiulian@qq.com\"";
        return content;
    }

    public static void main(String[] args) {
        String content = getToken();
        Token token = JSON.parseObject(content, Token.class);
        System.out.println(token.getAccess_token());
        String result = applicants(token.getAccess_token());
        System.out.println(result);
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

    private static String applicants(String token) {
        String content = getApplyInfo();
        String url = "http://39.108.144.152:8100/api/applicants?client_id=123456&access_token=" + token + "&title=test&content=" + content;
        String u = null;
        try {
            u = URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url = u.replace("%3F", "?").replace("%26", "&").replace("%3D", "=").replace("%2F", "/").replace("%3A", ":").replace("%25","%").replace("%2B","+").replace("%2C",",");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        try {
            CloseableHttpResponse response = client.execute(post);
            return IOUtils.toString(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
