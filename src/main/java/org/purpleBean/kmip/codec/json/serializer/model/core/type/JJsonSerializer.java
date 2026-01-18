package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.J;

import java.math.BigInteger;

public class JJsonSerializer extends AbstractKmipDataTypeJsonSerializer<J, BigInteger> {

    public JJsonSerializer() {
        super(J::getValue);
    }
}