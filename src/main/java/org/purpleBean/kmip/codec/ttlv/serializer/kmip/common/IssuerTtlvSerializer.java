package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Issuer, String> {

    public IssuerTtlvSerializer() {
        super(Issuer::getValue);
    }
}