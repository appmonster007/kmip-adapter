package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DigestValue, ByteBuffer> {

    public DigestValueXmlSerializer() {
        super(DigestValue::getValue);
    }
}