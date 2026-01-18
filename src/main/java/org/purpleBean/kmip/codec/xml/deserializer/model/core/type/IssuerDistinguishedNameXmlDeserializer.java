package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameXmlDeserializer() {
        super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType, ByteBuffer.class, value -> IssuerDistinguishedName.builder().value(value).build());
    }
}