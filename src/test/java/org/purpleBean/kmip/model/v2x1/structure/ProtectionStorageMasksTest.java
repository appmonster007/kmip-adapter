package org.purpleBean.kmip.model.v2x1.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2x1.type.ProtectionStorageMask;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ProtectionStorageMasks Domain Tests")
class ProtectionStorageMasksTest extends AbstractKmipStructureTestSuite<ProtectionStorageMasks> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ProtectionStorageMasks> type() {
    return ProtectionStorageMasks.class;
  }

  @Override
  protected ProtectionStorageMasks createDefault() {
    return ProtectionStorageMasks.of(List.of(ProtectionStorageMask.of(1)));
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