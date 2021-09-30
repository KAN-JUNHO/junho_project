package com.book.demo.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("counts")
public class Count {

    @Id
    private MessageType type;
    private String sender;
    private String content;
    private int cnt;

    public Count(MessageType type, String sender, String content, int cnt) {
        this.type = type;
        this.sender = sender;
        this.content = content;
        this.cnt = cnt;
    }

    public Count() {}


    public int getCnt(){
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }



    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
