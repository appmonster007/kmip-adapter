package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueXmlSerializer() {
        super(OpaqueDataValue::getValue);
    }
}