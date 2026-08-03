package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Attributes;

public class AttributesTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Attributes, Attributes.AttributesBuilder> {

  public AttributesTtlvDeserializer() {
    super(Attributes.kmipTag, Attributes.encodingType);
  }

  @Override
  protected Attributes.AttributesBuilder createBuilder() {
    return Attributes.builder();
  }

  @Override
  protected void setValue(Attributes.AttributesBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.attribute(mapper.readValue(p, KmipAttribute.class));
  }

  @Override
  protected Attributes build(Attributes.AttributesBuilder builder) {
    return builder.build();
  }
}