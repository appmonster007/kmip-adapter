package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PgpKey;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<PgpKey, PgpKey.PgpKeyBuilder> {

  public PgpKeyJsonDeserializer() {
    super(PgpKey.kmipTag, PgpKey.encodingType);
  }

  @Override
  protected PgpKey.PgpKeyBuilder createBuilder() {
    return PgpKey.builder();
  }

  @Override
  protected void setValue(PgpKey.PgpKeyBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PGP_KEY_VERSION ->
          builder.pgpKeyVersion(ctxt.readValue(p, PgpKeyVersion.class));
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PgpKey build(PgpKey.PgpKeyBuilder builder) {
    return builder.build();
  }
}