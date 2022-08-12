//TEMP if cookiecutter.include_s3_listener is selected_option:
package com.turnitin.__TEMP__project_name_lower_caseTEMP__;


import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.turnitin.commons.TurnitinContext;
import com.turnitin.commons.lambda.S3SqsLambda;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Slf4j
public class __TEMP__project_name_pascal_caseTEMP__S3 extends S3SqsLambda {

    // This constructor is used in the reculat flow
    public __TEMP__project_name_pascal_caseTEMP__S3() {
        ctx = TurnitinContext.builder()
                .defaultS3Client()
                .defaultDlqHandling()
                .build();
    }

    // This constructor is used by the tests so they can mock the context (or parts thereof) of needs be
    public __TEMP__project_name_pascal_caseTEMP__S3(TurnitinContext ctx) {
        this.ctx = ctx;
    }

    protected void handleMessage(final S3EventNotification.S3EventNotificationRecord s3EventNotificationRecord) {
        // your code goes here
        log.info("different logging levels are now supported!");
        String bucket = s3EventNotificationRecord.getS3().getBucket().getName();
        String key = s3EventNotificationRecord.getS3().getObject().getKey();
        log.debug("Got S3 event, bucket={}, key={}", bucket, key);
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .key(key)
                .bucket(bucket)
                .build();

        GetObjectResponse s3ObjectResponse = ctx.getS3client().getObject(getObjectRequest).response();
    }
}
//TEMP endif
