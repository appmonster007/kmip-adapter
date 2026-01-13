package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MacData, ByteBuffer> {

    public MacDataXmlSerializer() {
        super(MacData::getValue);
    }
}