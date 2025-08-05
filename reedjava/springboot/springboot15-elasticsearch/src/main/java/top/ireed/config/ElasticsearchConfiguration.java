package top.ireed.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能简述:〈es 配置〉
 *
 * @author reedsource
 * date 2024/3/8 16:58
 * reedsource@189.cn
 */
@Configuration
public class ElasticsearchConfiguration {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    /**
     * 连接超时
     */
    @Value("${elasticsearch.connTimeout}")
    private int connTimeout;

    /**
     * 套接字超时
     */
    @Value("${elasticsearch.socketTimeout}")
    private int socketTimeout;

    /**
     * 连接请求超时
     */
    @Value("${elasticsearch.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    @Bean(destroyMethod = "close", name = "client")
    public RestHighLevelClient initRestClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port))
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                        .setConnectTimeout(connTimeout)
                        .setSocketTimeout(socketTimeout)
                        .setConnectionRequestTimeout(connectionRequestTimeout));
        return new RestHighLevelClient(builder);
    }
}
