package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;

public class PublicKeyAttributesTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PublicKeyAttributes,
        PublicKeyAttributes.PublicKeyAttributesBuilder> {

  public PublicKeyAttributesTtlvDeserializer() {
    super(PublicKeyAttributes.kmipTag, PublicKeyAttributes.encodingType);
  }

  @Override
  protected PublicKeyAttributes.PublicKeyAttributesBuilder createBuilder() {
    return PublicKeyAttributes.builder();
  }

  @Override
  protected void setValue(PublicKeyAttributes.PublicKeyAttributesBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.attribute(mapper.readValue(p, KmipAttribute.class));
  }

  @Override
  protected PublicKeyAttributes build(PublicKeyAttributes.PublicKeyAttributesBuilder builder) {
    return builder.build();
  }
}