package com.turnitin;

import com.turnitin.commons.TurnitinContextCustomizer;
import com.turnitin.commons.db.dyanmo.Dao;
import com.turnitin.commons.lambda.ApiGatewayLambda;
import com.turnitin.dao.SimpleRecord;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class $TempDynamoGet extends ApiGatewayLambda<List<SimpleRecord>> {
	static final String DYNAMO_TABLE = "dynamo_table";

	private final Dao dao;

	public $TempDynamoGet() {
		this.ctx = TurnitinContextCustomizer.customizeInstance()
				.addEnvironmentVariable(DYNAMO_TABLE)
				.getInstance();
		this.dao = new Dao(ctx.getVariable(DYNAMO_TABLE));
	}

	@Override
	protected List<SimpleRecord> handleMethod() throws Exception {
		log.info("input path: {}", input.getPath());

		String dbkey = ctx.getKeyFromPathParams(input, "dbkey");
		String value = ctx.getKeyFromPathParams(input, "value");
		log.debug("dbkey: {}, value: {}", dbkey, value);

		if (dbkey != null && !dbkey.isEmpty()) {
			List<SimpleRecord> resultRaw = dao.findAllByPk(dbkey, SimpleRecord.class)
					.stream()
					.filter(rme -> "type".equals(rme.getType()))
					.collect(Collectors.toList());
			return resultRaw
		} else {
			throw new Exception("unsupported query");
		}
	}

	@Override
	protected List<String> getSupportedHttpMethods() {
		return Arrays.asList(HttpMethod.GET);
	}


}
