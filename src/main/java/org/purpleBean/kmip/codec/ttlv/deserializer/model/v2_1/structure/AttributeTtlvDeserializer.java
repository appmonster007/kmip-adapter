package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.VendorIdentification;
import org.purpleBean.kmip.model.v2_1.structure.Attribute;

public class AttributeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Attribute, Attribute.AttributeBuilder> {

  public AttributeTtlvDeserializer() {
    super(Attribute.kmipTag, Attribute.encodingType);
  }

  @Override
  protected Attribute.AttributeBuilder createBuilder() {
    return Attribute.builder();
  }

  @Override
  protected void setValue(Attribute.AttributeBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.VENDOR_IDENTIFICATION ->
          builder.vendorIdentification(mapper.readValue(p, VendorIdentification.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(mapper.readValue(p, AttributeName.class));
      case KmipTag.Standard.ATTRIBUTE_VALUE ->
          builder.attributeValue(mapper.readValue(p, AttributeValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Attribute build(Attribute.AttributeBuilder builder) {
    return builder.build();
  }
}