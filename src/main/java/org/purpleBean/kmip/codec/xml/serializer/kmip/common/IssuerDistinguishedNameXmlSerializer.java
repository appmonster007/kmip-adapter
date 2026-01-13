package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameXmlSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}