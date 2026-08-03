package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.structure.Nonce;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseHeader;

/**
 * TTLV deserializer for {@link ResponseHeader}.
 */
public class ResponseHeaderTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ResponseHeader, ResponseHeader.ResponseHeaderBuilder> {

  /**
   * Constructs a new {@link ResponseHeaderTtlvDeserializer}.
   */
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
      case KmipTag.Standard.ATTESTATION_TYPE ->
          builder.attestationType(mapper.readValue(p, AttestationType.class));
      case KmipTag.Standard.BATCH_COUNT ->
          builder.batchCount(mapper.readValue(p, BatchCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ResponseHeader build(ResponseHeader.ResponseHeaderBuilder builder) {
    return builder.build();
  }
}