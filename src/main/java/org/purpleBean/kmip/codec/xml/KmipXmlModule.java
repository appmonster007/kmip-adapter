package org.purpleBean.kmip.codec.xml;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.RequestMessageStructure;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.common.ActivationDateAttributeXmlDeserializer;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration.StateXmlDeserializer;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.SampleStructureXmlDeserializer;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request.SimpleRequestBatchItemXmlDeserializer;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request.SimpleRequestHeaderXmlDeserializer;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request.SimpleRequestMessageXmlDeserializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.ProtocolVersionMajorXmlSerializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.ProtocolVersionMinorXmlSerializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.ProtocolVersionXmlSerializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.common.ActivationDateAttributeXmlSerializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration.StateXmlSerializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.SampleStructureXmlSerializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.request.SimpleRequestBatchItemXmlSerializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.request.SimpleRequestHeaderXmlSerializer;
import org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.request.SimpleRequestMessageXmlSerializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

public class KmipXmlModule extends SimpleModule {
    public KmipXmlModule() {
        super("KmipXmlModule", Version.unknownVersion());

        addSerializer(ProtocolVersion.class, new ProtocolVersionXmlSerializer());
        addDeserializer(ProtocolVersion.class, new ProtocolVersionXmlDeserializer());

        addSerializer(ProtocolVersion.ProtocolVersionMajor.class, new ProtocolVersionMajorXmlSerializer());
        addDeserializer(ProtocolVersion.ProtocolVersionMajor.class, new ProtocolVersionMajorXmlDeserializer());

        addSerializer(ProtocolVersion.ProtocolVersionMinor.class, new ProtocolVersionMinorXmlSerializer());
        addDeserializer(ProtocolVersion.ProtocolVersionMinor.class, new ProtocolVersionMinorXmlDeserializer());

        addSerializer(SimpleRequestMessage.class, new SimpleRequestMessageXmlSerializer());
        addDeserializer(SimpleRequestMessage.class, new SimpleRequestMessageXmlDeserializer());

        addDeserializer(RequestMessageStructure.class, new RequestMessageXmlDeserializer());

        addSerializer(SimpleRequestHeader.class, new SimpleRequestHeaderXmlSerializer());
        addDeserializer(SimpleRequestHeader.class, new SimpleRequestHeaderXmlDeserializer());

        addSerializer(SimpleRequestBatchItem.class, new SimpleRequestBatchItemXmlSerializer());
        addDeserializer(SimpleRequestBatchItem.class, new SimpleRequestBatchItemXmlDeserializer());

        addSerializer(State.class, new StateXmlSerializer());
        addDeserializer(State.class, new StateXmlDeserializer());

        addSerializer(ActivationDateAttribute.class, new ActivationDateAttributeXmlSerializer());
        addDeserializer(ActivationDateAttribute.class, new ActivationDateAttributeXmlDeserializer());

        addSerializer(SampleStructure.class, new SampleStructureXmlSerializer());
        addDeserializer(SampleStructure.class, new SampleStructureXmlDeserializer());


    }
}


