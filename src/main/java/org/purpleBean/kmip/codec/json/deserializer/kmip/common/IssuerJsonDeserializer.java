package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Issuer, String> {

    public IssuerJsonDeserializer() {
        super(Issuer.kmipTag, Issuer.encodingType, String.class, value -> Issuer.builder().value(value).build());
    }
}