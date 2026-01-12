package org.purpleBean.kmip.codec.xml;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.purpleBean.kmip.codec.xml.deserializer.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.codec.xml.serializer.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ServiceLoader;

public class KmipXmlModule extends SimpleModule {
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
                System.err.println("[KmipXmlModule] Failed to register serializer via ServiceLoader: " + ser.getClass().getName() + ": " + t.getMessage());
            }
        }
        for (KmipDataTypeXmlDeserializer<?> deser : ServiceLoader.load(KmipDataTypeXmlDeserializer.class)) {
            try {
                Class<?> target = deser.handledType();
                if (target != null) {
                    addDeserializer((Class) target, deser);
                } else {
                    System.err.println("[KmipXmlModule] Could not infer handled type for deserializer: " + deser.getClass().getName());
                }
            } catch (Throwable t) {
                System.err.println("[KmipXmlModule] Failed to register deserializer via ServiceLoader: " + deser.getClass().getName() + ": " + t.getMessage());
            }
        }
    }
}