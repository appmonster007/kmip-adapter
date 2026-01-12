package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameXmlSerializer extends AbstractKmipXmlSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameXmlSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}