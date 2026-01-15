package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.RandomIv;

public class RandomIvXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RandomIv, Boolean> {

    public RandomIvXmlSerializer() {
        super(RandomIv::getValue);
    }
}