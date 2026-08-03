package org.purplebean.kmip.codec.ttlv.mapper;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

/**
 * An abstract base class for creating custom deserializers that convert a TTLV
 * (Tag-Type-Length-Value)
 * byte representation into a Java object.
 *
 * <p>This class provides the core logic for deserialization and includes a mechanism to
 * automatically
 * determine the target type {@code <T>} that the deserializer handles. This is essential for the
 * {@link TtlvMapper} to correctly associate the deserializer with the appropriate class.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Deserialization Logic:</b> The main responsibility of a concrete implementation is to
 *   provide
 *       the logic for the {@link #deserialize(ByteBuffer, TtlvMapper)} method, which reads from the
 *       byte buffer and constructs the target object.</li>
 *   <li><b>Type Inference:</b> The {@link #handledType()} method uses reflection to
 *   automatically infer the
 *       generic type {@code <T>} that the deserializer is designed for. This simplifies
 *       registration
 *       by removing the need to explicitly specify the target class.</li>
 *   <li><b>Recursive Deserialization:</b> The provided {@link TtlvMapper} instance allows for
 *   recursive
 *       deserialization of nested objects, enabling the handling of complex data structures.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * To create a custom deserializer, extend this class and implement the abstract {@code
 * deserialize} method.
 * The handled type should be specified as a generic parameter.
 *
 * <pre>
 * {@code
 * public class MyObjectDeserializer extends TtlvDeserializer<MyObject> {
 *     @Override
 *     public MyObject deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
 *         // Read from ttlvBuffer and construct MyObject
 *         // ...
 *         return new MyObject(...);
 *     }
 * }
 * }
 * </pre>
 *
 * @param <T> The type of the object that this deserializer creates.
 * @see TtlvMapper
 * @see TtlvSerializer
 */
public abstract class TtlvDeserializer<T> {

  /**
   * Deserializes a TTLV byte representation from the given {@link ByteBuffer} into an object of
   * type {@code T}.
   *
   * @param ttlvBuffer The byte buffer containing the TTLV data to be deserialized.
   * @param mapper     The {@link TtlvMapper} instance that can be used to recursively
   *                   deserialize nested objects.
   * @return The deserialized object of type {@code T}.
   * @throws IOException if an I/O error occurs while reading from the buffer.
   */
  public abstract T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException;

  /**
   * Infers and returns the class of the type {@code T} that this deserializer handles.
   *
   * <p>This method uses reflection to inspect the generic type arguments of the class that extends
   * {@code TtlvDeserializer}. It first checks the generic superclass and then falls back to
   * implemented interfaces. This allows the {@link TtlvMapper} to automatically register the
   * deserializer for the correct target type.
   *
   * @return The {@link Class} of the handled type, or {@code null} if it cannot be determined.
   */
  @SuppressWarnings("unchecked")
  public Class<T> handledType() {
    // Prefer generic superclass
    Type superType = getClass().getGenericSuperclass();
    if (superType instanceof ParameterizedType pt) {
      Type typeArg = pt.getActualTypeArguments()[0];
      if (typeArg instanceof Class<?> c) {
        return (Class<T>) c;
      }
    }
    // Fallback: inspect implemented interfaces for TtlvDeserializer<T>
    for (Type itf : getClass().getGenericInterfaces()) {
      if (itf instanceof ParameterizedType itfPt) {
        if (itfPt.getRawType() instanceof Class<?> raw && raw
            .getName()
            .equals(TtlvDeserializer.class.getName())) {
          Type typeArg = itfPt.getActualTypeArguments()[0];
          if (typeArg instanceof Class<?> c) {
            return (Class<T>) c;
          }
        }
      }
    }
    return null;
  }
}