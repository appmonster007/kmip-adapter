package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataXmlDeserializer extends AbstractKmipXmlDeserializer<MacData, ByteBuffer> {

    public MacDataXmlDeserializer() {
        super(MacData.kmipTag, MacData.encodingType, ByteBuffer.class, value -> MacData.builder().value(value).build());
    }
}