package org.purplebean.kmip.codec.xml;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ServiceLoader;
import org.purplebean.kmip.codec.xml.deserializer.BigIntegerXmlDeserializer;
import org.purplebean.kmip.codec.xml.deserializer.BooleanXmlDeserializer;
import org.purplebean.kmip.codec.xml.deserializer.ByteStringXmlDeserializer;
import org.purplebean.kmip.codec.xml.deserializer.IntegerXmlDeserializer;
import org.purplebean.kmip.codec.xml.deserializer.LongXmlDeserializer;
import org.purplebean.kmip.codec.xml.deserializer.OffsetDateTimeXmlDeserializer;
import org.purplebean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purplebean.kmip.codec.xml.serializer.BigIntegerXmlSerializer;
import org.purplebean.kmip.codec.xml.serializer.BooleanXmlSerializer;
import org.purplebean.kmip.codec.xml.serializer.ByteStringXmlSerializer;
import org.purplebean.kmip.codec.xml.serializer.IntegerXmlSerializer;
import org.purplebean.kmip.codec.xml.serializer.LongXmlSerializer;
import org.purplebean.kmip.codec.xml.serializer.OffsetDateTimeXmlSerializer;
import org.purplebean.kmip.codec.xml.serializer.api.KmipDataTypeXmlSerializer;

/**
 * Jackson module registering KMIP's XML (de)serializers.
 */
public class KmipXmlModule extends SimpleModule {
  /**
   * Registers the KMIP XML (de)serializers with this module.
   */
  public KmipXmlModule() {
    super("KmipXmlModule", Version.unknownVersion());

    addSerializer(ByteBuffer.class, new ByteStringXmlSerializer());
    addDeserializer(ByteBuffer.class, new ByteStringXmlDeserializer());

    addSerializer(BigInteger.class, new BigIntegerXmlSerializer());
    addDeserializer(BigInteger.class, new BigIntegerXmlDeserializer());

    addSerializer(Integer.class, new IntegerXmlSerializer());
    addDeserializer(Integer.class, new IntegerXmlDeserializer());

    addSerializer(Long.class, new LongXmlSerializer());
    addDeserializer(Long.class, new LongXmlDeserializer());

    addSerializer(OffsetDateTime.class, new OffsetDateTimeXmlSerializer());
    addDeserializer(OffsetDateTime.class, new OffsetDateTimeXmlDeserializer());

    addSerializer(Boolean.class, new BooleanXmlSerializer());
    addDeserializer(Boolean.class, new BooleanXmlDeserializer());

    for (KmipDataTypeXmlSerializer<?> ser : ServiceLoader.load(KmipDataTypeXmlSerializer.class)) {
      try {
        addSerializer(ser);
      } catch (Throwable t) {
        System.err.println("[KmipXmlModule] Failed to register serializer via ServiceLoader: " + ser
            .getClass()
            .getName() + ": " + t.getMessage());
      }
    }
    for (KmipDataTypeXmlDeserializer<?> deser : ServiceLoader.load(
        KmipDataTypeXmlDeserializer.class)) {
      try {
        Class<?> target = deser.handledType();
        if (target != null) {
          addDeserializer((Class) target, deser);
        } else {
          System.err.println("[KmipXmlModule] Could not infer handled type for deserializer: " +
              deser
                  .getClass()
                  .getName());
        }
      } catch (Throwable t) {
        System.err.println("[KmipXmlModule] Failed to register deserializer via ServiceLoader: " +
            deser
                .getClass()
                .getName() + ": " + t.getMessage());
      }
    }
  }
}