package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.MacData;

import java.nio.ByteBuffer;

public class MacDataXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MacData, ByteBuffer> {

    public MacDataXmlSerializer() {
        super(MacData::getValue);
    }
}