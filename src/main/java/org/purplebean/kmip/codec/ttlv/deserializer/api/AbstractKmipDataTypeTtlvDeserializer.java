package org.purplebean.kmip.codec.ttlv.deserializer.api;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.TtlvConstants;
import org.purplebean.kmip.codec.ttlv.TtlvObject;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * Abstract base class for custom TTLV deserializers of specific {@link KmipDataType}
 * implementations.
 *
 * <p>This class provides a template for deserializing complex KMIP objects from TTLV. It handles
 * the validation of the KMIP tag and encoding type, and orchestrates the parsing of the
 * TTLV structure into a builder object, which is then used to construct the final result.
 *
 * @param <T> The type of {@link KmipDataType} being deserialized.
 * @param <B> The type of the builder used to construct the object.
 */
public abstract class AbstractKmipDataTypeTtlvDeserializer<T extends KmipDataType, B>
    extends KmipDataTypeTtlvDeserializer<T> {

  private final KmipTag kmipTag;
  private final EncodingType encodingType;

  /**
   * Constructs a new deserializer for the specified KMIP tag and encoding type.
   *
   * @param kmipTag      The expected KMIP tag of the object.
   * @param encodingType The expected encoding type of the object.
   */
  protected AbstractKmipDataTypeTtlvDeserializer(KmipTag kmipTag, EncodingType encodingType) {
    this.kmipTag = kmipTag;
    this.encodingType = encodingType;
  }

  @Override
  public T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    B builder = createBuilder();

    TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
    byte[] tag = verifyTag(obj, mapper, builder);
    byte type = verifyType(obj, mapper, builder);

    if (EncodingType.STRUCTURE.getTypeValue() == type) {
      List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
      for (TtlvObject ttlvObject : nestedObjects) {
        setValue(builder, ttlvObject.getTag(), type, ttlvObject.toByteBuffer(), mapper);
      }
    } else {
      ByteBuffer bb = ByteBuffer
          .wrap(obj.getValue())
          .order(TtlvConstants.BYTE_ORDER);
      setValue(builder, tag, type, bb, mapper);
    }

    T result = build(builder);

    verifyVersionSupport(result);
    return result;
  }

  /**
   * Verifies that the deserialized object is supported by the current KMIP specification.
   *
   * @param result The deserialized object.
   * @throws NoSuchElementException if the object is not supported.
   */
  protected void verifyVersionSupport(T result) {
    KmipSpec spec = KmipContext.getSpec();
    if (!result.isSupported()) {
      throw new NoSuchElementException(
          String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
    }
  }

  /**
   * Verifies that the TTLV object's tag matches the expected tag.
   *
   * @param obj     The TTLV object.
   * @param mapper  The TTLV mapper.
   * @param builder The builder object.
   * @return The tag bytes if valid.
   * @throws IllegalArgumentException if the tag does not match.
   */
  protected byte[] verifyTag(TtlvObject obj, TtlvMapper mapper, B builder) {
    if (kmipTag != null && !Arrays.equals(obj.getTag(), kmipTag.getTagBytes())) {
      throw new IllegalArgumentException(
          String.format("Expected %s tag, got %s", kmipTag.getDescription(), obj.getType()));
    }
    return obj.getTag();
  }

  /**
   * Verifies that the TTLV object's type matches the expected encoding type.
   *
   * @param obj     The TTLV object.
   * @param mapper  The TTLV mapper.
   * @param builder The builder object.
   * @return The type byte if valid.
   * @throws IllegalArgumentException if the type does not match.
   */
  protected byte verifyType(TtlvObject obj, TtlvMapper mapper, B builder) {
    if (encodingType != null && obj.getType() != encodingType.getTypeValue()) {
      throw new IllegalArgumentException(
          String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(),
              kmipTag != null ? kmipTag.getDescription() : "unknown", obj.getType()));
    }
    return obj.getType();
  }

  /**
   * Creates a new builder instance for constructing the object.
   *
   * @return A new builder instance.
   */
  protected abstract B createBuilder();

  /**
   * Sets a value on the builder based on the parsed TTLV data.
   *
   * @param builder The builder instance.
   * @param tag     The tag of the value being set.
   * @param type    The type of the value being set.
   * @param p       The ByteBuffer containing the value data.
   * @param mapper  The TTLV mapper.
   * @throws IOException if an I/O error occurs.
   */
  protected abstract void setValue(B builder, byte[] tag, byte type, ByteBuffer p,
                                   TtlvMapper mapper) throws IOException;

  /**
   * Builds the final object from the builder.
   *
   * @param builder The builder instance.
   * @return The constructed object.
   */
  protected abstract T build(B builder);
}
