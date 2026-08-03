package org.purplebean.kmip.codec.ttlv.deserializer.api;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.TtlvObject;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * Base TTLV deserializer for {@link KmipDataType} objects.
 *
 * <p>This class provides the common logic for deserializing KMIP data types from TTLV
 * (Tag-Type-Length-Value)
 * format. It reads the tag and type from the TTLV buffer and uses the {@link KmipDataType} registry
 * to find the appropriate concrete class to instantiate.
 *
 * @param <T> The specific type of {@link KmipDataType} to deserialize.
 */
public class KmipDataTypeTtlvDeserializer<T extends KmipDataType>
    extends TtlvDeserializer<KmipDataType> {

  @Override
  public T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    TtlvObject ttlvObject = TtlvObject.fromBuffer(ttlvBuffer);
    KmipTag.Value kmipTagValue = KmipTag.fromBytes(ttlvObject.getTag());
    EncodingType encodingType = EncodingType
        .fromTypeValue(ttlvObject.getType())
        .orElse(null);

    if (kmipTagValue == null || encodingType == null) {
      return null;
    }

    Class<? extends KmipDataType> clazz = getKmipDataTypeClass(kmipTagValue, encodingType, mapper);
    if (clazz == null) {
      throw new NoSuchElementException(
          String.format("No class registered for tag %s and encoding type %s",
              kmipTagValue.getValue(), encodingType));
    }

    ttlvBuffer.rewind();
    return (T) mapper.readValue(ttlvBuffer, clazz);
  }

  /**
   * Determines the concrete {@link KmipDataType} class to instantiate based on the tag and
   * encoding type.
   *
   * @param kmipTag      The KMIP tag.
   * @param encodingType The encoding type.
   * @param mapper       The TTLV mapper.
   * @return The concrete class to instantiate.
   */
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
  }
}
