package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Modulus;

import java.math.BigInteger;

public class ModulusXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Modulus, BigInteger> {

    public ModulusXmlSerializer() {
        super(Modulus::getValue);
    }
}