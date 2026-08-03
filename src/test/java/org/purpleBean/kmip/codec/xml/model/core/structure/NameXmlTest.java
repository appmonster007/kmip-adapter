package org.purplebean.kmip.codec.xml.model.core.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.model.core.structure.Name;
import org.purplebean.kmip.model.core.type.NameValue;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Name Xml Serialization Tests")
class NameXmlTest extends AbstractXmlSerializationTestSuite<Name> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<Name> type() {
    return Name.class;
  }

  @Override
  public Name createDefault() {
    return Name
        .builder()
        .nameValue(NameValue.of("some-name"))
        .nameType(NameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
        .build();
  }

  @Override
  public Name createVariant() {
    return Name
        .builder()
        .nameValue(NameValue.of("some-variant-name"))
        .nameType(NameType.Standard.URI.inst())
        .build();
  }
}
