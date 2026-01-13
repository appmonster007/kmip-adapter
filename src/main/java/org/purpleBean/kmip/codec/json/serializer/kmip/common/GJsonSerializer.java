package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.G;

import java.math.BigInteger;

public class GJsonSerializer extends AbstractKmipDataTypeJsonSerializer<G, BigInteger> {

    public GJsonSerializer() {
        super(G::getValue);
    }
}