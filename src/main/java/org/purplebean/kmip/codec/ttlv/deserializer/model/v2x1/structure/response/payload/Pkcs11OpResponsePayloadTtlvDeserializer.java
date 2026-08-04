package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode;
import org.purplebean.kmip.model.v2x1.structure.response.payload.Pkcs11OpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;

/**
 * TTLV deserializer for {@link Pkcs11OpResponsePayload}.
 */
public class Pkcs11OpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Pkcs11OpResponsePayload,
        Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link Pkcs11OpResponsePayloadTtlvDeserializer}.
   */
  public Pkcs11OpResponsePayloadTtlvDeserializer() {
    super(Pkcs11OpResponsePayload.kmipTag, Pkcs11OpResponsePayload.encodingType);
  }

  @Override
  protected Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder createBuilder() {
    return Pkcs11OpResponsePayload.builder();
  }

  @Override
  protected void setValue(Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PKCS_11_FUNCTION ->
          builder.pkcs11Function(mapper.readValue(p, Pkcs11Function.class));
      case KmipTag.Standard.PKCS_11_RETURN_CODE ->
          builder.pkcs11ReturnCode(mapper.readValue(p, Pkcs11ReturnCode.class));
      case KmipTag.Standard.PKCS_11_OUTPUT_PARAMETERS ->
          builder.pkcs11OutputParameters(mapper.readValue(p, Pkcs11OutputParameters.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs11OpResponsePayload build(
      Pkcs11OpResponsePayload.Pkcs11OpResponsePayloadBuilder builder) {
    return builder.build();
  }
}