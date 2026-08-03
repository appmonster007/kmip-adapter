package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.VendorIdentification;
import org.purpleBean.kmip.model.v2_1.structure.AttributeReference;

public class AttributeReferenceXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AttributeReference,
        AttributeReference.AttributeReferenceBuilder> {

  public AttributeReferenceXmlDeserializer() {
    super(AttributeReference.kmipTag, AttributeReference.encodingType);
  }

  @Override
  protected AttributeReference.AttributeReferenceBuilder createBuilder() {
    return AttributeReference.builder();
  }

  @Override
  protected void setValue(AttributeReference.AttributeReferenceBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.VENDOR_IDENTIFICATION ->
          builder.vendorIdentification(ctxt.readValue(p, VendorIdentification.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(ctxt.readValue(p, AttributeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AttributeReference build(AttributeReference.AttributeReferenceBuilder builder) {
    return builder.build();
  }
}