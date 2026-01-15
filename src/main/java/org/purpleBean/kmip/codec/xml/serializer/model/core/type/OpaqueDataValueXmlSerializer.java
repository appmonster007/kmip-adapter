package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueXmlSerializer() {
        super(OpaqueDataValue::getValue);
    }
}