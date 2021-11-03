package com.turnitin.__temp_lower__.dao;

import com.turnitin.commons.db.dynamo.Persistable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SimpleRecord implements Persistable {
    @DynamoDBHashKey(attributeName = "pk")
    private String pk;
    @DynamoDBRangeKey(attributeName = "sk")
    private String sk;

    @DynamoDBAttribute(attributeName = "type")
    private String type;
    @DynamoDBAttribute(attributeName = "subType")
    private String subType;

    @DynamoDBAttribute(attributeName = "created")
    private long created;

    @DynamoDBAttribute(attributeName = "data")
    private String data;
}
