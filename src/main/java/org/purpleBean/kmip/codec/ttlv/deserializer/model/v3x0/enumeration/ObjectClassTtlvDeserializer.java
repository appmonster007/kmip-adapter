package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3x0.enumeration.ObjectClass;

public class ObjectClassTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ObjectClass, ObjectClass.ObjectClassBuilder> {

  public ObjectClassTtlvDeserializer() {
    super(ObjectClass.kmipTag, ObjectClass.encodingType);
  }

  @Override
  protected ObjectClass.ObjectClassBuilder createBuilder() {
    return ObjectClass.builder();
  }

  @Override
  protected void setValue(ObjectClass.ObjectClassBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ObjectClass.fromValue(value));
  }

  @Override
  protected ObjectClass build(ObjectClass.ObjectClassBuilder builder) {
    return builder.build();
  }
}
