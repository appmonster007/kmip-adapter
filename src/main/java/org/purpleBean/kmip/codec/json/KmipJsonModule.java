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

public class KmipJsonModule extends SimpleModule {
    public KmipJsonModule() {
        super("KmipJsonModule", Version.unknownVersion());

        // Explicitly register JSON serializers/deserializers for auto-discovery via SPI.

        addSerializer(KmipTag.class, new KmipTagJsonSerializer());
        addDeserializer(KmipTag.class, new KmipTagJsonDeserializer());

        addSerializer(ProtocolVersion.class, new ProtocolVersionJsonSerializer());
        addDeserializer(ProtocolVersion.class, new ProtocolVersionJsonDeserializer());

        addSerializer(ProtocolVersion.ProtocolVersionMajor.class, new ProtocolVersionMajorJsonSerializer());
        addDeserializer(ProtocolVersion.ProtocolVersionMajor.class, new ProtocolVersionMajorJsonDeserializer());

        addSerializer(ProtocolVersion.ProtocolVersionMinor.class, new ProtocolVersionMinorJsonSerializer());
        addDeserializer(ProtocolVersion.ProtocolVersionMinor.class, new ProtocolVersionMinorJsonDeserializer());

        addSerializer(SimpleRequestMessage.class, new SimpleRequestMessageJsonSerializer());
        addDeserializer(SimpleRequestMessage.class, new SimpleRequestMessageJsonDeserializer());

        addDeserializer(RequestMessageStructure.class, new RequestMessageJsonDeserializer());

        addSerializer(SimpleRequestHeader.class, new SimpleRequestHeaderJsonSerializer());
        addDeserializer(SimpleRequestHeader.class, new SimpleRequestHeaderJsonDeserializer());

        addSerializer(SimpleRequestBatchItem.class, new SimpleRequestBatchItemJsonSerializer());
        addDeserializer(SimpleRequestBatchItem.class, new SimpleRequestBatchItemJsonDeserializer());

        addSerializer(State.class, new StateJsonSerializer());
        addDeserializer(State.class, new StateJsonDeserializer());

        addSerializer(ActivationDateAttribute.class, new ActivationDateAttributeJsonSerializer());
        addDeserializer(ActivationDateAttribute.class, new ActivationDateAttributeJsonDeserializer());

        addSerializer(SampleStructure.class, new SampleStructureJsonSerializer());
        addDeserializer(SampleStructure.class, new SampleStructureJsonDeserializer());
    }
}
