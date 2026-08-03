package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v2x1.enumeration.ProcessingStage;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousRequest;
import org.purplebean.kmip.model.v2x1.type.SubmissionDate;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AsynchronousRequest Ttlv Serialization Tests")
class AsynchronousRequestTtlvTest extends AbstractTtlvSerializationTestSuite<AsynchronousRequest> {

  @Override
  public Class<AsynchronousRequest> type() {
    return AsynchronousRequest.class;
  }

  @Override
  public AsynchronousRequest createDefault() {
    return AsynchronousRequest.of(
        AsynchronousCorrelationValue.of(new byte[] {0x01}),
        Operation.Standard.CREATE.inst(),
        SubmissionDate.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)),
        ProcessingStage.Standard.SUBMITTED.inst());
  }

  @Override
  public AsynchronousRequest createVariant() {
    return AsynchronousRequest.of(
        AsynchronousCorrelationValue.of(new byte[] {0x02}),
        Operation.Standard.GET.inst(),
        SubmissionDate.of(OffsetDateTime.of(2024, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC)),
        ProcessingStage.Standard.SUBMITTED.inst());
  }
}