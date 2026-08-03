package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CertifyOpRequestPayload;

/**
 * TTLV deserializer for {@link CertifyOpRequestPayload}.
 */
public class CertifyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertifyOpRequestPayload,
        CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CertifyOpRequestPayloadTtlvDeserializer}.
   */
  public CertifyOpRequestPayloadTtlvDeserializer() {
    super(CertifyOpRequestPayload.kmipTag, CertifyOpRequestPayload.encodingType);
  }

  @Override
  protected CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder createBuilder() {
    return CertifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CERTIFICATE_REQUEST_TYPE ->
          builder.certificateRequestType(mapper.readValue(p, CertificateRequestType.class));
      case KmipTag.Standard.CERTIFICATE_REQUEST ->
          builder.certificateRequest(mapper.readValue(p, CertificateRequest.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertifyOpRequestPayload build(
      CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}