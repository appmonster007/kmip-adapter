package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetDefaultsOpRequestPayload;

/**
 * TTLV deserializer for {@link SetDefaultsOpRequestPayload}.
 */
public class SetDefaultsOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SetDefaultsOpRequestPayload,
        SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link SetDefaultsOpRequestPayloadTtlvDeserializer}.
   */
  public SetDefaultsOpRequestPayloadTtlvDeserializer() {
    super(SetDefaultsOpRequestPayload.kmipTag, SetDefaultsOpRequestPayload.encodingType);
  }

  @Override
  protected SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder createBuilder() {
    return SetDefaultsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.DEFAULTS_INFORMATION ->
          builder.defaultsInformation(mapper.readValue(p, DefaultsInformation.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetDefaultsOpRequestPayload build(
      SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
