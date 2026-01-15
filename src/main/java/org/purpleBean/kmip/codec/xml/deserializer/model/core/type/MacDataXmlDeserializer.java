package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MacData;

import java.nio.ByteBuffer;

public class MacDataXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MacData, ByteBuffer> {

    public MacDataXmlDeserializer() {
        super(MacData.kmipTag, MacData.encodingType, ByteBuffer.class, value -> MacData.builder().value(value).build());
    }
}