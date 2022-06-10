package com.turnitin.__temp_lower__;

import com.turnitin.__temp_lower__.dao.SimpleRecord;
import com.turnitin.commons.TurnitinContext;
import com.turnitin.commons.db.dynamo.Dao;
import com.turnitin.commons.http.HttpMethod;
import com.turnitin.commons.lambda.ApiGatewayLambda;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class __temp_title__DynamoPost extends ApiGatewayLambda<SimpleRecord> {
    static final String DYNAMO_TABLE = "dynamo_table";

    private final Dao dao;

    // This constructor is for regular flow
    public __temp_title__DynamoPost() {
        this.ctx = TurnitinContext.builder()
                .addEnvironmentVariable(DYNAMO_TABLE)
                .build();
        this.dao = new Dao(ctx.getVariable(DYNAMO_TABLE), SimpleRecord.class);
        // Optional: Invoking a simple api here to pre-warm the application
        dao.findByPkAndSk("warmup", "warmup");
    }

    // This Constructor is use in tests if you want to mock the context or parts there of.
    public __temp_title__DynamoPost(TurnitinContext ctx, Dao mockedDao) {
        this.ctx = ctx;
        this.dao = mockedDao;
    }

    @Override
    protected SimpleRecord handleMethod() throws Exception {
        log.info("input path: {}", input.getPath());

        String dbkey = ctx.getKeyFromPathParams(input, "dbkey");
        String value = ctx.getKeyFromPathParams(input, "value");
        log.debug("dbkey: {}, value: {}", dbkey, value);

        if (dbkey != null && !dbkey.isEmpty()) {

            log.debug("POST body: {}", input.getBody());
            String body = input.getBody();
            SimpleRecord simpleRecord = SimpleRecord.builder()
                    .pk(dbkey)
                    .sk(value)
                    .created(System.currentTimeMillis())
                    .data(body)
                    .build();
            dao.save(simpleRecord);
            return simpleRecord;
        } else {
            throw new Exception("unsupported query");
        }
    }

    @Override
    protected List<String> getSupportedHttpMethods() {
        return Arrays.asList(HttpMethod.POST.name());
    }


}
