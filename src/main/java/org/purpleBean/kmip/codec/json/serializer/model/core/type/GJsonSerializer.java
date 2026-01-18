package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.G;

import java.math.BigInteger;

public class GJsonSerializer extends AbstractKmipDataTypeJsonSerializer<G, BigInteger> {

    public GJsonSerializer() {
        super(G::getValue);
    }
}