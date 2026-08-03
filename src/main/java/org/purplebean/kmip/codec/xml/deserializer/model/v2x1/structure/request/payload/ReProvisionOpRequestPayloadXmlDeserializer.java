package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ReProvisionOpRequestPayload;

/**
 * XML deserializer for {@link ReProvisionOpRequestPayload}.
 */
public class ReProvisionOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ReProvisionOpRequestPayload,
        ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ReProvisionOpRequestPayloadXmlDeserializer}.
   */
  public ReProvisionOpRequestPayloadXmlDeserializer() {
    super(ReProvisionOpRequestPayload.kmipTag, ReProvisionOpRequestPayload.encodingType);
  }

  @Override
  protected ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder createBuilder() {
    return ReProvisionOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CERTIFICATE_REQUEST ->
          builder.certificateRequest(ctxt.readValue(p, CertificateRequest.class));
      case KmipTag.Standard.CERTIFICATE ->
          builder.certificate(ctxt.readValue(p, Certificate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReProvisionOpRequestPayload build(
      ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}