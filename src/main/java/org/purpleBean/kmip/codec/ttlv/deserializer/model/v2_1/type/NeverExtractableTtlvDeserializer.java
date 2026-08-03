package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.NeverExtractable;

public class NeverExtractableTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<NeverExtractable,
        NeverExtractable.NeverExtractableBuilder> {

  public NeverExtractableTtlvDeserializer() {
    super(NeverExtractable.kmipTag, NeverExtractable.encodingType);
  }

  @Override
  protected NeverExtractable.NeverExtractableBuilder createBuilder() {
    return NeverExtractable.builder();
  }

  @Override
  protected void setValue(NeverExtractable.NeverExtractableBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected NeverExtractable build(NeverExtractable.NeverExtractableBuilder builder) {
    return builder.build();
  }
}