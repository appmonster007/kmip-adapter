package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameXmlSerializer extends AbstractKmipXmlSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameXmlSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}