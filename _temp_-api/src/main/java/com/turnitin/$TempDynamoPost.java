package com.turnitin;

import com.turnitin.commons.TurnitinContextCustomizer;
import com.turnitin.commons.db.dyanmo.Dao;
import com.turnitin.commons.lambda.ApiGatewayLambda;
import com.turnitin.dao.SimpleRecord;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class $TempDynamoPost extends ApiGatewayLambda<SimpleRecord> {
	static final String DYNAMO_TABLE = "dynamo_table";

	private final Dao dao;

	public $TempDynamoPost() {
		this.ctx = TurnitinContextCustomizer.customizeInstance()
				.addEnvironmentVariable(DYNAMO_TABLE)
				.getInstance();
		this.dao = new Dao(ctx.getVariable(DYNAMO_TABLE));
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
					.type("type")
					.subType("subtype")
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
		return Arrays.asList(HttpMethod.POST);
	}


}
