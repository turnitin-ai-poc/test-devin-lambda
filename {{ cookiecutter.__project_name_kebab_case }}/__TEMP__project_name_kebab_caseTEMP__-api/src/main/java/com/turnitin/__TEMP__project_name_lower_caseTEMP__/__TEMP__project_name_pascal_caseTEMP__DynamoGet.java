//TEMP if cookiecutter.include_dynamodb is selected_option:
package com.turnitin.__TEMP__project_name_lower_caseTEMP__;

import com.turnitin.__TEMP__project_name_lower_caseTEMP__.dao.SimpleRecord;
import com.turnitin.commons.TurnitinContext;
import com.turnitin.commons.db.dynamo.Dao;
import com.turnitin.commons.http.HttpMethod;
import com.turnitin.commons.lambda.ApiGatewayLambda;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class __TEMP__project_name_pascal_caseTEMP__DynamoGet extends ApiGatewayLambda<List<SimpleRecord>> {
    static final String DYNAMO_TABLE = "dynamo_table";

    private final Dao dao;

    // This constructor is for regular flow
    public __TEMP__project_name_pascal_caseTEMP__DynamoGet() {
        this.ctx = TurnitinContext.builder()
                .addEnvironmentVariable(DYNAMO_TABLE)
                .build();
        this.dao = new Dao(ctx.getVariable(DYNAMO_TABLE), SimpleRecord.class);
        // Optional: Invoking a simple api here to pre-warm the application
        dao.findByPkAndSk("warmup", "warmup");
    }

    // This Constructor is use in tests if you want to mock the context or parts there of.
    public __TEMP__project_name_pascal_caseTEMP__DynamoGet(TurnitinContext ctx, Dao mockedDao) {
        this.ctx = ctx;
        this.dao = mockedDao;
    }

    @Override
    protected List<SimpleRecord> handleMethod() throws Exception {
        log.info("input path: {}", input.getPath());

        String dbkey = ctx.getKeyFromPathParams(input, "dbkey");
        String value = ctx.getKeyFromPathParams(input, "value");
        log.debug("dbkey: {}, value: {}", dbkey, value);

        if (dbkey != null && !dbkey.isEmpty()) {
            List<SimpleRecord> resultRaw = dao.findAllByPk(dbkey);
            return resultRaw;
        } else {
            throw new Exception("unsupported query");
        }
    }

    @Override
    protected List<String> getSupportedHttpMethods() {
        return Arrays.asList(HttpMethod.GET.name());
    }


}
//TEMP endif
