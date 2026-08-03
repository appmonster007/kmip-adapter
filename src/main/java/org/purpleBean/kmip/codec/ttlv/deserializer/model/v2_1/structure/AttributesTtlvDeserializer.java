package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

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