package com.es.esspringstu;

import com.es.esspringstu.canal.empty.User;
import com.es.esspringstu.canal.es.UserEsDocument;
import com.es.esspringstu.canal.service.UserService;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {EsSpringStuApplication.class},
        properties = {"app.rabbitmq.enable=false"})
class EsSpringStuApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    ElasticsearchRestTemplate template;

    @Test
    void contextLoads() {
//        service.getAllInfo();
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i <= 100_000; i++) {
//            for (int j = 0; j < 10000; j++) {
            String str = String.valueOf(i);
            User user = new User();
            user.setUserId("name" + i);
            user.setEmail("name" + i);
            user.setPassword(str);
            user.setFirstName(str);
            user.setLastName(str);
            user.setUsername(str);
            list.add(user);
            // 直接发送还是没经过canal
//            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPICS_INFORM, MQRoutingKeyEnum.SEND_FENPEI_CANAL.getRoutingKey(), JSON.toJSONString(user));
//            }
//            list.clear();
        }
        userService.saveBatch(list);
    }

    @Test
    void test1(){
        // 查询users索引的数据
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();

        String query = null; // 假设我们按username字段进行匹配
        // 如果要执行全文本搜索
        if (StringUtils.isNotBlank(query)) {
            QueryBuilders.matchQuery("username", query); // 假设我们按username字段进行匹配
            searchQuery.withQuery(QueryBuilders.matchQuery("username", query));
        }

        // 添加排序、过滤条件等
        // searchQuery.withSort(SortBuilders.fieldSort("created_at").order(SortOrder.DESC));

        // 执行搜索
        SearchHits<UserEsDocument> searchHits = template.search(searchQuery.build(), UserEsDocument.class, IndexCoordinates.of("users"));

        // 将搜索结果转换为User对象列表
        List<UserEsDocument> users = new ArrayList<>();
        for (SearchHit<UserEsDocument> hit : searchHits.getSearchHits()) {
            users.add(hit.getContent());
        }
        System.out.println(users);
    }

}
