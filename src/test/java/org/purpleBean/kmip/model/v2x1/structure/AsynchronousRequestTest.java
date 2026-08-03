package org.purpleBean.kmip.model.v2x1.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v2x1.enumeration.ProcessingStage;
import org.purpleBean.kmip.model.v2x1.type.SubmissionDate;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("AsynchronousRequest Domain Tests")
class AsynchronousRequestTest extends AbstractKmipStructureTestSuite<AsynchronousRequest> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AsynchronousRequest> type() {
    return AsynchronousRequest.class;
  }

  @Override
  protected AsynchronousRequest createDefault() {
    return AsynchronousRequest.of(
        AsynchronousCorrelationValue.of(new byte[] {0x01}),
        Operation.Standard.CREATE.inst(),
        SubmissionDate.of(
            java.time.OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, java.time.ZoneOffset.UTC)),
        ProcessingStage.Standard.SUBMITTED.inst());
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    // TODO: Set the expected minimum number of components
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // TODO: Validate the components of the structure
    // assertThat(values).hasSize(0);
  }
}