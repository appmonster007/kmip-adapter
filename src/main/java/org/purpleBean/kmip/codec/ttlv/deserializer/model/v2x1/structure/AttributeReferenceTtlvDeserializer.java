package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.VendorIdentification;
import org.purpleBean.kmip.model.v2x1.structure.AttributeReference;

public class AttributeReferenceTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttributeReference,
        AttributeReference.AttributeReferenceBuilder> {

  public AttributeReferenceTtlvDeserializer() {
    super(AttributeReference.kmipTag, AttributeReference.encodingType);
  }

  @Override
  protected AttributeReference.AttributeReferenceBuilder createBuilder() {
    return AttributeReference.builder();
  }

  @Override
  protected void setValue(AttributeReference.AttributeReferenceBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.VENDOR_IDENTIFICATION ->
          builder.vendorIdentification(mapper.readValue(p, VendorIdentification.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(mapper.readValue(p, AttributeName.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AttributeReference build(AttributeReference.AttributeReferenceBuilder builder) {
    return builder.build();
  }
}