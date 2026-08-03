package org.purplebean.kmip.model.v2x1.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ObjectTypes Domain Tests")
class ObjectTypesTest extends AbstractKmipStructureTestSuite<ObjectTypes> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ObjectTypes> type() {
    return ObjectTypes.class;
  }

  @Override
  protected ObjectTypes createDefault() {
    return ObjectTypes.of(List.of(ObjectType.Standard.CERTIFICATE.inst()));
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