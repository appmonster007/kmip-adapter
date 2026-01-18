package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameXmlSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}