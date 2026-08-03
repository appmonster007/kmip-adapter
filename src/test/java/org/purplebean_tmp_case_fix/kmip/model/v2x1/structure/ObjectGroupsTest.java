package org.purplebean.kmip.model.v2x1.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.ObjectGroup;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ObjectGroups Domain Tests")
class ObjectGroupsTest extends AbstractKmipStructureTestSuite<ObjectGroups> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ObjectGroups> type() {
    return ObjectGroups.class;
  }

  @Override
  protected ObjectGroups createDefault() {
    return ObjectGroups.of(List.of(ObjectGroup.of("test-group")));
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