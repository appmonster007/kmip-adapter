package org.purpleBean.kmip.codec.json.serializer.kmip;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.purpleBean.kmip.KmipDataType;

public abstract class KmipDataTypeJsonSerializer<T extends KmipDataType> extends JsonSerializer<T> {
}
