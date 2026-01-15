package org.purpleBean.kmip.codec.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipTagJsonDeserializer;
import org.purpleBean.kmip.codec.json.serializer.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipTagJsonSerializer;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ServiceLoader;

public class KmipJsonModule extends SimpleModule {
    public KmipJsonModule() {
        super("KmipJsonModule", Version.unknownVersion());

        addSerializer(KmipTag.class, new KmipTagJsonSerializer());
        addDeserializer(KmipTag.class, new KmipTagJsonDeserializer());

        addSerializer(ByteBuffer.class, new ByteStringJsonSerializer());
        addDeserializer(ByteBuffer.class, new ByteStringJsonDeserializer());

        addSerializer(BigInteger.class, new BigIntegerJsonSerializer());
        addDeserializer(BigInteger.class, new BigIntegerJsonDeserializer());

        addSerializer(Integer.class, new IntegerJsonSerializer());
        addDeserializer(Integer.class, new IntegerJsonDeserializer());

        addSerializer(Long.class, new LongJsonSerializer());
        addDeserializer(Long.class, new LongJsonDeserializer());

        addSerializer(OffsetDateTime.class, new OffsetDateTimeJsonSerializer());
        addDeserializer(OffsetDateTime.class, new OffsetDateTimeJsonDeserializer());

        addSerializer(Boolean.class, new BooleanJsonSerializer());
        addDeserializer(Boolean.class, new BooleanJsonDeserializer());

        for (KmipDataTypeJsonSerializer<?> ser : ServiceLoader.load(KmipDataTypeJsonSerializer.class)) {
            try {
                addSerializer(ser);
            } catch (Throwable t) {
                System.err.println("[KmipJsonModule] Failed to register serializer via ServiceLoader: " + ser.getClass().getName() + ": " + t.getMessage());
            }
        }
        for (KmipDataTypeJsonDeserializer<?> deser : ServiceLoader.load(KmipDataTypeJsonDeserializer.class)) {
            try {
                Class<?> target = deser.handledType();
                if (target != null) {
                    addDeserializer((Class) target, deser);
                } else {
                    System.err.println("[KmipJsonModule] Could not infer handled type for deserializer: " + deser.getClass().getName());
                }
            } catch (Throwable t) {
                System.err.println("[KmipJsonModule] Failed to register deserializer via ServiceLoader: " + deser.getClass().getName() + ": " + t.getMessage());
            }
        }
    }
}