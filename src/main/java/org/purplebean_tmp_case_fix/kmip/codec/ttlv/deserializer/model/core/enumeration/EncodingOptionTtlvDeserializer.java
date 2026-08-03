package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<EncodingOption, EncodingOption.EncodingOptionBuilder> {

  public EncodingOptionTtlvDeserializer() {
    super(EncodingOption.kmipTag, EncodingOption.encodingType);
  }

  @Override
  protected EncodingOption.EncodingOptionBuilder createBuilder() {
    return EncodingOption.builder();
  }

  @Override
  protected void setValue(EncodingOption.EncodingOptionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(EncodingOption.fromValue(value));
  }

  @Override
  protected EncodingOption build(EncodingOption.EncodingOptionBuilder builder) {
    return builder.build();
  }
}
