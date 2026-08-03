package org.purpleBean.kmip.codec.json.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.CancelOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CancelOpResponsePayload Json Serialization Tests")
class CancelOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CancelOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CancelOpResponsePayload> type() {
    return CancelOpResponsePayload.class;
  }

  @Override
  public CancelOpResponsePayload createDefault() {
    return CancelOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[] {1, 2, 3}))
        .cancellationResult(CancellationResult.of(CancellationResult.Standard.CANCELED))
        .build();
  }

  @Override
  public CancelOpResponsePayload createVariant() {
    return CancelOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[] {4, 5, 6}))
        .cancellationResult(CancellationResult.of(CancellationResult.Standard.UNABLE_TO_CANCEL))
        .build();
  }
}
