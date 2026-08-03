package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;

/**
 * JSON deserializer for {@link AttributeReference}.
 */
public class AttributeReferenceJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttributeReference,
        AttributeReference.AttributeReferenceBuilder> {

  /**
   * Constructs a new {@link AttributeReferenceJsonDeserializer}.
   */
  public AttributeReferenceJsonDeserializer() {
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