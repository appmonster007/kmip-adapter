package org.purpleBean.kmip.codec.ttlv;

import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.RequestMessageStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.*;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.ActivationDateAttributeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration.StateTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.SampleStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request.SimpleRequestBatchItemTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request.SimpleRequestHeaderTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request.SimpleRequestMessageTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvModule;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.*;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.ActivationDateAttributeTtlvSerializer;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration.StateTtlvSerializer;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.structure.SampleStructureTtlvSerializer;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.structure.request.SimpleRequestBatchItemTtlvSerializer;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.structure.request.SimpleRequestHeaderTtlvSerializer;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.structure.request.SimpleRequestMessageTtlvSerializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class KmipTtlvModule extends TtlvModule {

    public KmipTtlvModule() {
        addSerializer(Boolean.class, new BooleanTtlvSerializer());
        addDeserializer(Boolean.class, new BooleanTtlvDeserializer());

        addSerializer(Integer.class, new IntegerTtlvSerializer());
        addDeserializer(Integer.class, new IntegerTtlvDeserializer());

        addSerializer(Long.class, new LongTtlvSerializer());
        addDeserializer(Long.class, new LongTtlvDeserializer());

        addSerializer(ByteBuffer.class, new ByteStringTtlvSerializer());
        addDeserializer(ByteBuffer.class, new ByteStringTtlvDeserializer());

        addSerializer(String.class, new TextStringTtlvSerializer());
        addDeserializer(String.class, new TextStringTtlvDeserializer());

        addSerializer(OffsetDateTime.class, new OffsetDateTimeTtlvSerializer());
        addDeserializer(OffsetDateTime.class, new OffsetDateTimeTtlvDeserializer());

        addSerializer(ProtocolVersion.class, new ProtocolVersionTtlvSerializer());
        addDeserializer(ProtocolVersion.class, new ProtocolVersionTtlvDeserializer());

        addSerializer(ProtocolVersion.ProtocolVersionMajor.class, new ProtocolVersionMajorTtlvSerializer());
        addDeserializer(ProtocolVersion.ProtocolVersionMajor.class, new ProtocolVersionMajorTtlvDeserializer());

        addSerializer(ProtocolVersion.ProtocolVersionMinor.class, new ProtocolVersionMinorTtlvSerializer());
        addDeserializer(ProtocolVersion.ProtocolVersionMinor.class, new ProtocolVersionMinorTtlvDeserializer());

        addSerializer(SimpleRequestMessage.class, new SimpleRequestMessageTtlvSerializer());
        addDeserializer(SimpleRequestMessage.class, new SimpleRequestMessageTtlvDeserializer());

        addDeserializer(RequestMessageStructure.class, new RequestMessageTtlvDeserializer());

        addSerializer(SimpleRequestHeader.class, new SimpleRequestHeaderTtlvSerializer());
        addDeserializer(SimpleRequestHeader.class, new SimpleRequestHeaderTtlvDeserializer());

        addSerializer(SimpleRequestBatchItem.class, new SimpleRequestBatchItemTtlvSerializer());
        addDeserializer(SimpleRequestBatchItem.class, new SimpleRequestBatchItemTtlvDeserializer());

        addSerializer(State.class, new StateTtlvSerializer());
        addDeserializer(State.class, new StateTtlvDeserializer());

        addSerializer(ActivationDateAttribute.class, new ActivationDateAttributeTtlvSerializer());
        addDeserializer(ActivationDateAttribute.class, new ActivationDateAttributeTtlvDeserializer());

        addSerializer(SampleStructure.class, new SampleStructureTtlvSerializer());
        addDeserializer(SampleStructure.class, new SampleStructureTtlvDeserializer());
    }
}
