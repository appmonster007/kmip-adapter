package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.TtlvObject;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * TTLV deserializer for {@link UniqueIdentifier}.
 */
public class UniqueIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<UniqueIdentifier,
        UniqueIdentifier.UniqueIdentifierBuilder> {

  /**
   * Constructs a new {@link UniqueIdentifierTtlvDeserializer}.
   */
  public UniqueIdentifierTtlvDeserializer() {
    super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
  }

  @Override
  protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
    return UniqueIdentifier.builder();
  }

  @Override
  protected byte verifyType(TtlvObject obj, TtlvMapper mapper,
                            UniqueIdentifier.UniqueIdentifierBuilder builder) {
    // Unlike the XML/JSON codecs (whose canonical tree representation always encodes "value" as
    // text regardless of wire type), raw TTLV value bytes are format-specific: Integer/Enumeration
    // are 4-byte binary ints, NOT UTF-8 text, so widening this whitelist to those variants would
    // misinterpret the bytes via the generic String reader below (this mirrors the pre-existing,
    // pre-interface-refactor behavior, which only ever supported TextString here). Identifier,
    // Reference and NameReference (KMIP 3.0 §4.68), however, are genuinely UTF-8 character
    // sequences on the wire — the same shape as TextString, just a distinct TTLV Item Type byte —
    // so they're safe to accept here too.
    byte type = obj.getType();
    if (type == EncodingType.TEXT_STRING.getTypeValue()
        || type == EncodingType.IDENTIFIER.getTypeValue()
        || type == EncodingType.REFERENCE.getTypeValue()
        || type == EncodingType.NAME_REFERENCE.getTypeValue()) {
      return type;
    }
    return super.verifyType(obj, mapper, builder);
  }

  @Override
  protected void setValue(UniqueIdentifier.UniqueIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
    EncodingType
        .fromTypeValue(type)
        .ifPresent(builder::sourceEncoding);
  }

  @Override
  protected UniqueIdentifier build(UniqueIdentifier.UniqueIdentifierBuilder builder) {
    return builder.build();
  }
}
