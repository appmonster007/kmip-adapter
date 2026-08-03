package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.PublicKeyAttributes;

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