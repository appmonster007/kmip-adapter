package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v2_1.type.ServerCorrelationValue;
import org.purpleBean.kmip.model.v3_0.structure.response.ResponseHeader;
import org.purpleBean.kmip.model.v3_0.type.ServerHashedPassword;

public class ResponseHeaderTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ResponseHeader, ResponseHeader.ResponseHeaderBuilder> {

  public ResponseHeaderTtlvDeserializer() {
    super(ResponseHeader.kmipTag, ResponseHeader.encodingType);
  }

  @Override
  protected ResponseHeader.ResponseHeaderBuilder createBuilder() {
    return ResponseHeader.builder();
  }

  @Override
  protected void setValue(ResponseHeader.ResponseHeaderBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTOCOL_VERSION ->
          builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
      case KmipTag.Standard.TIME_STAMP -> builder.timeStamp(mapper.readValue(p, TimeStamp.class));
      case KmipTag.Standard.NONCE -> builder.nonce(mapper.readValue(p, Nonce.class));
      case KmipTag.Standard.SERVER_HASHED_PASSWORD ->
          builder.serverHashedPassword(mapper.readValue(p, ServerHashedPassword.class));
      case KmipTag.Standard.SERVER_CORRELATION_VALUE ->
          builder.serverCorrelationValue(mapper.readValue(p, ServerCorrelationValue.class));
      case KmipTag.Standard.ATTESTATION_TYPE ->
          builder.attestationType(mapper.readValue(p, AttestationType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ResponseHeader build(ResponseHeader.ResponseHeaderBuilder builder) {
    return builder.build();
  }
}