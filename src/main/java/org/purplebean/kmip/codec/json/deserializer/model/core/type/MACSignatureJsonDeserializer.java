package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.MACSignature;

/**
 * JSON deserializer for {@link MACSignature}.
 */
public class MACSignatureJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<MACSignature, MACSignature.MACSignatureBuilder> {

  /**
   * Constructs a new {@link MACSignatureJsonDeserializer}.
   */
  public MACSignatureJsonDeserializer() {
    super(MACSignature.kmipTag, MACSignature.encodingType);
  }

  @Override
  protected MACSignature.MACSignatureBuilder createBuilder() {
    return MACSignature.builder();
  }

  @Override
  protected void setValue(MACSignature.MACSignatureBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected MACSignature build(MACSignature.MACSignatureBuilder builder) {
    return builder.build();
  }
}
