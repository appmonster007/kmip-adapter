package org.purpleBean.kmip.codec.ttlv;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;

import java.util.ServiceLoader;

public class KmipTtlvModule extends TtlvModule {

    public KmipTtlvModule() {

        // Auto-register any TTLV serializers/deserializers exposed via Java ServiceLoader.
        // Supports both concrete providers extending KmipDataTypeTtlvSerializer/KmipDataTypeTtlvDeserializer
        // and providers implementing the plain TtlvSerializer/TtlvDeserializer interfaces.
        for (TtlvSerializer<?> ser : ServiceLoader.load(TtlvSerializer.class)) {
            try {
                Class<?> target = ser.handledType();
                if (target != null) {
                    addSerializer((Class) target, (TtlvSerializer) ser);
                } else {
                    System.err.println("[KmipTtlvModule] Could not infer handled type for serializer: " + ser.getClass().getName());
                }
            } catch (Throwable t) {
                System.err.println("[KmipTtlvModule] Failed to register TTLV serializer via ServiceLoader: " + ser.getClass().getName() + ": " + t.getMessage());
            }
        }
        for (TtlvDeserializer<?> deser : ServiceLoader.load(TtlvDeserializer.class)) {
            try {
                Class<?> target = deser.handledType();
                if (target != null) {
                    addDeserializer((Class) target, (TtlvDeserializer) deser);
                } else {
                    System.err.println("[KmipTtlvModule] Could not infer handled type for deserializer: " + deser.getClass().getName());
                }
            } catch (Throwable t) {
                System.err.println("[KmipTtlvModule] Failed to register TTLV deserializer via ServiceLoader: " + deser.getClass().getName() + ": " + t.getMessage());
            }
        }

        for (KmipDataTypeTtlvSerializer<?> ser : ServiceLoader.load(KmipDataTypeTtlvSerializer.class)) {
            try {
                Class<?> target = ser.handledType();
                if (target != null) {
                    addSerializer((Class) target, (TtlvSerializer) ser);
                } else {
                    System.err.println("[KmipTtlvModule] Could not infer handled type for serializer: " + ser.getClass().getName());
                }
            } catch (Throwable t) {
                System.err.println("[KmipTtlvModule] Failed to register TTLV serializer via ServiceLoader: " + ser.getClass().getName() + ": " + t.getMessage());
            }
        }
        for (KmipDataTypeTtlvDeserializer<?> deser : ServiceLoader.load(KmipDataTypeTtlvDeserializer.class)) {
            try {
                Class<?> target = deser.handledType();
                if (target != null) {
                    addDeserializer((Class) target, (TtlvDeserializer) deser);
                } else {
                    System.err.println("[KmipTtlvModule] Could not infer handled type for deserializer: " + deser.getClass().getName());
                }
            } catch (Throwable t) {
                System.err.println("[KmipTtlvModule] Failed to register TTLV deserializer via ServiceLoader: " + deser.getClass().getName() + ": " + t.getMessage());
            }
        }
    }
}
