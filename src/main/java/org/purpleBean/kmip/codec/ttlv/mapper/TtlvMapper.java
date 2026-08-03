package org.purplebean.kmip.codec.ttlv.mapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.purplebean.kmip.codec.ttlv.TtlvConstants;

/**
 * A central class for mapping Java objects to and from their TTLV (Tag-Type-Length-Value) byte
 * representations.
 * <p>
 * This class functions similarly to object mappers in other data-binding libraries (like
 * Jackson's {@code ObjectMapper}).
 * It manages a collection of {@link TtlvSerializer} and {@link TtlvDeserializer} instances and
 * orchestrates the
 * process of serialization and deserialization.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Serialization/Deserialization:</b> Provides high-level methods like
 *   {@link #writeValueAsBytes(Object)} and
 *       {@link #readValue(byte[], Class)} for easy conversion between Java objects and byte
 *       arrays.</li>
 *   <li><b>Handler Registry:</b> Maintains internal registries for serializers and
 *   deserializers, allowing for
 *       custom handlers to be registered for specific types.</li>
 *   <li><b>Modular Configuration:</b> Supports the registration of {@link TtlvModule} instances,
 *   which can bundle
 *       multiple serializers and deserializers, making the configuration process more organized
 *       and extensible.</li>
 *   <li><b>Type Hierarchy Lookup:</b> When searching for a handler, it intelligently traverses
 *   the class hierarchy
 *       (including superclasses and interfaces) to find the most appropriate serializer or
 *       deserializer.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * Create an instance of {@code TtlvMapper}, register any custom modules, and then use the
 * read/write methods.
 *
 * <pre>{@code
 * TtlvMapper mapper = new TtlvMapper();
 * mapper.registerModule(new KmipTtlvModule());
 *
 * // Serialize an object to bytes
 * MyObject obj = new MyObject();
 * byte[] ttlvBytes = mapper.writeValueAsBytes(obj);
 *
 * // Deserialize bytes back to an object
 * MyObject reconstructedObj = mapper.readValue(ttlvBytes, MyObject.class);
 * }
 * </pre>
 *
 * @see TtlvSerializer
 * @see TtlvDeserializer
 * @see TtlvModule
 */
public class TtlvMapper {
  private final ThreadLocal<Map<Object, Object>> ctxtHolder =
      ThreadLocal.withInitial(ConcurrentHashMap::new);
  private final ThreadLocal<Integer> callDepth = ThreadLocal.withInitial(() -> 0);
  private final Map<Class<?>, TtlvSerializer<?>> serializers = new ConcurrentHashMap<>();
  private final Map<Class<?>, TtlvDeserializer<?>> deserializers = new ConcurrentHashMap<>();

  /**
   * Registers a {@link TtlvModule}, adding all its serializers and deserializers to this mapper.
   *
   * @param module The module to register. Must not be {@code null}.
   */
  public void registerModule(TtlvModule module) {
    Objects.requireNonNull(module, "module cannot be null");
    serializers.putAll(module.getSerializers());
    deserializers.putAll(module.getDeserializers());
  }


  /**
   * Serializes a Java object into a {@link ByteBuffer} containing its full TTLV encoding.
   *
   * @param value The object to serialize. Must not be {@code null}.
   * @param <T>   The type of the value being serialized.
   * @return A {@link ByteBuffer} containing the TTLV data, ready for reading.
   * @throws IOException if an error occurs during serialization.
   */
  public <T> ByteBuffer writeValueAsByteBuffer(T value) throws IOException {
    beginOperation();
    try {
      Objects.requireNonNull(value, "value cannot be null");
      TtlvSerializer<T> ser = getSerializer(value.getClass());
      ByteBuffer buf = ser.serialize(value, this);
      // Ensure buffer is positioned for reading
      buf.rewind();
      return buf;
    } finally {
      endOperation();
    }
  }


  /**
   * Deserializes a single TTLV element from a {@link ByteBuffer} into a Java object of the
   * specified class.
   *
   * @param buffer The buffer containing the TTLV data. Must not be {@code null}.
   * @param clazz  The target class to deserialize into. Must not be {@code null}.
   * @param <T>    The type of the object to be returned.
   * @return The deserialized Java object.
   * @throws IOException if an error occurs during deserialization.
   */
  public <T> T readValue(ByteBuffer buffer, Class<T> clazz) throws IOException {
    beginOperation();
    try {
      Objects.requireNonNull(buffer, "buffer cannot be null");
      Objects.requireNonNull(clazz, "clazz cannot be null");
      TtlvDeserializer<T> deser = getDeserializer(clazz);
      return deser.deserialize(buffer, this);
    } finally {
      endOperation();
    }
  }


  /**
   * A convenience method to serialize a Java object directly into a byte array.
   *
   * @param value The object to serialize.
   * @param <T>   The type of the value.
   * @return A byte array containing the TTLV representation of the object.
   * @throws IOException if an error occurs during serialization.
   */
  public <T> byte[] writeValueAsBytes(T value) throws IOException {
    ByteBuffer bb = writeValueAsByteBuffer(value);
    byte[] out = new byte[bb.remaining()];
    bb.get(out);
    return out;
  }


  /**
   * A convenience method to deserialize a byte array containing TTLV data into a Java object.
   *
   * @param data  The byte array to deserialize.
   * @param clazz The target class.
   * @param <T>   The type of the object to be returned.
   * @return The deserialized Java object.
   * @throws IOException if an error occurs during deserialization.
   */
  public <T> T readValue(byte[] data, Class<T> clazz) throws IOException {
    ByteBuffer buffer = ByteBuffer
        .wrap(data)
        .order(TtlvConstants.BYTE_ORDER);
    return readValue(buffer, clazz);
  }


  @SuppressWarnings("unchecked")
  private <T> TtlvSerializer<T> getSerializer(Class<?> type) {
    // First try the exact type
    TtlvSerializer<?> serializer = serializers.get(type);
    if (serializer != null) {
      return (TtlvSerializer<T>) serializer;
    }

    // Then try superclasses
    Class<?> current = type.getSuperclass();
    while (current != null && current != Object.class) {
      serializer = serializers.get(current);
      if (serializer != null) {
        // Cache the result for faster lookup next time
        serializers.put(type, serializer);
        return (TtlvSerializer<T>) serializer;
      }
      current = current.getSuperclass();
    }

    // Then try interfaces
    for (Class<?> iface : type.getInterfaces()) {
      serializer = serializers.get(iface);
      if (serializer != null) {
        // Cache the result for faster lookup next time
        serializers.put(type, serializer);
        return (TtlvSerializer<T>) serializer;
      }
    }

    throw new IllegalArgumentException("No serializer found for type: " + type.getName());
  }

  @SuppressWarnings("unchecked")
  private <T> TtlvDeserializer<T> getDeserializer(Class<T> type) {
    // First try the exact type
    TtlvDeserializer<?> deserializer = deserializers.get(type);
    if (deserializer != null) {
      return (TtlvDeserializer<T>) deserializer;
    }

    // Then try superclasses
    Class<?> current = type.getSuperclass();
    while (current != null && current != Object.class) {
      deserializer = deserializers.get(current);
      if (deserializer != null) {
        // Cache the result for faster lookup next time
        deserializers.put(type, deserializer);
        return (TtlvDeserializer<T>) deserializer;
      }
      current = current.getSuperclass();
    }

    // Then try interfaces
    for (Class<?> iface : type.getInterfaces()) {
      deserializer = deserializers.get(iface);
      if (deserializer != null) {
        // Cache the result for faster lookup next time
        deserializers.put(type, deserializer);
        return (TtlvDeserializer<T>) deserializer;
      }
    }

    throw new IllegalArgumentException("No deserializer found for type: " + type.getName());
  }

  /**
   * Sets a contextual attribute for the current operation. These attributes are thread-local
   * and are cleared when the outermost operation (serialization or deserialization) completes.
   * This allows for passing state between different parts of the serialization/deserialization
   * process without modifying the object being processed or the mapper's global state.
   *
   * @param key   The key for the attribute.
   * @param value The value of the attribute.
   */
  public void setAttribute(Object key, Object value) {
    ctxtHolder
        .get()
        .put(key, value);
  }

  /**
   * Retrieves a contextual attribute for the current operation.
   *
   * @param key The key of the attribute to retrieve.
   * @return The value associated with the key, or {@code null} if not found.
   */
  public Object getAttribute(Object key) {
    return ctxtHolder
        .get()
        .get(key);
  }

  private void beginOperation() {
    callDepth.set(callDepth.get() + 1);
  }

  /**
   * Decrements the call depth counter. If the call depth reaches 0 (meaning the outermost
   * serialization/deserialization operation has completed), it clears the thread-local
   * context and call depth.
   */
  private void endOperation() {
    int depth = callDepth.get();
    if (depth <= 1) {
      callDepth.remove();
      ctxtHolder.remove();
    } else {
      callDepth.set(depth - 1);
    }
  }

}
