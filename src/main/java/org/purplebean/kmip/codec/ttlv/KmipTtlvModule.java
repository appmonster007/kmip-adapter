package org.purplebean.kmip.codec.ttlv;

import java.util.ServiceLoader;
import org.purplebean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvModule;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purplebean.kmip.codec.ttlv.serializer.api.KmipDataTypeTtlvSerializer;

/**
 * A {@link TtlvModule} that automatically discovers and registers KMIP-specific TTLV serializers
 * and deserializers.
 *
 * <p>This module leverages the Java {@link ServiceLoader} mechanism to find and register custom
 * handlers for
 * KMIP data types. It simplifies the process of extending the TTLV codec with new or custom KMIP
 * objects
 * by removing the need for manual registration.
 *
 * <p><b>Key Functionality:</b></p>
 * <ul>
 *   <li><b>Automatic Discovery:</b> On initialization, this module scans the classpath for
 *   implementations of
 *       {@link TtlvSerializer}, {@link TtlvDeserializer}, {@link KmipDataTypeTtlvSerializer}, and
 *       {@link KmipDataTypeTtlvDeserializer}.</li>
 *   <li><b>Service-Based Registration:</b> To be discovered, custom handlers must be declared in
 *   the
 *       {@code META-INF/services} directory, following the standard Java Service Provider
 *       Interface (SPI) pattern.</li>
 *   <li><b>Flexible Handler Support:</b> It supports both generic TTLV handlers and more
 *   specific KMIP data type
 *       handlers, providing flexibility for different use cases.</li>
 *   <li><b>Error Handling:</b> The module includes robust error handling to log issues during
 *   registration without
 *       halting the application, making it easier to diagnose problems with service provider
 *       configurations.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * To use this module, simply create an instance of it and register it with a {@code TtlvMapper}.
 * The module
 * will handle the rest of the discovery and registration process automatically.
 *
 * <pre>
 * {@code
 * TtlvMapper mapper = new TtlvMapper();
 * mapper.registerModule(new KmipTtlvModule());
 * }
 * </pre>
 *
 * @see TtlvModule
 * @see TtlvSerializer
 * @see TtlvDeserializer
 * @see ServiceLoader
 */
public class KmipTtlvModule extends TtlvModule {

  /**
   * Constructs a new {@code KmipTtlvModule} and triggers the automatic discovery and
   * registration of
   * TTLV serializers and deserializers.
   */
  public KmipTtlvModule() {

    // Auto-register any TTLV serializers/deserializers exposed via Java ServiceLoader.
    // Supports both concrete providers extending
    // KmipDataTypeTtlvSerializer/KmipDataTypeTtlvDeserializer
    // and providers implementing the plain TtlvSerializer/TtlvDeserializer interfaces.
    for (TtlvSerializer<?> ser : ServiceLoader.load(TtlvSerializer.class)) {
      try {
        Class<?> target = ser.handledType();
        if (target != null) {
          addSerializer((Class) target, (TtlvSerializer) ser);
        } else {
          System.err.println("[KmipTtlvModule] Could not infer handled type for serializer: " + ser
              .getClass()
              .getName());
        }
      } catch (Throwable t) {
        System.err.println(
            "[KmipTtlvModule] Failed to register TTLV serializer via ServiceLoader: " + ser
                .getClass()
                .getName() + ": " + t.getMessage());
      }
    }
    for (TtlvDeserializer<?> deser : ServiceLoader.load(TtlvDeserializer.class)) {
      try {
        Class<?> target = deser.handledType();
        if (target != null) {
          addDeserializer((Class) target, (TtlvDeserializer) deser);
        } else {
          System.err.println("[KmipTtlvModule] Could not infer handled type for deserializer: "
              + deser
                  .getClass()
                  .getName());
        }
      } catch (Throwable t) {
        System.err.println(
            "[KmipTtlvModule] Failed to register TTLV deserializer via ServiceLoader: " + deser
                .getClass()
                .getName() + ": " + t.getMessage());
      }
    }

    for (KmipDataTypeTtlvSerializer<?> ser : ServiceLoader.load(KmipDataTypeTtlvSerializer.class)) {
      try {
        Class<?> target = ser.handledType();
        if (target != null) {
          addSerializer((Class) target, (TtlvSerializer) ser);
        } else {
          System.err.println("[KmipTtlvModule] Could not infer handled type for serializer: " + ser
              .getClass()
              .getName());
        }
      } catch (Throwable t) {
        System.err.println(
            "[KmipTtlvModule] Failed to register TTLV serializer via ServiceLoader: " + ser
                .getClass()
                .getName() + ": " + t.getMessage());
      }
    }
    for (KmipDataTypeTtlvDeserializer<?> deser : ServiceLoader.load(
        KmipDataTypeTtlvDeserializer.class)) {
      try {
        Class<?> target = deser.handledType();
        if (target != null) {
          addDeserializer((Class) target, (TtlvDeserializer) deser);
        } else {
          System.err.println("[KmipTtlvModule] Could not infer handled type for deserializer: "
              + deser
                  .getClass()
                  .getName());
        }
      } catch (Throwable t) {
        System.err.println(
            "[KmipTtlvModule] Failed to register TTLV deserializer via ServiceLoader: " + deser
                .getClass()
                .getName() + ": " + t.getMessage());
      }
    }
  }
}