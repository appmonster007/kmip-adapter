package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.model.v2x1.structure.Attribute;

public class AttributeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Attribute, Attribute.AttributeBuilder> {

  public AttributeXmlDeserializer() {
    super(Attribute.kmipTag, Attribute.encodingType);
  }

  @Override
  protected Attribute.AttributeBuilder createBuilder() {
    return Attribute.builder();
  }

  @Override
  protected void setValue(Attribute.AttributeBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.VENDOR_IDENTIFICATION ->
          builder.vendorIdentification(ctxt.readValue(p, VendorIdentification.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(ctxt.readValue(p, AttributeName.class));
      case KmipTag.Standard.ATTRIBUTE_VALUE ->
          builder.attributeValue(ctxt.readValue(p, AttributeValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Attribute build(Attribute.AttributeBuilder builder) {
    return builder.build();
  }
}