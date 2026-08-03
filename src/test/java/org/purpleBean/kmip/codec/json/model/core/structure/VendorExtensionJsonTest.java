package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.TtlvDataType;
import org.purplebean.kmip.model.core.structure.VendorExtension;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("VendorExtension Json Serialization Tests")
class VendorExtensionJsonTest extends AbstractJsonSerializationTestSuite<VendorExtension> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<VendorExtension> type() {
    return VendorExtension.class;
  }

  @Override
  public VendorExtension createDefault() {
    return VendorExtension
        .builder()
        .ttlvDataType(TtlvDataType
            .builder()
            .kmipTag(KmipTag.Standard.UNIQUE_IDENTIFIER.inst())
            .encodingType(EncodingType.TEXT_STRING)
            .value("test-value")
            .build())
        .build();
  }

  @Override
  public VendorExtension createVariant() {
    return VendorExtension
        .builder()
        .ttlvDataType(TtlvDataType
            .builder()
            .kmipTag(KmipTag.Standard.ATTRIBUTE_NAME.inst())
            .encodingType(EncodingType.TEXT_STRING)
            .value("variant-value")
            .build())
        .build();
  }
}
