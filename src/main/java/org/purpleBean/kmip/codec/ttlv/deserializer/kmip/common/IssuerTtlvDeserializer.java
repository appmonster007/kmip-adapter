package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerTtlvDeserializer extends AbstractKmipTtlvDeserializer<Issuer, String> {

    public IssuerTtlvDeserializer() {
        super(Issuer.kmipTag, Issuer.encodingType, String.class, value -> Issuer.builder().value(value).build());
    }
}