package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PublicKeyTemplateAttribute;

/**
 * TTLV deserializer for {@link PublicKeyTemplateAttribute}.
 */
public class PublicKeyTemplateAttributeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PublicKeyTemplateAttribute,
        PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder> {

  /**
   * Constructs a new {@link PublicKeyTemplateAttributeTtlvDeserializer}.
   */
  public PublicKeyTemplateAttributeTtlvDeserializer() {
    super(PublicKeyTemplateAttribute.kmipTag, PublicKeyTemplateAttribute.encodingType);
  }

  @Override
  protected PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder createBuilder() {
    return PublicKeyTemplateAttribute.builder();
  }

  @Override
  protected void setValue(PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected PublicKeyTemplateAttribute build(
      PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder builder) {
    return builder.build();
  }
}
