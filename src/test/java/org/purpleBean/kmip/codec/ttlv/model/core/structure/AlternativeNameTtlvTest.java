package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;
import org.purplebean.kmip.model.core.structure.AlternativeName;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AlternativeName Ttlv Serialization Tests")
class AlternativeNameTtlvTest extends AbstractTtlvSerializationTestSuite<AlternativeName> {

  @Override
  public Class<AlternativeName> type() {
    return AlternativeName.class;
  }

  @Override
  public AlternativeName createDefault() {
    return AlternativeName
        .builder()
        .alternativeNameValue(AlternativeNameValue.of("some-name"))
        .alternativeNameType(AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
        .build();
  }
}