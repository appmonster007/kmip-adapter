package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PrivateKeyTemplateAttribute;

public class PrivateKeyTemplateAttributeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PrivateKeyTemplateAttribute,
        PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder> {

  public PrivateKeyTemplateAttributeTtlvDeserializer() {
    super(PrivateKeyTemplateAttribute.kmipTag, PrivateKeyTemplateAttribute.encodingType);
  }

  @Override
  protected PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder createBuilder() {
    return PrivateKeyTemplateAttribute.builder();
  }

  @Override
  protected void setValue(PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected PrivateKeyTemplateAttribute build(
      PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder) {
    return builder.build();
  }
}