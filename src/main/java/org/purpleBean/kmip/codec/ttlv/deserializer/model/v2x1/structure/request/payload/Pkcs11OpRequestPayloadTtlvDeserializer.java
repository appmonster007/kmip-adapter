package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.structure.request.payload.Pkcs11OpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.Pkcs11InputParameters;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;

public class Pkcs11OpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs11OpRequestPayload,
        Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder> {

  public Pkcs11OpRequestPayloadTtlvDeserializer() {
    super(Pkcs11OpRequestPayload.kmipTag, Pkcs11OpRequestPayload.encodingType);
  }

  @Override
  protected Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder createBuilder() {
    return Pkcs11OpRequestPayload.builder();
  }

  @Override
  protected void setValue(Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.PKCS_11_FUNCTION ->
          builder.pkcs11Function(mapper.readValue(p, Pkcs11Function.class));
      case KmipTag.Standard.PKCS_11_INPUT_PARAMETERS ->
          builder.pkcs11InputParameters(mapper.readValue(p, Pkcs11InputParameters.class));
      case KmipTag.Standard.PKCS_11_OUTPUT_PARAMETERS ->
          builder.pkcs11OutputParameters(mapper.readValue(p, Pkcs11OutputParameters.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs11OpRequestPayload build(
      Pkcs11OpRequestPayload.Pkcs11OpRequestPayloadBuilder builder) {
    return builder.build();
  }
}