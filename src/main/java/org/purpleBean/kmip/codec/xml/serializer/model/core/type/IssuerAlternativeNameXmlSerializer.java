package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameXmlSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}