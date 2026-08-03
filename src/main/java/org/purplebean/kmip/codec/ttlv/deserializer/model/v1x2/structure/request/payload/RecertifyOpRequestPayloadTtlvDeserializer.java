package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.core.type.Offset;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RecertifyOpRequestPayload;

/**
 * TTLV deserializer for {@link RecertifyOpRequestPayload}.
 */
public class RecertifyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RecertifyOpRequestPayload,
        RecertifyOpRequestPayload.RecertifyOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link RecertifyOpRequestPayloadTtlvDeserializer}.
   */
  public RecertifyOpRequestPayloadTtlvDeserializer() {
    super(RecertifyOpRequestPayload.kmipTag, RecertifyOpRequestPayload.encodingType);
  }

  @Override
  protected RecertifyOpRequestPayload.RecertifyOpRequestPayloadBuilder createBuilder() {
    return RecertifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RecertifyOpRequestPayload.RecertifyOpRequestPayloadBuilder builder,
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
      case KmipTag.Standard.OFFSET -> builder.offset(mapper.readValue(p, Offset.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RecertifyOpRequestPayload build(
      RecertifyOpRequestPayload.RecertifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
