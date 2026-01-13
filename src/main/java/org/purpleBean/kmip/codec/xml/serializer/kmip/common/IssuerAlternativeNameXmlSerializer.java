package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameXmlSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}