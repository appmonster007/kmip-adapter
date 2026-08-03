package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RngMode;

public class RngModeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<RngMode, RngMode.RngModeBuilder> {

  public RngModeTtlvDeserializer() {
    super(RngMode.kmipTag, RngMode.encodingType);
  }

  @Override
  protected RngMode.RngModeBuilder createBuilder() {
    return RngMode.builder();
  }

  @Override
  protected void setValue(RngMode.RngModeBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(RngMode.fromValue(value));
  }

  @Override
  protected RngMode build(RngMode.RngModeBuilder builder) {
    return builder.build();
  }
}
