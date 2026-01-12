package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueXmlSerializer extends AbstractKmipXmlSerializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueXmlSerializer() {
        super(OpaqueDataValue::getValue);
    }
}