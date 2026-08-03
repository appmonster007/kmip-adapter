package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.PrivateKeyAttributes;

public class PrivateKeyAttributesTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PrivateKeyAttributes,
        PrivateKeyAttributes.PrivateKeyAttributesBuilder> {

  public PrivateKeyAttributesTtlvDeserializer() {
    super(PrivateKeyAttributes.kmipTag, PrivateKeyAttributes.encodingType);
  }

  @Override
  protected PrivateKeyAttributes.PrivateKeyAttributesBuilder createBuilder() {
    return PrivateKeyAttributes.builder();
  }

  @Override
  protected void setValue(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.attribute(mapper.readValue(p, KmipAttribute.class));
  }

  @Override
  protected PrivateKeyAttributes build(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder) {
    return builder.build();
  }
}