package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.MacOpRequestPayload;

public class MacOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MacOpRequestPayload,
        MacOpRequestPayload.MacOpRequestPayloadBuilder> {

  public MacOpRequestPayloadTtlvDeserializer() {
    super(MacOpRequestPayload.kmipTag, MacOpRequestPayload.encodingType);
  }

  @Override
  protected MacOpRequestPayload.MacOpRequestPayloadBuilder createBuilder() {
    return MacOpRequestPayload.builder();
  }

  @Override
  protected void setValue(MacOpRequestPayload.MacOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacOpRequestPayload build(MacOpRequestPayload.MacOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}