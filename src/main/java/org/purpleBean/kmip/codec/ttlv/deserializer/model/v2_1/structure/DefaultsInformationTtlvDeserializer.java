package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2_1.structure.ObjectDefaults;

public class DefaultsInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DefaultsInformation,
        DefaultsInformation.DefaultsInformationBuilder> {

  public DefaultsInformationTtlvDeserializer() {
    super(DefaultsInformation.kmipTag, DefaultsInformation.encodingType);
  }

  @Override
  protected DefaultsInformation.DefaultsInformationBuilder createBuilder() {
    return DefaultsInformation.builder();
  }

  @Override
  protected void setValue(DefaultsInformation.DefaultsInformationBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_DEFAULTS ->
          builder.objectDefault(mapper.readValue(p, ObjectDefaults.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DefaultsInformation build(DefaultsInformation.DefaultsInformationBuilder builder) {
    return builder.build();
  }
}