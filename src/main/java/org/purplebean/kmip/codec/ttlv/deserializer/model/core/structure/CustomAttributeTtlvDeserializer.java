package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CustomAttribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;

/**
 * TTLV deserializer for {@link CustomAttribute}.
 */
public class CustomAttributeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CustomAttribute, CustomAttribute.CustomAttributeBuilder> {

  /**
   * Constructs a new {@link CustomAttributeTtlvDeserializer}.
   */
  public CustomAttributeTtlvDeserializer() {
    super(CustomAttribute.kmipTag, CustomAttribute.encodingType);
  }

  @Override
  protected CustomAttribute.CustomAttributeBuilder createBuilder() {
    return CustomAttribute.builder();
  }

  @Override
  protected void setValue(CustomAttribute.CustomAttributeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(mapper.readValue(p, AttributeName.class));
      case KmipTag.Standard.ATTRIBUTE_VALUE ->
          builder.attributeValue(mapper.readValue(p, AttributeValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CustomAttribute build(CustomAttribute.CustomAttributeBuilder builder) {
    return builder.build();
  }
}