package com.es.esspringstu.canal.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "users")
public class UserEsDocument {

    @Id
    private Integer id;

    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Keyword)
    private String email;

    // 对于密码，通常不会存储明文，这里仅作演示，实际项目中可能不需要存储密码字段
    @Field(type = FieldType.Text, index = false)
    private String password;

    @Field(type = FieldType.Text)
    private String firstName;

    @Field(type = FieldType.Text)
    private String lastName;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
    private LocalDateTime updatedAt;
}