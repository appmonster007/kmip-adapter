package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ApplicationData;

/**
 * TTLV deserializer for {@link ApplicationData}.
 */
public class ApplicationDataTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ApplicationData, ApplicationData.ApplicationDataBuilder> {

  /**
   * Constructs a new {@link ApplicationDataTtlvDeserializer}.
   */
  public ApplicationDataTtlvDeserializer() {
    super(ApplicationData.kmipTag, ApplicationData.encodingType);
  }

  @Override
  protected ApplicationData.ApplicationDataBuilder createBuilder() {
    return ApplicationData.builder();
  }

  @Override
  protected void setValue(ApplicationData.ApplicationDataBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected ApplicationData build(ApplicationData.ApplicationDataBuilder builder) {
    return builder.build();
  }
}