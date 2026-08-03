package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.structure.Nonce;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseHeader;

/**
 * JSON deserializer for {@link ResponseHeader}.
 */
public class ResponseHeaderJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ResponseHeader, ResponseHeader.ResponseHeaderBuilder> {

  /**
   * Constructs a new {@link ResponseHeaderJsonDeserializer}.
   */
  public ResponseHeaderJsonDeserializer() {
    super(ResponseHeader.kmipTag, ResponseHeader.encodingType);
  }

  @Override
  protected ResponseHeader.ResponseHeaderBuilder createBuilder() {
    return ResponseHeader.builder();
  }

  @Override
  protected void setValue(ResponseHeader.ResponseHeaderBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTOCOL_VERSION ->
          builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
      case KmipTag.Standard.TIME_STAMP -> builder.timeStamp(ctxt.readValue(p, TimeStamp.class));
      case KmipTag.Standard.NONCE -> builder.nonce(ctxt.readValue(p, Nonce.class));
      case KmipTag.Standard.ATTESTATION_TYPE ->
          builder.attestationType(ctxt.readValue(p, AttestationType.class));
      case KmipTag.Standard.BATCH_COUNT -> builder.batchCount(ctxt.readValue(p, BatchCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ResponseHeader build(ResponseHeader.ResponseHeaderBuilder builder) {
    return builder.build();
  }
}
