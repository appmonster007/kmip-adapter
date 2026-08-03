package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3_0.type.OtpDigest;

public class OtpDigestJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<OtpDigest, OtpDigest.OtpDigestBuilder> {

  public OtpDigestJsonDeserializer() {
    super(OtpDigest.kmipTag, OtpDigest.encodingType);
  }

  @Override
  protected OtpDigest.OtpDigestBuilder createBuilder() {
    return OtpDigest.builder();
  }

  @Override
  protected void setValue(OtpDigest.OtpDigestBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(OtpDigest.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected OtpDigest build(OtpDigest.OtpDigestBuilder builder) {
    return builder.build();
  }
}