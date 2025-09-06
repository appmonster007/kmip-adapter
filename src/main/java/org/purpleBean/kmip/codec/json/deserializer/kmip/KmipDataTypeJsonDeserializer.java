package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.databind.JsonDeserializer;
import org.purpleBean.kmip.KmipDataType;

public abstract class KmipDataTypeJsonDeserializer<T extends KmipDataType> extends JsonDeserializer<T> {
}
