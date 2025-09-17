package org.purpleBean.kmip.codec.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.RequestMessageStructure;
import org.purpleBean.kmip.codec.json.deserializer.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.common.ActivationDateAttributeJsonDeserializer;
import org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration.StateJsonDeserializer;
import org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure.SampleStructureJsonDeserializer;
import org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure.request.SimpleRequestBatchItemJsonDeserializer;
import org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure.request.SimpleRequestHeaderJsonDeserializer;
import org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure.request.SimpleRequestMessageJsonDeserializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipTagJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.ProtocolVersionJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.ProtocolVersionMajorJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.ProtocolVersionMinorJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.common.ActivationDateAttributeJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration.StateJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.common.structure.SampleStructureJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.common.structure.request.SimpleRequestBatchItemJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.common.structure.request.SimpleRequestHeaderJsonSerializer;
import org.purpleBean.kmip.codec.json.serializer.kmip.common.structure.request.SimpleRequestMessageJsonSerializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;
import java.lang.reflect.ParameterizedType;
import java.util.ServiceLoader;

public class KmipJsonModule extends SimpleModule {
    public KmipJsonModule() {
        super("KmipJsonModule", Version.unknownVersion());

        // Explicitly register JSON serializers/deserializers for auto-discovery via SPI.

        addSerializer(KmipTag.class, new KmipTagJsonSerializer());
        addDeserializer(KmipTag.class, new KmipTagJsonDeserializer());

        // Also auto-register any serializers/deserializers exposed via Java ServiceLoader
        // This enables external modules to contribute handlers without modifying this file.
        // Serializers: SimpleModule.addSerializer(JsonSerializer) uses handledType(), which we override.
        for (KmipDataTypeJsonSerializer<?> ser : ServiceLoader.load(KmipDataTypeJsonSerializer.class)) {
            try {
                addSerializer(ser);
            } catch (Throwable t) {
                // Best-effort registration; don't break module init for one faulty provider.
                System.err.println("[KmipJsonModule] Failed to register serializer via ServiceLoader: " + ser.getClass().getName() + ": " + t.getMessage());
            }
        }
        // Deserializers: need to infer the target class from the generic parameter.
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

