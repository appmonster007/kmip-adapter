package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.Description;

public class DescriptionTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Description, Description.DescriptionBuilder> {

  public DescriptionTtlvDeserializer() {
    super(Description.kmipTag, Description.encodingType);
  }

  @Override
  protected Description.DescriptionBuilder createBuilder() {
    return Description.builder();
  }

  @Override
  protected void setValue(Description.DescriptionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected Description build(Description.DescriptionBuilder builder) {
    return builder.build();
  }
}