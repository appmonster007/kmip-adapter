package org.purpleBean.kmip.codec.json.model.v1_2.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseBatchItem;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResponseBatchItem Json Serialization Tests")
class ResponseBatchItemJsonTest extends AbstractJsonSerializationTestSuite<ResponseBatchItem> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ResponseBatchItem> type() {
    return ResponseBatchItem.class;
  }

  @Override
  public ResponseBatchItem createDefault() {
    return ResponseBatchItem
        .builder()
        .operation(Operation.of(Operation.Standard.CREATE))
        .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
        .build();
  }

  @Override
  public ResponseBatchItem createVariant() {
    return ResponseBatchItem
        .builder()
        .operation(Operation.of(Operation.Standard.GET))
        .resultStatus(ResultStatus.of(ResultStatus.Standard.OPERATION_FAILED))
        .build();
  }
}
