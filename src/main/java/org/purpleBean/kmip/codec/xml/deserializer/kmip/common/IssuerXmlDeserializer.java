package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Issuer, String> {

    public IssuerXmlDeserializer() {
        super(Issuer.kmipTag, Issuer.encodingType, String.class, value -> Issuer.builder().value(value).build());
    }
}