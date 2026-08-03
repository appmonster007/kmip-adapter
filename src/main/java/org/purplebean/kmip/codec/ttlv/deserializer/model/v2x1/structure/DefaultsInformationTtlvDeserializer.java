package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purplebean.kmip.model.v2x1.structure.ObjectDefaults;

/**
 * TTLV deserializer for {@link DefaultsInformation}.
 */
public class DefaultsInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DefaultsInformation,
        DefaultsInformation.DefaultsInformationBuilder> {

  /**
   * Constructs a new {@link DefaultsInformationTtlvDeserializer}.
   */
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