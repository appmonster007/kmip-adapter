package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;
import org.purplebean.kmip.model.core.structure.AlternativeName;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AlternativeName Json Serialization Tests")
class AlternativeNameJsonTest extends AbstractJsonSerializationTestSuite<AlternativeName> {

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