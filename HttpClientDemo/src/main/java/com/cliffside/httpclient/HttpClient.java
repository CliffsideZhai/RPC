package com.cliffside.httpclient;

import com.cliffside.bean.User;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cliffside
 * @date 2020-10-28 10:26
 */
public class HttpClient {
    private static final long serialVersionUID = -6437800749411518984L;


    @Test
    public void testDemo() {
        try {
            //第一步、创建http工具（类似浏览器，可以发送请求，解析响应）
            CloseableHttpClient httpClient= HttpClients.createDefault();
            //第二部、确定请求路径
            //URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/demo?param=你弟弟");
            //设置参数不仅可以从uri里直接设置，还可以通过kv来
            URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/demo");
            uriBuilder.addParameter("param","哈哈哈哈哈哈哈哈哈");

            //第三步：创建httpGet请求对象,需要放一个参数
            HttpGet get = new HttpGet(uriBuilder.build());

            //第四步：创建响应对象，
            CloseableHttpResponse httpResponse = httpClient.execute(get);

            //通过响应对象获取结果
            //由于响应体是个字符串，因此把HttpEntity类型转换为字符串类型，并设置字符集编码
            String s = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            //输出结果
            System.out.println(s);

            //释放资源,很重要，别忘记
            httpResponse.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDemo01(){
        try {
            //1、创建http工具，发送请求，解析响应
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/demo");

            //2、创建请求对象
            HttpPost post = new HttpPost(uriBuilder.build());

            //2.1 创建请求参数,NameValuePair是个接口，BasicNameValuePair是具体实现对象
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("param","好兄弟"));
            params.add(new BasicNameValuePair("param","你好啊"));
            //2.2创建HttpEntity接口的文本实现类对象，放入参数并设置编码，UrlEncodedFormEntity也是实现对象，这里都很好的用到了多态
            HttpEntity entity = new UrlEncodedFormEntity(params,"utf-8");

            //2.3放入HttpPost的对象中
            post.setEntity(entity);

            //3、创建响应对象
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(post);

            //4、由于响应体是字符串，因此把HttpEntity类型转换为字符串类型。
            String s = EntityUtils.toString(httpResponse.getEntity());

            //输出结果释放资源
            System.out.println(s);
            closeableHttpClient.close();
            httpResponse.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 响应对象转换为json格式的字符串
     */
    @Test
    public void test02() {
        try {
            //1.创建http工具，
            CloseableHttpClient httpClient = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/demo2");

            //2.创建httpPost请求对象
            HttpPost httpPost = new HttpPost(uriBuilder.build());

            //3、创建参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("id","1223"));
            list.add(new BasicNameValuePair("name","王五"));

            HttpEntity httpEntity = new UrlEncodedFormEntity(list,"utf-8");

            httpPost .setEntity(httpEntity);
            //4.获取响应对象
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
            String s = EntityUtils.toString(closeableHttpResponse.getEntity());
            System.out.println(s);

            //jackson把字符串转换成对象
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(s,User.class);
            System.out.println(user);

            //jackson把对象转为字符串
            String s1 = objectMapper.writeValueAsString(user);
            System.out.println(s1);


            httpClient.close();
            closeableHttpResponse.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo03(){
        try {
            //1.创建http工具
            CloseableHttpClient client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/demo03");
            //2.创建http请求工具
            HttpPost post = new HttpPost(uriBuilder.build());

            //3.创建响应工具
            CloseableHttpResponse response = client.execute(post);

            String s = EntityUtils.toString(response.getEntity());
            System.out.println(s);

            //使用jackson取list
            ObjectMapper objectMapper = new ObjectMapper();
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, User.class);
            List<User> list = objectMapper.readValue(s,javaType);

            System.out.println(list);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
