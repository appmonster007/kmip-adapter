package org.purplebean.kmip.model.core;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("TtlvDataType Domain Tests")
class TtlvDataTypeTest extends AbstractKmipDataTypeTestSuite<TtlvDataType> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<TtlvDataType> type() {
    return TtlvDataType.class;
  }

  @Override
  protected TtlvDataType createDefault() {
    return TtlvDataType
        .builder()
        .kmipTag(UniqueIdentifier.kmipTag)
        .encodingType(UniqueIdentifier.encodingType)
        .value("default-string")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}