package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.MachineIdentifier;

/**
 * TTLV deserializer for {@link MachineIdentifier}.
 */
public class MachineIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MachineIdentifier,
        MachineIdentifier.MachineIdentifierBuilder> {

  /**
   * Constructs a new {@link MachineIdentifierTtlvDeserializer}.
   */
  public MachineIdentifierTtlvDeserializer() {
    super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType);
  }

  @Override
  protected MachineIdentifier.MachineIdentifierBuilder createBuilder() {
    return MachineIdentifier.builder();
  }

  @Override
  protected void setValue(MachineIdentifier.MachineIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected MachineIdentifier build(MachineIdentifier.MachineIdentifierBuilder builder) {
    return builder.build();
  }
}
