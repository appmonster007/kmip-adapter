package org.purpleBean.kmip.codec.xml;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;

import java.util.ServiceLoader;

public class KmipXmlModule extends SimpleModule {
    public KmipXmlModule() {
        super("KmipXmlModule", Version.unknownVersion());

        // Auto-register any XML serializers/deserializers exposed via Java ServiceLoader
        // Providers should extend KmipDataTypeXmlSerializer / KmipDataTypeXmlDeserializer.
        for (KmipDataTypeXmlSerializer<?> ser : ServiceLoader.load(KmipDataTypeXmlSerializer.class)) {
            try {
                addSerializer(ser);
            } catch (Throwable t) {
                System.err.println("[KmipXmlModule] Failed to register XML serializer via ServiceLoader: " + ser.getClass().getName() + ": " + t.getMessage());
            }
        }
        for (KmipDataTypeXmlDeserializer<?> deser : ServiceLoader.load(KmipDataTypeXmlDeserializer.class)) {
            try {
                Class<?> target = deser.handledType();
                if (target != null) {
                    addDeserializer((Class) target, deser);
                } else {
                    System.err.println("[KmipXmlModule] Could not infer handled type for XML deserializer: " + deser.getClass().getName());
                }
            } catch (Throwable t) {
                System.err.println("[KmipXmlModule] Failed to register XML deserializer via ServiceLoader: " + deser.getClass().getName() + ": " + t.getMessage());
            }
        }

    }
}
