package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DJsonSerializer extends AbstractKmipJsonSerializer<D, BigInteger> {

    public DJsonSerializer() {
        super(D::getValue);
    }
}