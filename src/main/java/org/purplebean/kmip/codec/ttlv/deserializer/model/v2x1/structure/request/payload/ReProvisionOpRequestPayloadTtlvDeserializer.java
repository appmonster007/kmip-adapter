package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ReProvisionOpRequestPayload;

/**
 * TTLV deserializer for {@link ReProvisionOpRequestPayload}.
 */
public class ReProvisionOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReProvisionOpRequestPayload,
        ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ReProvisionOpRequestPayloadTtlvDeserializer}.
   */
  public ReProvisionOpRequestPayloadTtlvDeserializer() {
    super(ReProvisionOpRequestPayload.kmipTag, ReProvisionOpRequestPayload.encodingType);
  }

  @Override
  protected ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder createBuilder() {
    return ReProvisionOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CERTIFICATE_REQUEST ->
          builder.certificateRequest(mapper.readValue(p, CertificateRequest.class));
      case KmipTag.Standard.CERTIFICATE ->
          builder.certificate(mapper.readValue(p, Certificate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReProvisionOpRequestPayload build(
      ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}