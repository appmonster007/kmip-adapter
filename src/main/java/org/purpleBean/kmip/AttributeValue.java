package org.purpleBean.kmip;

/**
 * KMIP AttributeValue dataType.
 */
public interface AttributeValue extends KmipDataType {
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_VALUE);
}
