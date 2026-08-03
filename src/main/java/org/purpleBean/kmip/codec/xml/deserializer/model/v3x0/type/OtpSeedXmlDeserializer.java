package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.type.OtpSeed;

public class OtpSeedXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<OtpSeed, OtpSeed.OtpSeedBuilder> {

  public OtpSeedXmlDeserializer() {
    super(OtpSeed.kmipTag, OtpSeed.encodingType);
  }

  @Override
  protected OtpSeed.OtpSeedBuilder createBuilder() {
    return OtpSeed.builder();
  }

  @Override
  protected void setValue(OtpSeed.OtpSeedBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected OtpSeed build(OtpSeed.OtpSeedBuilder builder) {
    return builder.build();
  }
}