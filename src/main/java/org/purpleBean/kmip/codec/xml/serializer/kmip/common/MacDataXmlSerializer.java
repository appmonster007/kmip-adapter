package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.MacData;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class MacDataXmlSerializer extends AbstractKmipXmlSerializer<MacData, ByteBuffer> {

    public MacDataXmlSerializer() {
        super(MacData::getValue);
    }
}