package org.purplebean.kmip.codec.ttlv.serializer.api;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.codec.ttlv.TtlvObject;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * Abstract base class for TTLV serialization of {@link KmipDataType} objects.
 *
 * <p>This class implements the core logic for serializing KMIP objects to TTLV format.
 * It handles the construction of the {@link TtlvObject}, including the tag, type,
 * and value. It supports recursive serialization of nested structures and proper
 * encoding of enumerations and other data types.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Version Checking:</b> Verifies that the object is supported by the current
 *       {@link KmipSpec} before serialization.</li>
 *   <li><b>Structure Support:</b> Recursively serializes nested {@link KmipStructure}
 *       objects by concatenating their byte representations.</li>
 *   <li><b>Enumeration Support:</b> Serializes {@link KmipEnumeration} values using
 *       their integer values.</li>
 * </ul>
 *
 * @param <T> The type of {@link KmipDataType} to serialize.
 */
public abstract class AbstractKmipDataTypeTtlvSerializer<T extends KmipDataType>
    extends KmipDataTypeTtlvSerializer<T> {

  @Override
  public ByteBuffer serialize(T obj, TtlvMapper mapper) throws IOException {
    if (obj == null) {
      return null;
    }

    KmipSpec spec = KmipContext.getSpec();
    if (!obj.isSupported()) {
      throw new IOException(
          String.format("%s is not supported for KMIP spec %s",
              obj
                  .getKmipTag()
                  .getDescription(), spec)
      );
    }

    var value = obj.getValue();
    byte[] payload;
    if (obj.getEncodingType() == EncodingType.STRUCTURE) {
      KmipDataType[] nestedValues = (KmipDataType[]) value;
      List<ByteBuffer> nestedObjects = new ArrayList<>(nestedValues.length);
      int totalLength = 0;
      for (KmipDataType object : nestedValues) {
        if (object != null) {
          ByteBuffer buffer = mapper.writeValueAsByteBuffer(object);
          nestedObjects.add(buffer);
          totalLength += buffer.remaining();
        }
      }

      ByteBuffer payloadBuffer = ByteBuffer.allocate(totalLength);
      for (ByteBuffer buffer : nestedObjects) {
        payloadBuffer.put(buffer);
      }
      payload = payloadBuffer.array();
    } else if (obj.getEncodingType() == EncodingType.ENUMERATION) {
      payload = mapper
          .writeValueAsByteBuffer(((KmipEnumeration.Value<?>) value).getValue())
          .array();
    } else {
      payload = mapper
          .writeValueAsByteBuffer(value)
          .array();
    }

    return TtlvObject
        .builder()
        .tag(obj
            .getKmipTag()
            .getTagBytes())
        .type(obj
            .getEncodingType()
            .getTypeValue())
        .value(payload)
        .build()
        .toByteBuffer();
  }
}
