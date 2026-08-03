package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ApplicationData;

public class ApplicationDataTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ApplicationData, ApplicationData.ApplicationDataBuilder> {

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